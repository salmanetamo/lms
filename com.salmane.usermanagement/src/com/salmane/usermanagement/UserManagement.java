package com.salmane.usermanagement;

import com.salmane.usermanagement.model.User;
import com.salmane.usermanagement.persistence.IUserPersistence;
import com.salmane.usermanagement.service.IUserService;
import com.salmane.usermanagement.service.UserService;
import com.salmane.usermanagement.ui.UserManagementMenu;

import java.util.List;

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
        System.out.print("showCreateUser");
    }

    private void showViewUser() {
        System.out.print("showViewUser");
    }

    private void showUpdateUser() {
        System.out.print("showUpdateUser");
    }

    private void showDeleteUser() {
        System.out.print("showDeleteUser");
    }
}
