package ui;

import java.util.Scanner;

public class Menu {
    private final Scanner SCANNER = new Scanner(System.in);

    public void showMenu() {
        print("Welcome to LMS");
        int choice;
        do {
            print("What do you want to do?");

            print("1.\tManage users");
            print("2.\tGenerate reports");
            print("3.\tManage courses");
            print("4.\tManage messages and notifications");
            print("5.\tManage assessments\n");
            print("0.\tQuit");

            choice = SCANNER.nextInt();
            processChoice(choice);
        } while (choice != 0);
    }

    private void showUserManagementMenu() {
        print("Welcome to your user management portal");
        int choice;
        do {
            print("What do you want to do?");

            print("1.\tList users");
            print("2.\tCreate user");
            print("3.\tView user");
            print("4.\tUpdate user");
            print("5.\tDelete user\n");
            print("0.\tQuit");

            choice = SCANNER.nextInt();
            processUserManagementChoice(choice);
        } while (choice != 0);
    }

    private void processUserManagementChoice(int choice) {
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

    private void showReportsMenu() {
        print("showReportsMenu()");
    }

    private void showCourseManagementMenu() {
        print("showCourseManagementMenu()");
    }

    private void showMessagesManagementMenu() {
        print("showMessagesManagementMenu()");
    }

    private void showAssessmentManagementMenu() {
        print("showAssessmentManagementMenu()");
    }


    private void processChoice(int choice) {
        switch (choice) {
            case 0:
                print("Bye!");
                break;
            case 1:
                showUserManagementMenu();
                break;
            case 2:
                showReportsMenu();
                break;
            case 3:
                showCourseManagementMenu();
                break;
            case 4:
                showMessagesManagementMenu();
                break;
            case 5:
                showAssessmentManagementMenu();
                break;
            default:
                print("Invalid choice!");
        }
    }

    private void print(String text) {
        System.out.println(text);
    }
}
