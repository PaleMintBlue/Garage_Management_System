//Rongyu.Wang

package CarSaleSys;

import java.util.Date;

public class Car {
	  private String carBrand;
		private double purchasePrice;
		private Date purchaseDate;
		private double salePrice;
		private Date saleDate;
		private double profit;

	    // constructor method of Car object
	    public Car (String information){
	        super();
	    	String [] infoArray = information.split(",");
	        this.carBrand = infoArray [1];
	    	this.purchasePrice = Double.parseDouble(infoArray [2]);
	        this.purchaseDate = DataInput.createDate(infoArray [3]);
	    }

	    public String getcarBrand() {
			return carBrand;
	    }

	    public void setcarBrand(String carBrand) {
	    	this.carBrand = carBrand;
	    }

		public double getPurchasePrice() {
	        return purchasePrice;
	    }

	    public void setpurchasePrice(double purchasePrice) {
	    	this.purchasePrice = purchasePrice;
	    }

	    public Date getPurchaseDate() {
			return purchaseDate;
	    }

	    public void setPurchaseDate(Date purchaseDate){
	    	this.purchaseDate = purchaseDate;
	    }

	    public double getSalePrice() {
	    	return salePrice;
	    }

	    public void setSalePrice(double salePrice) {
	    	this.salePrice = salePrice;
	    }

	    public Date getSaleDate() {
			return saleDate;
	    }

	    public void setSaleDate(Date saleDate){
	    	this.saleDate = saleDate;
	    }

	    public double getProfit() {
	    	profit = getSalePrice() - getPurchasePrice();
	    	return profit;
	    }

	    public void setProfit(double profit) {
	    	this.profit =  profit;
	    }


}
