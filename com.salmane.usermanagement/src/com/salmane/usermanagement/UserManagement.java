package com.salmane.usermanagement;

import com.salmane.usermanagement.model.User;
import com.salmane.usermanagement.persistence.IUserPersistence;
import com.salmane.usermanagement.service.IUserService;
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
                    default:
                        System.out.println("Invalid choice!");
                }
            }
    );
    private final IUserService userService;

    public UserManagement(IUserPersistence userPersistence) {
        this.userService = new UserService(userPersistence);
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
        if (this.userService.get(id).isPresent()) {
            this.userManagementMenu.print("User not found!");
        } else {
            boolean deleted = this.userService.delete(id);
            this.userManagementMenu.print(deleted ? "User deleted successfully" : "Could not delete user");
        }
    }
}
