package team9.clip_loginhomecareer;

/**
 * Created by Mary on 4/18/2015.
 */
public class EduFinanceItem {
    String awardName;
    double amount;
    String period;
    String condition;

    @Override
    public String toString() {
        return "EduFinanceItem{" +
                "awardName='" + awardName + '\'' +
                ", amount=" + amount +
                ", period='" + period + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }

    public EduFinanceItem()
    {
        awardName = "";
        amount =0;
        period = "semester";
        condition = "";
    }

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
