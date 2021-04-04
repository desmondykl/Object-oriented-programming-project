package Class;


import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import wagu.Block;
import wagu.Board;
import wagu.Table;
import java.util.*; 

import Data.DataManager;

/**
 * This is the Ordering class
 * Any functions related to ordering at the restaurant (Adding, updating and removing an order) will happen here
 * It implements serializable to allow us to serialize and deserialize to a file (After oder is paid for)
 * It also implements the Generate Id interface to be able to generate a random id for the integer 
 * 
 * @author Muhammad Hisyam bin Jukifli and Munirah Mohd
 * @version 9.0
 * @since 2019/04/01
 */

public class Ordering implements Serializable, GenerateId {
	/**
	 * An identification for the orders
	 */
	private int orderID; 
	
	/**
	 * An identification for the table for this order
	 */
	private int tableID; 
	
	/**
	 * An identification for the employee who created this order
	 */
	private int employeeID;
	
	/**
	 * A created menu for this order
	 */
	private MainMenu menuList;
	
	/**
	 * The status of this order
	 */
	private String status; 
	
	/**
	 * The date this order was created
	 */
	private Date date; 
	
	/**
	 * The total price for this order
	 */
	private double totalPrice;
	
	/**
	 * A token to check if this order has already been added to orderList
	 */
	private boolean token;
	
	/**
	 * Creates a new Ordering item with the following parameters.
	 * @param orderID, This ordering's Order ID
	 * @param tableID, This ordering's Table ID
	 * @param employeeID, This orderings's Staff ID
	 * @param menuList, This ordering's selected menu
	 * @param status, This orderings's order status
	 * @param date, This ordering's order date
	 * @param totalPrice, This ordering's price
	 * @param token, This ordering's token
	 */
	public Ordering(int orderID, int tableID, int employeeID,
			MainMenu menuList, String status, Date date, double totalPrice, boolean token) {
		super();
		this.orderID = orderID;
		this.tableID = tableID;
		this.employeeID = employeeID;
		this.menuList = menuList;
		this.status = status;
		this.date = date;
		this.totalPrice = totalPrice;
		this.token = false;
		// It will be false at first
	}
	
	/**
	 * Gets the token of the order
	 * @return token (this Ordering's token)
	 */
	public boolean getToken()
	{
		return token;
	}
	
	/**
	 * Changes the token status of this order.
	 * @param tk (This order's token)
	 */
	public void setToken(boolean tk)
	{
		this.token = tk;
	}
	
	/**
	 * This is a default constructor 
	 * Creates an empty new order object 
	 */
	public Ordering() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the order's identification
	 * @return orderID (This Ordering's orderID)
	 */
	public int getOrderID() {
		return orderID;
	}
	
	/**
	 * Set/Change the order identification of order
	 * @param orderID (The new Ordering's orderID)
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	/**
	 * Gets the table identification that was assigned to this order
	 * @return tableID (This Ordering's tableID)
	 */
	public int getTableID() {
		return tableID;
	}
	
	/**
	 * Set/Change the table Identification that was assigned to this order
	 * @param tableID (The new Ordering's tableID)
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	
	/**
	 * Gets the employee identification that created the order
	 * @return employeeID (This Ordering's employeeID)
	 */
	public int getEmployeeID() {
		return employeeID;
	}
	
	/**
	 * Set/Change the employee Identification on the order
	 * @param employeeID (The new Ordering's employeeID)
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	/**
	 * Gets the main MenuList of the order
	 * @return menuList (This Ordering's MenuList)
	 */
	public MainMenu getMenuList() {
		return menuList;
	}
	
	/** 
	 * Set/Change the MenuList that was assigned to the order
	 * @param menuList (The new Ordering's MenuList)
	 */
	public void setMenuList(MainMenu menuList) {
		this.menuList = menuList;
	}
	
	/**
	 * Gets the status of the order
	 * @return status (This Ordering's status)
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Set/Change the status of the order
	 * @param status (The new Ordering's status)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the date the order was created
	 * @return getDate (This Ordering's creation date)
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set/Change the date that the order was created
	 * @param date (The new Ordering's creation date)
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the total price of the order
	 * @return totalPrice  (This Ordering's totalprice)
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Set/Change the total price of the order
	 * @param totalPrice (The new Ordering's totalprice)
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * Prints the order summary of the order (Includes - Staff ID, OrderID, Status, Date and Cost) 
	 */
	public void printOrderInput()
		{
			//desmond
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-15s %-15s %-15s %-30s %-3s", "STAFF ID", "ORDER ID", "STATUS", "DATE", "COST");
		    System.out.println();
		    System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		    
	    
	    	//This is a function that return the Alacare item from the menulsit or the main menu arraylist form order object
	        System.out.format("%-15d %-15d %-15s %-30s %.2f", this.getEmployeeID(), this.getOrderID(), this.getStatus(), this.getDate(), this.getTotalPrice());
	        System.out.println();
		    System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		
		    // If there is a menu order
		    if(this.getMenuList().getMenuitem() != null || !(this.getMenuList().getMenuitem().isEmpty()))
		    {
		    	this.getMenuList().printListMenuItem();
		    }
		    
		    // If there is a promo order
		    if(this.getMenuList().getPromoitem() != null || !(this.getMenuList().getPromoitem().isEmpty()))
		    {
		    	this.getMenuList().printpromoItem();
		    }
		}
	
	
	/**
	 * Prints the receipt of this order
	 * Includes the full order details in a receipt form 
	 * Makes use of the wagu library and classes 
	 */
	public void printOrderInvoice(){
		

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String company = ""
                + "IGNITE STEAKHOUSE(PVT) LTD\n"
                + "NO 20/B, Main Street, Kandy, Sri Lanka.\n"
                + "Land: 812254630 Mob: 712205220 Fax: 812254639\n"
                + " \n"
                + "CUSTOMER INVOICE\n"
                + " \n";
		List<String> t1Headers = Arrays.asList("INFO", "TABLE");
        List<List<String>> t1Rows = Arrays.asList(
                Arrays.asList("DATE: "+formatter.format(date), "ORDER ID: "+orderID),
                Arrays.asList("TIME: "+date.getHours()+":"+date.getMinutes(), "TABLE NO: "+tableID)
        );
        String t2Desc = "ORDER DETAILS";
        
        List<Integer> t2ColWidths = Arrays.asList(20, 9, 5, 12);
        List<String> t2Headers = Arrays.asList("ALACARTE ITEM", "PRICE($)", "QTY", "VALUE");
        List<List<String>> t2Rows = menuList.GetStringArrayF(3, 0);
        
        //List<List<String>> t3Rows = menuList.GetStringArrayF(4, 0);

        String summary = ""
                + "SUBTOTAL\n"
                + "SERVICE CHARGE\n"
                + "GST\n"
                + "TOTAL PRICE\n";

        String summaryVal = ""
        		+ String.format("%.2f", (totalPrice/1.177))+"\n"
        		+ String.format("%.2f", ((totalPrice/1.177)*0.1))+"\n"
        		+ String.format("%.2f", (((totalPrice/1.177)*1.1)*0.07))+"\n"
        		+ String.format("%.2f", (totalPrice))+"\n";
                
        String sign1 = ""
                + "---------------------\n"
                + "CASH COLLECTOR\n";
        String sign2 = ""
                + "---------------------\n"
                + "GOODS RECEIVED BY\n";
        Board b = new Board(55);
        b.setInitialBlock(new Block(b, 53, 7, company).allowGrid(false).setBlockAlign(Block.BLOCK_CENTRE).setDataAlign(Block.DATA_CENTER));
        b.appendTableTo(0, Board.APPEND_BELOW, new Table(b, 51, t1Headers, t1Rows));
        b.getBlock(3).setBelowBlock(new Block(b, 49, 1, t2Desc).setDataAlign(Block.DATA_CENTER));
        if(!t2Rows.isEmpty()){
        	b.appendTableTo(5, Board.APPEND_BELOW, new Table(b, 55, t2Headers, t2Rows, t2ColWidths));
        }
        
        
        
        List<List<String>> datapromo= new ArrayList<List<String>>();
        ArrayList<Integer> uID = new ArrayList<Integer>();	
		for(Promotion p: menuList.getPromoitem()){
        	boolean u = true;
        	for(Integer i :uID){
        		if(p.getPromoID()==i)
        			u=false;
        	}
        	if(u)
        		uID.add(p.getPromoID());
        }
		int k=0;
		for(Integer i :uID){
			String str[] = new String[4];
			double totalprice = 0;
        	int qty = 0;
        	for(Promotion p :menuList.getPromoitem()){
        		if(p.getPromoID()==i){
          	        str[0]=p.getName();
          	        str[1]=String.valueOf(p.getPrice());
          	        qty++;
          	        totalprice +=  p.getPrice();
        		}
           }
        	str[2]=String.valueOf(qty);
        	str[3]=String.valueOf(totalprice);
        	datapromo.add(Arrays.asList(str));
        	
        	List<List<String>>  data = menuList.GetStringArrayF(4,i);
        	for(List<String> d : data){
        		datapromo.add(d);
        	}
       
        	k++;
        	
		}
    	List<String> t3Headers = Arrays.asList("PROMO ITEM", "PRICE($)", "QTY", "VALUE");
    	List<Integer> colAlignsList = Arrays.asList(Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT);
    	if(!t2Rows.isEmpty()){
    		if(!datapromo.isEmpty()){
    			b.appendTableTo(10, Board.APPEND_BELOW, new Table(b, 55, t3Headers, datapromo, t2ColWidths).setColAlignsList(colAlignsList));
        		//sumaryblock
        		Block summaryBlock = new Block(b, 39, 9, summary).allowGrid(false).setDataAlign(Block.DATA_MIDDLE_RIGHT);
                b.getBlock(18).setBelowBlock(summaryBlock);
                Block summaryValBlock = new Block(b, 12, 9, summaryVal).allowGrid(false).setDataAlign(Block.DATA_MIDDLE_RIGHT);
                summaryBlock.setRightBlock(summaryValBlock);
                Block sign1Block = new Block(b, 26, 7, sign1).setDataAlign(Block.DATA_BOTTOM_MIDDLE).allowGrid(false);
                summaryBlock.setBelowBlock(sign1Block);
                sign1Block.setRightBlock(new Block(b, 26, 7, sign2).setDataAlign(Block.DATA_BOTTOM_MIDDLE).allowGrid(false));	
    		}
    		else{
    			Block summaryBlock = new Block(b, 39, 9, summary).allowGrid(false).setDataAlign(Block.DATA_MIDDLE_RIGHT);
                b.getBlock(10).setBelowBlock(summaryBlock);
                Block summaryValBlock = new Block(b, 12, 9, summaryVal).allowGrid(false).setDataAlign(Block.DATA_MIDDLE_RIGHT);
                summaryBlock.setRightBlock(summaryValBlock);
                Block sign1Block = new Block(b, 26, 7, sign1).setDataAlign(Block.DATA_BOTTOM_MIDDLE).allowGrid(false);
                summaryBlock.setBelowBlock(sign1Block);
                sign1Block.setRightBlock(new Block(b, 26, 7, sign2).setDataAlign(Block.DATA_BOTTOM_MIDDLE).allowGrid(false));	
    		}
    	}
    	else{
    		
    		b.appendTableTo(5, Board.APPEND_BELOW, new Table(b, 55, t3Headers, datapromo, t2ColWidths).setColAlignsList(colAlignsList));
    		Block summaryBlock = new Block(b, 39, 9, summary).allowGrid(false).setDataAlign(Block.DATA_MIDDLE_RIGHT);
            b.getBlock(10).setBelowBlock(summaryBlock);
            Block summaryValBlock = new Block(b, 12, 9, summaryVal).allowGrid(false).setDataAlign(Block.DATA_MIDDLE_RIGHT);
            summaryBlock.setRightBlock(summaryValBlock);
            Block sign1Block = new Block(b, 26, 7, sign1).setDataAlign(Block.DATA_BOTTOM_MIDDLE).allowGrid(false);
            summaryBlock.setBelowBlock(sign1Block);
            sign1Block.setRightBlock(new Block(b, 26, 7, sign2).setDataAlign(Block.DATA_BOTTOM_MIDDLE).allowGrid(false));
    	
    	}
        
        //b.getBlock(10).setBelowBlock(new Block(b, 46, 1, t3Desc).setDataAlign(Block.DATA_CENTER));
        //b.appendTableTo(14, Board.APPEND_BELOW, new Table(b, 48, t3Headers, t3Rows, t2ColWidths));
        //sign1Block.setBelowBlock(new Block(b, 48, 3, advertise).setDataAlign(Block.DATA_CENTER).allowGrid(false));
        //b.showBlockIndex(true);
        System.out.println(b.getPreview());
       
	}

	/**
	 * Creates a clone (exact copy) of this order
	 * @param o (This Ordering's object)
	 * @return o (The new exact copy of the Ordering's order attributes)
	 */
	public static Ordering cloning(Ordering o) {
	     o.setOrderID(o.getOrderID());
	     o.setTableID(o.getTableID());
	     o.setEmployeeID(o.getEmployeeID());
	     o.setMenuList(o.getMenuList());
	     o.setStatus(o.getStatus());
	     o.setDate(o.getDate());
	     o.setTotalPrice(o.getTotalPrice());
	     o.setToken(o.getToken());
	     return o;
	}
	
	/**
	 * Creates an order with the following parameters being set
	 * @param mainmenu (The customer's selection of food choices)
	 * @param currDate (The creation date and time of the order )
	 * @param Tableid (The table ID that the order was assigned to)
	 * @param Staffid (The employee that created the order)
	 */
	public void createOrder(MainMenu mainmenu, Timestamp currDate,int Tableid, int Staffid)
	{
		this.tableID=(Tableid);
		this.employeeID=(Staffid);
		menuSelection(mainmenu,currDate);
	}
	
	/**
	 * A Menu Selection that allows user to choose the food Items into their order
	 * @param mainmenu (A MainMenu object to contain the customers food selection)
	 * @param currDate (The creation date and time of the order )
	 */
	public void menuSelection(MainMenu mainmenu,Timestamp currDate)
	{	
		Scanner sc = new Scanner(System.in);
		ArrayList<AlaCarteItem> m = new ArrayList<AlaCarteItem>(); 
		ArrayList<Promotion> p = new ArrayList<Promotion>();
		
		int ch = 0;
		do {
			System.out.println();
			System.out.println("+----------------------+");
			System.out.println("|    MENU SELECTION:   |");
			System.out.println("+----------------------+");
			System.out.println("|1. Menu Items         |");
			System.out.println("|2. Promotional Items  |");
			System.out.println("|3. Done               |");
			System.out.println("|4. Exit               |");
			System.out.println("+----------------------+");
			// New changes
			System.out.print("Choice: ");
			ch = customScanner.readOnlyIntegers(sc);
			System.out.println();
			
			int id;
			if(ch == 1)
			{
				if(mainmenu.getMenuitem()!=null && !mainmenu.getMenuitem().isEmpty()){
                    // FInd the function to print the Menu Item
                    mainmenu.printListMenuItem();
                    System.out.print("Item Id: ");
                    id = customScanner.onlyMenuItemId(sc, mainmenu);
                    if(id==-1) return ;
                    // If the order is already create
                    if(this.token == true)
                    {
                        this.menuList.getMenuitem().add(mainmenu.getAlacartObject(id));
                        m=this.menuList.getMenuitem();
                    }
                    else
                    {
                        m.add(mainmenu.getAlacartObject(id));
                    }
                }
                else{
                    System.out.println("ERROR: No menu item!");
                    System.out.println("Please add menu item before proceeding to order");
                    return;
                }
			}
			else if( ch == 2)
			{
				if(mainmenu.getMenuitem()==null || mainmenu.getMenuitem().isEmpty()){
                    System.out.println("ERROR: No menu item!");
                    System.out.println("Please add menu item before proceeding to order");
                    return;
                }
				
				if(mainmenu.getPromoitem()!=null && !mainmenu.getPromoitem().isEmpty()){
                    // Find the function to print the Promo Item
                    mainmenu.printpromoItem();
                    System.out.print("Item Id: ");
                    id = customScanner.onlyPromoItemId(sc, mainmenu);
                    if(id==-1) return ;
                    if(this.token == true)
                    {
                        this.menuList.getPromoitem().add(mainmenu.getPromoObject(id));
                        p=this.menuList.getPromoitem();
                    }
                    else
                    {
                        p.add(mainmenu.getPromoObject(id));
                    }
                }
                else
                    System.out.print("No promotion item available!");
			}
			else if(ch == 3) // Confirmation
			{ 
				//Change by desmond
				//Should be correct?
				if(m.isEmpty() && p.isEmpty()){
					System.out.println("ERROR: You have not selected any item to order");
					System.out.println("Please select a item!");
					ch=4;
				}
				else{
					double cost = 0;
					
					int rand = 0;
					int check = 0;
					
					do
					{
						 rand = genRandomInt();
						 check = checkDuplicateIDs("OrderList", rand) + checkDuplicateIDs("OrderList", rand);
						 
					} while(check != 0);
					
					this.setOrderID(rand);
					
					if(this.token == false)
					{
						MainMenu mm = new MainMenu();
						mm.setMenuitem(m);
						mm.setPromoitem(p);
					
						// New changes
						this.menuList=mm;
						this.status="UNPAID";
						this.date=currDate;
					}
					
					if(!(menuList.getMenuitem().isEmpty()))
					{
						for(AlaCarteItem al : menuList.getMenuitem())
						{
							cost += al.getPrice();
						}
					}
					if(!(menuList.getPromoitem().isEmpty()))
					{
						for(Promotion pm : menuList.getPromoitem())
						{
							cost += pm.getPrice();
						}
					}
					this.totalPrice=(cost);
					this.printOrderInput();
					return ;
				}
				
			}
			else if(ch == 4)
			{
				break;
			}
			else // Inputting the wrong input
				
			{
				System.out.println("Please re-enter your choice:");
				System.out.println();
			}
		} while(ch < 3 || ch > 0);
	}
	
	/**
	 * A selection of choices that allows the user to either add, remove an item or removing the whole order
	 * @param mainmenu (The customer's current food selection)
	 * @param orderList (The array that contain all unpaid orders)
	 * @param ind (The index of the Table ID in the orderList array)
	 * @param ch (The choice made in the Main Menu Selection)
	 * @return (The updated Ordering's order object)
	 */
	public ArrayList<Ordering> addRemoveOrder(MainMenu mainmenu,ArrayList<Ordering> orderList,int ind,int ch){
		// Start the menu
		Scanner sc = new Scanner(System.in);
//		System.out.println();
//		System.out.println("1. Add Item");
//		System.out.println("2. Remove Item");
//		System.out.println("3. Remove Whole Order");
//		System.out.println("4. Back");

		switch(ch)
		{
		case 1:

			menuSelection(mainmenu,null);
			System.out.print("Confirm the updated order? ");
			System.out.print("Confirm update to Order (Y/N): ");
			char conf = customScanner.checkConfirmationID(sc).charAt(0);
			if(conf == 'Y' || conf == 'y')
			{
				// Add to orderlist 
				orderList.set(ind, this);
				System.out.println("Order Updated!");
				return orderList;
			}
			break;

		case 2:

			printOrderInput();
			do {
				System.out.println();
				System.out.println("1. Remove Menu Items");
				System.out.println("2. Remove Promo Items");
				System.out.println("3. Done");
				System.out.println("4. Exit");
				System.out.print("Choice: ");
				ch = customScanner.readOnlyIntegers(sc);

				int id;
				if(ch == 1)
				{

					getMenuList().printListMenuItem(); //Chooose an index 
					System.out.println("\nPlease choose the item to remove : ");
					//int menuInd = customScanner.readOnlyIntegers(sc);
					id = customScanner.onlyMenuItemId(sc, mainmenu);
					for(AlaCarteItem ala : getMenuList().getMenuitem()){
						if(ala.getMenuID()==id){
							getMenuList().getMenuitem().remove(ala);
							break;
						}
					}
					
					orderList.set(ind, new Ordering());
					System.out.println("\n Item Remove Sucess!");

				}
				else if( ch == 2)
				{
					int promoid;	

					getMenuList().printpromoItem(); //Chooose an index 
					System.out.println("\nPlease choose the promo item to remove : ");
					//int promoInd = customScanner.readOnlyIntegers(sc);
					promoid = customScanner.onlyPromoItemId(sc, mainmenu);
					for(Promotion ala : getMenuList().getPromoitem()){
						if(ala.getPromoID()==promoid){
							getMenuList().getPromoitem().remove(ala);
							break;
						}
					}

					orderList.set(ind, this);
					System.out.println("\n Promo Item Remove Sucess!");

				}
				else if(ch == 3) // Confirmation
				{
					printOrderInput();
					System.out.print("Confirm order (Y/N): ");
					char conf3 = customScanner.checkConfirmationID(sc).charAt(0);
					if(conf3 == 'Y' || conf3 == 'y')
					{
						// Add to orderlist 
						orderList.set(ind, this);
						System.out.println("Order Updated!");
						return orderList;
					}
					else
					{
						break;
					}
				}
				else // Inputting the wrong input
					
				{
					System.out.println("Please re-enter your choice:");
					System.out.println();
				}

			} while(ch < 4 && ch > 0);
			break;
			
		}
		return orderList;
	}


	/**
	 * An override of getRandomInt()
	 */
	@Override
	public int genRandomInt()
	{
		Random rand = new Random();
		 int r = (1 + rand.nextInt(5)) * 10000 + rand.nextInt(10000);
		 return r;
	}
	
	
	/**
	 * Checks the duplicate IDs of a particular ID that you want to generate 
	 * @param listType (The list that the function should check for the duplicated ID)
	 * @param newRand (The random ID that need to be chekced for duplication)
	 */
	@Override
	public int checkDuplicateIDs(String listType, int newRand)
	{
		int intCheck = 0;
		
		switch(listType)
		{
		case "OrderList":
			for(Ordering orders: Main.orderList)
			{
				if(orders.getOrderID() == newRand)
				{
					intCheck++;
				}
			}
			break;

		case "OrderHist":
			ArrayList<Ordering> historyList = (ArrayList)DataManager.readSerializedObject("OrderHistory.dat");
			if(historyList == null || historyList.isEmpty()) {
				return 0;
			}
			for(Ordering orders: historyList)
			{
				if(orders.getOrderID() == newRand)
				{
					intCheck++;
				}
			}
			break;
		}
		
		return intCheck;

	}

}
