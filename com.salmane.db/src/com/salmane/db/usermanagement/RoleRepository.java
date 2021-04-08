package com.salmane.db.usermanagement;

import com.salmane.db.Repository;
import com.salmane.usermanagement.model.Role;
import com.salmane.usermanagement.persistence.IRolePersistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.salmane.db.usermanagement.UserRepository.USERS_TABLE;

public class RoleRepository extends Repository implements IRolePersistence {
    public static final String USERS_ROLES_TABLE = "users_roles";
    public static final String USER_ID_COLUMN_USERS_ROLES = "user_id";
    public static final String ROLE_ID_COLUMN_USERS_ROLES = "role_id";

    public static final String ROLES_TABLE = "roles";
    public static final String ROLES_ID_COLUMN = "id";
    public static final String ROLES_NAME_COLUMN = "name";

    @Override
    public List<Role> get() {
        String query = FIND_ALL_QUERY_FUNCTION.apply(ROLES_TABLE);
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(this.resultSetToRole(resultSet));
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Role create(Role toCreate) {
        String insertQuery = INSERT_INTO_TABLE_QUERY_FUNCTION.apply(
                ROLES_TABLE,
                List.of(ROLES_ID_COLUMN, ROLES_NAME_COLUMN)
        );
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(insertQuery)) {
            statement.setString(1, toCreate.getId());
            statement.setString(2, toCreate.getName());

            statement.executeUpdate();

            return toCreate;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role get(String id) {
        String query = FIND_BY_COLUMN_QUERY_FUNCTION.apply(ROLES_TABLE, ROLES_ID_COLUMN);
        try(PreparedStatement statement = this.datasource.getConnection().prepareStatement(query)) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            Role role = null;
            while (resultSet.next()) {
                role = this.resultSetToRole(resultSet);
            }
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String id, Role toUpdate) {
        String updateQuery = UPDATE_TABLE_QUERY_FUNCTION.apply(
                Map.of("tableName", ROLES_TABLE, "columnToMatch", ROLES_ID_COLUMN),
                List.of(ROLES_NAME_COLUMN)
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
        String deleteQuery = DELETE_QUERY_FUNCTION.apply(ROLES_TABLE, ROLES_ID_COLUMN);
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
                .append("CREATE TABLE IF NOT EXISTS " + ROLES_TABLE + " (")
                .append("id VARCHAR(36) NOT NULL,")
                .append("name VARCHAR(100) NOT NULL,")
                .append("PRIMARY KEY (id)")
                .append(")");
        try (Statement statement = this.datasource.getConnection().createStatement()) {
            statement.executeUpdate(queryStringBuilder.toString());
            this.createUsersRolesTable();
        } catch (SQLException e) {
            System.out.println("Error initializing roles table");
            e.printStackTrace();
        }
    }

    private void createUsersRolesTable() throws SQLException {
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder
                .append("CREATE TABLE IF NOT EXISTS " + USERS_ROLES_TABLE + " (")
                .append("user_id VARCHAR(36) NOT NULL,")
                .append("role_id VARCHAR(36) NOT NULL,")
                .append("PRIMARY KEY (user_id, role_id),")
                .append("FOREIGN KEY (user_id) REFERENCES " + USERS_TABLE + "(id) ON UPDATE CASCADE,")
                .append("FOREIGN KEY (role_id) REFERENCES " + ROLES_TABLE + "(id) ON UPDATE CASCADE")
                .append(")");
        try (Statement statement = this.datasource.getConnection().createStatement()) {
            statement.executeUpdate(queryStringBuilder.toString());
        }
    }

    @Override
    public Role getByName(String name) {
        return null;
    }

    private Role resultSetToRole(ResultSet resultSet) throws SQLException {
        return new Role(
                resultSet.getString(ROLES_ID_COLUMN),
                resultSet.getString(ROLES_NAME_COLUMN)
        );
    }
}
