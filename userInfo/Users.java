/*Name: Vishnu Piraliyil
Date: Dec. 17, 2022
Purpose: Class to create user accounts and add them to array list
*/

package userInfo;
import java.util.*;
import java.io.*;
import userInfo.Account;
public class Users{

  
  // Array list to store all users log in info
  ArrayList <Account> users = new ArrayList <Account>();

  //Method to add user to array list
  public void addAcc(Account a){
    users.add(a);

    try
    {
      FileWriter fw = new FileWriter("userInfo/AccountInfo.txt", true);
      PrintWriter printer = new PrintWriter (fw);
      printer.println(a.getUser());
      printer.println(a.getPass());
      printer.close();
    }
    catch(IOException e)
    {
      System.out.println("error writing to file the file specified");
    }
  
  }

  
}