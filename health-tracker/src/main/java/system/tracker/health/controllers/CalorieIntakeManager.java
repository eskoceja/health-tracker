package system.tracker.health.controllers;

import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static system.tracker.health.App.parseDate;

public class CalorieIntakeManager {
    private static final String CALORIE_INTAKE_FILE = "src/main/java/system/tracker/health/utils/calorie_intake.txt";
    private DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
    //get data

    public List<CalorieIntake> getCalorieIntakes(User user) {
        List<CalorieIntake> calorieIntakes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CALORIE_INTAKE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) { //4?
                    Date date = dateFormatter.parse(fields[0]);
                    String foodItem = fields[1];
                    int calories = Integer.parseInt(fields[2]);
//                    Date time = timeFormatter.parse(fields[3]);
                    CalorieIntake calorieIntake = new CalorieIntake(date, foodItem, calories);
                    calorieIntakes.add(calorieIntake);

                    String dateStr = dateFormatter.format(calorieIntake.getDate());
                    System.out.println("Date: " + dateStr + "\n" +
                            "Food item: " + calorieIntake.getFoodItem() + "\n" +
                            "Calories in food: " + calorieIntake.getCalories() + "\n");

                }
            }
        } catch (IOException e) {
            System.out.println("Error loading calorie intakes from file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return calorieIntakes;
    }

    //store data
    public void storeCalorieIntake(User user, CalorieIntake calorieIntake) {
        try (FileWriter writer = new FileWriter(CALORIE_INTAKE_FILE, true)) {
            String dateStr = dateFormatter.format(calorieIntake.getDate());
//            String timeStr = timeFormatter.format(calorieIntake.getTime());
            writer.write(user.getUsername() + "," + dateStr + "," + calorieIntake.getFoodItem() + "," + calorieIntake.getCalories() + "\n");
            System.out.println("Calorie intake stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing calorie intake: " + e.getMessage());
        }
    }
}
