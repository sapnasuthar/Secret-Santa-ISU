/*
Name: Vishnu Piraliyil
Date: Dec. 17, 2022
Purpose: Class to allow user to log in
*/
import java.io.*;

public class Login {

  // Method to check password
  public boolean checkPass(String password, String user) {
    // Variables
    String line = "";
    
    try{
      // File reader
      FileReader fr = new FileReader("userInfo/AccountInfo.txt");
      BufferedReader reader = new BufferedReader(fr);
  
      // Reads first line
      line = reader.readLine();
      
      // Loops through file
      while(line != null){
        if (line.equals(user)){
          line = reader.readLine();
          if (line.equals(password)){
            return true;
          }
          else{
            line = reader.readLine();
          }
        }
        else {
          line = reader.readLine();
        }
      }
      reader.close();
      return false;
    }
    catch(IOException e){
      System.out.println("Error reading from file");
      return false;
    }
    catch(NullPointerException er){
      System.out.println("Error reading from file");
      return false;
    }
  } // end checkPass method
}