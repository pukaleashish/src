package com.hexad.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PackageCalculatorTest {
	
	private Map<String, Map<Integer, Double>> productMap = new HashMap<>();
	private PackageCalculator classUnderTest;
	@Before
	public void setup(){
		
		List<String> inputList = new ArrayList<>();
		classUnderTest = new PackageCalculator(inputList);
		
	}
	
	@Test
	public void processItem_ValidScenario_VS5(){
		classUnderTest.processProductItem("VS5", 15);
		Map<String, Map<Integer,Integer>> actualDetails = classUnderTest.getBundleDetails();
		int expectedNoOfBundles = 3;
		Assert.assertEquals(expectedNoOfBundles, actualDetails.get("VS5").get(5).intValue());
	}
	
	@Test
	public void processItem_InValidScenario_VS5(){
		classUnderTest.processProductItem("VS5", 1);
		Map<String, Map<Integer,Integer>> actualDetails = classUnderTest.getBundleDetails();
		Assert.assertEquals(null, actualDetails.get("VS5"));
	}
	
	@Test
	public void processItem_ValidScenario_MB11(){
		classUnderTest.processProductItem("MB11", 14);
		Map<String, Map<Integer,Integer>> actualDetails = classUnderTest.getBundleDetails();
		int expectedNoOfPackages = 2;
		System.out.println("PackageCalculatorTest.processItem_ValidScenario_MB11() "+ actualDetails.get("MB11").size());
		Assert.assertEquals(expectedNoOfPackages, actualDetails.get("MB11").size());
	}


}
