package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.*;

final class OutputProcessor implements system.Components.OutputProcessor{
	private final Components.InventoryManager inventoryManager;
	private final Components.OrderProcessor orderProcessor;
	private final int printLineWidth = 95;
	
	OutputProcessor(Components.InventoryManager inventoryManager, Components.OrderProcessor orderProcessor){
		this.inventoryManager = inventoryManager;
		this.orderProcessor = orderProcessor;
	}

	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {
		StringBuffer sbAllOrders = new StringBuffer("-------------");
		StringBuffer sbLineItem = new StringBuffer();
		String s = "";
		long price = 0;
		long totalPrice = 0;
		String fmtPriceTotal = "";
		long fullMwst = 0;
		String mwstPrint = "";

		for (Order order : orders) {
			Customer customer = order.getCustomer();
			String customerName = splitName( customer, customer.getFirstName() + " " + customer.getLastName() );
			
			for (OrderItem item : order.getItems()) {
				s += ", " + item.getUnitsOrdered() + "x " + item.getDescription();
				price += item.getArticle().getUnitPrice() * item.getUnitsOrdered();
				}

			sbLineItem = fmtLine("#" + order.getId() + ", " + customerName + "'s Bestellung: "
					+ s.replaceFirst(", ", ""), fmtPrice(price, "EUR", 14), printLineWidth);

			sbAllOrders.append("\n");
			sbAllOrders.append(sbLineItem);
			s = "";
			fmtPriceTotal = pad(fmtPrice(totalPrice += price, "", " EUR"), 14, true);
			
			if(printVAT==true)
				mwstPrint = pad(fmtPrice(fullMwst += orderProcessor.vat(price), "", " EUR"), 14, true);
			
			price = 0;
		}
		
		
		sbAllOrders.append("\n").append(fmtLine("-------------", " -------------", printLineWidth)).append("\n")
				.append(fmtLine("Gesamtwert aller Bestellungen:", fmtPriceTotal, printLineWidth));
		if(printVAT==true)
			sbAllOrders.append("\n").append(fmtLine("Im Gesamtbetrag enthaltene Mehrwertsteuer (19%):", mwstPrint, printLineWidth));
		System.out.println(sbAllOrders.toString());

	}

	@Override
	public void printInventory() {
		// TODO Auto-generated method stub

	}
	


	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int totalWidth) {
		StringBuffer sb = new StringBuffer(leftStr);
		int shiftable = 0; // leading spaces before first digit
		for (int i = 1; rightStr.charAt(i) == ' ' && i < rightStr.length(); i++) {
			shiftable++;
		}
		final int tab1 = totalWidth - rightStr.length() + 1; // - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max(0, excess - shiftable);
		if (shift2 > 0) {
			rightStr = rightStr.substring(shift2, rightStr.length());
			excess -= shift2;
		}
		if (excess > 0) {
			switch (excess) {
			case 1:
				sb.delete(sbLen - excess, sbLen);
				break;
			case 2:
				sb.delete(sbLen - excess - 2, sbLen);
				sb.append("..");
				break;
			default:
				sb.delete(sbLen - excess - 3, sbLen);
				sb.append("...");
				break;
			}
		}
		String strLineItem = String.format("%-" + (tab1 - 1) + "s%s", sb.toString(), rightStr);
		sb.setLength(0);
		sb.append(strLineItem);
		return sb;
	}

	@Override
	public String splitName(Customer customer, String name) {
		name= name.trim();
		String[] namen;
		if (name.contains(", ")) {
			namen = name.split(", ");
			customer.setFirstName( namen[1]);
				customer.setLastName(namen[0]);		
		}
		else {
		 namen = name.split(" ");
		if(namen.length>2)
			customer.setFirstName( namen[0] + " " + namen[1]);
		else
			customer.setFirstName( namen[0]);
		customer.setLastName(namen[namen.length-1]);
		}
		return singleName(customer);
	}

	@Override
	public String singleName(Customer customer) {
		return customer.getLastName() + ", " + customer.getFirstName();
	}
	
	
	private String fmtPrice(long price, String prefix, String postfix) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if (prefix != null) {
			fmtPriceSB.append(prefix);
		}

		fmtPriceSB = fmtPrice(fmtPriceSB, price);

		if (postfix != null) {
			fmtPriceSB.append(postfix);
		}
		return fmtPriceSB.toString();
	}
	
	private StringBuffer fmtPrice(StringBuffer sb, long price) {
		if (sb == null) {
			sb = new StringBuffer();
		}
		double dblPrice = ((double) price) / 100.0; // convert cent to Euro
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("de"))); // rounds double
																										// to 2 digits

		String fmtPrice = df.format(dblPrice); // convert result to String in DecimalFormat
		sb.append(fmtPrice);
		return sb;
	}
	
	private String pad(String str, int width, boolean rightAligned) {
		String fmtter = (rightAligned ? "%" : "%-") + width + "s";
		String padded = String.format(fmtter, str);
		return padded;
	}	

}
