/*Name: Sapna Suthar
Date: Dec 15, 2022
Purpose: Create a class for the items stored in the digital shop
*/

// Create Package
package store;

// Import library
import java.text.*;
import java.util.*;

public class Item{
  // FIELDS
  private String name;
  private String category;
  private double price;
  private double itemNo;

  private static final String [] CATEGORY = {"Candy and Chocolate", "Electronics", "Toys and Games", "Personal Care", "Home Decor", "Clothes", "Shoes", "Jewellery", "Other"};

  // CONSTRUCTORS
  // Default
  public Item(){
    name = "";
    price = 0.00;
    category = "Other";
    itemNo = 0.00;
  }

  // Overloading
  public Item(String n, double p, String c, double i){
    if(!n.matches("[0-9]+"))
      name = n;
    else
      name = "";
    if (p < 0.00)
      this.price = 0.00;
    else
      this.price = p;
    for (int j = 0; j < 9; j++){
      if(c.equals(CATEGORY[j]))
        this.category = c;
      else
        this.category = "Other";
    }  
    if (i < 0.00)
      this.itemNo = 0.00;
    else
      this.itemNo = i;
  }

  // GETTERS
  public String getName(){
    return this.name;
  }

  public double getPrice(){
    return this.price;
  }

  public String getCategory(){
    return this.category;
  }

  public double getItemNo(){
    return this.itemNo;
  }

  // SETTERS
  public void setName(String n){
    if(!n.matches("[0-9]+"))
      this.name = n;
    else
      throw new IllegalArgumentException("Invalid item name");
  }

  public void setPrice(double p){
    if (p < 0.00)
      throw new IllegalArgumentException("Invalid item price");
    else
      this.price = p;
  }

  public void setCategory(String c){
    boolean match = false;
    for (int i = 0; i < 8; i++){
      if(category.equals(CATEGORY[i]))
        match = true;
    }
    if (match == true)
      this.category = c;
    else
      throw new IllegalArgumentException("Invalid category name");
  }  

  public void setItemNo(double i){
    if (i < 0.00)
      throw new IllegalArgumentException("Invalid item number");
    else
      this.itemNo = i;
  }

  // FORMATTING THE TO STRING
  public String toString(){
    DecimalFormat df = new DecimalFormat ("0.00");
    return "\nItem No.: " + df.format(itemNo) + "\nItem: " + name + "\nCategory: " + category + "\nPrice: $" + df.format(price);
  }
  
}