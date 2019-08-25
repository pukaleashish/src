package com.hexad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hexad.product.PackageCalculator;
import com.hexad.product.ProductFactory;

public class MainApp {

	public static void main(String[] args) {
		// For simplicity the input list is hardcoded here instead of taking it from user in CLI
		
		List<String> inputList = new ArrayList<>();
		inputList.add("14 MB11");
		inputList.add("10 VS5");
		inputList.add("13 CF");
//		inputList.add("3 MB11");
		PackageCalculator calculator = new PackageCalculator(inputList);
		calculator.calculatePackage();
		printPrettyPackageDetails(calculator.getBundleDetails());

	}

	private static void printPrettyPackageDetails(Map<String, Map<Integer, Integer>> bundleDetails) {
		Map<String, Map<Integer, Double>> productMap = ProductFactory.getInstance().getProductMap();;
		bundleDetails.forEach((key,value) -> {
			StringBuilder outputItem = new StringBuilder();
			Map<Integer,Double> bundlePriceMap = productMap.get(key);// Key is product code
			double totalPrice = 0;
			int totalQty = 0;
			for(Map.Entry<Integer,Integer> packageItem : value.entrySet()){ // Here key is package size and value is no.of bundles of that package
				
				totalPrice += packageItem.getValue() * bundlePriceMap.get(packageItem.getKey());
				totalQty += packageItem.getValue() * packageItem.getKey();
				outputItem.append(packageItem.getValue() +" X " + packageItem.getKey() + " - "+ bundlePriceMap.get(packageItem.getKey()) + "\n");
			}
			System.out.println("Product : "+key +" Total Qty : "+ totalQty + " Total Price : "+totalPrice );
			System.out.println(""+outputItem);
			System.out.println("---------------------------------");
			
			
		});
	}

}
