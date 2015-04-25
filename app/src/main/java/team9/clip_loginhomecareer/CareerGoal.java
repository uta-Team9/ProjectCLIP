package team9.clip_loginhomecareer;

import java.io.Serializable;

/**
 * Created by Edward on 4/22/2015.
 */
public class CareerGoal implements Serializable {
	//"ID", "Title", "Description", "EndDate", "TermLength", "HashID"
	private String title;
	private String date;
	private String description;
	private long rowID;
	private boolean isLongTerm;

	public CareerGoal(long rowID) {
		this.rowID = rowID;
		title = "";
		date = "";
		description = "";
		isLongTerm = false; //assume short term
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return this.title + " By " + date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isLongTerm() {
		return isLongTerm;
	}

	public void setLongTerm(boolean isLongTerm) {
		this.isLongTerm = isLongTerm;
	}

	public long getDatabaseID() {
		return rowID;
	}
}
