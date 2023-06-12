package system.tracker.health.controllers;

import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static system.tracker.health.App.parseDate;

public class ExerciseActivityManager {
    private static final String EXERCISE_ACTIVITY_FILE = "src/main/java/system/tracker/health/utils/exercise_activity.txt";
    private DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

    //get data
    public List<ExerciseActivity> getExerciseActivities(User user) {
        List<ExerciseActivity> exerciseActivities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(EXERCISE_ACTIVITY_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {   //5?
                    Date date = parseDate(fields[0]);
                    String exerciseType = fields[1];
                    int duration = Integer.parseInt(fields[2]);
                    int caloriesBurned = Integer.parseInt(fields[3]);
                    ExerciseActivity exerciseActivity = new ExerciseActivity(date, exerciseType, duration, caloriesBurned);
                    exerciseActivities.add(exerciseActivity);


                    String dateStr = dateFormatter.format(exerciseActivity.getDate());
                    System.out.println("Date: " + dateStr);
                    System.out.println("Exercise Type: " + exerciseActivity.getExerciseType());
                    System.out.println("Duration: " + exerciseActivity.getDuration() + " minutes");
                    System.out.println("Calories Burned: " + exerciseActivity.getCaloriesBurned());
                    System.out.println("---------------------------------------");

                }
            }

        } catch (IOException e) {
            System.out.println("Error loading exercise activities from file: " + e.getMessage());
        }

        return exerciseActivities;
    }


    //store data
    public void storeExerciseActivity(User user, ExerciseActivity exerciseActivity) {
        try (FileWriter writer = new FileWriter(EXERCISE_ACTIVITY_FILE, true)) {
            String dateStr = dateFormatter.format(exerciseActivity.getDate());
//            String timeStr = timeFormatter.format(exerciseActivity.getTime());
            writer.write(user.getUsername() + "," + dateStr + "," + exerciseActivity.getExerciseType() + "," + exerciseActivity.getDuration() + "," + exerciseActivity.getCaloriesBurned() + "\n");
            System.out.println("Exercise activity stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing exercise activity: " + e.getMessage());
        }
    }
}
