package datamodel;

public class Customer {
	
	private final String id;
	private String firstName;
	private String lastName;
	private String contact;
	
	protected Customer(String id, String name, String contact) {
		this.id = id;
		setFirstName("");
		setLastName(name);
		this.contact = contact;
	}

	public String getFirstName() {
		if(firstName==null)
			return "";
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if(lastName==null)
			return "";
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getContact() {
		if(contact==null)
			return "";
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}

}
