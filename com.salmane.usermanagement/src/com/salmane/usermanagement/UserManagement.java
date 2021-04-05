package com.salmane.usermanagement;

import com.salmane.usermanagement.model.Role;
import com.salmane.usermanagement.model.User;
import com.salmane.usermanagement.persistence.IRolePersistence;
import com.salmane.usermanagement.persistence.IUserPersistence;
import com.salmane.usermanagement.service.IRoleService;
import com.salmane.usermanagement.service.IUserService;
import com.salmane.usermanagement.service.RoleService;
import com.salmane.usermanagement.service.UserService;
import com.salmane.usermanagement.ui.UserManagementMenu;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UserManagement {
    private final UserManagementMenu userManagementMenu = new UserManagementMenu(
            choice -> {
                switch (choice) {
                    case 0:
                        System.out.println("Back to main menu");
                        break;
                    case 1:
                        showListUsers();
                        break;
                    case 2:
                        showCreateUser();
                        break;
                    case 3:
                        showViewUser();
                        break;
                    case 4:
                        showUpdateUser();
                        break;
                    case 5:
                        showDeleteUser();
                        break;
                    case 6:
                        showListRoles();
                        break;
                    case 7:
                        showCreateRole();
                        break;
                    case 8:
                        showViewRole();
                        break;
                    case 9:
                        showUpdateRole();
                        break;
                    case 10:
                        showDeleteRole();
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
    );
    private final IUserService userService;
    private final IRoleService roleService;

    public UserManagement(IUserPersistence userPersistence, IRolePersistence rolePersistence) {
        this.userService = new UserService(userPersistence);
        this.roleService = new RoleService(rolePersistence);
    }

    public void connect() {
        this.userManagementMenu.showMenu();
    }

    private void showListUsers() {
        List<User> users = this.userService.get();
        this.userManagementMenu.displayUsersList(users);
    }

    private void showCreateUser() {
        String name = this.userManagementMenu.askName();
        String email = this.userManagementMenu.askEmail();
        this.userService.create(name, email);
        this.userManagementMenu.print("User created successfully!");
    }

    private void showViewUser() {
        String id = this.userManagementMenu.askId();
        this.userService.get(id).ifPresentOrElse(
                user -> this.userManagementMenu.print(user.toString()),
                () -> this.userManagementMenu.print("User not found!")
        );
    }

    private void showUpdateUser() {
        String id = this.userManagementMenu.askId();
        AtomicReference<String> newName = new AtomicReference<>("");
        AtomicReference<String> newEmail = new AtomicReference<>("");
        this.userService.get(id).ifPresentOrElse(
                user -> {
                    newName.set(this.userManagementMenu.askName());
                    newEmail.set(this.userManagementMenu.askEmail());
                },
                () -> this.userManagementMenu.print("User not found!")
        );
        boolean updated = this.userService.update(id, new User(newName.get(), newEmail.get()));
        this.userManagementMenu.print(updated ? "User updated successfully" : "Could not update user");
    }

    private void showDeleteUser() {
        String id = this.userManagementMenu.askId();
        if (this.userService.get(id).isEmpty()) {
            this.userManagementMenu.print("User not found!");
        } else {
            boolean deleted = this.userService.delete(id);
            this.userManagementMenu.print(deleted ? "User deleted successfully" : "Could not delete user");
        }
    }

    private void showListRoles() {
        List<Role> roles = this.roleService.get();
        this.userManagementMenu.displayRolesList(roles);
    }

    private void showCreateRole() {
        String name = this.userManagementMenu.askName();
        this.roleService.create(name);
        this.userManagementMenu.print("Role created successfully!");
    }

    private void showViewRole() {
        String id = this.userManagementMenu.askId();
        this.roleService.get(id).ifPresentOrElse(
                role -> this.userManagementMenu.print(role.toString()),
                () -> this.userManagementMenu.print("Role not found!")
        );
    }

    private void showUpdateRole() {
        String id = this.userManagementMenu.askId();
        AtomicReference<String> newName = new AtomicReference<>("");
        this.roleService.get(id).ifPresentOrElse(
                user -> newName.set(this.userManagementMenu.askName()),
                () -> this.userManagementMenu.print("User not found!")
        );
        boolean updated = this.roleService.update(id, new Role(newName.get()));
        this.userManagementMenu.print(updated ? "Role updated successfully" : "Could not update role");
    }

    private void showDeleteRole() {
        String id = this.userManagementMenu.askId();
        if (this.roleService.get(id).isEmpty()) {
            this.userManagementMenu.print("Role not found!");
        } else {
            boolean deleted = this.roleService.delete(id);
            this.userManagementMenu.print(deleted ? "Role deleted successfully" : "Could not delete role");
        }
    }

}
