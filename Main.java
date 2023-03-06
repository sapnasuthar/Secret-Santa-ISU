/*Name: Sapna Suthar, Vishnu Piraliyil, Josh Vanderkuyl
Date: Jan 09/2023
Purpose: To create a Secret Santa Application*/

// Import packages
import store.*;
import userInfo.*;
import budgetTracker.*;
//Import library

import java.util.*;
import java.io.*;
import java.text.*;

class Main {
  public static void main(String[] args) {
    // VARIABLES
    String choice = "";
    String login = "";
    String shopChoice = "";
    String wishlistChoice = "";
    String groupChoice = "";
    String user = "";
    String pass = "";
    String itemWish = "";
    String groupName = "";
    double itemNum = 0.00;
    double budget = 0.00;
    int index = 0;
    boolean validUser = false;
    boolean validPass = false;
    Shop s = new Shop();
    Cart c = new Cart();  
    Wishlist w = new Wishlist();
    Account ac = new Account();
    Users u = new Users();
    Login log = new Login();
    Group g = new Group();
    
    
    // Scanner - SAPNA
    Scanner input = new Scanner (System.in);
    // Create shop array - SAPNA
    s.addToShop();
    // Title - SAPNA
    System.out.println("SECRET SANTA");
    
    // Ask user to login/make an account - SAPNA
    System.out.println("Please pick one of the following options:");
    System.out.println("1 - Make an Account");
    System.out.println("2 - Login");
    do{
      login = input.next();
    } while(!(login.equals("1") || login.equals("2")));
    // Flush Screen
    flush();
    
    // MAKE AN ACCOUNT - VISHNU
    if (login.equals("1")){
    System.out.println("MAKE AN ACCOUNT");
    // Ask user for username
      System.out.println("Please enter username.");
      user = input.next();
      ac.setUser(user);

      // Ask user to create a password
      do{
        System.out.println("Please enter password, must contain atleast 8 characters, atleast 1 capital and atleast 1 number. Your password cannot contain your username");
        pass = input.next();
        if (ac.validPass(pass, user) == true) { 
          System.out.println("Password is valid");
          ac.setPass(pass);
          u.addAcc(ac);
        }
      }while(ac.validPass(pass, user) == false);
      
      // Allows user to go to login after making their account
      login = "2";
    }// End of make an account

    // LOGIN - VISHNU
    if (login.equals("2")){
      // Flush Screen
      flush();
      // Login code
      System.out.println("LOGIN");
      do{
        System.out.println("Enter username");
        user = input.next();
        validUser = ac.findAccount(user);
      } while(validUser == false);
      do
      {
        System.out.println("Enter your password");
        pass = input.next();
        validPass = log.checkPass(pass, user);
      } while(validPass == false);

    } // End of login

    // RUN PROGRAM - SAPNA
    do{
      flush();
      // Validiate choice - SAPNA
      do{
        mainMenu();
        choice = input.next(); 
        flush();
      } while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")));
      
      // GROUPS - VISHNU
      if (choice.equals("1")){
        do{
          // Validate groups choice - SAPNA
          do{
            groupsMenu(); 
            groupChoice = input.next(); 
          } while (!(groupChoice.equals("1") || groupChoice.equals("2") || groupChoice.equals("3")));
          
          // Groups - Make Group - VISHNU
          if (groupChoice.equals("1")){
            // Flush screen
            flush();
            // Create a group
            Account ac2 = new Account();
            System.out.println("Enter what you would like to name your group.");
            groupName = input.next();
            do
            {
              do{
                System.out.println("Enter the username of a user you would like to add.");
                user = input.next();
                validUser = ac2.findAccount(user);
                if(validUser == false)
                {
                  System.out.println("That user doesnt exist, make sure your spelling is correct");
                }
              }while(validUser == false);
                
              g.addUser(groupName, user);
              System.out.println("To add another user enter 1 if your group is complete enter 0");
              choice = input.next();
            }while(choice.equals("1"));

            do {
              System.out.println("Enter the budget you would like to set for this group.");
              budget = input.nextDouble();
            } while (budget < 0);
            g.setBudget(budget);

            g.newGroup(groupName, budget);
          } // End Groups - Make Group
            
          // Groups - View - SAPNA
          else if (groupChoice.equals("2")){
            // VISHNU
            System.out.println("\nEnter the name of the group you wish to see.");
            groupName = input.next();
            System.out.println("\nGROUP NAME: " + groupName);
            System.out.println("MEMBERS:");
            g.printMembers(groupName);
            g.secretSanta(groupName, user);
          } // End Groups - View Group
            
          // Groups - Return to Main Menu - SAPNA
          else if (groupChoice.equals("3")){
            flush();
            break;
          }
            
        } while (groupChoice.equals("1") || groupChoice.equals("2") || groupChoice.equals("3")); // END OF GROUPS
      } // END IF FOR GROUPS

      // WISHLIST - SAPNA
      else if (choice.equals("2")){
        do{ 
          // Validate wishlist choice - SAPNA
          do{
            wishlistMenu();
            wishlistChoice = input.next();
          } while (!(wishlistChoice.equals("1") || wishlistChoice.equals("2") || wishlistChoice.equals("3") || wishlistChoice.equals("4") || wishlistChoice.equals("5")));

          // Read from file - SAPNA
          w.readWishlistFromFile(user);
          
          // Wishlist - View Wishlist - SAPNA
          if (wishlistChoice.equals("1")){
            // Flush screen
            flush();
            // Print wishlist
            w.printWishlist();
          } // End Wishlist - View Wishlist
            
          // Wishlist - Add Wishlist - SAPNA
          else if (wishlistChoice.equals("2")){
            // Flush screen
            flush();
            // Add item to wishlist
            System.out.println("To add an item enter the item name. To stop adding items enter 0.");
            itemWish = input.next();
            while (!itemWish.equals("0")){
              w.addToWishlist(itemWish);
              w.printWishlistToFileAdd(user, itemWish);
              itemWish = input.next();
            }
          } // End Wishlist - Add Wishlist
            
          // Wishlist - Remove Wishlist - SAPNA
          else if (wishlistChoice.equals("3")){
            // Flush screen
            flush();
            // Remove item from wishlist
            System.out.println("To remove an item enter the item name.");
            itemWish = input.next();
            w.removeFromWishlist(itemWish);
            // Print wishlist
            w.printWishlistToFileRemove(user);
          } // End Wishlist - Remove Wishlist
            
          // Wishlist - View Group Members Wishlist - SAPNA
          else if (wishlistChoice.equals("4")){
            // Flush screen
            flush();
            // Ask for group name
            System.out.println("Please enter the name of the group");
            groupName = input.next();
            // Get names of members
            g.getMemberNames(groupName);
            // Print each member's wishlist
            for (String i: g.members){
              w.clearWishlist();
              System.out.println("\n" + i.toUpperCase() + " WISHLIST");
              w.readWishlistFromFile(i);
              w.printWishlist();
            }
          } // End Wishlist - View Group Members Wishlist
            
          // Wishlist - Main Menu - SAPNA
          else if (wishlistChoice.equals("5")){
            flush();
            break;
          }
          
          //Clear wishlist - SAPNA
          w.clearWishlist();
          
        } while (wishlistChoice.equals("1") || wishlistChoice.equals("2") || wishlistChoice.equals("3") || wishlistChoice.equals("4") || wishlistChoice.equals("5")); // END OF WISHLIST
        
      } // END ELSE IF FROM WISHLIST 

      // SHOP - SAPNA
      else if (choice.equals("3")){
        // Find group to shop for - SAPNA
        System.out.println("Enter the name of the group you are shopping for");
        groupName = input.next();
        
        do{      
          // Valdiate choice - SAPNA
          do{
            shopMenu();
            shopChoice = input.next();
          } while (!(shopChoice.equals("1") || shopChoice.equals("2") || shopChoice.equals("3") || shopChoice.equals("4") || shopChoice.equals("5")));
  
          // Shop - Add item - SAPNA
          if (shopChoice.equals("1")){
            // Print Shop
            s.printShop();
            // User Input for Item
            do{
              System.out.println("\nEnter the Item No. correspoding to the item you wish to add to cart.");
              itemNum = input.nextDouble();
            } while (!(itemNum == 0.00 || itemNum >= 1.01 && itemNum <= 8.15));
            // Find index for item in Shop
            index = s.findItem(itemNum);
            // Add the item to cart
            c.addToCart(s, index);
            // Flush
            flush();
            // Print out the items in the cart
            System.out.println("\nYOUR CART");
            c.printCart();
          } // End Shop - Add item
          
          // Shop - Remove item - SAPNA
          else if (shopChoice.equals("2")){
            do{
              System.out.println("Enter the Item No. for the item you wish to remove.");
              itemNum = input.nextDouble();
            } while (!(itemNum == 0.00 || itemNum >= 1.01 && itemNum <= 8.15));
            c.removeFromCart(itemNum);
            // Flush
            flush();
            // Print items in modified cart
            System.out.println("\nYOUR MODIFIED CART");
            c.printCart();
          } // End Shop - Remove item
            
          // Shop - View cart - SAPNA
          else if (shopChoice.equals("3")){
            // Flush
            flush();
            // Print items in cart
            System.out.println("YOUR CART");
            c.printCart();
          } // End Shop - View cart
          
          // Shop - Checkout - SAPNA
          else if (shopChoice.equals("4")){
            if (c.isCartEmpty() == true)
              System.out.println("You can't checkout with an empty cart.");
            else{
              budget = g.findBudget(groupName);
              c.printReceipt(user, budget);
              System.out.println("Check receipts folder for your receipt");
            }
          } // End Shop - Checkout
            
          // Shop - Main Menu - SAPNA
          else if (shopChoice.equals("5")){
            flush();
            break;
          }

        } while (shopChoice.equals("1") || shopChoice.equals("2") || shopChoice.equals("3") || shopChoice.equals("4") || shopChoice.equals("5")); // END OF SHOP
        
      } // END ELSE IF FROM SHOP

      // EXIT - SAPNA
      else if (choice.equals("4")){
        flush();
        break;
      }
      
    } while (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")); // END OF PROGRAM

    

  } // End Main Class

  // Display Menu Options - SAPNA
  public static void mainMenu(){
    System.out.println("\nMAIN MENU");
    System.out.println("Please pick one of the following options:");
    System.out.println("1 - Groups");
    System.out.println("2 - Wishlists");
    System.out.println("3 - Shop");
    System.out.println("4 - Exit Secret Santa");
  }
  
  // Display Menu Options - SAPNA
  public static void shopMenu(){ 
    System.out.println("\nSHOP MENU");
    System.out.println("1 - Add an Item to your Cart");
    System.out.println("2 - Remove an Item from your Cart");
    System.out.println("3 - View your Cart");
    System.out.println("4 - Checkout");
    System.out.println("5 - Main Menu");
  }

  // Print menu for groups - SAPNA
  public static void groupsMenu(){
    System.out.println("\nGROUPS MENU");
    System.out.println("Please pick one of the following options:");
    System.out.println("1 - Make a new group");
    System.out.println("2 - View your group");
    System.out.println("3 - Main Menu");
  }

  // Print menu for wishlist - SAPNA
  public static void wishlistMenu(){
    System.out.println("\nWISHLIST MENU");
    System.out.println("\nPlease pick one of the following options:");
    System.out.println("1 - View your wishlist");
    System.out.println("2 - Add to your wishlist");
    System.out.println("3 - Remove from your wishlist");
    System.out.println("4 - View group members wishlists");
    System.out.println("5 - Main Menu");
  }

  // Flush Screen - SAPNA
  public static void flush(){
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }
}
