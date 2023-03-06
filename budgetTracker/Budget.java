/*Name: Josh Vanderkuyl
Date: Jan 12, 2022
Purpose: Create a class for the items stored in the digital shop
*/

package budgetTracker;

public class Budget{
  //variables for budget limit and the remaining amount left to spend
  private double limit;
  private double spending;

  // Default
  public Budget(){
    this.limit = 0.00;
    this.spending = 0.00;
  }
  // Overloading
  public Budget(double l, double s){
    if (l > 0.00)
      this.limit = l;
    else
      this.limit = 0.00;
    if (s > 0.00)
      this.spending = s;
    else
      this.spending = 0.00;
  }
  
  // Setters for limit and remaining amount
  public void setLimit(double l){
    if(l > 0.00){
      limit = l;
    }
    else{
      throw new IllegalArgumentException("Please insert valid limit");
    } 
  }

  public void setSpending(double s){
    if(s > 0.00)
      spending = s;
    else
      throw new IllegalArgumentException("Please insert valid amount");
  } 

  // Getters for limit and remaining amount
  public double getLimit(){
    return this.limit;
  }

  public double getSpending(){
    return this.spending;
  }

  //calculate remaining amount
  public double calcRemainingAmount(){
    return limit - spending;
  }

  public String toString(){
    return "Budget: $" + limit + "\nSpent: $" + spending;
  }
}