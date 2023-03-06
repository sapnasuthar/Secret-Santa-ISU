/*Name: Sapna Suthar
Date: Jan 17, 2022
Purpose: Create a class for users to input the items on their wishlist
*/

// Add to package
package userInfo;

// Import
import java.util.*;
import java.io.*;

public class Wishlist{
  // FIELDS
  private static ArrayList <String> wishlist;

  // Constructor
  public Wishlist(){
    wishlist = new ArrayList<String>();
  }

  // Getter for wishlist
  public ArrayList<String> getWishlist(){
    return wishlist;
  }

  // Allow user to add an item to their wishlist
  public void addToWishlist(String s){
    wishlist.add(s);
  }

  // Allow user to remove an item from their wishlist
  public void removeFromWishlist(String s){
    wishlist.remove(s);
  }

  // Prints the user's wishlist
  public void printWishlist(){
    System.out.println("WISHLIST");
    for (String w: wishlist){
      System.out.println(w);
    }
  }

  // Read wishlist from file
  public void readWishlistFromFile(String username){
    String line = "";
    String wishlistName = "wishlist/" + username + "-wishlist.txt";
    try
    {
      // File Reader
      FileReader fr = new FileReader(wishlistName);
      BufferedReader reader = new BufferedReader (fr);

      // Reading from file
      line = reader.readLine();
      while(line != null){
        wishlist.add(line);
        line = reader.readLine();
      }
      reader.close();
    }
    catch (IOException err)
    {
      System.out.println("User has not added to their wishlist yet");
    }
    catch (ConcurrentModificationException err)
    {
      System.out.println("Error reading from a file");
    }
  }

  // Print wishlist to a file for add
  public void printWishlistToFileAdd(String username, String s){
    String wishlistName = "wishlist/" + username + "-wishlist.txt";
    try
    {
      FileWriter fw = new FileWriter(wishlistName, true);
      PrintWriter printer = new PrintWriter (fw);
      // Print wishlist array to file
      printer.println(s);
      printer.close();
      wishlist.clear();
    }
    catch(IOException e)
    {
      System.out.println("error writing to file the file specified");
    }
  }
  
  // Print wishlist to a file for remove
  public void printWishlistToFileRemove(String username){
    String wishlistName = "wishlist/" + username + "-wishlist.txt";
    //wishlist.clear();
    try
    {
      FileWriter fw = new FileWriter(wishlistName, false);
      PrintWriter printer = new PrintWriter (fw);
      for(String s: wishlist){
        printer.println(s);
      }
      // Print wishlist array to file
      printer.close();
    }
    catch(IOException e)
    {
      System.out.println("error writing to file the file specified");
    }
  }

  public void clearWishlist(){
    wishlist.clear();
  }
}