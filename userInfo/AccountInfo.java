/*
Name: Vishnu Piraliyil
Date: Dec. 17, 2022
Purpose: Class to create user accounts and add them to array list
*/

package userInfo;
import java.util.*;
import java.io.*;
public class Account{
  
  //fields
  private String user;
  private String pass;

  ArrayList <Account> accounts = new ArrayList <Account>();


  
  // Setters
  public void setUser(String user) {
    this.user = user;
  }
  
  public void setPass(String pass) {
    this.pass = pass;
  }
  
  // Getters
  public String getUser() {
    return this.user;
  }
  
  public String getPass() {
    return this.pass;
  }

  // Method to validate password
  public boolean validPass(String pass, String user){
      if(pass.length()>=8 && pass.matches(".*[A-Z].*") == true && pass.matches(".*[0-9].*") == true && pass.contains(user) == false)
      {
       return true;
      }
      else
        return false;
    }

  // Method to add accounts to array list
  public void addToAccounts() {
      //Variables
      String line;
      Account ac = new Account();
    try{
      // File Reader
      FileReader fr = new FileReader("userInfo/AccountInfo.txt");
      BufferedReader reader = new BufferedReader(fr);
      // Reads first line
      line = reader.readLine();
      //Loop through
      while(line != null){
        ac.setUser(line);
        line = reader.readLine();
        ac.setPass(line);
        accounts.add(ac);
        line = reader.readLine();
      }
      reader.close();
    }
    catch(IOException e){
      System.out.println("Error reading from file");
    }
  }

  //Method to find account
  public boolean findAccount(String user){
    //Variables
    String line = "";
    Account acc = new Account();
    try{
      // File Reader
      FileReader fr = new FileReader("userInfo/AccountInfo.txt");
      BufferedReader reader = new BufferedReader(fr);
      // Reads first line
      line = reader.readLine();
      //Loop through
      while(line != null && !line.equals(user)){
        line = reader.readLine();
      }
      if(line.equals(user))
      {
         return true;
      }
      else
      {
        return false;
      }
    }
    catch(IOException e){
      System.out.println("That username does not exist");
      return false;
    }
    catch(NullPointerException er)
      {
        System.out.println("That username does not exist");
        return false;
      }
    
  }
  
   // Default constructor
  public Account() {
    this.user = "";
    this.pass = "";
  }

  // Overloading constructor
  public Account(String user, String pass) {
    this.user = user;
    this.pass = pass;
  }
}