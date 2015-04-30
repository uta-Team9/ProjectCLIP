package team9.clip_loginhomecareer;

import java.io.Serializable;

/**
 * Created by Mary on 4/18/2015.
 */
public class EduApp implements Serializable {
    private String college;
    private int deadline;
    private int reply_expected;
    private long dbRow;

    public EduApp(long id){
        college = "";
        deadline = 0;
        reply_expected = 0;
        dbRow = id;
    }

    public long getDbRow() {
        return dbRow;
    }

    @Override
    public String toString() {
        return  "college=" + college + "\n" +
                "deadline=" + deadline + "\n" +
                "reply_expected=" + reply_expected;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCollege() {
        return college;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getReply_expected() {
        return reply_expected;
    }

    public void setReply_expected(int reply_expected) {
        this.reply_expected = reply_expected;
    }
}
