package com.hexad.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageCalculator {

	
	private Map<String, Map<Integer, Double>> productMap;
	private List<String> inputList = new ArrayList<>();

	private Map<String, Map<Integer,Integer>> priceMap = new HashMap<String, Map<Integer,Integer>>();
	public PackageCalculator(List<String> inputList) {
		this.inputList = inputList;
		productMap = ProductFactory.getInstance().getProductMap();
	}

	public void calculatePackage() {
		for (String item : inputList) {
			String[] lineItem = item.split(" ");
			if (lineItem.length > 0) {
				String productCode = lineItem[1].trim();
				int productQty = Integer.parseInt(lineItem[0]);
				processProductItem(productCode,productQty);

			}
		}
	}
	
	public Map<String, Map<Integer,Integer>> getBundleDetails(){
		return Collections.unmodifiableMap(priceMap);
	}
	
	public void processProductItem(String productCode, int productQty) {
		Map<Integer, Double> packageMap = productMap.get(productCode);
		if (packageMap != null && !packageMap.isEmpty()) {
			int firstQuotient = 0;
			int firstReminder = 0;
			boolean isSolutionFound = false;
			List<Integer> packageList = new ArrayList<Integer>(packageMap.keySet());
			for (int i = 0; i < packageList.size(); i++) {
				Map<Integer,Integer> bundleMap = new HashMap<Integer, Integer>();	
				 // This map holds prepack bundle number and
				if(!isSolutionFound){																	// No.of bundles of that bunch
				if(productQty >= packageList.get(i)){ //Check if the prod qty is greater than prepack bunch
					
					firstReminder = productQty %  packageList.get(i);
					firstQuotient = productQty /  packageList.get(i);
//					System.out.println("PackageCalculator2.processProductItem() firstReminder = "+firstReminder);
					if(firstReminder == 0){
						//solution found
						isSolutionFound = true;
						bundleMap.put(packageList.get(i), firstQuotient);
						priceMap.put(productCode, bundleMap);
						break;
					}else{
						//Iterate through next items
						
						int tempReminder = firstReminder;
						for(int j = i+1; j<packageList.size() ; j++){
							if(tempReminder >= packageList.get(j)){
								bundleMap.put(packageList.get(i), firstQuotient);
								int tempQuotient = tempReminder / packageList.get(j);
								tempReminder = tempReminder % packageList.get(j);
//								System.out.println("PackageCalculator2.processProductItem() tempReminder = "+tempReminder);
								if(tempReminder == 0){
									bundleMap.put(packageList.get(j), tempQuotient);
									//solution found
									isSolutionFound = true;
									priceMap.put(productCode, bundleMap);
									break;
									
								}else{
									
									tempReminder = firstReminder;
									continue;
								}
							}else{
								continue;
							}
						}
					}
					
					
				}else{
					continue;
				}
				}
			}
			if(!isSolutionFound){
				System.out.println("Skipping product "+productCode +" Reason : Minum package cannot be calculated for given quantity");
			}
			
		}
	}
}
