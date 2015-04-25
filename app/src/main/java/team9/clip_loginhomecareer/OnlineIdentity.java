package team9.clip_loginhomecareer;

import java.io.Serializable;

/**
 * Created by Edward on 4/25/2015.
 */
public class OnlineIdentity implements Serializable {
	private String website;
	private String account;
	private String password;
	private long rowID;

	public OnlineIdentity(long rowID) {
		this.rowID = rowID;
		website = "";
		account = "";
		password = "";
	}

	public String toString() {
		return (website + " " + account);
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getDatabaseID() {
		return rowID;
	}
}
