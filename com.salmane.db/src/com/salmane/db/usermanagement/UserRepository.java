package com.salmane.db.usermanagement;

import com.salmane.db.Repository;
import com.salmane.usermanagement.model.User;
import com.salmane.usermanagement.persistence.IUserPersistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserRepository extends Repository implements IUserPersistence {
    public static final String USERS_TABLE = "users";
    public static final String USERS_ID_COLUMN = "id";
    public static final String USERS_EMAIL_COLUMN = "email";
    public static final String USERS_NAME_COLUMN = "name";

    @Override
    public User getByEmail(String email) {
        return getByColumnName(USERS_EMAIL_COLUMN, email);
    }

    private User resultSetToUser(ResultSet resultSet) throws SQLException {
        return resultSet == null ? null : new User(
                resultSet.getString(USERS_ID_COLUMN),
                resultSet.getString(USERS_EMAIL_COLUMN),
                resultSet.getString(USERS_NAME_COLUMN)
        );
    }

    @Override
    public List<User> get() {
        String query = FIND_ALL_QUERY_FUNCTION.apply(USERS_TABLE);
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(this.resultSetToUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public User create(User toCreate) {
        String insertQuery = INSERT_INTO_TABLE_QUERY_FUNCTION.apply(
                USERS_TABLE,
                List.of(USERS_ID_COLUMN, USERS_EMAIL_COLUMN, USERS_NAME_COLUMN)
        );
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(insertQuery)) {
            statement.setString(1, toCreate.getId());
            statement.setString(2, toCreate.getEmail());
            statement.setString(3, toCreate.getName());

            ResultSet resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = this.resultSetToUser(resultSet);
            }
            if (user == null)
                throw new SQLException("Error creating user!");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User get(String id) {
        return getByColumnName(USERS_ID_COLUMN, id);
    }

    private User getByColumnName(String column, String value) {
        String query = FIND_BY_COLUMN_QUERY_FUNCTION.apply(USERS_TABLE, column);
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(query)) {
            statement.setString(1, value);

            ResultSet resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = this.resultSetToUser(resultSet);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String id, User toUpdate) {
        String updateQuery = UPDATE_TABLE_QUERY_FUNCTION.apply(
                Map.of("tableName", USERS_TABLE, "columnToMatch", USERS_ID_COLUMN),
                List.of(USERS_ID_COLUMN, USERS_EMAIL_COLUMN, USERS_NAME_COLUMN)
        );
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(updateQuery)) {
            statement.setString(1, toUpdate.getName());
            statement.setString(2, id);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String deleteQuery = DELETE_QUERY_FUNCTION.apply(USERS_TABLE, USERS_ID_COLUMN);
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(deleteQuery)) {
            statement.setString(1, id);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void setup() {
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder
                .append("CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (")
                .append("id VARCHAR(36) NOT NULL,")
                .append("name VARCHAR(100) NOT NULL,")
                .append("email VARCHAR(75) NOT NULL,")
                .append("PRIMARY KEY (id)")
                .append(")");
        try (Statement statement = this.datasource.getConnection().createStatement()) {
            statement.executeUpdate(queryStringBuilder.toString());
        } catch (SQLException e) {
            System.out.println("Error initializing users table");
            e.printStackTrace();
        }
    }
}
