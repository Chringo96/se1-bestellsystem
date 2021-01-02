package datamodel;

public class Article {
	
	private String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;
	
	protected Article(String id, String descr,long price,int units){
		this.id = id;
		description = descr;
		unitPrice = price;
		unitsInStore = units;
	}

	public String getDescription() {
	if(description == null || description == "")
		return "";
		return description;
	}

	public void setDescription(String descr) {
		if (descr == "" || descr == null)
			description = "";
		else description = descr;
	}

	public long getUnitPrice() {
		if(unitPrice<0)
			return 0;
		return unitPrice;
	}

	public void setUnitPrice(long price) {
		if(price<0 || price == Long.MAX_VALUE)
			unitPrice = 0;
			else unitPrice = price;
	}

	public int getUnitsInStore() {
		if(unitsInStore<0)
			return 0;
		return unitsInStore;
	}

	public void setUnitsInStore(int number) {
		if(number<0 || number == Integer.MAX_VALUE)
			unitsInStore = 0;
		else unitsInStore = number;
	}

	public String getId() {
		return id;
	}

}
