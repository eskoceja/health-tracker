package system.tracker.health.controllers;

import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.SleepRecord;
import system.tracker.health.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserManagement {
    private Map<String, User> users;
    private static Scanner scanner;
    private static final String USER_INTAKE_FILE = "src/main/java/system/tracker/health/utils/user_info.txt";
    private SleepRecordManager sleepRecordManager;
    private ExerciseActivityManager exerciseActivityManager;
    private CalorieIntakeManager calorieIntakeManager;

    public UserManagement() {
        users = new HashMap<>();
        loadUsersFromFile();
        sleepRecordManager = new SleepRecordManager();
        exerciseActivityManager = new ExerciseActivityManager();
        calorieIntakeManager = new CalorieIntakeManager();
    }

    //create user
    public User createUser(String username, int age) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return null;
        }
        User newUser = new User(username, age);
        users.put(username, newUser);
        System.out.println("User created successfully!");
        storeUsers(newUser); // Save the new user to the file
        return newUser;
    }

    //login
    public boolean login(String username, int age) {
        User user = users.get(username);
        if (user != null && user.getAge() == age) {
            System.out.println("Logged in as: " + username + " Age: " + age);
            return true;
        } else {
            System.out.println("User does not exist or invalid age.");
            return false;
        }
    }

    public void storeUsers(User user) {
        try (FileWriter writer = new FileWriter(USER_INTAKE_FILE, true)) {
            writer.write(user.getUsername() + "," + user.getAge() + "\n");
            writer.flush(); // Flush the writer
        } catch (IOException e) {
            System.out.println("Error storing user information: " + e.getMessage());
        }
    }

    //load users from file
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_INTAKE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userFields = line.split(",");
                if (userFields.length == 2) {
                    String username = userFields[0];
                    int age = Integer.parseInt(userFields[1]);
                    User user = new User(username, age);
                    users.put(username, user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void calculateCalories(User user) {
        List<CalorieIntake> calorieIntakes = calorieIntakeManager.getCalorieIntakes(user);
        List<ExerciseActivity> exerciseActivities = exerciseActivityManager.getExerciseActivities(user);

        int totalCaloriesConsumed = 0;
        int totalCaloriesBurned = 0;

        for (CalorieIntake calorieIntake : calorieIntakes) {
            totalCaloriesConsumed += calorieIntake.getCalories();
        }

        for (ExerciseActivity exerciseActivity : exerciseActivities) {
            totalCaloriesBurned += exerciseActivity.getCaloriesBurned();
        }

        int caloriesDifference = totalCaloriesConsumed - totalCaloriesBurned;
        System.out.println("Calories Consumed: " + totalCaloriesConsumed);
        System.out.println("Calories Burned: " + totalCaloriesBurned);
        System.out.println("Calories Difference: " + caloriesDifference);
    }













    //connect calories intake, exercise, sleep ------ maybe delete these:
    public void recordCalorieIntake(User user, CalorieIntake calorieIntake) {
        calorieIntakeManager.storeCalorieIntake(user, calorieIntake);
    }

    public void recordExerciseActivity(User user, ExerciseActivity exerciseActivity) {
        exerciseActivityManager.storeExerciseActivity(user, exerciseActivity);
    }

    public void recordSleepRecord(User user, SleepRecord sleepRecord) {
        sleepRecordManager.storeSleepRecord(user, sleepRecord);
    }

}
