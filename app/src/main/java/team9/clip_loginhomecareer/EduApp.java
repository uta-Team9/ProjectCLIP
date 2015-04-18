package team9.clip_loginhomecareer;

/**
 * Created by Mary on 4/18/2015.
 */
public class EduApp {
    private String college;
    private int deadline;
    private int reply_expected;

    public EduApp(){
        college = "";
        deadline = 0;
        reply_expected = 0;
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
