package system.tracker.health.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class SleepRecord {
    private LocalTime startTime;
    private LocalTime endTime;
    private long sleepDuration;
    private LocalDate date;

    public SleepRecord(LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;

        Duration duration = Duration.between(startTime, endTime);

        if (startTime.isAfter(endTime)) {
            this.sleepDuration = duration.toHours() + 24;
        } else {
            this.sleepDuration = duration.toHours();
        }
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public long getSleepDuration() {
        return sleepDuration;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SleepRecord{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", sleepDuration=" + sleepDuration +
                ", date=" + date +
                '}';
    }
}
