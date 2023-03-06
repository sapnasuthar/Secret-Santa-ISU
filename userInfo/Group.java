/*Dec. 15, 2022
Vishnu 
Purpose: A class to make the groups of accounts and to assign who is buying for who is a group*/
//imports
import userInfo.Account;
import java.util.*;
import java.io.*;

public class Group{
  // Fields
    private String name;
    private double budget = 0.00;

    ArrayList <String> members = new ArrayList <String>();

    // method to create a group with a given name
    public void newGroup(String name, double budget) {
      try
      {
        FileWriter fw = new FileWriter("userInfo/GroupInfo.txt", true);
        PrintWriter printer = new PrintWriter (fw);
        printer.println(name);
        printer.println(budget);
        printer.close();
      }
      catch(IOException e)
      {
        System.out.println("error writing to file the file specified");
      }
    }

    // method to add users to the group
    public void addUser(String name, String user){
      try{ 
      FileWriter fw = new FileWriter ("groups/" + name + "-group.txt", true);
      PrintWriter printer = new PrintWriter(fw);
      printer.println(user);
      printer.close();
      }
      catch(IOException e)
        {
          
        }
    }


    // method to assing secret santa
    public void secretSanta(String groupName, String user){
      //variables
      String line = "";
      int memberIndex = 0;
      try
    {
      // File Reader
      FileReader fr = new FileReader ("groups/" + groupName + "-group.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while(line != null)
        {
          members.add(line);
          line = reader.readLine();
        }
      reader.close();
    }
      catch(IOException e)
      {
        
      }

      //randomization
      do{
      memberIndex = (int)(members.size() * Math.random());
      }while(members.get(memberIndex).equals(user));
      System.out.println("You are buying for " + members.get(memberIndex) + "! ");
      members.remove(memberIndex);
  }

  // method to get the names of the members of a group
  public void getMemberNames(String groupName){
    //variables
    String line = "";
    try
    {
      // File Reader
      FileReader fr = new FileReader ("groups/" + groupName + "-group.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while(line != null)
      {
        members.add(line);
        line = reader.readLine();
      }
    }
    catch(IOException e)
    {

    }
  }

  // method to get the names of the members of a group
  public int count(String groupName){
    //variables
    String line = "";
    int i = 0;
    try
    {
      // File Reader
      FileReader fr = new FileReader ("groups/" + groupName + "-group.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while(line != null)
      {
        i++;
        line = reader.readLine();
      }
      reader.close();
    }
    catch(IOException e)
    {

    }
    return i;
  }

  // method to print group members
  public void printMembers(String groupName){
    //variables
    String line = "";
    try
    {
      // File Reader
      FileReader fr = new FileReader ("groups/" + groupName + "-group.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while(line != null)
        {
          System.out.println(line);
          line = reader.readLine();
        }
    }
      catch(IOException e)
      {
        
      }
  }
  
    // getter for the name of the group
    public String getName() {
        return name;
    }

    // setter for the name of the group
    public void setName(String name) {
        this.name = name;
    }

  // getter for budget
  public double getBudget(){
    return budget;
  }

  // setter for budget
  public void setBudget(double budget){
    if(budget>0)
    {
      this.budget = budget;
    }
  }

  public double findBudget(String groupName){
    // Variables
    String line = "";
    double budget = 0.00;
    // Try
    try{
      //Create File Reader
      FileReader fr = new FileReader ("userInfo/GroupInfo.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while(line != null)
      {
        if(line.equals(groupName))
        {
          line = reader.readLine();
          budget = Double.parseDouble(line); 
        }
        else 
        {
          line = reader.readLine();
        }
      }
      return budget;
    }
    catch (IOException err){
      System.out.println("Error reading from file");
    }
    return budget;
  }

  public boolean findGroup(String groupName){
    // Variables
    String line = "";
    // Try
    try{
      //Create File Reader
      FileReader fr = new FileReader ("userInfo/GroupInfo.txt");
      BufferedReader reader = new BufferedReader (fr);
      // Reads first line
      line = reader.readLine();
      // Loop through 
      while(line != null)
      {
        if(line.equals(groupName))
        {
          return true;
        }
        else 
        {
          line = reader.readLine();
        }
      }
      return false;
    }
    catch (IOException err){
      System.out.println("Error reading from file");
    }
    return false;
  }
}