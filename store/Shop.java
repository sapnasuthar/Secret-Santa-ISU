/*Name: Sapna Suthar
Date: Jan 12, 2022
Purpose: Create a class for the items stored in the digital shop
*/

// Add to package
package store;

// Imports
import store.Item.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Shop {
  // FIELDS
  private ArrayList<Item> shop;

  // Constructor
  public Shop() {
    shop = new ArrayList<Item>();
  }

  // Getter
  public Item getShop(int index){
    return shop.get(index);
  }

  // Add to Shop
  public void addToShop(){
    // Variables
    String line = "";
    String name = "";
    String category = "";
    double itemNum = 0.00;
    double price = 0.00;
    Item a;
    // Try Catch
    try
    {
      // File Reader
      FileReader fr = new FileReader ("store/ShopItems.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while (line != null)
      {
        String words[] = line.split(",");
        itemNum = Double.parseDouble(words[0]);
        name = words[1];
        price = Double.parseDouble(words[2]);
        category = words[3];
        shop.add(new Item(name, price, category, itemNum));
        line = reader.readLine();
      }
      fr.close();
      reader.close();
    }
    catch (IOException err)
    {
      System.out.println("Error reading from a file");
    }
    catch (NumberFormatException err)
    {
      System.out.println("Error reading number");
    }
    catch (OutOfMemoryError oom){
      System.out.println("I hate my life");
    }
  }

  // Find Item in Shop
  public int findItem(double itemNum) {
    int index = 0;
    for (Item i : shop) {
      if (i.getItemNo() == itemNum) {
        return index;
      }
      index++;
    }
    return index;
  }

  // Print the shop
  public void printShop() {
    for (Item i : shop) {
      System.out.println(i);
    }
  }
}
