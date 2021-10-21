package ru.i.sys.labs.timer;

import java.util.Date;

public class TimerInfo {
    private long timeNotice;
    private Date timeStart;
    private int repetitions;

    public TimerInfo(long timeNotice, Date timeStart, int repetitions) {
        this.timeNotice = timeNotice;
        this.timeStart = timeStart;
        this.repetitions = repetitions;
    }

    public long getTimeNotice() {
        return timeNotice;
    }

    public void setTimeNotice(long timeNotice) {
        this.timeNotice = timeNotice;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
