package logic;

import java.util.*;

public class Order {
	
	private List<Product> orderList = null;
	
	public Order(){
		orderList = new ArrayList<Product>();
	}

	public void add(Product item, int units){
		Product itemInOrder = null;
	
		for (Product a : orderList){
			if (a.getCode().equals(item.getCode()))
				itemInOrder = a;
		}
		
		if (itemInOrder == null){
			Product itemToOrder = new Product(item);
			itemToOrder.setUnits(units);
			orderList.add(itemToOrder);
		}
		else{
			int totalUnits = itemInOrder.getUnits() + units;
			itemInOrder.setUnits(totalUnits);
		}
	}
	
	public float calcTotal(){
		float total = 0.0f;
		for (Product a : orderList){
			total += a.getPrice()* a.getUnits();
		}
		return total;
	}
	
	public void saveOrder(String fileName){
		FileUtil.saveToFile(fileName, orderList);
	  }

	public void initialize(){
		orderList.clear();
	}
}

