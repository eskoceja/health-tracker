package system.tracker.health.models;

import java.time.LocalDate;

public class ExerciseActivity {
    private LocalDate date;
    private String exerciseType;
    private String category;
    private int duration;
    private int caloriesBurned;

    public ExerciseActivity(LocalDate date, String exerciseType, String category, int duration, int caloriesBurned) {
        this.date = date;
        this.exerciseType = exerciseType;
        this.category = category;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    @Override
    public String toString() {
        return "ExerciseActivity{" +
                "date=" + date +
                ", exerciseType='" + exerciseType + '\'' +
                ", category='" + category + '\'' +
                ", duration=" + duration +
                ", caloriesBurned=" + caloriesBurned +
                '}';
    }
}
