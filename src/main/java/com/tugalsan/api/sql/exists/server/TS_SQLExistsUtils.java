package com.tugalsan.api.sql.exists.server;

import module com.tugalsan.api.sql.conn;

public class TS_SQLExistsUtils {

    public static TS_SQLExists exists(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLExists(anchor, tableName);
    }
//
//    public static void test() { 
//        var exists = TS_SQLExistsUtils
//                .exists(null, "ya")
//                .whereConditionAnd(conditions -> conditions.lngEq("", 0));
//    }
}
