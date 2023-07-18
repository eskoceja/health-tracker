package system.tracker.health.utils;

import system.tracker.health.controllers.UserManagement;
import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.SleepRecord;
import system.tracker.health.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class FileManager {
    private UserManagement userManagement;
    public FileManager(UserManagement userManagement) {
        this.userManagement = userManagement;
    }
    public void saveData() {
        try {
            FileWriter writer = new FileWriter("health-tracker/src/main/java/system/tracker/health/utils/user-data.txt");
            for (Map.Entry<String, User> entry : userManagement.getUsers().entrySet()) {
                writer.write(entry.getValue().getUsername() + ", " +
                entry.getValue().getCalorieIntakes() + ", " +
                entry.getValue().getExerciseActivities() + ", " +
                        entry.getValue().getSleepRecords() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("health-tracker/src/main/java/system/tracker/health/utils/user-data.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\[");
                String username = parts[0];
                User user = userManagement.getUserByUsername(username);
                if (user == null) {
                    user = new User(username);
                    userManagement.getUsers().put(username, user);
                }

                String caloriesEntryStr = parts[1];
                String[] caloriesEntryArr = caloriesEntryStr.substring(1, caloriesEntryStr.length() - 1).split("\\},\\s*\\{");
                for (String entry : caloriesEntryArr) {
                    String[] splitEntry = entry.split(",\\{");
                    String [] result = splitEntry[0].split(",");
                    String foodName = result[0].replaceAll("\\{", "");
                    int calories = Integer.parseInt(result[1]);
                    LocalDate date = LocalDate.parse(result[2].replaceAll("}", ""));
                    CalorieIntake calorieIntake = new CalorieIntake(date, foodName, calories);

                    if (user.getCalorieIntakes().size() == 0) {
                        user.getCalorieIntakes().add(calorieIntake);
                    }
                }

                String exerciseEntriesStr = parts[2];
                String[] exerciseEntriesArr = exerciseEntriesStr.substring(1, exerciseEntriesStr.length() - 1).split("\\},\\s*\\{");
                for (String entry : exerciseEntriesArr) {
                    String[] splitEntry = entry.split(",\\{");
                    String [] result = splitEntry[0].split(",");
                    String exerciseName = result[0].replaceAll("\\{", "");;
                    int duration = Integer.parseInt(result[1]);
                    int caloriesBurned = Integer.parseInt(result[2]);
                    String category = result[3];
                    LocalDate date = LocalDate.parse(result[4].replaceAll("}", ""));
                    ExerciseActivity exerciseEntry = new ExerciseActivity(date, exerciseName, category, duration, caloriesBurned);

                    if (user.getExerciseActivities().size() == 0) {
                        user.getExerciseActivities().add(exerciseEntry);
                    }
                }

                String sleepEntriesStr = parts[3];
                String[] sleepEntriesArr = sleepEntriesStr.substring(1, sleepEntriesStr.length() - 1).split("\\},\\s*\\{");
                for (String entry : sleepEntriesArr) {
                    String[] splitEntry = entry.split(",\\{");
                    String [] result = splitEntry[0].split(",");
                    LocalTime startTime = LocalTime.parse(result[0].replaceAll("\\{", ""));
                    LocalTime endTime = LocalTime.parse(result[1]);
                    LocalDate date = LocalDate.parse(result[3].replaceAll("}", ""));
                    SleepRecord sleepEntry = new SleepRecord(startTime, endTime, date);
                    if (user.getSleepRecords().size() == 0) {
                        user.getSleepRecords().add(sleepEntry);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
