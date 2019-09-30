package workshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import logic.FileUtil;
import logic.Menu;
import logic.Order;
import logic.Product;

/**
 * Human-Computer Interaction Laboratory Practices.
 * 
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
		@SuppressWarnings("unused")
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
		// We convert the array into an ArrayList in order to perform functional operations
		ArrayList<Product> products = new ArrayList<Product>(
				Arrays.asList(menu.getProducts()));
		
		System.out.println("\nList of items. Please select an item by introducing its code.");	
		System.out.println("Code - Type - Name - Prize - Units");
		
		// We print the product's information
		products.forEach(p -> System.out.println(p.getCode() + " - " + p.toString()));
		
		// We ask the user for input
		String code = scanner.next();
		
		// Control variable
		boolean validCode = false;
		
		// We need an atomic reference in order to 
		// be able to use this variable in the lambda function
		// that returns the selected product
		final AtomicReference<String> selectedCode = new AtomicReference<String>();
		
		// We keep iterating until the user inputs a valid code
		while (validCode != true) {			
			for (Product p : products) {
				if (p.getCode().toLowerCase().equals(code.toLowerCase())) {
					validCode = true;
					selectedCode.set(code);
					break;
				}
			}		
			
			if (validCode != true) {
				System.out.println("Invalid code. Please specify a valid code.");
				code = scanner.next();
			}
		}
		
		// We return the selected product.
		// The filter returns just one element, which is element zero, the selected product
		return products
				.parallelStream()
				.filter(p -> p.getCode().toLowerCase().equals(selectedCode.get().toLowerCase()))
				.collect(Collectors.toList()).get(0); 
		
	}
	
	/**
	 * Adds products with the selected units to the order until the user
	 * finishes it, then prints the order's information onto console and, 
	 * if given permission, saves the order information into a file.
	 */
	private void processOrder() {
		// We create the order
		Order order = new Order();
		
		// We create a dictionary containing the ordered products with their correspondent units.
		HashMap<Product, Integer> orderedProducts = new HashMap<Product, Integer>();
		
		// Control variable
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
				System.out.println("y/n?");
				answer = scanner.next();				
			}
			
			if (answer.equals("n")) {
				finishedOrdering = true;
			}
		}
		
		// We print the order's information
		printOrder(order, orderedProducts);
		
		// If the user wants, we save the order into a file
		saveToFile(order);
		
		// Exit message
		System.out.println("\n\nThank you for using our services!");
		
	}

	/**
	 * Asks the user for permission, and if granted, saves the specified order
	 * into a file.
	 * 
	 * @param order
	 * 			The specified order.
	 */
	private void saveToFile(Order order) {
		// We ask the user whether or not they want to save the
		// order information into a file
		System.out.println("\n\nWould you like to save your order into a file? y/n");
		String answer = scanner.next();
		
		while (!(answer.equals("y") || answer.equals("n"))) {
			System.out.println("y/n?");
			answer = scanner.next();				
		}
		
		// If the user wants to save the order
		if (answer.equals("y")) {
			String fileName = FileUtil.setFileName();
			order.saveOrder(fileName);
			System.out.println("Order successfuly saved to file: " + fileName);
		}	
		
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
		System.out.println("\n\nSelect the number of units you want to order");
		int selectedUnits = 0;
		boolean incorrectInputType = false; // Control variable for showing 
		
		while (selectedUnits <= 0) {
			try {
				selectedUnits = scanner.nextInt();
			} catch (InputMismatchException e) { // If the user does not input a integer value
				scanner.nextLine();
				incorrectInputType = true;
				System.out.println("Invalid input. Please specify a valid number of units");
			}
			
			// If the user entered an integer value lower or equal to zero
			if (selectedUnits <= 0 && !incorrectInputType) { 
				System.out.println("The value cannot be lower or equal to zero. Please specify a valid number of units");				
			}
			
			// We reset the control variable
			incorrectInputType = false;
		}
		
		return selectedUnits;
	}
	

	/**
	 * Prints onto console information about the order.
	 * 
	 * @param order
	 * 			The order.
	 * @param orderedProducts
	 * 			A dictionary containing the ordered products with their correspondent units.
	 */
	private void printOrder(Order order, HashMap<Product, Integer> orderedProducts) {
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("\n\nThis is the information regarding your order:\n");
		sbInfo.append("Type - Name - Prize - Units\n");
		
		// We append the elements of the dictionary
		orderedProducts.entrySet().forEach(p -> sbInfo.append(p.getKey() + " - " + p.getValue() + "\n"));
		
		// We append the total prize
		sbInfo.append("\nTotal prize: " + order.calcTotal());
		
		// We print the information
		System.out.println(sbInfo.toString());		
	}

}
