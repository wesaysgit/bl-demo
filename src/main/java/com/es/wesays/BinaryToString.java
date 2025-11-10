package com.es.wesays;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BinaryToString {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        long start = 1672545600;
        String sql = "INSERT INTO \"public\".\"transfer_account_tb\"(\"platform_id\", \"amount\", \"w_date\", \"create_time\", \"user_type\", \"state\", \"remark\", \"update_time\", \"order_id\", \"trade_no\", \"fee\", \"auto_type\", \"money\", \"pay_channel\") VALUES (314564, '2.1200', 'delay_time', ctime, 4, 0, 'fapiaohAOMahahahha123242', utime, '202207041617242294320056814821', '202207041617242294320056814821', '0.0', 0, NULL, NULL);";
        for (int i = 0; i < 100; i++) {
            String format = sdf.format(new Date((start - 24 * 3600) * 1000));
            String replace = sql.replace("delay_time", format).replace("ctime", String.valueOf(start)).replace("utime", String.valueOf(start));
            System.out.println(replace);
            start+=24*3600;
        }
    }

}

