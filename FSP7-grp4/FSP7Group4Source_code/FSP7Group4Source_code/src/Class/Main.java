package Class;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintStream;


import Data.DataManager;
import java.io.*;

/**
 * This is the main class  
 * It is a controller that communicates with boundaries(MainApp) and enitites(Ordering, MainMenu, Promotion and etc classes) 
 * 
 * @author Muhammad Hisyam bin Jukifli
 * @version 1.0
 * @since 2019-04-18
 * 
 */
public class Main {
	/**
	 * Before staff enters their ID their default value will be -1 
	 */
	int activeStaff = -1;
	/**
	 * This is the arraylist of Staff
	 */
	ArrayList<Staff> staffList;
	/**
	 * This is the arraylist of reservation
	 */
	ArrayList<Reservation> resList; 
	/**
	 * This is the arraylist of orderList
	 */
	static ArrayList<Ordering> orderList = new ArrayList<Ordering>(); 
	/**
	 * Declaring a new MainMenu object 
	 */
	MainMenu mainmenu = new MainMenu();
	/**
	 * Declaring a new scanner object 
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Declaring a new tableManager object 
	 */
	TableManager tm = new TableManager();
	
	/**
	 * This is the Main constructor.
	 */
	public Main(){
		getStaffList();
		mainmenu.setMenuitem((ArrayList) DataManager.readSerializedObject("MenuItems.dat"));
		mainmenu.setPromoitem((ArrayList)DataManager.readSerializedObject("PromoItem.dat"));
	}
	
	/**
	 * This function for the staff to enter their ID at the start of the program for validation, before showing the list of functions 
	 * After entering the correct ID, get the staffname and display it at the functions menu
	 * @param m staffID
	 * @param staffName name of staff
	 * @return s.getName returns name of staff with parameters(StaffID, staffList)
	 */
	public String selectStaff(boolean m,String staffName){
		Staff s = new Staff();
		int StaffID = -1;
		boolean first = false;
		do{
			if(first)
				System.out.println("Invalid Staff please try again!");
			else{
				if(m==false)
					System.out.println("Enter your Staff Id (Press -1 to back):");
				else
					System.out.println("Enter your Staff Id:");
			}
				
			StaffID = customScanner.checkId(sc);
			if(StaffID==-1 && m==false)
				return staffName;
			first= true;
			if(s.validStaff(StaffID,staffList))
				activeStaff = StaffID;
		}while(!s.validStaff(StaffID,staffList));
		
		return s.getName(StaffID, staffList);
	}

	/**
	 * Used for hardcoding the employee staff list 
	 * When you exit the program, you can enter the staffID of another staff for program use 
	 */
	private void getStaffList(){
		staffList = new ArrayList<Staff>();
		Staff s = new Staff();
		s.setEmployeeID(10000);
		s.setGender("Female");
		s.setJobTitle("Waiter");
		s.setName("Rachelle Lee Jia Xin");
		staffList.add(s);
		
		Staff s2 = new Staff();
		s2.setEmployeeID(10001);
		s2.setGender("Male");
		s2.setJobTitle("Waiter");
		s2.setName("Ezzudin Son of Sapao");
		staffList.add(s2);
		
		Staff s3 = new Staff();
		s3.setEmployeeID(10002);
		s3.setGender("Male");
		s3.setJobTitle("Cook");
		s3.setName("Hui Ling");
		staffList.add(s3);
		
		DataManager.writeSerializedObject("Staff.dat",staffList);
		staffList = (ArrayList) DataManager.readSerializedObject("Staff.dat");
		
	}

	/**
	 * This is a function to add or remove order
	 * 
	 */
	public void addRemoveOrder() 
	{
		Table t = new Table();
		int tableID;
		int orderid = 0;
		int ind = 0;
		if(tm.printUnpaidTable()){
			System.out.print("Table ID: ");
			tableID = customScanner.onlyTableID(sc, orderList);
			if(tableID==-1)
				return;
			Ordering currOrder = new Ordering();
			Ordering cloneOrder = new Ordering(); 
			
			int i = -1;
			for(Ordering or : orderList)
			{
				i++;
				if(or.getTableID() == (tableID))
				{
					ind = i;
					orderid = or.getOrderID(); 
					cloneOrder = Ordering.cloning(or);
					cloneOrder.printOrderInput();
				}
			}
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("+--------------------------------+");
			System.out.println("|ADD/REMOVE ORDER ITEM SELECTION:|");
			System.out.println("+--------------------------------+");
			System.out.println("|1. Add Item                     |");
			System.out.println("|2. Remove Item                  |");
			System.out.println("|3. Remove Whole Order           |");
			System.out.println("|4. Back                         |");
			System.out.println("+--------------------------------+");
			System.out.println("Please Enter Your Choice:       ");
			int ch = 0;
			do {
				ch = customScanner.readOnlyIntegers(sc);
			switch(ch){
				case 1: case 2:
					orderList = cloneOrder.addRemoveOrder(mainmenu,orderList,ind,ch);
				break;
				case 3:
					System.out.print("Confirm Removal of Order (Y/N): ");
					char conf2 = sc.next().charAt(0);
					if(conf2 == 'Y' || conf2=='y')
					{
						//Remove order object here 
						orderList.remove(ind);
						System.out.println("Order Succesfully Removed!");
						tm.freeTable(tableID);
					}
				break;
				case 4: case -1:
					return;
				default:
					System.out.println("Invalid input!");
					break;
			}
			}while(ch<1 || ch >4);
			
		}
		
	}
	/**
	 * This is a function to create an order 
	 */
	public void createOrder()
	{ 
		Ordering order = new Ordering();
		Delivery cm =null;

		// New changes
		System.out.println();
		System.out.println("+-------------------+");
		System.out.println("|CUSTOMER SELECTION:|");
		System.out.println("+-------------------+");
		System.out.println("|1. Reserved Table  |");
		System.out.println("|2. New Customer    |");
		System.out.println("|3. Back            |");
		System.out.println("+-------------------+");
		System.out.println();
		
		System.out.print("Choice: ");
		int n = customScanner.readOnlyIntegers(sc);
		
		int ind = 0;
		int indConf = 0;
		
		System.out.println();
		if(n == 1)
		{
			System.out.print("Contact Number: ");
			ind=checkRes();
			
			// Retrieve tableID from the reservation database
			// Assign table id to particular order
		}
		else if(n==4)
		{
			cm = new Delivery(); //Creating new Delivery 
			ind=-2; // Set table index = -2
			order = cm; 
		}
		else if(n == 2)
		{
			System.out.println("TABLE SELECTION:");
			System.out.println("----------------");
			System.out.print("How many pax (-1 to go back): ");
			int px = customScanner.prefferedDigits(sc, 1, 10);
			
			if(px == 1 || px == 2)
			{
				px = 2;
			}
			
			if(px == 3 || px == 4)
			{
				px = 4;
			}
			if(px >= 5 && px <= 8)
			{
				px = 8;
			}
			if(px == 9 || px == 10)
			{
				px = 10;
			}
			if(px == -1)
			{
				return;
			}
			
			ArrayList<Table> avail = new ArrayList<Table>();
			
			if(tm.checkTableAvail(px))
			{
				System.out.println();
				System.out.println("There are seats available!");
				System.out.print("Table Number (-1 to go back): ");

				do
				{
					int ifExist = 0;
					ind = customScanner.readOnlyIntegers(sc);
					// New Changes
					if(ind == -1)
					{
						break;
					}
					ArrayList<Table> tables = tm.getAvailableTables(px);
					for(Table t :tables)
					{
						if(t.getTableID() == ind && t.getStatus().equals("Unoccupied"))
						{
							System.out.println("Table ID Accepted: " + t.getTableID());
							indConf++;
							ifExist++;
							System.out.println();
						}
					}
					
					if(ifExist == 0)
					{
						System.out.println();
						if(ind > 30 || ind < 1)
						{
							System.out.println("Table ID does not exist");
						}
						else
						{
							System.out.println("This table is not available!");
						}
						
						System.out.print("Find another table (Y/N)?");
						char anTbl = customScanner.checkConfirmationID(sc).charAt(0);
						switch(anTbl)
						{
						case 'Y': case 'y':
							System.out.print("Table Number: ");
							break;
						case 'N': case 'n':
							indConf = 2;
							break;
						}
					}
				
				}while(indConf == 0);	
			}
			else 
			{
				// New changes
				if(ind != -1)
				{
					System.out.println("No Available tables left!");
					indConf = 2;
				}
			}
		}
		else
		{
			return;
		}
		
		// New changes
		if(indConf == 2 || ind == -1)
		{
			return;
		}
		else
		{
			
			order.createOrder(mainmenu, getcurrentDate(),ind,activeStaff);
			
			if(order.getTotalPrice() == 0 || order.getDate() == null)// Currdate null)
			{
				return;
			}
			
			
			System.out.print("Confirm order (Y/N): ");
			char conf = customScanner.checkConfirmationID(sc).charAt(0);
			if(conf == 'Y' || conf == 'y')
			{
				orderList.add(order);
				order.setToken(true);
				System.out.println();
				System.out.println("Order Confirmed!");
				tm.setTableOcc(ind);
				return;
			}
			else
			{
				return;
			}
		}
	}
	/**
	 * This is a function to view an order 
	 */
	public void viewOrder()
	{
		int ind=0;
		int check = 0;
		Table t = new Table();
		System.out.println();
		if(tm.printUnpaidTable()){
			System.out.println();
			System.out.print("Table ID (-1 to go back): ");
			int tableID = customScanner.readOnlyIntegers(sc);
			
			do
			{
				int i = -1;
				for(Ordering or : orderList)
				{
					i++;
					if(or.getTableID() == (tableID))
					{
						ind = i; 
						Ordering retOrder = orderList.get(ind); 
						retOrder.printOrderInput();
						check++;
						return;
					}
					if(tableID == -1)
					{
						return;
					}
				}
				System.out.println("ERROR: Table ID Order does not exist!");
				System.out.print("Please re-enter choice: ");
				tableID = customScanner.readOnlyIntegers(sc);
				
			}while(check == 0 );
			
			return;
		}
		
	}
	
	
	/**
	 * To print the receipt after the customer makes the payment for their order 
	 */
	public void payPrintBill(){
		OrderHistory oh = new OrderHistory();
		if(!tm.printUnpaidTable())
			return;
		boolean Notsuccess = true;
		do{
			System.out.println("What is your table number?, Press -1 to back");
			int tableid = customScanner.readOnlyIntegers(sc);
			if(tableid==-1){
				return;
			}
			for(Ordering o:orderList){			
				if(o.getTableID()==tableid){
					o.setStatus("PAID");
					o.printOrderInvoice();
					oh.updateHistory(o);
					orderList.remove(o);
					Notsuccess=false;
					tm.freeTable(tableid);
					break;
				}
			}
			if(Notsuccess)
				System.out.println("Incorrect TableID please try again!, Press -1 to back");
		}while(Notsuccess);
	}
	/**
	 * This is a function to create a Alacarte item from the main menu class
	 */
	public void menu(){
		mainmenu.createMenu();
	}
	/**
	 * This is a function to create a promo from the main menu class
	 */
	public void promo(){
		mainmenu.createPromo();
	}
	
	/**
	 * This is a function is to create a reservation booking from the booking class
	 */	
	public void createResBooking() {
		Reservation r = new Reservation();
		r.createReservation();
		tm.setReservedTable();
	}
	/**
	 * This is a function is to check reservation (From reservation class)
	 * @return i returns the checkRes Method 
	 */
	public int checkRes()
	{		
		Reservation r = new Reservation();
		int i = r.checkRes();
		return i;
	}
	/**
	 * This is a function is to remove a reservation (From reservation class)
	 */
	public void removeRes()
	{
		Reservation r = new Reservation();
		r.removeRes();
	}
	/**
	 * This is a function is to check if the guests are late on arrival (From reservation class)
	 */
	public void lateArrival() {
		Reservation r = new Reservation();
		r.checkLateReservation();
		tm.setReservedTable();
	}
	
	//END RESERVATION
	/**
	 * This is a function is to end and remove the reservation 
	 * @return ts getting the current date 
	 */	
	public static Timestamp getcurrentDate(){
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}

}
