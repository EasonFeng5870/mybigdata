package com.bigdata.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * similar degress by cos
 * @author admin
 *
 */
public class SimilarDegreeByCos {
	
	public static double getSimilarDegree(String docOne, String docTwo){
		Map<String, int[]> vectorSpace = new HashMap<String, int[]>();
		int[] itemCounterArray = null;
		String strArray[] = docOne.split(" ");
		for (int i = 0; i < strArray.length; i++) {
			if(vectorSpace.containsKey(strArray[i])){
				++(vectorSpace.get(strArray[i])[0]);
			}else {
				itemCounterArray = new int[2];
				itemCounterArray[0] = 1;
				itemCounterArray[1] = 0;
				vectorSpace.put(strArray[i], itemCounterArray);
			}
		}
		
		strArray = docTwo.split(" ");
		for (int i = 0; i < strArray.length; i++) {
			if(vectorSpace.containsKey(strArray[i])){
				++(vectorSpace.get(strArray[i])[1]);
			}else {
				itemCounterArray = new int[2];
				itemCounterArray[0] = 0;
				itemCounterArray[1] = 1;
				vectorSpace.put(strArray[i], itemCounterArray);
			}
		}
		
		
		double vector1Module = 0.00;
		double vector2Module = 0.00;
		double vectorProduct = 0.00;
		
		Iterator iterator = vectorSpace.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			System.out.println("the key:   " + entry.getKey());
			System.out.println("the key:   " + ((int[])entry.getValue())[0] + "," + ((int[])entry.getValue())[1]);
			itemCounterArray = (int[]) entry.getValue();
			
			vector1Module += itemCounterArray[0] * itemCounterArray[0];
			vector2Module += itemCounterArray[1] * itemCounterArray[1];
			
			vectorProduct += itemCounterArray[0] * itemCounterArray[1];
			
			System.out.println(vector1Module + " " + vector2Module + " " + vectorProduct);
		}
		
		vector1Module = Math.sqrt(vector1Module);
		vector2Module = Math.sqrt(vector2Module);
		return (vectorProduct/(vector1Module*vector2Module));
	}

	public static void main(String[] args) {
		String str1 = "gold silver truck";
		String str2 = "Shipment of gold damaged in a fire";
		String str3 = "Delivery of silver arrived in a silver truck";
		String str4 = "Shipment of gold arrived in a truck";
		String str5 = "gold gold gold gold gold gold gold";
		
		System.out.println(SimilarDegreeByCos.getSimilarDegree(str1, str2));
		//System.out.println(SimilarDegreeByCos.getSimilarDegree(str1, str3));
		//System.out.println(SimilarDegreeByCos.getSimilarDegree(str1, str4));
		//System.out.println(SimilarDegreeByCos.getSimilarDegree(str1, str5));
	}
}
