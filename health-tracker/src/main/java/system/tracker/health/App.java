package system.tracker.health;

import system.tracker.health.controllers.*;

import system.tracker.health.models.User;
import system.tracker.health.utils.FileManager;



public class App {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        MenuManager menuManager = new MenuManager();
        CalorieIntakeManager calorieIntakeManager = new CalorieIntakeManager();
        ExerciseActivityManager exerciseActivityManager = new ExerciseActivityManager();
        SleepRecordManager sleepRecordManager = new SleepRecordManager();
        HealthAnalysisManager healthAnalysisManager = new HealthAnalysisManager();
        FileManager fileManager = new FileManager(userManagement);
        fileManager.loadData();

        while (true) {
            int choice = menuManager.showMainMenuAndGetChoice();
            User currentUser = userManagement.getCurrentUser();

            switch (choice) {
                case 1 -> userManagement.login();
                case 2 -> userManagement.register();
                case 3 -> {
                    calorieIntakeManager.setUser(currentUser);
                    calorieIntakeManager.showMenu();
                    fileManager.saveData();
                }
                case 4 -> {
                    exerciseActivityManager.setUser(currentUser);
                    exerciseActivityManager.showMenu();
                    fileManager.saveData();
                }
                case 5 -> {
                    sleepRecordManager.setUser(currentUser);
                    sleepRecordManager.showMenu();
                    fileManager.saveData();
                }
                case 6 -> {
                    healthAnalysisManager.setUser(currentUser);
                    healthAnalysisManager.showMenu();
                }
                case 7 -> fileManager.saveData();
                case 8 -> userManagement.logout();
                case 0 -> userManagement.exit();
                default -> {
                    if (choice != -1) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }
}
