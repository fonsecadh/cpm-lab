package logic.adapter.products;

import logic.Product;

public interface OrderAdapter {
	
	/**
	 * Adds a product to the order.
	 * 
	 * @param item
	 * 			The product to be added.
	 * @param units
	 * 			The number of units to be ordered.
	 */
	void addProduct(Product item, int units);
	
	/**
	 * Returns a string with all the information about the 
	 * products in the order.
	 * 
	 * @return
	 * 			A string with information about the order's products.
	 */
	String getAllProductInformation();

	/**
	 * Returns to the initial state.
	 */
	void initialize();
	
	/**
	 * Deletes a given number of units from a product 
	 * in the order.
	 * 
	 * @param item
	 * 			The product whose units will be subtracted.
	 * @param units
	 * 			The number of units to subtract from the product.
	 */
	void deleteProduct(Product item, int units);

	/**
	 * Checks whether we can remove a given amount of units 
	 * from a product.
	 * 
	 * @param item
	 * 			The product to be checked.
	 * @param units
	 * 			The number of units to be removed.
	 * @return
	 * 			True if we can remove the specified number of units from the product. False otherwise.
	 */
	boolean canDeleteFromProduct(Product item, int units);

}
