package team9.clip_loginhomecareer;

/**
 * Created by Edward on 4/16/2015.
 */
public class Contact {
	private String name;
	private int met;
	private String description;
	private int used = 0;
	private String company;
	private int phone;
	private String email;

	public Contact() {
		name = "";
		phone = 0;
		met = 0;
		description = "";
		used = 0;
		company = "";
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
