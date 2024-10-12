package com.tecsharp.teachevaluationpro.utils;

import java.lang.reflect.Array;
import java.util.Random;

public class Utils {
	
	public static String convertArrayToString(String answer) {

        return answer.replace(",", "");
		
	}

	public static String randomStringSelectedFromArray(String[] array){
		Random random = new Random();
		int i = random.nextInt(array.length);
		return array[i];
	}

	public static String buildGreeting(String greeting, String text){

		return greeting + " " +  text + " ";
	}

}
