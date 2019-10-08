package logic.adapter.discount;

import logic.Order;

public class McHappyDay implements DiscountAdapter {	
	
	// Constants
	private static final double DISCOUNT = 0.1;
	private static final double THRESHOLD = 50.0;
	
	// Attributes
	Order order;
	
	/**
	 * Creates a McHappyDay business class extending
	 * the functionality of the Order class.
	 * 
	 * @param order
	 * 			The order to manage.
	 */
	public McHappyDay(Order order) {
		this.order = order;
	}

	@Override
	public float calculateTotalPrize() {
		if (needToApplyDiscount()) {
			return (float) (order.calcTotal() * (1 - DISCOUNT));
		} 
		
		return order.calcTotal();				
	}
	
	@Override
	public boolean needToApplyDiscount() {
		if (order.calcTotal() >= THRESHOLD) {
			return true;
		}
		
		return false;
	}

}
