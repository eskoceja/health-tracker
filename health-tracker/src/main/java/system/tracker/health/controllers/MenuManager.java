package system.tracker.health.controllers;

import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;
    public MenuManager() {
        this.scanner = new Scanner(System.in);
    }
    public void showMainMenu() {
        System.out.println("\n1. Login\n" +
                "2. Register\n" +
                "3. Get caloric intake\n" +
                "4. Exercise activities\n" +
                "5. Sleep records\n" +
                "6. Health data analysis\n" +
                "7. Save\n" +
                "8. Log out\n" +
                "0. Exit");
    }
    public int getChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 0 || choice > 8) {
                throw new NumberFormatException("Invalid choice");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
            return -1;
        }
        return choice;
    }
    public int showMainMenuAndGetChoice() {
        showMainMenu();
        int choice = getChoice();
        return choice;
    }
}
