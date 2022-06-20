package com.tugalsan.api.sql.exists.server;

import com.tugalsan.api.pack.client.TGS_Pack1;
import com.tugalsan.api.sql.conn.server.TS_SQLConnAnchor;
import com.tugalsan.api.sql.select.server.TS_SQLSelectStmtUtils;
import com.tugalsan.api.sql.where.server.TS_SQLWhere;

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
        var sb = new StringBuilder("SELECT COUNT(*) ").append(" FROM ").append(tableName);
        if (where != null) {
            sb.append(" ").append(where);
        }
        return sb.toString();
    }

    public boolean execute() {
        TGS_Pack1<Long> pack = new TGS_Pack1(-1);
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
