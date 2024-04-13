package com.tugalsan.api.sql.exists.server;

import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import com.tugalsan.api.sql.conn.server.TS_SQLConnAnchor;
import com.tugalsan.api.sql.conn.server.TS_SQLConnColUtils;
import com.tugalsan.api.sql.where.server.TS_SQLWhereConditions;
import com.tugalsan.api.sql.where.server.TS_SQLWhereGroups;
import com.tugalsan.api.sql.where.server.TS_SQLWhereUtils;
import com.tugalsan.api.union.client.TGS_UnionExcuse;

public class TS_SQLExists {

    public TS_SQLExists(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLExistsExecutor(anchor, tableName);
    }
    private final TS_SQLExistsExecutor executor;

    public TGS_UnionExcuse<Boolean> whereGroupAnd(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public TGS_UnionExcuse<Boolean> whereGroupOr(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public TGS_UnionExcuse<Boolean> whereConditionAnd(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public TGS_UnionExcuse<Boolean> whereConditionOr(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public TGS_UnionExcuse<Boolean> whereFirstColumnAsId(long id) {
        var u_names = TS_SQLConnColUtils.names(executor.anchor, executor.tableName);
        if (u_names.isExcuse()){
            return u_names.toExcuse();
        }
        return whereConditionAnd(conditions -> {
            conditions.lngEq(u_names.value().get(0), id);
        });
    }
}
