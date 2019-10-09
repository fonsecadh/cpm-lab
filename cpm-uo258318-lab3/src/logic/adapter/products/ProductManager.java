package logic.adapter.products;

import java.util.HashMap;
import java.util.stream.Collectors;

import logic.Order;
import logic.Product;

public class ProductManager implements OrderAdapter {
	
	// Attributes
	private Order order;
	private HashMap<Product, Integer> orderedProducts;
	
	/**
	 * Creates a ProductManager business class extending
	 * the functionality of the Order class.
	 * 
	 * @param order
	 * 			The order to manage.
	 */
	public ProductManager(Order order) {
		this.order = order;
		this.orderedProducts = new HashMap<Product, Integer>();
	}

	@Override
	public void addProduct(Product item, int units) {
		order.add(item, units);	
		
		Integer currentUnits = orderedProducts.get(item);
		
		if (currentUnits != null) { // If the product is already in the order
			// We update the units
			orderedProducts.replace(item, currentUnits, currentUnits + units);
		} else {
			// We add the new product to the dictionary
			orderedProducts.put(item, units);
		}
		
	}

	@Override
	public String getAllProductInformation() {
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("This is the information regarding your order:\n");
		sbInfo.append("Type - Name - Prize - Units\n");
		
		// We append the elements of the dictionary
		orderedProducts.entrySet().forEach(p -> sbInfo.append(p.getKey() + " - " + p.getValue() + "\n"));
		
		return sbInfo.toString();
	}

	@Override
	public void initialize() {
		order.initialize();
		orderedProducts.clear();
	}

	@Override
	public void deleteProduct(Product item, int units) {		
		int currentUnits = orderedProducts.get(item);
		
		if (currentUnits == units) {
			order.getOrderList().remove(item);
			orderedProducts.remove(item);
		} else {
			order
				.getOrderList()
				.stream()
				.filter(p -> p.getCode().equals(item.getCode()))
				.collect(Collectors.toList()).get(0).setUnits(currentUnits - units);;
			orderedProducts.replace(item, currentUnits, currentUnits - units);
		}
	}

	@Override
	public boolean canDeleteFromProduct(Product item, int units) {
		if (orderedProducts.get(item) == null) {
			return false;
		}
		
		if (orderedProducts.get(item) < units) {
			return false;
		}
		
		return true;
	}

}
