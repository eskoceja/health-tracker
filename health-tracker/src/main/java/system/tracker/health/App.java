package system.tracker.health;

import system.tracker.health.controllers.CalorieIntakeManager;
import system.tracker.health.controllers.ExerciseActivityManager;
import system.tracker.health.controllers.SleepRecordManager;
import system.tracker.health.controllers.UserManagement;
import system.tracker.health.models.CalorieIntake;
import system.tracker.health.models.ExerciseActivity;
import system.tracker.health.models.SleepRecord;
import system.tracker.health.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    private static DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private static DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManagement userManager = new UserManagement();
        CalorieIntakeManager calorieIntakeManager = new CalorieIntakeManager();
        ExerciseActivityManager exerciseActivityManager = new ExerciseActivityManager();
        SleepRecordManager sleepRecordManager = new SleepRecordManager();

        User currentUser = null;
        boolean loggedIn = false;
        boolean inApp = true;

        while (inApp) {

            if (loggedIn) {
                System.out.println("Welcome! What would you like to do today?\n" +
                        "1. View data\n" +
                        "2. Store data\n" +
                        "0. Log out\n");
            } else {
                System.out.println("\n----- Health Tracker Menu -----\n" +
                        "What would you like to do today?\n" +
                        "1. Login\n" +
                        "2. Create user\n" +
                        "0. Exit\n");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: //login

                    if (loggedIn) {

                        System.out.println("Name: "+currentUser.getUsername() + "\n" +
                                "Age: " + currentUser.getAge() + "\n");

                        //view all data by reading from file
                        // view all data by reading from file
                        List<CalorieIntake> calorieIntakes = calorieIntakeManager.getCalorieIntakes(currentUser);
                        List<ExerciseActivity> exerciseActivities = exerciseActivityManager.getExerciseActivities(currentUser);
                        List<SleepRecord> sleepRecords = sleepRecordManager.getSleepRecords(currentUser);

                        if (exerciseActivities.isEmpty()) {
                            System.out.println("No exercise activities found for the user.");
                        } else {
                            for (ExerciseActivity exerciseActivity : exerciseActivities) {
                                String dateStr = dateFormatter.format(exerciseActivity.getDate());
                                System.out.println("Date: " + dateStr);
                                System.out.println("Exercise Type: " + exerciseActivity.getExerciseType());
                                System.out.println("Duration: " + exerciseActivity.getDuration() + " minutes");
                                System.out.println("Calories Burned: " + exerciseActivity.getCaloriesBurned());
                                System.out.println("---------------------------------------");
                            }
                        }

                        if (calorieIntakes.isEmpty()) {
                            System.out.println("No calorie intakes found for the user.");
                        } else {
                            for (CalorieIntake calorieIntake : calorieIntakes) {
                                String dateStr = dateFormatter.format(calorieIntake.getDate());
                                System.out.println("Date: " + dateStr);
                                System.out.println("Food Item: " + calorieIntake.getFoodItem());
                                System.out.println("Calories In: " + calorieIntake.getCalories());
                                System.out.println("---------------------------------------");
                            }
                        }

                        if (sleepRecords.isEmpty()) {
                            System.out.println("No sleep records found for the user.");
                        } else {
                            for (SleepRecord sleepRecord : sleepRecords) {
                                String dateStr = dateFormatter.format(sleepRecord.getDate());
                                String bedTimeStr = timeFormatter.format(sleepRecord.getSleepTime());
                                String wakeUpTimeStr = timeFormatter.format(sleepRecord.getWakeUpTime());
                                System.out.println("Date: " + dateStr);
                                System.out.println("Bed Time: " + bedTimeStr);
                                System.out.println("Wake Up Time: " + wakeUpTimeStr);
                                System.out.println("---------------------------------------");
                            }
                        }
//
                    } else {
                        // Login
                        System.out.println("Enter your username: ");
                        String existingUsername = scanner.nextLine();
                        System.out.println("Enter your age: ");
                        int existingAge = scanner.nextInt();
                        scanner.nextLine();

                        loggedIn = userManager.login(existingUsername, existingAge);
                        if (loggedIn) {
                            currentUser = userManager.getUser(existingUsername); // Store the currently logged-in user
                            System.out.println("Login successful.");
                        } else {
                            System.out.println("Invalid username or age. Login failed.");
                        }
                    }
                    break;
                case 2: //create user
                    if (loggedIn) {
                        // store/view data options
                        System.out.println("What type of data would you like to store?\n" +
                                "1. Store calorie intake\n" +
                                "2. Store exercise activity\n" +
                                "3. Store sleep data\n" +
                                "0. Go back\n");

                        int storeChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (storeChoice) {
                            case 1: // Calorie Intake
                                System.out.println("Enter a food item: ");
                                String foodItem = scanner.nextLine();

                                System.out.println("Enter the calories in that food item: ");
                                int caloriesIn = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Enter date of meal consumed: (MM/dd/yyyy)");
                                String calDateString = scanner.nextLine();
                                Date calorieIntakeDate = parseDate(calDateString);

                                CalorieIntake calorieIntake = new CalorieIntake(calorieIntakeDate, foodItem, caloriesIn);
                                calorieIntakeManager.storeCalorieIntake(currentUser, calorieIntake);
                                break;
                            case 2: // Exercise Activity
                                System.out.println("Enter a type of workout you did: (Cardio, Strength training, Flexibility and stretching, OR Balance and stability)");
                                String workoutType = scanner.nextLine();

                                System.out.println("Enter workout time in minutes: ");
                                int duration = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Enter number of calories burned: ");
                                int caloriesBurned = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Enter date of workout: (MM/dd/yyyy)");
                                String exerciseDateString = scanner.nextLine();
                                Date exerciseDate = parseDate(exerciseDateString);

                                ExerciseActivity exerciseActivity = new ExerciseActivity(exerciseDate, workoutType, duration, caloriesBurned);
                                exerciseActivityManager.storeExerciseActivity(currentUser, exerciseActivity);
                                break;
                            case 3: // Sleep Record
                                System.out.println("Enter time of bed: (HH:mm)");
                                String bedTimeString = scanner.nextLine();
                                Date bedTime = parseTime(bedTimeString);

                                System.out.println("Enter wake up time: (HH:mm)");
                                String wakeUpTimeString = scanner.nextLine();
                                Date wakeUpTime = parseTime(wakeUpTimeString);

                                System.out.println("Enter date of sleep data: (MM/dd/yyyy)");
                                String sleepDateString = scanner.nextLine();
                                Date sleepDate = parseDate(sleepDateString);

                                SleepRecord sleepRecord = new SleepRecord(sleepDate, bedTime, wakeUpTime);
                                sleepRecordManager.storeSleepRecord(currentUser, sleepRecord);
                                break;
                            case 0: // Go back
                                //COME BACK HERE TO FIX THIS
                                break;
                            default:
                                System.out.println("Invalid entry, try again!");
                                break;
                        }

                        // Implement the code to add data here
                    } else {
                        // Create user
                        System.out.println("Enter a new username: ");
                        String username = scanner.nextLine();
                        System.out.println("Enter your age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        currentUser = userManager.createUser(username, age);
                        loggedIn = true;
                        System.out.println("User created and logged in successfully.");
                    }
                    break;
                case 0: //record calorie intake
                    if (loggedIn) {
                        loggedIn = false;
                        currentUser = null;
                        System.out.println("Logged out successfully.");
                    } else {
                        System.out.println("Goodbye!");
                        inApp = false;
                    }
                    break;

                default:
                    System.out.println("Invalid entry, try again!");
                    break;
            }

        }

    }

    public static Date parseTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}
