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
		return description;
	}

	public void setDescription(String descr) {
		description = descr;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long price) {
		unitPrice = price;
	}

	public int getUnitsInStore() {
		return unitsInStore;
	}

	public void setUnitsInStore(int number) {
		unitsInStore = number;
	}

	public String getId() {
		return id;
	}

}
