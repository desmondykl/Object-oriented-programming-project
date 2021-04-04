package Class;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * A helper class for Scanning user input from the console
 * Generalized error message for invalid inputs from the user
 * 
 * @author Yeo Kok Leong Desmond
 * @version 1.0
 * @since 2019-04-12
 *
 */
public class customScanner {
	
	/**
	 * Only allow user to enter date in the following format "dd/MM/yy"
	 * otherwise will prompt error message and ask to re-enter
	 * @param sc Scanner Class
	 * @return d Date type 
	 */
	public static Date readOnlyDate(Scanner sc) 
    {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		Date d =  new Date();
		String text = "";
		text = sc.nextLine();
		if(text=="-1" || text.equals("-1")){
			return null;
		}
		try {
			d = formatter.parse(text);
		} catch (ParseException e) {
			System.out.println("Invalid Date!");
			System.out.print("Please try again:");
			d = readOnlyDate(sc);
		}
        return d;
    }
	/**
	 * Only allow the user to enter an integer value
	 * otherwise will prompt error message and ask to re-enter
	 * @param sc Scanner Class
	 * @return integer Data Type - int
	 */
	public static int readOnlyIntegers(Scanner sc) 
    {
        int integer = 0;
            try
                {
                    integer = sc.nextInt();
                    sc.nextLine();
                }
                catch(Exception e)
                {
                	System.out.println("ERROR: Only numbers are allowed");
                    System.out.print("Please re-enter choice: ");
                    sc.nextLine();
                    integer = readOnlyIntegers(sc);
                }
        return integer;
    }
	/**
	 * Only allow the user to enter a double data type
	 * otherwise will prompt error message and ask to re-enter
	 * The user will not be allow to enter negative number
	 * @param sc Scanner Class
	 * @return doubles Validates and returns double data type
	 */
	public static double readOnlyDoubles(Scanner sc) 
    {
        double doubles = 0;
        String textdouble ="";
            try
                {
            		textdouble = sc.nextLine();
            		doubles = Double.parseDouble(textdouble);
            		if(doubles == -1) {
            			return -1;
            		}
            		if(doubles < 0) {
            			System.out.println("ERROR: Please enter a positive number");
                        System.out.print("Please re-enter choice: ");
                        doubles = readOnlyDoubles(sc);
            		}
                }
                catch(Exception e)
                {
                	System.out.println("ERROR: Only numbers are allowed");
                    System.out.print("Please re-enter choice: ");
                    sc.nextLine();
                    doubles = readOnlyDoubles(sc);
                }
        return doubles;
    }
	/**
	 * Only allow user to enter only strings with a limited number of characters"
	 * It will otherwise prompt the user to enter the correct number of characters within the limit 
	 * @param sc Scanner Class 
	 * @param min Minimum number of characters to be specified 
	 * @param max Maximum number of characters to be specified 
	 * @return text Validates and returns a valid String 
	 */
	public static String readOnlyString(Scanner sc,int min, int max) 
    {
        String text = "";
      	text = sc.nextLine();
      	text =text.trim();
      	if(text.equals("-1"))
      		return text;
      	if(text.length()<min){
      		System.out.println("Number of character is too short!!");
      		System.out.print("Please try again:");
      		text = readOnlyString(sc,min,max);
      	}
      	else if(text.length()>max){
      		System.out.println("Number of character is too long!!");
      		System.out.print("Please try again:");
      		text = readOnlyString(sc,min,max);
      	}
      	else{
      		return text;

      	}
      	return text;
    }

	/**
	 * Check the Staff ID and catch that it contains 5 digits only 
	 * It will otherwise prompt the user to enter the correct number of characters within the limit 
	 * @param sc Scanner class
	 * @return id Validates and returns an Id
	 */
			public static int checkId(Scanner sc)
			{
				int id = 0;
				int length = 0;
				
				try
				{
				 id = sc.nextInt();
				}
				catch(Exception e)
				{
					System.out.println("ERROR: only numbers are allowed");
					sc.nextLine();
		            id = checkId(sc);
				}
				
				int idCopy = id;
			
				while(idCopy > 0)
		        {
		        	idCopy = idCopy / 10;
		        	length++;
		        }
				
				if( length != 5)
				{
					if(id==-1)
						return -1;
					System.out.println("ERROR: StaffID must contain 5 digits.");
					System.out.print("Enter StaffId: ");
		            id = checkId(sc);
				} 

				return id;
			}
			
			
			/**
			 * Checks that only numbers are allowed in the MenuID, if not 
			 * Checks that the menuitem ID exist if not, 
			 * It will otherwise prompt the user to enter the correct menuID/numbers 
			 * @param sc Scanner class 
			 * @param mm MainMenu class 
			 * @return mId Validates and returns a menuID
			 */
			public static int onlyMenuItemId(Scanner sc,MainMenu mm)
			{
				int mId = 0;
				
				try
				{
				int check=0;
				 mId = sc.nextInt();
				 if(mId == -1)
					 return -1;
				 for(int i = 0; i < mm.getMenuitem().size(); i++)
					{
						if (mm.getMenuitem().get(i).getMenuID() == mId)
						{
							check++;
							return mId;						
						}
					}
				 
				 if(check == 0)
					{
					 	System.out.println();
						System.out.println("ERROR: Menu Item ID does not exist!");
						System.out.print("Please enter Menu Item Id again:");
						mId = onlyMenuItemId(sc,mm);
					}
				}
				catch(Exception e)
				{
					System.out.println();
					System.out.println("ERROR: Only numbers are allowed!");
					System.out.print("Please enter Menu Item ID again: ");
					sc.nextLine();
					mId = onlyMenuItemId(sc,mm);
				}
				
				return mId;
			}
			
			/**
			 * Checks that only numbers are allowed in the promoID, if not 
			 * Checks that the promo ID exist if not, 
			 * It will otherwise prompt the user to enter the correct promoID/number 
			 * @param sc Scanner class
			 * @param mm MainMenu class
			 * @return pId Validates and returns a promo ID
			 */
			public static int onlyPromoItemId(Scanner sc,MainMenu mm)
			{
				int pId = 0;
				
				try
				{
				int check=0;
				 pId = sc.nextInt();
				 if(pId == -1) return -1;
				 for(int i = 0; i < mm.getPromoitem().size(); i++)
					{
						if (mm.getPromoitem().get(i).getPromoID() == pId)
						{
							check++;
							return pId;						
						}
					}
				 
				 if(check == 0)
					{
					 	System.out.println();
						System.out.println("ERROR: Promo Item ID does not exist!");
						System.out.print("Please enter Promo Item Id again:");
						pId = onlyPromoItemId(sc,mm);
					}
				}
				catch(Exception e)
				{
					System.out.println();
					System.out.println("ERROR: Only numbers are allowed!");
					System.out.print("Please enter Promo Item ID again: ");
					sc.nextLine();
					pId = onlyPromoItemId(sc,mm);
				}
				
				return pId;
			}
			
			/**
			 * Checks that only numbers are allowed in the tableID, if not 
			 * Checks that the tableID ID exist if not, 
			 * It will otherwise prompt the user to enter the correct menuID/numbers 
			 * @param sc Scanner class
			 * @param orderList ArrayList of orders
			 * @return tid Validates and returns a tableID
			 */
			public static int onlyTableID(Scanner sc, ArrayList<Ordering> orderList)
			{
				int tid = 0;
				
				try
				{
				int check=0;
				 tid = sc.nextInt();
				 if(tid==-1) {
					 return -1;
				 }
				 for(int i = 0; i < orderList.size(); i++)
					{
						if (orderList.get(i).getTableID() == tid)
						{
							check++;
							return tid;						
						}
					}
				 
				 if(check == 0)
					{
					 	System.out.println();
						System.out.println("ERROR: Table ID does not exist!");
						System.out.print("Please enter Table ID again:");
						tid = onlyTableID(sc, orderList);
					}
				}
				catch(Exception e)
				{
					System.out.println();
					System.out.println("ERROR: Only numbers are allowed!");
					System.out.print("Please enter Table ID again: ");
					sc.nextLine();
					tid = onlyTableID(sc, orderList);
				}
				
				return tid;
			}
			/**
			 * Checks that the user inputs only Y or N (1 character)
			 * Checks that the user will not enter too short/long strings 
			 * It will otherwise prompt the user to enter their result again
			 * @param sc Scanner class
			 * @return text Validates and then returns a valid confirmation character (Y or N) 
			 */
			public static String checkConfirmationID(Scanner sc)
			{
				String text = "";
				
		      	text = sc.nextLine();
		      	if(text=="-1" || text.equals("-1")) {
			      		return "N";
			      	}
		      	if(text.length()<1)
		      	{
		      		System.out.println();
		      		System.out.println("ERROR: Only 'Y' or 'N' is accepted!! Input too Short!");
		      		System.out.print("Please enter choice (Y/N): ");
		      		text = checkConfirmationID(sc);
		      	}
		      	else if(text.length()>1)
		      	{
		      		System.out.println();
		      		System.out.println("Error: Only 'Y' or 'N' is accepted!! Input too Long!");
		      		System.out.print("Please enter choice (Y/N):");
		      		text = checkConfirmationID(sc);
		      	}
		      	else if(text.charAt(0) != 'Y' && text.charAt(0) != 'N' && text.charAt(0) != 'y' && text.charAt(0) != 'n')
		      	{
		      		System.out.println();
	      			System.out.println("ERROR: Only 'Y' or 'N' is accepted!!");
		      		System.out.print("Please enter choice (Y/N):");
		      		text = checkConfirmationID(sc);
		      	}
		      	else
		      	{
		      		return text;
		      	}
		      	
		      	return text;
			}
			/**
			 * Checks the range of string is between min and max digits 
			 * It will otherwise reprompt the user to enter their choice again
			 * @param sc Scanner class 
			 * @param min Minimum no of characters that is specified
			 * @param max Maximum no of characters that is specified 
			 * @return amt Validates and returns a valid digit 
			 */
			public static int prefferedDigits(Scanner sc, int min, int max)
			{
				int amt;
				   try
	               {
	           		amt = sc.nextInt();
	                   sc.nextLine();
	                 if(amt == -1)
	                 {
	                	 return -1;
	                 }
	                 else if(amt < min)
	    			{	System.out.println();
	    				System.out.println("ERROR: Only accepting between " + min + " and " + max );
	    	      		System.out.print("Please enter choice: ");
	    	      		amt = prefferedDigits(sc, min,max);
	    			}
	    			else if(amt > max)
	    			{
	    				System.out.println();
	    				System.out.println("ERROR: Only accepting between " + min + " and " + max );
	    	      		System.out.print("Please enter choice: ");
	    	      		amt = prefferedDigits(sc, min,max);
	    			}
	                  
	               }
	               catch(Exception e)
	               {
	            	   System.out.println();
	                   System.out.println("ERROR: Only digts are allowed!");
	                   System.out.print("Please enter choice: ");
	                   sc.nextLine();
	                   amt = prefferedDigits(sc, min,max);;
	               }
				
			
				return amt;
			}
			
			/**
			 * Validates that the user has entered the valid date time 
			 * It will otherwise recursively prompt the user to enter the correct menuID/numbers 
			 * @param sc Scanner class
			 * @param arrTime Date that is passed in to validate that it is in the correct format, if not it will prompt the user to enter the correct format
			 * @param formatter To get the correct date format for the date [Scan based on the format that you want, there is no fixed format] 
			 * @return arrTime Validates recursively and returns a valid time 
			 */
		public static Date readDateTime(Date arrTime, Scanner sc, DateFormat formatter){
			String arrival = sc.nextLine();
			if(arrival.equals("-1")) {
				return null;
			}
			try {
				arrTime = formatter.parse(arrival);
			} catch (ParseException e) {
				System.out.println("ERROR: Wrong format.");  
				System.out.println("Please try again:");
				arrTime = readDateTime(arrTime, sc, formatter);
			}
			return arrTime;
		}
		
}
