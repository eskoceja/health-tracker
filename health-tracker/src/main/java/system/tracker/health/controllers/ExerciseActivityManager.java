package system.tracker.health.controllers;

import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.User;

import java.time.LocalDate;

import java.util.Scanner;


public class ExerciseActivityManager {
    private User user;
    private Scanner scanner;

    public ExerciseActivityManager() {
        this.scanner = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showEntries() {
        for (ExerciseActivity activity : user.getExerciseActivities()) {
            System.out.println(activity.toString());
        }
    }

    public void addEntry(ExerciseActivity activity) {
        user.getExerciseActivities().add(activity);
    }

    public String promptExercise() {
        System.out.println("Enter exercise: ");
        return scanner.nextLine();
    }

    public int promptDuration() {
        System.out.println("Enter how long you exercised in minutes: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int promptCaloriesBurned() {
        System.out.println("Enter amount of calories burned: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String promptCategory() {
        System.out.println("Enter exercise category: ");
        return scanner.nextLine();
    }

    public void enterExerciseEntry() {
        String exercise = promptExercise();
        int duration = promptDuration();
        int caloriesBurned = promptCaloriesBurned();
        String category = promptCategory();

        addEntry(new ExerciseActivity(LocalDate.now(), exercise, category, duration, caloriesBurned));
    }

    public void showMenu() {
        int choice = 0;

        do {
            System.out.println("\nExercise Activity Menu:\n" +
                    "1. Add new entry\n" +
                    "2. View Entries\n" +
                    "0. Exit\n" +
                    "\n" +
                    "Enter choice: \n");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> enterExerciseEntry();
                    case 2 -> showEntries();
                    case 0 -> System.out.println("Good-bye!");
                    default -> System.out.println("Enter valid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice: " + e.getMessage());
                showMenu();
            }
        } while (choice != 0);
    }
}
