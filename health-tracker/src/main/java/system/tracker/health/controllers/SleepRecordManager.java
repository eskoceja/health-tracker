package system.tracker.health.controllers;

import system.tracker.health.App;
import system.tracker.health.models.SleepRecord;
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
import static system.tracker.health.App.parseTime;

public class SleepRecordManager {
    private static final String SLEEP_RECORD_FILE = "src/main/java/system/tracker/health/utils/sleep_record.txt";
    private DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

    //get data
    public List<SleepRecord> getSleepRecords(User user) {
        List<SleepRecord> sleepRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_RECORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4 && fields[0].equals(user.getUsername())) {
                    Date date = parseDate(fields[1]);
                    Date sleepTime = App.parseTime(fields[2]);
                    Date wakeUpTime = App.parseTime(fields[3]);
                    SleepRecord sleepRecord = new SleepRecord(date, sleepTime, wakeUpTime);
                    sleepRecords.add(sleepRecord);

                    String dateStg = dateFormatter.format(sleepRecord.getSleepTime());
                    System.out.println("Date: " + dateStg +"\n" +
                            "Bedtime type: " + sleepRecord.getSleepTime()+"\n" +
                            "Wake up time: " + sleepRecord.getWakeUpTime() + "\n");

                }
            }
        } catch (IOException e) {
            System.out.println("Error loading sleep records from file: " + e.getMessage());
        }

        return sleepRecords;
    }


    //store data
    public void storeSleepRecord(User user, SleepRecord sleepRecord) {
        try (FileWriter writer = new FileWriter(SLEEP_RECORD_FILE, true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String sleepTime = dateFormat.format(sleepRecord.getSleepTime());
            String wakeUpTime = dateFormat.format(sleepRecord.getWakeUpTime());
            writer.write(user.getUsername() + "," + sleepRecord.getDate() + "," + sleepRecord.getSleepTime() + "," + sleepRecord.getWakeUpTime() + "\n");
            writer.write(user.getUsername() + "," + sleepRecord.getDate() + "," + sleepTime + "," + wakeUpTime + "\n");
            System.out.println("Sleep record stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing sleep record: " + e.getMessage());
        }
    }
}
