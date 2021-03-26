package com.salmane.core.ui;

import java.util.List;
import java.util.Scanner;
import java.util.function.IntConsumer;

public class Menu {
    protected final Scanner scanner = new Scanner(System.in);
    protected final List<String> menuOptions;
    protected final String welcomeMessage;
    protected final String exitMessage;
    protected final String initialPrompt;
    protected IntConsumer processChoice;

    public Menu(List<String> menuOptions, String welcomeMessage, String exitMessage, String initialPrompt, IntConsumer processChoice) {
        this.menuOptions = menuOptions;
        this.welcomeMessage = welcomeMessage;
        this.exitMessage = exitMessage;
        this.initialPrompt = initialPrompt;
        this.processChoice = processChoice;
    }

    public Menu(IntConsumer processChoice) {
        this(
                List.of(
                        "Manage users",
                        "Generate reports",
                        "Manage courses",
                        "Manage messages and notifications",
                        "Manage assessments\n"
                ),
                "Welcome to LMS",
                "Quit",
                "What do you want to do?",
                processChoice
        );
    }

    public void showMenu() {
        print(this.welcomeMessage);
        int choice;
        do {
            print(this.initialPrompt);
            for (int i = 0; i < this.menuOptions.size(); i++) {
                print((i + 1) + ".\t" + this.menuOptions.get(i));
            }

            print("0.\t" + this.exitMessage);

            choice = scanner.nextInt();
            this.processChoice.accept(choice);
        } while (choice != 0);
    }

    protected void print(String text) {
        System.out.println(text);
    }
}
