package com.salmane.usermanagement.ui;

import com.salmane.core.ui.Menu;
import com.salmane.usermanagement.model.User;

import java.util.List;
import java.util.function.IntConsumer;

public class UserManagementMenu extends Menu {

    public UserManagementMenu(
            List<String> menuOptions,
            String welcomeMessage,
            String exitMessage,
            String initialPrompt,
            IntConsumer processChoice
    ) {
        super(menuOptions, welcomeMessage, exitMessage, initialPrompt, null);
        this.processChoice = processChoice;
    }

    public UserManagementMenu(IntConsumer processChoice) {
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
                "What do you want to do?",
                processChoice
        );
    }

    public void displayUsersList(List<User> users) {
        users.stream()
                .map(user -> "\t" + user.toString())
                .forEach(this::print);
    }
}
