package system.tracker.health.models;

import java.time.LocalDate;

public class CalorieIntake {
    private LocalDate date;
    private String foodItem;
    private int calories;

    public CalorieIntake(LocalDate date, String foodItem, int calories) {
        this.date = date;
        this.foodItem = foodItem;
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "CalorieIntake{" +
                "date=" + date +
                ", foodItem='" + foodItem + '\'' +
                ", calories=" + calories +
                '}';
    }
}
