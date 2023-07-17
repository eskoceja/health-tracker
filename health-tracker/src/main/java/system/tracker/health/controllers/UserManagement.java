package system.tracker.health.controllers;

import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.User;


import java.time.LocalDate;
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

        user1.getExerciseActivities().add(new ExerciseActivity(LocalDate.of(LocalDate.of(2023, 6, 11),))

    }

}
