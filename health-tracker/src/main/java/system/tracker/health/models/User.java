package system.tracker.health.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    List<CalorieIntake> calorieIntakes;
    private List<ExerciseActivity> exerciseActivities;
    private List<SleepRecord> sleepRecords;

    public User(String username) {
        this.username = username;
        this.calorieIntakes = new ArrayList<>();
        this.exerciseActivities = new ArrayList<>();
        this.sleepRecords = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CalorieIntake> getCalorieIntakes() {
        return calorieIntakes;
    }

    public void setCalorieIntakes(List<CalorieIntake> calorieIntakes) {
        this.calorieIntakes = calorieIntakes;
    }

    public List<ExerciseActivity> getExerciseActivities() {
        return exerciseActivities;
    }

    public void setExerciseActivities(List<ExerciseActivity> exerciseActivities) {
        this.exerciseActivities = exerciseActivities;
    }

    public List<SleepRecord> getSleepRecords() {
        return sleepRecords;
    }

    public void setSleepRecords(List<SleepRecord> sleepRecords) {
        this.sleepRecords = sleepRecords;
    }

    public int totalCaloriesBurned() {
        int total = 0;
        for (ExerciseActivity activity : getExerciseActivities()) {
            total += activity.getCaloriesBurned();
        }
        return total;
    }

    public int totalCalories() {
        int total = 0;
        for (CalorieIntake intake : getCalorieIntakes()) {
            total += intake.getCalories();
        }
        return total;
    }

    public int totalHoursSlept() {
        int total = 0;
        for (SleepRecord record : getSleepRecords()) {
            total += record.getSleepDuration();
        }
        return total;
    }

}
