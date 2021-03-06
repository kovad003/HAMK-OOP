package com.javainterviewpoint;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter 
{	
	public void writeReceipt(String formattedPriceStr, String fuelTypeStr, String fuelAmountStr) throws IOException
	{
		//Convert from LocalDate to String
		String datePattern1 = "yyyyMMddMMHHmmss";
		String datePattern2 = "HH:mm:ss dd-MM-yyyy";
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern(datePattern1);
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern(datePattern2);
        LocalDateTime now = LocalDateTime.now();
        String strNow1 = "WTU008-" + df1.format(now);
        String strNow2 = df2.format(now);
        
        //text file location
        String filePath = System.getProperty("user.home") + "\\Desktop\\" + strNow1 +".txt";
        
        //create text file
        File file = new File(filePath);
        
        //write text file
        FileWriter myWriter = new FileWriter(filePath);
        myWriter.write("\n");
        myWriter.write("******************* RECEIPT *******************");
        myWriter.write("\n");
        myWriter.write("Transaction ID: " + strNow1);    
        myWriter.write("\n");
        myWriter.write("\n");
        myWriter.write("Date and time of purchase: " + strNow2);
        myWriter.write("\n");
        myWriter.write("Purchased good: " + fuelTypeStr);
        myWriter.write("\n");
        myWriter.write("Purchased amount: " + fuelAmountStr + " L");
        myWriter.write("\n");
        myWriter.write("\n");
        myWriter.write("-----------------------------------------------");
        myWriter.write("\n");
        myWriter.write("Paid in total: " + formattedPriceStr + " �");
        myWriter.write("\n");
        myWriter.write("-----------------------------------------------");
        myWriter.write("\n");
        myWriter.write("\n");
        myWriter.write("Thank you for your purchase.");
        myWriter.write("\n");
        myWriter.write("\n");
        myWriter.write("WeTankU LTD");
        myWriter.write("\n");
        myWriter.write("***********************************************");
        myWriter.close();
         
        //open text file
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
	}
}
