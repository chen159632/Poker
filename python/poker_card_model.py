# -*- coding:utf-8 -*-

import pandas as pd
import numpy as np
import xgboost as xgb

path = '/Users/chenhong/'
na_values = ['', 'NULL', 'null', 'NA', 'na', 'NaN', 'nan', '\\N']


## load data
feature_import='xgb_model/feature_importance_.txt'
train_pre_file='xgb_model/train_pred.txt'
valid_pre_file='xgb_model/valid_pred.txt'
model = 'xgb_model/card.model'
feature_file='/Users/chenhong/card_play_feature_2.csv'
feat_map_file = '/Users/chenhong/card_feat_map.txt'

xy_df = pd.read_table(feature_file, sep=',', na_values=na_values)
xy_df = xy_df.fillna(-999999)

## split train test oot dataset

print ("finish reading training data")

# shuffle and split train_set to train and valid set
idx = np.array([i for i in range(xy_df.shape[0])])
np.random.shuffle(idx)
xy_df.index = idx
samp_rate = 0.7
n_rcd = xy_df.shape[0]
valid_set = xy_df[xy_df.index.values >  n_rcd * samp_rate]
train_set = xy_df[xy_df.index.values <= n_rcd * samp_rate]

## convert to DMatrix

print ("after shuffle the train and validate data")


cols_x = train_set.columns.values[1:]
print(cols_x)

train_data = train_set[cols_x].as_matrix()
train_label = train_set['label'].as_matrix()
dtrain = xgb.DMatrix(train_data, label = train_label)

valid_data = valid_set[cols_x].as_matrix()
valid_label = valid_set['label'].as_matrix()
dvalid = xgb.DMatrix(valid_data, label = valid_label)


## Train model

# parameter
param = {'max_depth': 3,
		 'learning_rate': 0.1,
		 'min_child_weight': 2,
		 'reg_alpha': 50,
		 'reg_lambda': 50,
		 'scale_pos_weight': 1,
		 'subsample': 0.9,
		 'colsample_bytree': 0.9,
		 'silent': True,
		 'objective': 'binary:logistic',
		 'nthread': 10,
		 'eval_metric': 'auc'}

evallist = [(dtrain, 'train'), (dvalid, 'valid')]

print ("try to train the modeling")

bst = xgb.train(param,
				dtrain,
				num_boost_round = 400,
				evals = evallist)

# save model
bst.save_model(path + model)

# load model
bst = xgb.Booster()
bst.load_model(path + model)

# dump model with feature map
feat_map_df = pd.DataFrame({'id': [i for i in range(len(cols_x))]})
feat_map_df = feat_map_df.assign(feat_name = cols_x)
feat_map_df = feat_map_df.assign(type = ['q' for i in range(len(cols_x))])
feat_map_df.to_csv(feat_map_file, sep = '\t', header = False, index = False)


## predict

cols = ['label']
train_pred_df = train_set[cols]
train_pred_df['score'] = bst.predict(dtrain)
train_pred_df.to_csv(path+ train_pre_file, sep='\t', index=False)

valid_pred_df = valid_set[cols]
valid_pred_df['score'] = bst.predict(dvalid)
valid_pred_df.to_csv(path+ valid_pre_file, sep='\t', index=False)



## 计算AUC、KS

import sklearn.metrics as mtrs

# compute auc and ks
def compute_auc_ks(y_true, y_pred):
	fpr, tpr, thresholds = mtrs.roc_curve(y_true, y_pred)
	auc = mtrs.auc(fpr, tpr)
	ks = (tpr - fpr).max()
	return auc, ks

train = train_pred_df
valid = valid_pred_df

auc_train, ks_train = compute_auc_ks(train.label, train.score)
auc_valid, ks_valid = compute_auc_ks(valid.label, valid.score)

print([round(auc_train,3), round(auc_valid,3), round(ks_train,3), round(ks_valid,3)])

