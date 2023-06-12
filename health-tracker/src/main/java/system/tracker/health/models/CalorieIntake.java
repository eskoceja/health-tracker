package system.tracker.health.models;

import java.util.Date;

public class CalorieIntake {
    private Date date;
    private String foodItem;
    private int calories;

    public CalorieIntake(Date date, String foodItem, int calories) {
        this.date = date;
        this.foodItem = foodItem;
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
