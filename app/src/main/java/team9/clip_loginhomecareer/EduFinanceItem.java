package team9.clip_loginhomecareer;

import java.io.Serializable;

/**
 * Created by Mary on 4/18/2015.
 */
public class EduFinanceItem implements Serializable {
    String awardName;
    double amount;
    String period;
    String condition;
    private long dbPosition;

    public EduFinanceItem(long dbPosition) {
        awardName = "";
        amount = 0;
        period = "";
        condition = "";
        this.dbPosition = dbPosition;
    }

    @Override
    public String toString() {
        return  "awardName=" + awardName + "\n" +
                "amount=" + amount + "\n" +
                "period=" + period + "\n" +
                "condition=" + condition + "\n";
    }

    public EduFinanceItem()
    {
        awardName = "";
        amount =0;
        period = "semester";
        condition = "";
    }

    public long getDatabaseID() {return dbPosition;}

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
