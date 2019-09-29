package workshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import logic.Menu;
import logic.Order;
import logic.Product;

/**
 * Assignment for the first week of lab practice 03.
 * 
 * @author Hugo Fonseca Díaz (UO258318)
 *
 */
public class Workshop {

	// Attributes
	private Menu menu;
	private Scanner scanner = new Scanner(System.in);
	
	

	// Constructor
	
	/**
	 * Creates a terminal program that asks the user to fill an order and then it
	 * processes it.
	 */
	public Workshop() {
		loadMenu();
		processOrder();
	}		


	// Main
	public static void main(String[] args) {
		Workshop workshop = new Workshop();
	}
	

	// Methods
	
	/**
	 * Initializes the menu object that retrieves the products from a file.
	 */
	private void loadMenu() {
		menu = new Menu();
	}

	/**
	 * Asks the user for selecting a product from the list of products.
	 * 
	 * @return
	 * 			The selected product.
	 */
	private Product productSelection() {
		ArrayList<Product> products = new ArrayList<Product>(
				Arrays.asList(menu.getProducts()));
		
		System.out.println("\nList of items. Please select an item by introducing its code.");		
		products.forEach(p -> System.out.println(p.getCode() + " - " + p.toString()));
		
		String code = scanner.next();;
		boolean validCode = false;
		final AtomicReference<String> selectedCode = new AtomicReference<String>();
		
		// We keep iterating until the user inputs a valid code
		while (validCode != true) {			
			for (Product p : products) {
				if (p.getCode().toLowerCase().equals(code.toLowerCase())) {
					validCode = true;
					selectedCode.set(code);
				}
			}		
			
			if (validCode != true) {
				System.out.println("Invalid code. Please specify a valid code.");
				code = scanner.next();
			}
		}
		
		// We return the selected product
		return products
				.parallelStream()
				.filter(p -> p.getCode().toLowerCase().equals(selectedCode.get().toLowerCase()))
				.collect(Collectors.toList()).get(0);
		
	}
	
	/**
	 * Adds products with the selected units to the order until the user
	 * finishes it, and then prints the order's information.
	 */
	private void processOrder() {
		Order order = new Order();
		HashMap<Product, Integer> orderedProducts = new HashMap<Product, Integer>();
		
		boolean finishedOrdering = false;
		
		// We keep iterating until the user finishes ordering
		while (finishedOrdering != true) {
			Product product = productSelection();
			int orderedUnits = unitSelection(product);
			order.add(product, orderedUnits);
			
			orderedProducts.put(product, orderedUnits);
			
			System.out.println("\n\nWould you like to add more products to your order? y/n");
			String answer = scanner.next();
			
			while (!(answer.equals("y") || answer.equals("n"))) {
				answer = scanner.next();
				System.out.println("y/n");
			}
			
			if (answer.equals("n")) {
				finishedOrdering = true;
			}
		}
		
		// We print the order's information
		printOrder(order, orderedProducts);
		
	}

	/**
	 * Returns the selected product units inputed by the user.
	 * 
	 * @param product
	 * 			The product for which the user will select the ordered units.
	 * @return
	 * 			The ordered units by the user.
	 */
	private int unitSelection(Product product) {
		int availableUnits = product.getUnits();
		
		System.out.println("\n\nSelect the number of units you want to order (Available units: " + availableUnits + ")");
		int selectedUnits = scanner.nextInt();
		
		while (selectedUnits <= 0 /* && selectedUnits <= availableUnits */) {
			System.out.println("Invalid number. Please specify a valid number of units (Available units: " + availableUnits + ")");
			selectedUnits = scanner.nextInt();
		}
		
		return selectedUnits;
	}
	

	/**
	 * Prints information about the order.
	 * 
	 * @param order
	 * 			The order.
	 * @param orderedProducts
	 * 			A dictionary containing the ordered products with their correspondent units.
	 */
	private void printOrder(Order order, HashMap<Product, Integer> orderedProducts) {
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("\n\nThis is the information regarding your order:\n");
		sbInfo.append("Product Name - Type - Name - Prize - Units\n");
		
		// We append the elements of the dictionary
		orderedProducts.entrySet().forEach(p -> sbInfo.append(p.getKey() + " - " + p.getValue() + "\n"));
		
		sbInfo.append("\nTotal prize: " + order.calcTotal());
		
		System.out.println(sbInfo.toString());		
	}

}
