/*Name: Sapna Suthar
Date: Jan 12, 2022
Purpose: Create a class for the items stored in the digital shop
*/

// Add to package
package store;

// Imports
import budgetTracker.*;
import store.*;
import java.util.ArrayList;
import java.io.*;
import java.text.*;

public class Cart {
  //FIELDS
  private ArrayList <Item> cart;

  public static final double TAX = 1.13;

  // Constructor
  public Cart(){
    cart = new ArrayList <Item>();
  }
  
  // Add to Cart
  public void addToCart(Shop s, int index){
    cart.add(s.getShop(index));
  }

  // Remove Card
  public void removeFromCart(double num){
    try {
      int index = 0;
      int location = 0;
      for (Item i : cart) {
        if (i.getItemNo() == num)
          location = index;
        else
          index++;
      }
      cart.remove(location);
    }
    catch (IndexOutOfBoundsException err){
      System.out.println("The item doesn't exist in the cart.");
    }
  }

  // Check if Cart is Empty
  public boolean isCartEmpty(){
    return (cart.size() == 0);
  }

  // Calculate the total price of all items in cart
  public double calculatePrice(){
    double total = 0.00;
    for (Item c: cart){
      total += c.getPrice();
    }
    return total;
  }

  // Print cart to console
  public void printCart() {
    for (Item i : cart){
      System.out.println(i);
    }
  }

  // Print receipt
  public void printReceipt(String username, double budget){
    // VARIABLES
    Budget b = new Budget(budget, calculatePrice()*TAX);
    // DecimalFormat
    DecimalFormat df = new DecimalFormat("0.00");
    String filename = "receipts/" + username + "-receipt.txt";
    // Try Catch for any errors
    try
    {
      // File Writer
      FileWriter fw = new FileWriter (filename);
      PrintWriter printer = new PrintWriter (fw);
      printer.println(username.toUpperCase() + "\'S RECEIPT");
      printer.println("-------------------------------------------------------------------------------");
      printer.println("ITEMS");
      // Print items in cart
      for (Item i : cart)
        printer.println(i);
      printer.println("-------------------------------------------------------------------------------");
      // Calculate costs
      printer.println("COST");
      printer.println("Cost: $" + df.format(calculatePrice()));
      printer.println("Total Cost: $" + df.format(calculatePrice()*TAX));
      printer.println("-------------------------------------------------------------------------------");
      // Calculate budger spending
      printer.println("SPENDING");
      if (budget < 0)
        printer.println("Budget: $0.00");
      else
      printer.println("Budget: $" + budget);
      printer.println("Remaining Budget: $ " + df.format(b.calcRemainingAmount()));
      // Close printer
      printer.close();
    }
    // File exception
    catch (IOException err)
    {
      System.out.println("Error writing to the file specified");
    }
  }
}

  