package com.javainterviewpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class ReadCSV_Scanner 
{
	/* DATA FOR gasPrices.csv
	 * backup in source folder
	 * 
	gas1	reg91	1.12
	gas2	e10_91	1.25
	gas3	prem95	1.41
	gas4	prem98	1.52
	gas5	regDiesel	1.59
	gas6	premDiesel	1.68
	*/
	
	//Delimiters used in the CSV file
	private static final String COMMA_DELIMITER = ",";
	
	public ArrayList<Double> readAndSavePrices()
	{
		// Variables
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<Double> doubleArray = new ArrayList<Double>();
		double d;
		 
		Scanner scanner = null;
		try {
			//Get the scanner instance
			scanner = new Scanner(new File(System.getProperty("user.home") + "\\Desktop\\gasPrices.csv"));
			//Use Delimiter as COMMA
			scanner.useDelimiter(COMMA_DELIMITER);
			
			
			while(scanner.hasNext())
			{
				System.out.println("Reading...");
				stringArray.add(scanner.next());
			}
			
			System.out.println(" ");
			System.out.println("List: ");
			for (int i = 2; i < stringArray.size(); i = i + 3) 
			{
					//System.out.println(i + "   " + stringArray.get(i));
					d = Double.parseDouble(stringArray.get(i));
					doubleArray.add(d);
					System.out.println(d);
			}	
			
		} 
		catch (FileNotFoundException fe) 
		{
			fe.printStackTrace();
		}
		finally
		{
			scanner.close();
		}
		return doubleArray;
	}
}