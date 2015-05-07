package team9.clip_loginhomecareer;

import java.io.Serializable;

/**
 * Created by Mary on 4/18/2015.
 */
public class Degree implements Serializable{
    private String college;
    private int grad_date;
    private int start_date;
    private String location;
    private int display_place;
    private String study_field;
    private String degree_type;
    private long dbPosition;

    public Degree(long dbPosition) {
        college = "";
        grad_date = 0;
        start_date = 0;
        study_field = "";
        degree_type = "";
        location = "";
        display_place = 0;
        this.dbPosition = dbPosition;
    }

    public String toString() {
        String info = "";
        info = college + "\n" + location + "\n" + degree_type + " in "+study_field + "\n" + grad_date;

        return info;
    }

    public long getDatabaseID() {
        return dbPosition;
    }

    public String getStudy_field() {
        return study_field;
    }

    public void setStudy_field(String study_field) {
        this.study_field = study_field;
    }

    public void setStart_date(int start_date) {
        this.start_date = start_date;
    }

    public int getStart_date() {
        return start_date;
    }

    public void setDegree_type(String degree_type) {
        this.degree_type = degree_type;
    }

    public String getDegree_type() {
        return degree_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getGrad_date() {
        return grad_date;
    }

    public void setGrad_date(int grad_date) {
        this.grad_date = grad_date;
    }

}
