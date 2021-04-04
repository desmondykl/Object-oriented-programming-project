package Class;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

import Data.DataManager;

/**
 * This is a reservation class.
 * It represents the reservations for the restaurant. 
 * It can add/remove/check the reservations by calling the functions.
 * It implements serializable to allow us to serialize and deserialize to a file (ReservationList.dat)
 * 
 * @author Jess Tan Jing Yi
 * @version 1.0
 * @since 2019-04-12
 *
 */
public class Reservation implements Serializable, Comparable<Reservation> {
	/**
	 * The session of the reservation [AM/PM]
	 */
	private String session; //AM/PM
	/**
	 * The contact number of the customer that had made this particular reservation
	 */
	private int contactNo; 
	/**
	 * The total number of pax of the customer that had made this particular reservation
	 */
	private int pax; 
	/**
	 * The contact number of the customer that had made this particular reservation
	 */
	private String name; 
	/**
	 * The name of the customer that had made this particular reservation
	 */
	private int tableID;
	/**
	 * The date that this particular reservation was made 
	 */
	private Date date;
	/**
	 * Arrival time of the reservation in hours 
	 */
	private int hrs;
	/**
	 * Arrival time of the reservation in minutes 
	 */
	private int min;
	
	
	/**
	 * This is a constructor for Reservation
	 */
	public Reservation (){
		
	}
	/**
	 * This is a reservation constructor. It is called every time a new reservation is made.
	 * It will take in the following parameters:
	 * 
	 * @param session The session of the reservation [AM/PM]
	 * @param name The name of the customer that had made this particular reservation
	 * @param contactNo The contact number of the customer that had made this particular reservation
	 * @param pax The total number of pax of the customer that had made this particular reservation
	 * @param date The date that this particular reservation was made 
	 * @param tableID The tableID that is will be assigned to this particular reservation [Only assigned when the staff takes the order] 
	 * @param hrs Arrival time of the reservation in hours 
	 * @param min Arrival time of the reservation in minutes 
	 */
	public Reservation(String session, String name, int contactNo, int pax, Date date, int tableID, int hrs, int min) {
		this.session = session;
		this.name = name;
		this.contactNo = contactNo;
		this.pax = pax;
		this.date = date;
		this.tableID = tableID;
		this.hrs = hrs;
		this.min = min;
	}
	/**
	 * The session of the reservation [AM/PM]
	 * @return session The session of the reservation [AM/PM]
	 */
	public String getSession() {
		return session;
	}
	/**
	 * The session of the reservation [AM/PM]
	 * @param session Designated based on the time that reservation is made for. AM = 11am-3pm, PM = 6pm-10pm
	 */
	public void setSession(String session) {
		this.session = session;
	}
	/**
	 * 
	 * @return date The date that this particular reservation was made 
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Date that this reservation is made for, includes time
	 * @param date Includes the Date and Time of Reservation
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * The contact number of the customer that had made this particular reservation
	 * @return contactNo Contact The contact number of the customer that had made this particular reservation
	 */
	public int getContactNo() {
		return contactNo;
	}
	/**
	 * The contact number of the customer that had made this particular reservation
	 * @param contactNo It will only allow 8 digits
	 */
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * The total number of pax of the customer that had made this particular reservation
	 * @return pax The total number of pax of the customer that had made this particular reservation
	 */
	public int getPax() {
		return pax;
	}
	/**
	 * The total number of pax of the customer that had made this particular reservation
	 * @param pax User can key in any number of pax, but if it exceeds table size, they will not be assigned a table
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}
	/**
	 * The name of the customer that had made this particular reservation
	 * @return name The name of the customer that had made this particular reservation
	 */
	public String getName() {
		return name;
	}
	/**
	 * The name of the customer that had made this particular reservation
	 * @param name The name of the customer that had made this particular reservation
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * The tableID that is will be assigned to this particular reservation [Only assigned when the staff takes the order] 
	 * @return tableID tableID of this reservation (designated when reservation is made)
	 */
	public int getTableID() {
		return tableID;
	}
	/**
	 * TableID of this reservation (designated when reservation is made)
	 * @param tableID Assigned accordingly to availability of required table size, starting from the smallest size that can fit the number of pax
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	} 
	/**
	 * 
	 * @return hrs hour that this reservation is made for
	 */
	public int getHrs() {
		return hrs;
	}
	/**
	 *  Arrival time of the reservation in hours 
	 * @param hrs Used as a check if the customer has arrived late [more than 30mins] 
	 */
	public void setHrs(int hrs) {
		this.hrs = hrs;
	}
	/**
	 * Arrival time of the reservation in minutes 
	 * @return min Used as a check if the customer has arrived late [more than 30mins] 
	 */
	public int getMin() {
		return min;
	}
	/**
	 * Arrival time of the reservation in minutes 
	 * @param min Used as a check if the customer has arrived late [more than 30mins] 
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * Method 'createReservation()' is called every time staff wants to create a new reservation.
	 * Prompts for inputs such as date, time, contact number, name, pax. 
	 * Time is then converted into session of AM/PM, based on the hour that staff input.
	 * TableID is automatically assigned upon creation of reservation.
	 * Status of table will also be updated as 'Reserved'
	 * 
	 * 
	 * Before assigning tableID, it will check against the date and session to see if there are any available tables.
	 * If there are available tables, it will print the reservation and a confirmation message. 
	 * Else, it will print that there are no available tables for that date and session. 
	 * 
	 * Customers are also not allowed to make a reservation on the same day and same session. 
	 * If attempted to do so, an error message will be printed.
	 * 
	 */
	public void createReservation(){
		TableManager tm = new TableManager();
		ArrayList<Table> tableList=tm.tableList;
		Scanner sc = new Scanner(System.in);
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateformatter = new SimpleDateFormat("dd");
		DateFormat mthformatter = new SimpleDateFormat("MM");
		DateFormat yrformatter = new SimpleDateFormat("yyyy");
		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yy HH:mm");
		
		String session = null, name;
		int pax, contactNo, tableID;
		Timestamp curDate = Main.getcurrentDate();
		int minute = curDate.getMinutes();
		int time = curDate.getHours();
		int day = Integer.parseInt(dateformatter.format(curDate));
		int month = Integer.parseInt(mthformatter.format(curDate));
		int year = Integer.parseInt(yrformatter.format(curDate));
		
		int tableID_res = 0;		
		
		ArrayList<Reservation> list_res = null;
		
		//Read Reservation.dat, load into list_res
		if(DataManager.readSerializedObject("ReservationList.dat") == null || 
				DataManager.readSerializedObject("ReservationList.dat").isEmpty()) {
			list_res= new ArrayList<Reservation>();
		}
		else
			list_res = (ArrayList)DataManager.readSerializedObject("ReservationList.dat");
		
		String date = Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
		Date date2 =null;
		Date date1 = null;
		
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		boolean notsuccess = true;
		boolean noclash = true;
		
		do {
			do {
				//Scanning 
				System.out.println("Enter arrival time (dd/MM/yy HH:mm):");
				System.out.println("<Enter -1 to back>");
				date2 = customScanner.readDateTime(date2,sc,formatter2);
				
				if(date2 == null)
				{
					System.out.println("-1 entered, returning to main menu.");
					return;
				}
				
				noclash = true;
				
				long datediff =  date2.getTime()-date1.getTime();
				int diffDays = (int) (datediff / (24 * 60 * 60 * 1000));
				    
				int hrs = date2.getHours(); //user input
				int min = date2.getMinutes(); //user input
		
				if(hrs<11 || (hrs>15 && hrs<18) || (hrs>=22) )
				{
					System.out.println("We are not operating during this hours!");
					System.out.println();
				}
				else
				{
					if(diffDays >= 0 && diffDays <= 31) //if within 1 month
					{
						if(diffDays == 0)//if same day 
						{
					    	if(hrs < time) //if hour has passed
					    	{
					    		System.out.println("Invalid time, it has already passed!");								    	
					    		System.out.println();
					    	}
					    	else if(hrs == time) //if within the same hour
					    	{
					    		if(min <= minute) //if minute has passed, min = input, minute = current
							    {
					    			System.out.println("Invalid time, it has already passed!");		
					    			
					    			System.out.println();
							    }
					    		else //if minute haven't passed
					    		{
					    			notsuccess = false;
					    		}
					    	}
					    	else //hour has not passed
					    	{
					    		notsuccess = false;
					    	}
					    }
						else //different day
						{
							notsuccess=false;
						}
					}
					else //not within 1 month
					{
						if(diffDays < 0)
						{
							System.out.println("Invalid date, it has already passed!");
						    System.out.println();
						}
						else
						{
							System.out.println("You only can book up to 1 month in advance!");
							System.out.println();
						}
				    }		
				}
			}while(notsuccess);
			
		System.out.println("<Enter -1 to back>");		

		System.out.println("Please enter your contact number (8 digits): ");
		contactNo = customScanner.readOnlyIntegers(sc);
		
		int length_contactno = String.valueOf(contactNo).length();
	
		while(length_contactno != 8)
		{
			if(contactNo == -1)
			{
				System.out.println("-1 entered, returning to main menu.");
				return;
			}
			
			System.out.println("Please re-enter 8 digits:");
			contactNo = customScanner.readOnlyIntegers(sc);
			length_contactno = String.valueOf(contactNo).length();
		}
					
		System.out.println("Please enter number of pax (numbers only): ");
		pax = customScanner.readOnlyIntegers(sc);
		
		while(pax < 2 || pax > 10)
		{
			if(pax == -1)
			{
				System.out.println("-1 entered, returning to main menu.");
				return;
			}
			else if(pax < 2)
			{
				System.out.print("Minimum 2 pax! ");
			}
			else if (pax > 10)
			{
				System.out.print("Maximum 10 pax! ");
			}
			
			System.out.println("Please enter again:");
			pax = customScanner.readOnlyIntegers(sc);
		}
			
		System.out.println("Please enter your name: ");
		name = sc.nextLine();
		
		while(name.equals(""))
		{
			name = sc.nextLine();
		}
		
		if(name.equals("-1"))
		{
			System.out.println("-1 entered, returning to main menu.");
			return;
		}
		
		int hrs = date2.getHours(); //user input
		int times = date2.getMinutes(); //user input
		
		if(hrs <= 15)// time >= 11 && -remove
		{
			session = "AM";
		}
		else if(hrs >= 18)// && time <= 22
		{
			session = "PM";
		}
		
		for(Reservation r2:list_res)
		{
			if(contactNo == r2.getContactNo() && session.equals(r2.getSession()) && 
					  r2.getDate().getDay()==date2.getDay() &&
 					  r2.getDate().getMonth()==date2.getMonth() &&
 					  r2.getDate().getYear()==date2.getYear())
			{
				System.out.println("Limited to 1 reservation per session on the same day, same session!");
				System.out.println("Please enter another reservation!");
				System.out.println();
				
				noclash = false;
				//sc.nextLine();
			}
		}
		}while(noclash == false);
		
		ArrayList<Table> newtable = (ArrayList) DataManager.readSerializedObject("Table.dat");
				
		//Check if fully booked 
		for(Reservation r:list_res) 
		{					
			if(r.getSession().equals(session) && r.getDate().getDay()==date2.getDay() &&
					   					  r.getDate().getMonth()==date2.getMonth() &&
					   					  r.getDate().getYear()==date2.getYear())
			{
				//user's input date is same as the date in reservation list
				//to check for existing reservation to know if it's fully booked
				//same session
					
				tableID = r.getTableID(); //retrieve tableID from RSVP list
				for(Table t1: newtable) //loop through the whole RSVP list
				{
					if(t1.getTableID() == tableID) //if got RSVP
					{
						t1.setStatus("Reserved"); //if booked, set reserved for that specific day
						break;
					}
				}
			}
		}
			
		//Check based on got table for that table size
		ArrayList<Table> tablex = tm.getAvailableTables(pax,newtable);
		 		
		//tablex will only contain unoccupied tables which FITS the pax size requirement
		if(!tablex.isEmpty()) //if there are available tables 
		{
			
			int hrs = date2.getHours(); //user input
			int times = date2.getMinutes(); //user input
			//int months = date2.getMonth(); //user input
			//int days = date2.getDay();
			
			System.out.println("+--------------------------------------+");
			System.out.println("|Reservation info                      |");
			System.out.println("+-----------------------+--------------+");
			System.out.format("%-35s|\n", "|Reservation Date & Time|" + formatter2.format(date2)+"");
			System.out.format("%-39s|\n", "|Reservation Session    |" + session+"");
			System.out.format("%-39s|\n", "|Reservation Name       |" + name+"");
			System.out.format("%-39s|\n", "|Reservation Pax:       |" + pax+"");
			System.out.println("+-----------------------+--------------+");
			
			tableID_res = tablex.get(tablex.size()-1).getTableID();		 		
			 		
			list_res.add(new Reservation(session, name, contactNo, pax, date2, tableID_res, hrs, times));
			Collections.sort(list_res);

			//Write back into Reservation.dat with updated ArrayList
			DataManager.writeSerializedObject("ReservationList.dat", list_res);
			System.out.println("Reservation completed!");
			
			//String em;
			//System.out.println("Do you want a confirmation email?");
			//Scanner sc = new Scanner();
//			if(sc == 'Y' || sc=='y')
//			{
			//System.out.println("Could you please input your email?");
			//em = sc.nextln();
//			//sendEmail(em) 
//			}
		}
		else 
		{
			System.out.println("Sorry, fully reserved for "+formatter.format(date2) +"'s " + session + " session!");
		}
	}
	
	/**
	 * Checks for late reservation and remove accordingly
	 * If customer has not arrived after 30 minutes from the date and time of their reservation (i.e. their reserved table is not made)
	 * Their reservation will automatically be removed from the reservation list
	 * Status of the table for that date and time will be back to 'Unoccupied'
	 */
	public void checkLateReservation() {
		Timestamp curDate = Main.getcurrentDate();
		int time = curDate.getHours();
		String session = "";
		if(time <= 15)// time >= 11 && -remove
		{
			session = "AM";
		}
		else if(time >= 18)// && time <= 22
		{
			session = "PM";
		}
		
		ArrayList<Reservation> list_res = null;
		if(DataManager.readSerializedObject("ReservationList.dat") == null || 
				DataManager.readSerializedObject("ReservationList.dat").isEmpty()) {
			list_res= new ArrayList<Reservation>();
		}
		else
			list_res = (ArrayList)DataManager.readSerializedObject("ReservationList.dat");
	    
		Iterator<Reservation> iterator = list_res.iterator();
		while(iterator.hasNext()){
			Reservation r = iterator.next();
			if(r.getSession().equals(session) && r.getDate().getDay()==curDate.getDay() &&
 					  r.getDate().getMonth()==curDate.getMonth() &&
 					  r.getDate().getYear()==curDate.getYear()){
				double rhours = r.getDate().getHours()*60;
				double rmm = r.getDate().getMinutes();
				double rarrival = (rhours+rmm);
				
				double chours = curDate.getHours()*60;
				double cmm = curDate.getMinutes();
				double carrival = (chours+cmm);
				if((rarrival-carrival)<-30){
					iterator.remove();
				}
			}
		}
		DataManager.writeSerializedObject("ReservationList.dat", list_res);
	}
	
	/**
	 * Checks if a reservation exists or not. 
	 * If it exists, print out the reservation details. 
	 * Else, print error message.
	 * 
	 * Reservations are identified through contact number, thus staff has to input contact number to check if reservation exists.
	 * If there are multiple reservations under 1 contact number, it will show all reservations starting from the upcoming one to the furthest one.
	 * 
	 * If a reservation will occur on the current day, it will print that they have a reservation today.
	 * Else, they will print that they do not have a reservation today.
	 * 
	 * As this method is also called when making an order/starting a table, it returns the tableID if there is a reservation.
	 * If there is no reservation, it will return -1. 
	 * 
	 * @return tableidToreturn Returns a tableID if there is a reservation. Returns (-1) if there are no reservations. 
	 */
	public int checkRes() //creating table is using this to check if there is existing rsvp for the contact number. returns tableID if exists
	{		
		
		Scanner sc =new Scanner(System.in);
		System.out.println("To check/view reservation details, please enter your contact number (8 digits): ");
		System.out.println("<Enter -1 to back>");

		//Prompt for contactNo, integers only
		int contactNo = customScanner.readOnlyIntegers(sc);
		int length_contactno = String.valueOf(contactNo).length();

		if(contactNo == -1)
		{
			System.out.println("-1 entered, returning to main menu.");
			return -1;
		}

		
		while(length_contactno != 8)
		{
			if(contactNo == -1)
			{
				System.out.println("-1 entered, returning to main menu.");
				return -1;
			}
			
			System.out.println("Please re-enter 8 digits:");
			contactNo = customScanner.readOnlyIntegers(sc);
			length_contactno = String.valueOf(contactNo).length();
		}
		
		int Notsuccess = -1;	
		int tableidToreturn= -1;
		boolean today = false;
		
		String session = null;
		Timestamp curDate = Main.getcurrentDate();
		
		int hours = curDate.getHours();
		
		if(hours <= 15)// time >= 11 && -remove
		{
			session = "AM";
		}
		else if(hours >= 18)// && time <= 22
		{
			session = "PM";
		}
		
		//Read Reservation.dat into lis_res
		ArrayList<Reservation> list_res = (ArrayList)DataManager.readSerializedObject("ReservationList.dat");

		ArrayList<Reservation> printList = new ArrayList<Reservation>();
		for(Reservation res:list_res) //store content we are looking through (list_res) into res (object)
		{
			//do checking for date & session here!! 
			//if match, then take that tableID
			//meanwhile, print everything else!!
			
			//Put current contactNo of list's row, into contactNo2
			int contactNo2 = res.getContactNo();
				
			//Check if user input contactNo == contactNo in Reservation.dat
			if(contactNo == contactNo2)
			{
				printList.add(res);
				Notsuccess = 2;
			
				//today reservation
				if(res.getDate().getDay()==curDate.getDay() &&
						  res.getDate().getMonth()==curDate.getMonth() &&
						  res.getDate().getYear()==curDate.getYear())
				{
					if(res.getSession() == "AM" || res.getSession().equals("AM"))
					{
						if(session == "PM" || session.equals("PM"))
						{
							today = true;
						}
					}
					else if(res.getSession() == "PM" || res.getSession().equals("PM"))
					{
						if(session == "PM" || session.equals("PM"))
						{
							today = true;
						}
					}
				}
				
				//today reservation + this session
				if(res.getSession().equals(session) && 
						res.getDate().getDay()==curDate.getDay() &&
						  res.getDate().getMonth()==curDate.getMonth() &&
						  res.getDate().getYear()==curDate.getYear())
				{
					//if found, return tableID
					tableidToreturn = res.getTableID();
				}
			}
		}
		printReservation(printList);
		//If cannot find
		if(Notsuccess == -1)
		{
			System.out.println("Reservation does not exist!");		
			return -1;
		}
		
		//if rsvp exist
		if(Notsuccess != -1)
		{
			if(tableidToreturn == -1)
			{
				System.out.println("Your reservation is not today!");
				return -1;
			}
			else if(today == true)
			{
				System.out.println("Your reservation is today!");
			}
		}

		//if not found, return -1
		//if found, return 2
		return tableidToreturn;
	}
	
	/**
	 * Removes a reservation.
	 * 
	 * Staff will be prompted to input the contact number to check if the reservation exists.
	 * 
	 * If the reservation exists, they will be shown all upcoming reservations made under this contact number.
	 * Staff will input the index shown to select which reservation to remove. 
	 * Staff will then be prompted to confirm to remove or not.
	 * After removed, status of the table for that date and time will be back to 'Unoccupied'
	 * 
	 * If the reservation does not exist, it will print an error message ["Reservation does not exist!"]
	 * 
	 */
	public void removeRes()
	{
		Scanner sc =new Scanner(System.in);
		//Read Reservation.db into lis_res
		ArrayList<Reservation> list_res = (ArrayList)DataManager.readSerializedObject("ReservationList.dat");

		//-1 == not found, anything else = found
		//int reserve = checkRes();	//tableID
		System.out.println("To remove reservation, please enter your contact number (8 digits): ");
		System.out.println("<Enter -1 to back>");
		
		int reserve = customScanner.readOnlyIntegers(sc);
		int length_contactno = String.valueOf(reserve).length();
		
		if(reserve==-1)
		{
			System.out.println("-1 entered, returning to main menu.");
			return;
		}
		
		while(length_contactno != 8)
		{
			if(reserve == -1)
			{
				System.out.println("-1 entered, returning to main menu.");
				return;
			}
			
			System.out.println("Please re-enter 8 digits:");
			reserve = customScanner.readOnlyIntegers(sc);
			length_contactno = String.valueOf(reserve).length();
		}
				
		//a new method to retrieve reservation list based on contact no
		ArrayList<Reservation> temp_res=getReservationList(reserve);
		//if found
		if(!temp_res.isEmpty())
		{	
			printReservation(temp_res);
			boolean chooseError = true;
			do{
				System.out.println("Select the reservation to remove:");
				System.out.println("<Enter -1 to back>");
				reserve = customScanner.readOnlyIntegers(sc);
				if(reserve==-1){
					System.out.println("-1 entered, returning to main menu.");
					return;
				}
				if(reserve>temp_res.size() && reserve<1){
					System.out.println("ERROR: Invalid input");
					System.out.println("Please choose the correct reservation!");
				}
				else
					chooseError=false;
			}
			while(chooseError);
			
			System.out.println();
			System.out.println("+-------------------------------+");
			System.out.println("|Confirm to remove reservation? |");
			System.out.println("+----------+--------------------+");
			System.out.format("%-32s|\n", "|1: Yes              ");
			System.out.format("%-32s|\n", "|2: No               ");
			System.out.println("+----------+--------------------+");

			int answer = customScanner.readOnlyIntegers(sc);
			
			do{
				if(answer == 1){ //want to remove
					Reservation r = temp_res.get(reserve-1);
					for(Reservation res:list_res){ //loop through rsvplist
						//If it is the one we want to remove
						if(r.getContactNo()==res.getContactNo() &&
							r.getDate().equals(res.getDate())&&
							r.getHrs()==res.getHrs()&&
							r.getMin()==res.getMin()&&
							r.getPax()==res.getPax() &&
							r.getTableID()==res.getTableID() &&
							r.getName().equals(res.getName()) &&
							r.getSession().equals(res.getSession()))
						{
							//remove
							list_res.remove(res); //remove at the specified index
							System.out.println("Reservation has been removed.");
							break;
						}	
					}
				}
				else if(answer == 2)
				{
					System.out.println("Reservation has not been removed.");
				}
				else
				{
					System.out.println("Please input either 1 or 2:");
					answer = customScanner.readOnlyIntegers(sc);
				}
			}while(answer != 1 && answer != 2);
			DataManager.writeSerializedObject("ReservationList.dat", list_res);
			TableManager tm = new TableManager();
			tm.setReservedTable();
		}
		else
		{
			System.out.println("Reservation does not exist!");
		}
	}
	
	/**
	 * Gets reservation list based on contact number
	 * 
	 * @param contactNo contact number of the customer 
	 * @return ReservationList Array List of Reservations
	 */
	public ArrayList<Reservation> getReservationList(int contactNo){
		ArrayList<Reservation> list_res = (ArrayList)DataManager.readSerializedObject("ReservationList.dat");
		ArrayList<Reservation> returnList = new ArrayList<Reservation>();
		if(list_res==null || list_res.isEmpty()){
			return returnList;
		}
		for(Reservation r :list_res){
			if(r.getContactNo()==contactNo)
				returnList.add(r);
		}
		return returnList;
	}
	
	/**
	 * Prints reservations with indexing 
	 * 	 * 
	 * @param list_res arraylist of reservations
	 */	
	public void printReservation(ArrayList<Reservation> list_res){
		int i=1;
		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yy HH:mm");

		for(Reservation res : list_res){
			System.out.println("+--------------------------------------+");
			System.out.println("|Reservation info("+i+")                   |");
			System.out.println("+-----------------------+--------------+");
			System.out.format("%-35s|\n", "|Reservation Date & Time|" + formatter2.format(res.getDate())+"");
			System.out.format("%-39s|\n", "|Reservation Session    |" + res.getSession()+"");
			System.out.format("%-39s|\n", "|Reservation Name       |" + res.getName()+"");
			System.out.format("%-39s|\n", "|Reservation Pax:       |" + res.getPax()+"");
			System.out.format("%-39s|\n", "|Reservation Table:     |" + res.getTableID() +"");
			System.out.println("+-----------------------+--------------+");
			i++;
		}
		
	}
	/**
	 * EXTRA FEATURE 1
	 * The program will send an email to the particular customer after he has made a reservation. 
	 * This function is executed by calling another function called sendEmail, which will be in the Reservation class. 
	 * This function will contain  an API that will send an email to the particular customer once they have made a reservation
	 * @param to The customer email the reservation will be sent to 
	 */	
	public void sendEmail(String to)
	{
//		String host = "relay.jangosmtp.net";
//
//	    Properties props = new Properties();
//	      props.put("mail.smtp.auth", "true");
//	      props.put("mail.smtp.starttls.enable", "true");
//	      props.put("mail.smtp.host", host);
//	      props.put("mail.smtp.port", "25");
//	    
//	   // Create a default MimeMessage object.
//		   Message message = new MimeMessage(session);
//		
//		   // Set From: header field of the header.
//		   message.setFrom(new InternetAddress(from));
//		
//		   // Set To: header field of the header.
//		   message.setRecipients(Message.RecipientType.TO,
//	               InternetAddress.parse(to));
//		
//		   // Set Subject: header field
//		   message.setSubject("Testing Subject");
//		
//		   // Now set the actual message
//		   message.setText("Hello, this is sample for to check send " +
//			"email using JavaMailAPI ");
//
//		   // Send message
//		   Transport.send(message);
//
//		   System.out.println("Sent message successfully....");
	      
	}
	
	
	/**
	 * Overwrites the compateTo function in Comparable class
	 * This function will sort Array List of reservations in ascending order of the dates 
	 * @param r This is a Reservation Object 
	 * @return int It will compare the reservation date. Return -1 if 'r.getDate' is larger than 'this.getDate' else return 1.  
	 */
	@Override
	public int compareTo(Reservation r) {
		return (r.getDate().getTime() > this.getDate().getTime() ? -1 : 1);     //ascending
	}
}
