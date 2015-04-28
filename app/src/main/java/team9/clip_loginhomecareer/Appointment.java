package team9.clip_loginhomecareer;

import java.io.Serializable;


public class Appointment implements Serializable
{
   private String doctorName;
   private int date;
   private int time;
   private String reason;
   private String officeAddress;
   private int phone;
   private int used = 0;


    public Appointment() {
        doctorName= "";
        date = 0;
        time = 0;
        reason = "";
        officeAddress = "";
        phone = 0;
        databaseID = 0;
        used = 0;
    }

    public Appointment(int dbID) {
        doctorName= "";
        date = 0;
        time = 0;
        reason = "";
        officeAddress = "";
        phone = 0;
        used = 0;
        databaseID = dbID;
    }
    public int getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(int databaseID) {
        this.databaseID = databaseID;
    }

    private int databaseID;


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void incrementUsed() {
        this.used++;
    }

    public void decrementUsed() {
        this.used--;
    }

    @Override
    public String toString()
    {
      return doctorName;
    }
}
