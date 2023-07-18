package system.tracker.health.controllers;

import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.SleepRecord;
import system.tracker.health.models.User;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

public class UserManagement {
    private Map<String, User> users;
    private Scanner scanner;
    private User currentUser;

    public UserManagement() {
        this.users = new HashMap<>();
        this.scanner = new Scanner(System.in);
        User user1 = new User("Emily");
        User user2 = new User("Blake");

        user1.getCalorieIntakes().add(new CalorieIntake(LocalDate.of(2023, 6, 11), "tacos", 650));
        user1.getCalorieIntakes().add(new CalorieIntake(LocalDate.of(2023, 6, 11), "banana", 40));
        user1.getCalorieIntakes().add(new CalorieIntake(LocalDate.of(2023, 6, 11), "cereal", 210));

        user2.getCalorieIntakes().add(new CalorieIntake(LocalDate.of(2023, 6, 11), "nachos", 650));
        user2.getCalorieIntakes().add(new CalorieIntake(LocalDate.of(2023, 6, 11), "oatmeal", 175));
        user2.getCalorieIntakes().add(new CalorieIntake(LocalDate.of(2023, 6, 11), "pasta", 430));

        user1.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(2023, 6, 11), "running", "cardio", 45, 210));
        user1.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(2023, 6, 11), "paddle boarding", "cardio", 65, 345));
        user1.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(2023, 6, 11), "body pump", "light weights", 30, 150));

        user2.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(2023, 6, 11), "squats", "weight lifting", 45, 320));
        user2.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(2023, 6, 11), "running", "cardio", 30, 105));
        user2.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(2023, 6, 11), "swimming", "cardio", 60, 210));

        user1.getSleepRecords().add(new SleepRecord(LocalTime.of(22, 0), LocalTime.of(06, 20), LocalDate.of(2023, 6, 11)));
        user2.getSleepRecords().add(new SleepRecord(LocalTime.of(23, 50), LocalTime.of(10, 40), LocalDate.of(2023, 6, 11)));

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    public void addUser(String username, User user) {
        users.put(username, user);
    }

    private String promptUsername() {
        System.out.println("Enter username: ");
        return scanner.nextLine();
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public boolean verifyUser(String username) {
        return users.containsKey(username);
    }

    public void login() {
        String username = promptUsername();
        User user = getUserByUsername(username);

        if (username != null && verifyUser(username)) {
            currentUser = user;
            System.out.println("Logged in as: " + username);
        } else {
            System.out.println("Invalid username");
        }
    }

    public void register() {
        String username = promptUsername();

        if (!verifyUser(username)) {
            users.put(username, new User(username));
            System.out.println("Register successful, you may now log in");
        } else {
            System.out.println("Username already exists");
        }
    }
    public void logout() {
        System.out.println("Good-bye " + currentUser);
    }
    public void exit() {
        System.out.println("Good-bye!");
        System.exit(0);
    }
}
