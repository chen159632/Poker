# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np
import sys


na_values = ['', 'NA', 'na', 'NULL', 'null', 'NaN', 'nan', '\\N']


def get_lift(data_file, n_groups, output_file, y_name='y', score_name='score'):
    '''
    data_file: 要带表头，\t分割
    '''

    # load data
    data = pd.read_table(data_file, sep='\t', na_values=na_values)

    # sort by score
    data_sorted = data.sort_values(by=score_name, axis=0, ascending=False)

    # group and stat
    n_rcds = data_sorted.shape[0]
    n_rcd_p_group = int(float(n_rcds) / n_groups)
    n_fraud_all = data_sorted[y_name].sum()  # 总欺诈数
    stat = []
    for i in range(n_groups - 1):
        y_this_g = pd.Series(np.array(data_sorted[y_name])[i * n_rcd_p_group: (i + 1) * n_rcd_p_group])
        score_this_g = pd.Series(np.array(data_sorted[score_name])[i * n_rcd_p_group: (i + 1) * n_rcd_p_group])

        id = i + 1
        # 分组欺诈数
        n_fraud_g = y_this_g.dropna().sum()
        # 分组欺诈率
        r_fraud_g = float(n_fraud_g) / n_rcd_p_group
        # 每组的最大分数
        max_score_g = score_this_g.dropna().max()
        # 每组的最小分数
        min_score_g = score_this_g.dropna().min()
        stat.append([id, max_score_g, min_score_g, n_rcd_p_group, n_fraud_g, r_fraud_g])

    # 最后一组
    y_this_g = pd.Series(np.array(data_sorted[y_name])[(n_groups - 1) * n_rcd_p_group:])
    score_this_g = pd.Series(np.array(data_sorted[score_name])[(n_groups - 1) * n_rcd_p_group:])
    id = n_groups
    n_fraud_g = y_this_g.dropna().sum()
    r_fraud_g = float(n_fraud_g) / y_this_g.shape[0]
    max_score_g = score_this_g.dropna().max()
    min_score_g = score_this_g.dropna().min()
    stat.append([id, max_score_g, min_score_g, y_this_g.shape[0], n_fraud_g, r_fraud_g])

    # 累计捕获率
    r_capture_acc = []
    acc = 0
    n = len(stat)
    for i in range(n):
        acc += float(stat[i][4]) / n_fraud_all
        r_capture_acc.append(acc)

    # output lift result
        header = ['分组', '最大分', '最小分', '次数', '胜利次数', '胜率']
    out_df = pd.DataFrame(data=stat, columns=header)
    out_df['累计捕获率'] = r_capture_acc
    print(out_df)

if __name__ == '__main__':
    data_file = "/Users/chenhong/xgb_model/valid_pred_with_sort_feature.txt"
    y_name = "label"
    score_name = "score"
    print(data_file)
    print(y_name)
    print(score_name)
    n_groups = 10
    output_file = '/Users/chenhong/t2.txt'
    get_lift(data_file, n_groups, output_file, y_name, score_name)
