
package CarSaleSys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Menu {

	static Garage g;

	// read a text file when open the system if there exits such a file
	// display the menu, operate the garage with menu
	// saves all cars to the text file before exits
	public static void GarageMenu() {
		if (!readFile()) {
            g = new Garage("Joe' s Garage");
        }
		System.out.println("****Welcome to " + g.name + "****");
    	boolean exit = false;
	    String select = "";
	    while (!exit) {
	    	System.out.println();
	    	System.out.println("What do you want to do? ");
	    	System.out.println("0= exit");
	        System.out.println("1= add car");
	        System.out.println("2= sell car");
	        System.out.println("3= all cars unsold");
	        System.out.println("4= all cars sold ");
	        select = DataInput.inputString();
	        switch (select) {
	            case "0":
	            	exit = true;
	                break;
	            case "1":
	                Garage.purchaseCar();
	                break;
	            case "2":
	                Garage.sellCar();
	                break;
	            case "3":
	                g.showAllCarForSale();
	                break;
	            case "4":
	            	g.soldReport();
	            default:
	            	System.out.println("Invalid option!");
	        }
	    }
	    g.storeFile();
	}

	// read a list of cars from a text file
	public static boolean readFile() {
    	boolean success = false;
	    File file = new File("Garage.txt");
	    String line;
	    	try {
	    		BufferedReader fr = new BufferedReader(new FileReader(file));
	    		line = fr.readLine();
	    		if(line != null) {
	    			g = new Garage(line);
	    		} else {
	    			fr.close();
	    			return false;
	    		}
	    		line = fr.readLine();
	    		line = fr.readLine();
	    		Car c = null;
	    	    while ((line = fr.readLine()) != null) { // read next line if it exists
	    	    	String[] s = line.split(",");
	    	    	if (s[0].equals("[Unsold]")) {
	    	    		c = new Car(line);
	    	    		g.getCar(c);
	    	    	} else if (s[0].equals("[Sold]")) {
	    	    			c = new Car(line);
	    	    		    g.listOfCarSold.add(c);
	    	  	    	    g.sortCarByDate();
	    	    		}
	    	    }
	    	    System.out.println("Read file successfully!");
	    	    System.out.println();
	    	    fr.close();
	    	    success = true;
	    	    } catch (IOException e) {
	    		System.out.println("Error reading file.");
	    		System.out.println();
	    	}
	    	return success;
    }
}
