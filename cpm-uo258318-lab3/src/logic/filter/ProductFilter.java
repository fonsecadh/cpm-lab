package logic.filter;

import logic.Product;
import logic.ProductType;

public interface ProductFilter {	
	
	/**
	 * Returns a filtered collection of products 
	 * of a given type.
	 * 
	 * @param type
	 * 			The given type.
	 * @return
	 * 			An array of filtered products of a given type.
	 */
	Product[] filterProductByType(ProductType type);

}
