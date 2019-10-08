package logic.adapter.discount;

public interface DiscountAdapter {
	
	/**
	 * Calculates the total prize of the order 
	 * with its correspondent discount if it fulfills
	 * the conditions.
	 * 
	 * @return
	 * 			The total prize of the order taking into account the possible discounts.
	 */
	float calculateTotalPrize();
	
	/**
	 * Checks whether a discount is to be applied to the order
	 * or not.
	 * 
	 * @return
	 * 			True if a discount is to be applied to the order. False otherwise.
	 */
	boolean needToApplyDiscount();

}
