
SELECT DISTINCT(otype) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 -- 0 3 8 18
SELECT SUM(money) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 0
SELECT * FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 18

SELECT COUNT(*) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 0
SELECT SUM(money) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 3

SELECT * FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 8

SELECT * FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and trade_no = '21202311141805260836459110'

SELECT * FROM union_order_apppay_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and record_time BETWEEN 1699804800 AND 1699804800 and trade_no = '21202311141805260836459110'


SELECT DISTINCT(otype) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800
SELECT * FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and trade_no = '21202311141805260836459110'

SELECT trade_no,COUNT(*) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 8
GROUP BY trade_no ORDER BY COUNT(*) desc

SELECT SUM(money) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 8
SELECT COUNT(*) FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 8
SELECT * FROM park_account_tb_29_202311 WHERE union_id = 200069 and park_id = '187742' and ctime BETWEEN 1699804800 AND 1699804800 and otype = 8 and trade_no in ('21202311141700357666283347','21202311141644408256275524','21202311141645328968412296') ORDER BY trade_no

--总收入 564 手续费 3.23+0.05=3.28 实际3.27
--分账手续费 记账 0.05 重复0.01 实际 0.04
--分账支出 记账：181.2 重复12 实际：169.2 分账失败：4.8 164.4