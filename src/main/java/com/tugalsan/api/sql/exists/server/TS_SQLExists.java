package com.tugalsan.api.sql.exists.server;

import module com.tugalsan.api.function;
import module com.tugalsan.api.sql.conn;
import module com.tugalsan.api.sql.where;

public class TS_SQLExists {

    public TS_SQLExists(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLExistsExecutor(anchor, tableName);
    }
    private final TS_SQLExistsExecutor executor;

    public boolean whereGroupAnd(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public boolean whereGroupOr(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public boolean whereConditionAnd(TGS_FuncMTU_In1<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public boolean whereConditionOr(TGS_FuncMTU_In1<TS_SQLWhereConditions> conditions) {
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
