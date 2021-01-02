package datamodel;

public class OrderItem {
	
	private String description;
	private final Article article;
	private int unitsOrdered;
	
	protected OrderItem(String descr, Article article, int units) {
		description = descr;
		this.article = article;
		unitsOrdered = units;
	}

	public String getDescription() {
		if (description == null)
			return "";
		return description;
	}

	public void setDescription(String descr) {
		description = descr;
	}

	public int getUnitsOrdered() {
		if ( unitsOrdered <0)
			return 0;
		return unitsOrdered;
	}

	public void setUnitsOrdered(int number) {
		unitsOrdered = number;
	}

	public Article getArticle() {
		return article;
	}

}
