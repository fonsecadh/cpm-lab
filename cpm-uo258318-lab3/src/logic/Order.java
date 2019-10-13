package logic;

import java.util.*;
import java.util.stream.Collectors;

public class Order {

	private List<Product> orderList = null;

	public Order() {
		orderList = new ArrayList<Product>();
	}

	public void add(Product item, int units) {
		Product itemInOrder = null;

		for (Product a : orderList) {
			if (a.getCode().equals(item.getCode()))
				itemInOrder = a;
		}

		if (itemInOrder == null) {
			Product itemToOrder = new Product(item);
			itemToOrder.setUnits(units);
			orderList.add(itemToOrder);
		} else {
			int totalUnits = itemInOrder.getUnits() + units;
			itemInOrder.setUnits(totalUnits);
		}
	}
	
	public void remove(Product item, int units) {			
		// Input validation
		List<Product> filteredList = orderList
			.parallelStream()
			.filter(p -> item.getCode().equals(item.getCode()))
			.collect(Collectors.toList());
		
		if (filteredList.size() == 0) {
			throw new IllegalArgumentException("There is no such product in the order.");
		}
		
		// The item is in the order
		Product toBeRemoved = filteredList.get(0);
		int currentUnits = toBeRemoved.getUnits();
		
		if (currentUnits == units) {
			toBeRemoved.setUnits(0);
			orderList.remove(item);
		} else {
			toBeRemoved.setUnits(currentUnits - units);
		}
	}

	public float calcTotal() {
		float total = 0.0f;
		for (Product a : orderList) {
			total += a.getPrice() * a.getUnits();
		}
		return total;
	}

	public void saveOrder(String fileName) {
		FileUtil.saveToFile(fileName, orderList);
	}
	
	/**
	 * Saves the order into a file with a given custom header.
	 * 
	 * @param fileName
	 * 				The name of the file.
	 * @param customHeader
	 * 				The information before showing the order contents.
	 */
	public void saveOrderWithCustomHeader(String fileName, String customHeader) {
		StringBuilder saveInfo = new StringBuilder();
		saveInfo.append(customHeader);
		saveInfo.append("\n" + orderList.toString());
		FileUtil.saveContentToFile(fileName, saveInfo.toString());
	}

	public void initialize() {
		orderList.clear();
	}
	
}
