package com.tugalsan.api.sql.exists.server;


import com.tugalsan.api.callable.client.TGS_CallableType1Void;
import com.tugalsan.api.sql.conn.server.TS_SQLConnAnchor;
import com.tugalsan.api.sql.conn.server.TS_SQLConnColUtils;
import com.tugalsan.api.sql.where.server.TS_SQLWhereConditions;
import com.tugalsan.api.sql.where.server.TS_SQLWhereGroups;
import com.tugalsan.api.sql.where.server.TS_SQLWhereUtils;

public class TS_SQLExists { 

    public TS_SQLExists(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLExistsExecutor(anchor, tableName);
    }
    private final TS_SQLExistsExecutor executor;

    public boolean whereGroupAnd(TGS_CallableType1Void<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public boolean whereGroupOr(TGS_CallableType1Void<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public boolean whereConditionAnd(TGS_CallableType1Void<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public boolean whereConditionOr(TGS_CallableType1Void<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public boolean whereFirstColumnAsId(long id) {
        return whereConditionAnd(conditions -> {
            conditions.lngEq(
                    TS_SQLConnColUtils.names(executor.anchor, executor.tableName).get(0),
                    id
            );
        });
    }
}
