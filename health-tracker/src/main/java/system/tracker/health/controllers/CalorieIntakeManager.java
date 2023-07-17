package system.tracker.health.controllers;

import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.User;

import java.time.LocalDate;
import java.util.Scanner;

import static system.tracker.health.App.parseDate;

public class CalorieIntakeManager {
    private User user;
    private Scanner scanner;
    public CalorieIntakeManager() {
        this.scanner = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showEntries() {
        for (CalorieIntake intake : user.getCalorieIntakes()){
            System.out.println(intake.toString());
        }
    }

    public void addEntry(CalorieIntake intake) {
        user.getCalorieIntakes().add(intake);
    }

    public String promptFood() {
        System.out.println("Enter food item: ");
        return scanner.nextLine();
    }

    public int promptCalories() {
        System.out.println("Enter amount of calories: ");
        return Integer.parseInt(scanner.nextLine());
    }
    public void enterCaloriesIntake() {
        String food = promptFood();
        int calories = promptCalories();

        addEntry(new CalorieIntake(LocalDate.now(), food, calories));
    }

    public void showMenu() {
        int choice = 0;
        do {
            System.out.println("\nDaily Calorie Intake Menu\n" +
                    "1. Add Entry\n" +
                    "2. Show Entries\n" +
                    "0. Exit\n" +
                    "\n" +
                    "Enter choice: \n");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> enterCaloriesIntake();
                    case 2 -> showEntries();
                    case 0 -> System.out.println("Good-bye!");
                    default ->
                        System.out.println("Enter valid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice" + e.getMessage());
                showMenu();
            }
        } while (choice != 0);
    }
}
