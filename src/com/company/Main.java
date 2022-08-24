package com.company;

import static com.company.Constants.EXIT_MENU_INDEX;

public class Main {

    public static void main(String[] args) {
        int key;
        do {
            MenuController.showMenu();
            key = MenuController.findMenuItem();
            MenuController.doChosenMenuItem(key);
        } while (key != EXIT_MENU_INDEX);
        InfoController.showExitLabel();
    }
}
