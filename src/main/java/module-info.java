module com.tugalsan.api.sql.exists {
    requires java.sql;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.pack;
    requires com.tugalsan.api.runnable;
    requires com.tugalsan.api.sql.sanitize;
    requires com.tugalsan.api.sql.select;
    requires com.tugalsan.api.sql.where;
    requires com.tugalsan.api.sql.conn;
    requires com.tugalsan.api.sql.resultset;
    exports com.tugalsan.api.sql.exists.server;
}
