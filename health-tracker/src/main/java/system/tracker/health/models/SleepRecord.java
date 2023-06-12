package system.tracker.health.models;

import java.util.Date;

public class SleepRecord {
    private Date date;
    private Date sleepTime;
    private Date wakeUpTime;
//    private String duration;

    public SleepRecord(Date date, Date sleepTime, Date wakeUpTime) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeUpTime = wakeUpTime;
//        calculateDuration();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Date sleepTime) {
        this.sleepTime = sleepTime;
//        calculateDuration();
    }

    public Date getWakeUpTime() {
        return wakeUpTime;
    }

    public void setWakeUpTime(Date wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
//        calculateDuration();
    }

    public long getDuration() {
        return wakeUpTime.getTime() - sleepTime.getTime();
    }

//    private void calculateDuration() {
//        if (sleepTime != null && wakeUpTime != null) {
//            long durationInMillis = wakeUpTime.getTime() - sleepTime.getTime();
//            long hours = durationInMillis / (60 * 60 * 1000);
//            long minutes = (durationInMillis / (60 * 1000)) % 60;
//            duration = hours + " hours " + minutes + " minutes";
//        } else {
//            duration = null;
//        }
//    }
}
