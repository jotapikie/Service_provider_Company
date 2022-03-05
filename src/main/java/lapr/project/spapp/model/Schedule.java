package lapr.project.spapp.model;

import java.io.Serializable;

public class Schedule implements Serializable {

    /**
     * Execution Order Start Date.
     */
    private String date;

    /**
     * Execution Order Start Time.
     */
    private String time;

    /**
     * Creates an instance of Schedule.
     *
     * @param date execution order date.
     * @param time execution order time.
     */
    public Schedule(String date, String time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the textual representation of a Schedule.
     * @return textual representation of a Schedule.
     */
    public String toString() {
        return String.format("%s %s", this.date, this.time);
    }
}
