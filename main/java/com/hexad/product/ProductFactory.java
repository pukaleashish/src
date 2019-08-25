package com.hexad.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * Factory class which provides the bundle to price mapping
 * @author apukale
 *
 */
public class ProductFactory {

	private static Map<String, Map<Integer, Double>> productMap = new HashMap<>();
	
	private static ProductFactory instance = new ProductFactory();
	private ProductFactory(){
		populateMap();
	}
	
	public static ProductFactory getInstance(){
		return instance;
	}
	
	public Map<String, Map<Integer, Double>> getProductMap(){
		return productMap;
	}
	private static void populateMap(){
		Map<Integer, Double> mb11PriceMap = new TreeMap<>(Collections.reverseOrder());
		mb11PriceMap.put(2, 9.95);
		mb11PriceMap.put(5, 16.95);
		mb11PriceMap.put(8, 24.95);

		Map<Integer, Double> vs5PriceMap = new TreeMap<>(Collections.reverseOrder());
		vs5PriceMap.put(3, 6.99);
		vs5PriceMap.put(5, 8.99);
		
		Map<Integer, Double> cfPriceMap = new TreeMap<>(Collections.reverseOrder());
		cfPriceMap.put(3, 5.95);
		cfPriceMap.put(5, 9.95);
		cfPriceMap.put(9, 16.95);
		productMap.put("VS5", vs5PriceMap);
		productMap.put("MB11", mb11PriceMap);
		productMap.put("CF", cfPriceMap);
	}
	
}
