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

}
