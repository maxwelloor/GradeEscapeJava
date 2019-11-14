package dev.java2dgame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
		
	}
	
	public static int parseInt(String num) {
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String arrayListToString(ArrayList<String> list) {
		String output = "";
		
		for (String letter : list) {
			output += letter;
		}
		
		return output;
	}
}


