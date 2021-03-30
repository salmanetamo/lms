package com.salmane.usermanagement.ui;

import com.salmane.core.ui.Menu;
import com.salmane.usermanagement.model.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        AtomicInteger index = new AtomicInteger(1);
        users.stream()
                .map(user -> index.getAndIncrement() + " -\t" + user.toString())
                .forEach(this::print);
    }

    public String askName() {
        return this.promptString(
                "Enter name below: ",
                name -> !name.isBlank()
        );
    }

    public String askEmail() {
        return this.promptString(
                "Enter email below: ",
                email -> !email.isBlank()
        );
    }

    public String askId() {
        return this.promptString(
                "Enter user id below: ",
                id -> !id.isBlank() && id.length() > 8
        );
    }
}
