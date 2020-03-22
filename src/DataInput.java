
package CarSaleSys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataInput {
		static Scanner kb = new Scanner(System.in);

		// input a string
		public static String inputString() {
			return kb.nextLine();
		}

		// input a integer
		public static int inputInteger() {
			int i = 0;
		    boolean validInput = false;
		    while (!validInput) {
		        String s = inputString();
		        try {
		        	i = Integer.parseInt(s);
		            validInput = true;
		        } catch (NumberFormatException e) {
		        	System.out.println("Not a valid integer");
		        }
		    }
		    return i;
		}

		// input a price
		public static double inputPrice() {
	        double d = 0;
	        boolean validInput = false;
	        while (!validInput) {
	        	System.out.print("Enter the price of the car (0.00): $ ");
	            String s = inputString();
	            try {
                     d = Double.parseDouble(s);
	                 validInput = true;
	            } catch (NumberFormatException e) {
	                System.out.println("Not a valid price.");
	            }
	        }
	        return d;
	    }

		// input a Date
		public static Date inputDate() {
	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date d = null;
	        while (d == null) {
	            System.out.print("Enter a date (dd/MM/yyyy): ");
	            String s = inputString();
	            try {
	                d = df.parse(s);
	            } catch (ParseException e) {
	                System.out.println("Not a valid date.");
	            }
	        }
	        return d;
	    }

		// transform string to Date
	    public static Date createDate(String dateString) {
	    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    	Date d = new Date();
	    	try {
	    		d = df.parse(dateString);
	    	} catch (Exception e) {
	    		System.out.println("Not a valid date");
	    	}
	        return d;
	    }

	    // transform Date to string
	    public static String dateInString(Date d) {
	    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    	String s = df.format(d);
	    	return s;
	    }



}
