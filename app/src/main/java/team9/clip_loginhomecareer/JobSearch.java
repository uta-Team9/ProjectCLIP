package team9.clip_loginhomecareer;

import java.io.Serializable;

/**
 * Created by Edward on 4/26/2015.
 */
public class JobSearch implements Serializable {
	//"ID", "Company", "Status", "Applied", "HashID"
	private String company;
	private String status;
	private String dateApplied;
	private long databaseID;

	public JobSearch(long databaseID) {
		this.databaseID = databaseID;
		company = "";
		status = "";
		dateApplied = "";
	}

	@Override
	public String toString() {
		return this.company;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

	public long getDatabaseID() {
		return databaseID;
	}
}
