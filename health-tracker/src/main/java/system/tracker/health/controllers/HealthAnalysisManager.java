package system.tracker.health.controllers;

import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.SleepRecord;
import system.tracker.health.models.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HealthAnalysisManager {
    private Scanner scanner;
    private User user;

    public HealthAnalysisManager() {
        this.scanner = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void caloricBalance(LocalDate day) {
        Map<LocalDate, Integer> intake = new HashMap<>();
        Map<LocalDate, Integer> burned = new HashMap<>();

        for (CalorieIntake entry : user.getCalorieIntakes()) {
            LocalDate date = entry.getDate();
            int calories = entry.getCalories();
            if (intake.containsKey(date)) {
                int existingValue = intake.get(date);
                calories += existingValue;
            }
            intake.put(date, calories);
        }

        for (ExerciseActivity activity : user.getExerciseActivities()) {
            LocalDate date = activity.getDate();
            int caloriesBurned = user.totalCaloriesBurned();
            if (burned.containsKey(date)) {
                int existingValue = intake.get(date);
                caloriesBurned += existingValue;
            }
            burned.put(date, caloriesBurned);
        }
        int caloric = intake.get(day) - burned.get(day);
        System.out.println("Caloric intake of date: " + caloric);
    }

    public void averageHoursOfSleep(LocalDate day) {
        LocalDate startDate = day.minusDays(7);
        long total = 0;

        for (SleepRecord record : user.getSleepRecords()) {
            if (record.getDate().isAfter(startDate) && !record.getDate().isAfter(day)) {
                total += record.getSleepDuration();
            }
        }
        System.out.println("Average hours of sleep this week: " + total);
    }
    public void promptDate() {
        System.out.println("Enter a date (yyyy-MM-dd): ");
        String input = scanner.nextLine();

        LocalDate date = LocalDate.parse(input);
        caloricBalance(date);
    }
    public void promptSleep() {
        System.out.println("Enter a date (yyyy-MM-dd): ");
        String input = scanner.nextLine();

        LocalDate date = LocalDate.parse(input);
        averageHoursOfSleep(date);
    }
    public void displayExercises() {
        for (ExerciseActivity activity : user.getExerciseActivities()) {
            System.out.println("Exercise: " + activity.getExerciseType() + "\n" +
                    "Category: " + activity.getCategory() + "\n" +
                    "Duration: " + activity.getDuration() + "\n" +
                    "Calories Burned: " + activity.getCaloriesBurned() + "\n" +
                    "Date: " + activity.getDate() + "\n");
        }
    }
    public void showMenu() {
        int choice = 0;

        do {
            System.out.println("\nHealth Analysis Menu: \n" +
                    "1. Get daily caloric value\n" +
                    "2. Get sleep analysis\n" +
                    "3. Get exercise log\n" +
                    "0. Exit\n" +
                    "\n" +
                    "Enter choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> promptDate();
                    case 2 -> promptSleep();
                    case 3 -> displayExercises();
                    case 0 -> System.out.println("Good-bye!");
                    default -> System.out.println("Invalid entry");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry: " + e.getMessage());
                showMenu();
            }
        } while (choice != 0);
    }

}
