package com.salmane.usermanagement;

import com.salmane.usermanagement.ui.UserManagementMenu;

public class UserManagement {
    private final UserManagementMenu userManagementMenu = new UserManagementMenu();

    public void connect() {
        this.userManagementMenu.showMenu();
    }
}
