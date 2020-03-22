
package CarSaleSys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Garage {
	private double totalProfit;
	String name;
    private ArrayList<Car> listOfCarForSale;
    ArrayList<Car> listOfCarSold;

    // constructor method of Garage object
    public Garage (String name){
        this.name = name;
        this.listOfCarForSale = new ArrayList<Car>();
        this.listOfCarSold = new ArrayList<Car>();
    }

    public String getName() {
        return name;
    }

    // add a Car object
    // add information of the car: price of purchase, date of purchase
 	public static Car createCar() {
 		System.out.print("Enter the brand of car: ");
 		String b = DataInput.inputString();
 		double p = DataInput.inputPrice();
 		Date d = DataInput.inputDate();
 		String ds = DataInput.dateInString(d);
 		String line = "[Unsold]," + b + "," + p + "," + ds;
 	    return new Car(line);
	}

    // add a car to the system when it is purchased
    // purchase can only happen when there are less than 20 cars in the garage
    public void addCar(Car c) {
    	if (this.listOfCarForSale.size() < 20){
    		this.listOfCarForSale.add(c);
    		System.out.println("Add successfully!");
    		System.out.println();
    		sortCarByDate();
    	} else {
    		System.out.println("The garage can only store 20 cars. ");
    	}
    }

    //add cars when read the .txt
    public void getCar(Car c) {
    		this.listOfCarForSale.add(c);
    		sortCarByDate();
    }

    // remove a car when it is sold
    // add information of purchase: price of sale, date of sale
    // send message when garage is empty of the car has been successfully sold
    public void removeCar(Car c) {
  	    if (!this.listOfCarForSale.contains(c)){
  	    	System.out.println("This car is not in the garage. ");
  	    } else {
  	    	double pr = DataInput.inputPrice();
  	    	c.setSalePrice(pr);
  	    	Date ds = DataInput.inputDate();
  	    	if (checkDateOrder(ds, c.getPurchaseDate())) {
			c.setSaleDate(ds);
			this.listOfCarForSale.remove(c);
			this.listOfCarSold.add(c);
			sortCarByDate();
			System.out.println("Sold successfully. ");
    	}
  	    }
    }

    //remove cars when read .txt
    public void deleteCar(Car c) {
  	    		this.listOfCarForSale.remove(c);
  	    	    this.listOfCarSold.add(c);
  	    	    sortCarByDate();
    }
    // purchase new car
    public static void purchaseCar() {
    	Car c = createCar();
    	Menu.g.addCar(c);
    }

    // sale the car assigned
    public static void sellCar() {
    	if (Menu.g.listOfCarForSale.isEmpty()){
  	    	System.out.println("No cars in the garage! ");
  	    } else {
  	        Menu.g.showAllCarForSale();
  	    	System.out.print("Enter the No. of the car: ");
  	    	int n = DataInput.inputInteger();
  	    	Car c = Menu.g.getCarByIndex(n);
  	    	Menu.g.removeCar(c);
  	    	}
    }

    // input the No. of the Car object
    public Car getCarByIndex(int n) {
        return listOfCarForSale.get(n - 1);
    }

    // all cars for sale sorted by date of purchase
    public void sortCarByDate() {
    	 int n = listOfCarForSale.size();
         Car temp = null;
         for (int i = 0; i < n; i++) {
             for (int j = 1; j < (n - i); j++) {
                 if (listOfCarForSale.get(j - 1).getPurchaseDate().after(listOfCarForSale.get(j).getPurchaseDate())) {
                     temp = listOfCarForSale.get(j - 1);
                     listOfCarForSale.set(j - 1, listOfCarForSale.get(j));
                     listOfCarForSale.set(j, temp);
                 }
             }
         }
    }

    // check if date of sale is later than date of purchase
    public boolean checkDateOrder(Date m, Date n) {
		if (m.getTime() > n.getTime()) {
			return true;
		} else {
			return false;
		}
    }

    // display a list of all cars in the garage
    public void showAllCarForSale() {
        String desc = "All Cars in the " + this.getName() + "\n";
        desc = desc + "-----------------------------------------";
        System.out.println(desc);
        System.out.printf("[No.], carBrand, purchasedPrice($), purchasedDate" + "\n");
        Iterator<Car> it = listOfCarForSale.iterator();
        Car c;
        int i = 0;
        while (it.hasNext()) {
        	c = it.next();
        	i = i + 1;
        	System.out.printf("[" + i + "]" + ", " + c.getcarBrand() + ", " + c.getPurchasePrice() + ", " + DataInput.dateInString(c.getPurchaseDate()) + "\n");
        }
        System.out.println();
        return;
    }

    // display a report showing all car sold
    // show the total profit at the end of the report.
    public void soldReport() {
		String desc = "All Cars sold in the " + this.getName() + "\n";
		desc = desc + "-----------------------------------------";
		System.out.println(desc);
        Iterator<Car> it = listOfCarSold.iterator();
        Car c;
        totalProfit = 0;
        while (it.hasNext()) {
 	        c = it.next();
 	        System.out.printf(c.getcarBrand() + ", cost " + c.getPurchasePrice() + ", sold in " + c.getSalePrice() + ". profit is " + String.format("%.2f", c.getProfit()) + "\n");
 	        totalProfit = totalProfit + c.getProfit();
        }

        System.out.println("[Total profit]: " + String.format("%.2f",totalProfit));
        System.out.println();
        return;
    }

    // saves all cars to a text file before the program exits
    public void storeFile() {
    	File file = new File("Garage.txt");
    	String descUnsold = null;
    	String descSold = null;
    	try {
    		BufferedWriter fw = new BufferedWriter(new FileWriter(file));
    		Iterator<Car> itForSale = listOfCarForSale.iterator();
    		Iterator<Car> itSold = listOfCarSold.iterator();
    		Car c;
    		fw.write(Menu.g.name + "\n");
    		fw.newLine();
    		fw.flush();
        	fw.write("Mark--carBrand--purchasedPrice($)--purchasedDate--soldPrice($)--soldDate" + "\n");
    		while (itForSale.hasNext()) {
    			c = itForSale.next();
            	descUnsold = "[Unsold]," + c.getcarBrand() + "," + c.getPurchasePrice() + "," + DataInput.dateInString(c.getPurchaseDate());
            	fw.newLine();
            	fw.write(descUnsold);
    	        fw.newLine();
    	        fw.flush();
    		}
    		while (itSold.hasNext()) {
    			c = itSold.next();
    			descSold = "[Sold]," + c.getcarBrand() + "," + c.getPurchasePrice() + "," + DataInput.dateInString(c.getPurchaseDate()) + "," + c.getSalePrice() + "," + DataInput.dateInString(c.getSaleDate());
    			fw.newLine();
    			fw.write(descSold);
    	        fw.newLine();
    	        fw.flush();
    		}
    		System.out.println("-----------------------------");
    		System.out.println("Writing to file successfully!");
    		fw.close();
    	} catch (IOException e) {
    		System.out.println("Error writing to file");
    	}
    }
}
