package system.tracker.health.controllers;

import system.tracker.health.models.SleepRecord;
import system.tracker.health.models.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.Scanner;


public class SleepRecordManager {
    private User user;
    private Scanner scanner;

    public SleepRecordManager() {
        this.scanner = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addEntry(SleepRecord record) {
        user.getSleepRecords().add(record);
    }

    public void showEntries() {
        for (SleepRecord record : user.getSleepRecords()) {
            System.out.println(record.toString());
        }
    }
    public LocalTime promptStartTime() {
        LocalTime startTime= null;
        boolean validFormat = false;

         while (!validFormat) {
             System.out.println("Enter the time you went to sleep in military time (HH:mm) : ");
             String startTimeStr = scanner.nextLine();
             try {
                 startTime = LocalTime.parse(startTimeStr);
                 validFormat = true;
             } catch (DateTimeParseException e) {
                 System.out.println("Invalid format: " + e.getMessage());
             }
         }
         return startTime;
    }

    public LocalTime promptEndTime() {
        LocalTime startTime = null;
        boolean validFormat = false;

        while (!validFormat) {
            System.out.println("Enter the time you woke up in military time (HH:mm) : ");
            String startTimeStr = scanner.nextLine();
            try {
                startTime = LocalTime.parse(startTimeStr);
                validFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid entry: " + e.getMessage());
            }
        }
        return startTime;
    }

    public void enterSleepEntry() {
        LocalTime startTime = promptStartTime();
        LocalTime endTime = promptEndTime();

        SleepRecord sleepRecord = new SleepRecord(startTime, endTime, LocalDate.now());
        addEntry(sleepRecord);
    }

    public void showMenu() {
        int choice = 0;
        do {
            System.out.println("\nSleep Records Menu:\n" +
                    "1. Add new entry\n" +
                    "2. View entries\n" +
                    "0. Exit\n" +
                    "\n" +
                    "Enter choice: \n");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> enterSleepEntry();
                    case 2 -> showEntries();
                    case 0 -> System.out.println("Good-bye!");
                    default -> System.out.println("Enter valid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice: " + e.getMessage());
                showMenu();
            }
        } while (choice != 0);
    }
}
