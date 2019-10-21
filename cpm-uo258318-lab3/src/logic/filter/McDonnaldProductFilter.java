package logic.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import logic.Product;
import logic.ProductType;

public class McDonnaldProductFilter implements ProductFilter {
	
	// Attributes
	private Product[] products;
	
	
	// Constructor
	/**
	 * Creates a product filter for the McDonnald's application 
	 * given a list of products.
	 * 
	 * @param products
	 * 			A list of products.
	 */
	public McDonnaldProductFilter(Product[] products) {
		this.products = Arrays.copyOf(products, products.length);
	}

	
	// Methods
	@Override
	public Product[] filterProductByType(ProductType type) {
		// We convert the array of products to a List<Product>
		List<Product> products = Arrays.asList(this.products);
		
		// We filter and collect a List<Product> with the filtered products
		List<Product> filteredProducts = products
			.parallelStream()
			.filter(p -> p.getType().toLowerCase().equals(
					type.toString().toLowerCase()))
			.collect(Collectors.toList());
		
		// We make an auxiliary array with size of the filtered products list
		// so we can convert that list to an array in the next line of code
		Product[] aux = new Product[filteredProducts.size()];
		
		// We return the array with the filtered products
		return filteredProducts.toArray(aux);
	}

}
