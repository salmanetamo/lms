package com.salmane.db;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class Repository {
    protected final Datasource datasource;

    protected Repository() {
        ResourceBundle databaseConfig = ResourceBundle.getBundle("com.salmane.db.db");
        this.datasource = Datasource.getInstance(
                databaseConfig.getString("url"),
                databaseConfig.getString("database"),
                databaseConfig.getString("user"),
                databaseConfig.getString("password")
        );
        this.setup();
    }

    protected abstract void setup();

    protected static final BinaryOperator<String> FIND_BY_COLUMN_QUERY_FUNCTION =
            (tableName, columnName) -> "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";

    protected static final UnaryOperator<String> FIND_ALL_QUERY_FUNCTION =
            tableName -> "SELECT * FROM " + tableName;

    protected static final BinaryOperator<String> DELETE_QUERY_FUNCTION =
            (tableName, columnName) -> "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";

    protected static final BiFunction<String, List<String>, String> INSERT_INTO_TABLE_QUERY_FUNCTION =
            (tableName, columnNames) -> {
                StringBuilder stringBuilder = new StringBuilder("INSERT INTO " + tableName +" (");
                for (String column : columnNames) {
                    stringBuilder.append(column).append(", ");
                }
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                stringBuilder.append(") VALUES(");
                stringBuilder.append("?, ".repeat(columnNames.size()));
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                stringBuilder.append(");");
                return stringBuilder.toString();
            };

    protected static final BiFunction<Map<String,String>, List<String>, String> UPDATE_TABLE_QUERY_FUNCTION =
            (tableAndWhereClauseColumn, columnNames) -> {
                StringBuilder stringBuilder = new StringBuilder("UPDATE ");
                stringBuilder.append(tableAndWhereClauseColumn.get("tableName")).append(" SET ");
                for (String column : columnNames) {
                    stringBuilder.append(column).append("= ?, ");
                }
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                stringBuilder.append("WHERE ").append(tableAndWhereClauseColumn.get("columnToMatch")).append(" = ?");
                stringBuilder.append(";");
                return stringBuilder.toString();
            };
}
