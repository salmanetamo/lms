package com.salmane.usermanagement.ui;

import com.salmane.core.ui.Menu;

import java.util.List;

public class UserManagementMenu extends Menu {

    public UserManagementMenu(
            List<String> menuOptions,
            String welcomeMessage,
            String exitMessage,
            String initialPrompt
    ) {
        super(menuOptions, welcomeMessage, exitMessage, initialPrompt, null);
        this.processChoice = choice -> {
            switch (choice) {
                case 0:
                    print("Back to main menu");
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
                    print("Invalid choice!");
            }
        };
    }

    public UserManagementMenu() {
        this(
                List.of(
                        "List users",
                        "Create user",
                        "View user",
                        "Update user",
                        "Delete user\n"
                ),
                "Welcome to your user management portal",
                "Back",
                "What do you want to do?"
        );
        this.processChoice = choice -> {
            switch (choice) {
                case 0:
                    print("Back to main menu");
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
                    print("Invalid choice!");
            }
        };
    }

    private void showListUsers() {
        print("showListUsers");
    }

    private void showCreateUser() {
        print("showCreateUser");
    }

    private void showViewUser() {
        print("showViewUser");
    }

    private void showUpdateUser() {
        print("showUpdateUser");
    }

    private void showDeleteUser() {
        print("showDeleteUser");
    }

}
