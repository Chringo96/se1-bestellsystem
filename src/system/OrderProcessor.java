package system;

import java.util.function.Consumer;

import datamodel.Order;
import datamodel.OrderItem;

final class OrderProcessor implements system.Components.OrderProcessor {
	private final Components.InventoryManager inventoryManager;
	
	OrderProcessor(Components.InventoryManager inventoryManager){
		this.inventoryManager = inventoryManager;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode,
			Consumer<OrderItem> rejectedOrderItemCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) {
		return (long)(grossValue - (grossValue/1.19));
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		if(rateIndex==1) {
			return vat(grossValue);
		}
		if (rateIndex==2)
		return (long)(grossValue - (grossValue/1.07));
		System.out.println("rateIndex hat keinen gültigen Wert. Bitte '1' oder '2' eingeben.");
		return Long.MAX_VALUE;
	}

}
