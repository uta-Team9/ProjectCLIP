package team9.clip_loginhomecareer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Edward on 4/16/2015.
 */
public class Contact implements Serializable {
	private String name;
	private int met;
	private String description;
	private int used = 0;
	private String company;
	private long phone;
	private String email;
	private long dbPosition;

	public long getDbPosition() {
		return dbPosition;
	}

	public void setDbPosition(long dbPosition) {
		this.dbPosition = dbPosition;
	}

	public int getDatabaseID() {
		return databaseID;
	}

	public void setDatabaseID(int databaseID) {
		this.databaseID = databaseID;
	}

	private int databaseID;

	public Contact() {
		name = "";
		phone = 0;
		met = 0;
		description = "";
		used = 0;
		company = "";
		databaseID = 0;
	}

	public Contact(int dbID) {
		name = "";
		phone = 0;
		met = 0;
		description = "";
		used = 0;
		company = "";
		databaseID = dbID;
	}

	public String toString() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getMet() {
		return met;
	}

	public void setMet(int met) {
		this.met = met;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
