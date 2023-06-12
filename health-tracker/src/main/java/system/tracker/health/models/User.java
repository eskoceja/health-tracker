package system.tracker.health.models;

import java.util.List;

public class User {
    private String username;
    private int age;
    private List<CalorieIntake> foodList;
    private List<ExerciseActivity> exerciseList;
    private List<SleepRecord> sleepList;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
        this.foodList = foodList;
        this.exerciseList = exerciseList;
        this.sleepList = sleepList;
    }

    public List<CalorieIntake> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<CalorieIntake> foodList) {
        this.foodList = foodList;
    }

    public List<ExerciseActivity> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<ExerciseActivity> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<SleepRecord> getSleepList() {
        return sleepList;
    }

    public void setSleepList(List<SleepRecord> sleepList) {
        this.sleepList = sleepList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        String user = username;
        for(CalorieIntake food: foodList){
            user += ", " + food.getFoodItem();
        }
        for(ExerciseActivity exercise: exerciseList){
            user += ", " + exercise.getExerciseType();
        }
        for(SleepRecord sleep: sleepList){
            user += ", " + sleep.getDuration();
        }
        return user;
    }
}
