package com.tugalsan.api.sql.exists.server;

import module com.tugalsan.api.tuple;
import module com.tugalsan.api.sql.conn;
import module com.tugalsan.api.sql.select;
import module com.tugalsan.api.sql.where;

public class TS_SQLExistsExecutor {

    public TS_SQLExistsExecutor(TS_SQLConnAnchor anchor, CharSequence tableName) {
        this.anchor = anchor;
        this.tableName = tableName;
    }
    final public TS_SQLConnAnchor anchor;
    final public CharSequence tableName;

    public TS_SQLWhere where = null;

    @Override
    public String toString() {
        var sb = new StringBuilder(anchor.tagSelectAndSpace()).append("COUNT(*) ").append(" FROM ").append(tableName);
        if (where != null) {
            sb.append(" ").append(where);
        }
        return sb.toString();
    }

    public boolean run() {
        TGS_Tuple1<Long> pack = new TGS_Tuple1(-1);
        TS_SQLSelectStmtUtils.select(anchor, toString(), fillStmt -> {
            if (where != null) {
                where.fill(fillStmt, 0);
            }
        }, rs -> {
            if (rs.row.isEmpty()) {
                return;
            }
            pack.value0 = rs.lng.get(0, 0);
        });
        return pack.value0 == -1 ? null : pack.value0 != 0;
    }
}
