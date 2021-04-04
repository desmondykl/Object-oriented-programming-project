package Class;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import wagu.Block;
import wagu.Board;
import wagu.Table;
import java.util.*; 

import Data.DataManager;

/**
 * Represent a Main menu in the restuarant.
 * Main menu can create/update/remove alarcarte or promotion items respectively.
 * Every functions may contain it's condition in order to execute.
 * It implements serializable to allow us to serialize and deserialize to a file
 * @author Sam Jian Shen
 * @version 9.0
 * @since 2019/04/01
 */
public class MainMenu implements Serializable, GenerateId {
	
	/**
	 * The alacarte item list in the main menu 
	 */
	ArrayList <AlaCarteItem> menuitem ; 
	
	/**
	 * The promotion list in the main menu.
	 */
	ArrayList <Promotion> promoitem;
	
	/**
	 * Get the main menu's alacarte item list.
	 * @return menuItem This main menu's alacarte item list.
	 */
	public ArrayList<AlaCarteItem> getMenuitem() {
		return menuitem;
	}
	
	/**
	 * Set the main menu's alacarte item list.
	 * @param menuitem This is the new main menu's alacarte item list 
	 */
	public void setMenuitem(ArrayList<AlaCarteItem> menuitem) {
		this.menuitem = menuitem;
	}
	
	/**
	 * Get the main menu's promotion list 
	 * @return promoitem This main menu's promotion list 
	 */
	public ArrayList<Promotion> getPromoitem() {
		return promoitem;
	}
	
	/**
	 * Set the main menu's promotion list.
	 * @param promoitem This is the new main menu's promotion list 
	 */
	public void setPromoitem(ArrayList<Promotion> promoitem) {
		this.promoitem = promoitem;
	}
	
	/**
	 * Converting ArrayList to (List(String))
	 * @param mode mode 1 and 3 is converting AlaCarteItem arrayList to (List(String)) the different is that 1 of them includes an extra column(Description) 
	 * mode 2 and 4 is for printing of promotion items , similarly, the only difference is that 1 of them has an extra column(description) 
	 * @param id To match the promotional id (Print only the id that matches)
	 * @return t2Rows (List(List(String))) used for printing of the formatted table 
	 */
	public List<List<String>> GetStringArrayF(int mode,int id) 
    { 
		List<List<String>> t2Rows= new ArrayList<List<String>>();
		if(mode==4){
			ArrayList<AlaCarteItem> temp = new ArrayList<AlaCarteItem>();
			for(Promotion p : promoitem){
				if(p.getPromoID()==id){
					for(AlaCarteItem ala :p.alaCartList()){
						temp.add(ala);
					}
				}
			}
			ArrayList<Integer> uID = new ArrayList<Integer>();	
			for(AlaCarteItem ala: temp){
	        	boolean u = true;
	        	for(Integer i :uID){
	        		if(ala.getMenuID()==i)
	        			u=false;
	        	}
	        	if(u)
	        		uID.add(ala.getMenuID());
	        }
			 for(Integer i :uID){
				 String str[] = new String[4]; 
				 double totalprice = 0;
				 int qty = 0;
				 for(AlaCarteItem ala :temp){
					 if(ala.getMenuID()==i){
						 str[0]="-"+ala.getName();
		          	     str[1]=String.valueOf(ala.getPrice());
		          	     qty++;
		          	     totalprice +=  ala.getPrice();
					 }
				 }
				 str[2]=String.valueOf(qty);
				 str[3]=String.valueOf(totalprice);
				 t2Rows.add(Arrays.asList(str));
			 }
			 String str2[] = new String[4];
			 str2[0]="--------------------";
			 str2[1]="---------";
			 str2[2]="-----";
			 str2[3]="------------";
			 t2Rows.add(Arrays.asList(str2));
		}
		if(mode==3){
			// declaration and initialise String Array
						ArrayList<Integer> uID = new ArrayList<Integer>();	
						for(AlaCarteItem ala: menuitem){
				        	boolean u = true;
				        	for(Integer i :uID){
				        		if(ala.getMenuID()==i)
				        			u=false;
				        	}
				        	if(u)
				        		uID.add(ala.getMenuID());
				        }
				        
				        for(Integer i :uID){
				        	String str[] = new String[4]; 
				        	double totalprice = 0;
				        	int qty = 0;
				        	for(AlaCarteItem ala :menuitem){
				        		if(ala.getMenuID()==i){
				          	        str[0]=ala.getName();
				          	        str[1]=String.valueOf(ala.getPrice());
				          	        
				          	        qty++;
				          	        totalprice +=  ala.getPrice();
				        		}
				           }
				        	str[2]=String.valueOf(qty);
				        	str[3]=String.valueOf(totalprice);
				        	t2Rows.add(Arrays.asList(str));
				    	}
		}
		if(mode==1){
	        // declaration and initialise String Array
			ArrayList<Integer> uID = new ArrayList<Integer>();	
			for(AlaCarteItem ala: menuitem){
	        	boolean u = true;
	        	for(Integer i :uID){
	        		if(ala.getMenuID()==i)
	        			u=false;
	        	}
	        	if(u)
	        		uID.add(ala.getMenuID());
	        }
	        
	        for(Integer i :uID){
	        	String str[] = new String[6]; 
	        	double totalprice = 0;
	        	int qty = 0;
	        	for(AlaCarteItem ala :menuitem){
	        		if(ala.getMenuID()==i){
	          	        str[0]=String.valueOf(ala.getMenuID());
	          	        str[1]=ala.getName();
	          	        str[2]=ala.getDesc();
	          	        str[3]=ala.getCat().toString();
	          	        qty++;
	          	        totalprice +=  ala.getPrice();
	        		}
	           }
	        	str[4]=String.valueOf(qty);
	        	str[5]=String.valueOf(totalprice);
	        	t2Rows.add(Arrays.asList(str));
	    	}
	        
		}
		if(mode==2){
			ArrayList<AlaCarteItem> temp = new ArrayList<AlaCarteItem>();
			for(Promotion p : promoitem){
				if(p.getPromoID()==id){
					for(AlaCarteItem ala :p.alaCartList()){
						temp.add(ala);
					}
				}
			}
			ArrayList<Integer> uID = new ArrayList<Integer>();	
			for(AlaCarteItem ala: temp){
	        	boolean u = true;
	        	for(Integer i :uID){
	        		if(ala.getMenuID()==i)
	        			u=false;
	        	}
	        	if(u)
	        		uID.add(ala.getMenuID());
	        }
			 for(Integer i :uID){
				 String str[] = new String[6]; 
		        	double totalprice = 0;
		        	int qty = 0;
		        	for(AlaCarteItem ala :temp){
		        		if(ala.getMenuID()==i){
		          	        str[0]="";
		          	        str[1]=ala.getName();
		          	        str[2]=ala.getDesc();
		          	        str[3]=ala.getCat().toString();
		          	        qty++;
		          	        totalprice +=  ala.getPrice();
		        		}
		           }
		        	str[4]=String.valueOf(qty);
		        	str[5]=String.valueOf(totalprice);
		        	t2Rows.add(Arrays.asList(str));
			 }
			 
			
			
		}
		return t2Rows; 
    }
	
	/**
	 * This print the main menu's Ala Carte item list 
	 */
	public void printListMenuItem()
	{
		List<String> headersList = Arrays.asList("ITEM ID", "ITEM NAME", "ITEM DESCRIPTION", "ITEM CATEGORY","QTY" ,"ITEM PRICE");
	       
		List<List<String>>  data = GetStringArrayF(1,0);
		if(!data.isEmpty()){
			Board board = new Board(750);
	        Table table = new Table(board, 750, headersList, data);
	        table.setGridMode(Table.GRID_COLUMN);
	        //setting width and data-align of columns
	        List<Integer> colWidthsList = Arrays.asList(8, 28,60,15,5,10);
	        List<Integer> colAlignList = Arrays.asList(Block.DATA_CENTER,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT);
	        table.setColWidthsList(colWidthsList);
	        table.setColAlignsList(colAlignList);
	        
	        Block tableBlock = table.tableToBlocks();
	        board.setInitialBlock(tableBlock);
	        board.build();
	        String tableString = board.getPreview();
	        System.out.println(tableString);
		}
	}
	
	/**
	 * This print the main menu's promotion item list.
	 *
	 * Condition:
	 * If promotional item is empty, it will display "There is no promotional package" 
	 * Else, it will proceed to print out the promotional item list 
	 */
	public void printpromoItem()
	{
		if(promoitem==null){
			System.out.println("There is no promotional package");
			return;
		}
		Board board = new Board(750);
		
        List<Integer> promoWidths = Arrays.asList(8, 28,60,15,16);
		ArrayList<Integer> uID = new ArrayList<Integer>();	
		for(Promotion p: promoitem){
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
			 String str[] = new String[5]; 
	        	double totalprice = 0;
	        	int qty = 0;
	        	for(Promotion p :promoitem){
	        		if(p.getPromoID()==i){
	          	        str[0]=String.valueOf(p.getPromoID());
	          	        str[1]=p.getName();
	          	        str[2]=p.getDesc();
	          	        qty++;
	          	        totalprice +=  p.getPrice();
	        		}
	           }
	        	str[3]=String.valueOf(qty);
	        	str[4]=String.valueOf(totalprice);
	        	List<List<String>> datapromo= new ArrayList<List<String>>();
	        	datapromo.add(Arrays.asList(str));
	        	List<String> headersList = Arrays.asList("PROMO ID", "PROMO NAME", "PROMO DESCRIPTION","QTY" ,"PROMO PRICE");
        		List<String> menuHeader = Arrays.asList("", "ITEM NAME", "ITEM DESCRIPTION", "ITEM CATEGORY","QTY" ,"ITEM PRICE");
        		List<Integer> colAlignList = Arrays.asList(Block.DATA_CENTER,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_CENTER,Block.DATA_CENTER);
        		List<Integer> menuAlign = Arrays.asList(Block.DATA_CENTER,Block.DATA_MIDDLE_LEFT,Block.DATA_MIDDLE_LEFT,Block.DATA_CENTER,Block.DATA_CENTER,Block.DATA_CENTER);
        		
	        	if(k==0){
	        		board.setInitialBlock(new Table(board, 750, headersList, datapromo,promoWidths).setColAlignsList(colAlignList).tableToBlocks());
	        		List<Integer> colWidthsList = Arrays.asList(8, 28,60,15,5,10);
	        		List<List<String>>  data = GetStringArrayF(2,i);
	        		board.appendTableTo(5, Board.APPEND_BELOW, new Table(board, 750, menuHeader, data, colWidthsList).setColAlignsList(menuAlign));
	        	}
	        	else if(k!=0){
	        		board.appendTableTo(k*22-6, Board.APPEND_BELOW, new Table(board, 750, headersList, datapromo,promoWidths).setColAlignsList(colAlignList));
	        		List<Integer> colWidthsList = Arrays.asList(8, 28,60,15,5,10);
	        		List<List<String>>  data = GetStringArrayF(2,i);
	        		board.appendTableTo(k*22-6+11, Board.APPEND_BELOW, new Table(board, 750, menuHeader, data, colWidthsList).setColAlignsList(menuAlign));

	        	}

	        	k++;
		 }
	     board.build();
	     String tableString = board.getPreview();
	     System.out.println(tableString);
		
	}
	
	/**
	 * Get a promotion from the main menu's promotion list.
	 * @param id This is the promotional item ID 
	 * @return toReturn Promotion depends on promotion's ID (null value if this promotion does not exist)
	 */
	public Promotion getPromoObject(int id){
		Promotion toReturn=null;
		if(promoitem == null || promoitem.isEmpty())
			return null;
		for(Promotion p:promoitem){
			if(p.getPromoID()==id){
				toReturn=p;
			}
		}
		return toReturn;
	}
	
	/**
	 * Get a alacarte item from the main menu's alacarte item list.
	 * @param id This is the alacarte item ID.
	 * @return this alacarte Item depends on alacarte's ID (null value if this promotion does not exist).
	 */
	public AlaCarteItem getAlacartObject(int id){
		AlaCarteItem toReturn=null;
		if(menuitem == null || menuitem.isEmpty())
			return null;
		for(AlaCarteItem ala:menuitem){
			if(ala.getMenuID()==id){
				toReturn=ala;
			}
		}
		return toReturn;
	}
	
	/**
	 * This is to create/update/remove options in the main menu for alacarte item.
	 * All options requires alacarte item's details depend on which options was chosen.
	 * 
	 * Unique IDs was randomly generated with a range between 10000 to 99999.
	 * User input "-1" within this method would go back to main menu.
	 * Invalid input will be re-prompt.
	 * If method process was completed it should print "created/updated/removed" - depend on which option chosen and  follow by "successfully" without error.
	 * Update and Remove options require alacarte item existence in order for it to work.
	 */
	public void createMenu(){
		//Initialize
		Scanner sc = new Scanner(System.in);
		AlaCarteItem ala = new AlaCarteItem();
		String itemName, itemDesc, selectStr = "NULL";
		Object dataUpdate = "NULL";
		int option = 0, i,high,low,itemID,itemIndex,itemPara,itemTypeNum;
		double itemPrice;
		boolean validationMenu = false;
		
//		System.out.println(
//				"+-------------------------------------+"
//				+"|\nCreate/Update/Remove menu item:    |"
//				+"+-------------------------------------+"
//				+"|\n<Key in -1 to go back to main menu>|"
//				+"|\n(1) Create an Item                 |"
//				+"|\n(1) Update an Item                 |"
//				+"|\n(3) Remove an Item"                |""
//				+"+-------------------------------------+"
//	);
		
		System.out.println("+----------------------------------+");
		System.out.println("|CREATE/UPDATE/REMOVE MENU ITEM    |");
		System.out.println("|Key in -1 to go back to main menu |");
		System.out.println("+----------------------------------+");
		System.out.println("|1. Create an Item                 |");
		System.out.println("|2. Update an Item                 |");
		System.out.println("|3. Remove an Item                 |");
		System.out.println("|4. Back                           |");
		System.out.println("+----------------------------------+");
		System.out.println("Please Enter Your Choice:       ");
		System.out.print("Choice : ");
		
		option = customScanner.readOnlyIntegers(sc);
		if(option == -1 || option == 4){
			return;
		}
		switch (option) {
		//Create---------------------------------------------------------------
		case 1: 
			//<Input>----------------------------------------------------------
			
			//Name
			System.out.print("\nPlease Enter Item Name: ");
			itemName = customScanner.readOnlyString(sc,3,27);
			if(itemName.equals("-1")) {
				return;
			}
			
			//Price
			System.out.print("\nPlease Enter Item Price in SGD : ");
			itemPrice = customScanner.readOnlyDoubles(sc);
			if(itemPrice == -1) {
				return;
			}
			
			//Type
			validationMenu = false;
			do {
				if(validationMenu == true){
					System.out.println("Invalid Input!");
				}
				System.out.println("\nPlease Enter Item Type");
				i = 1;
				for (Type t : Type.values()) {
					System.out.println("(" + i + ")" + t.toString());
					i++;
				}
				System.out.print("Choice : ");
				itemTypeNum = customScanner.readOnlyIntegers(sc);
				if(itemTypeNum == -1) {
					return;
				}
				validationMenu = true;
			}while(itemTypeNum < 1 || itemTypeNum > 4);
			
			//Description
			System.out.print("\nPlease Enter Item Description : ");
			itemDesc = customScanner.readOnlyString(sc,5,50);
			if(itemDesc.equals("-1")) {
				return;
			}
			
			//RandomID
			System.out.print("\nGenerating Random ID...");		
			int y = 0;
			do {
				itemID = genRandomInt();
				y = checkDuplicateIDs("ala",itemID);
			}while(y==1);
			System.out.println(itemID);

			//Write Data
			System.out.println("\nCreating a menu item...");
			if(this.getMenuitem()==null){
                this.menuitem = new ArrayList<AlaCarteItem>();
            }
			this.getMenuitem().add(new AlaCarteItem(itemName, itemPrice, itemID, itemDesc, Type.values()[itemTypeNum-1]));
			DataManager.writeSerializedObject("MenuItems.dat", this.getMenuitem());
			
			//<Output>----------------------------------------------------------
			//Print Updated Data
			this.printListMenuItem();
			System.out.println("\nItem Successfully Added!");
			break;
			
		//Update---------------------------------------------------------------
		case 2:
			//Existence Check
			if(menuitem.isEmpty()){
				System.out.println("No item to remove!");
				return;
			}
			
			//<Input>----------------------------------------------------------
			//Select Item/s
			validationMenu = false;
			do {
				if(validationMenu == true){
					System.out.println("ID does not exist");
				}
				System.out.println("\nPlease choose the item to update : ");
				this.printListMenuItem();
				System.out.println("<Key in -1 to go back to main menu>");
				System.out.print("Choice : ");
				itemIndex = customScanner.readOnlyIntegers(sc);
				if(itemIndex == -1) {
					return;
				}
				validationMenu = true;
			}while(null == getAlacartObject(itemIndex));
			
			//Select Item Parameters
			validationMenu = false;
			do {
				if(validationMenu == true){
					System.out.println("Invalid input");
				}

				
				System.out.println("+----------------------------------+");
				System.out.println("|PLEASE CHOOSE UPDATE OPTION   |");
				System.out.println("|Key in -1 to go back to main menu |");
				System.out.println("+----------------------------------+");
				System.out.println("|1. Item Name                      |");
				System.out.println("|2. Item Price                     |");
				System.out.println("|3. Item Type                      |");
				System.out.println("|4. Item Description               |");
				System.out.println("+----------------------------------+");
				System.out.println("Please Enter Your Choice:       ");
				System.out.print("Choice : ");

				itemPara = customScanner.readOnlyIntegers(sc);
				if(itemPara == -1) {
					return;
				}
				validationMenu = true;
			}while(itemPara < 1 || itemPara > 4);
			
			//Set New Data
			switch(itemPara) {
				case 1:
					selectStr  = "name";
					System.out.print("\nSelect new item's " + selectStr + " : ");
					dataUpdate = customScanner.readOnlyString(sc,3,27);
					if(dataUpdate.equals("-1")) {
						return;
					}
					break;
				case 2:
					selectStr  = "price in SGD";
					System.out.print("\nSelect new item's " + selectStr + " : ");
					dataUpdate = customScanner.readOnlyDoubles(sc);
					if((double)dataUpdate == -1) {
						return;
					}
					break;
				case 3:
					selectStr  = "type";
					validationMenu = false;
					do {
						if(validationMenu == true){
							System.out.println("Invalid Input!");
						}
						System.out.print("\nSelect new item's " + selectStr + " : ");
						System.out.println("\nPlease Enter Item Type");
						i = 1;
						for (Type t : Type.values()) {
							System.out.println("(" + i + ")" + t.toString());
							i++;
						}
						System.out.print("Choice : ");
						dataUpdate = customScanner.readOnlyIntegers(sc);
						if((int)dataUpdate == -1) {
							return;
						}
						validationMenu = true;
					} while((int) dataUpdate > 4 || (int)dataUpdate < 1);
					break;
				case 4: 
					selectStr  = "description";
					System.out.print("\nSelect new item's " + selectStr + " : ");
					dataUpdate = customScanner.readOnlyString(sc,5,50);
					if(dataUpdate.equals("-1")) {
						return;
					}
					break;
			}

			//Update Process
			UpdateMenu(itemIndex, itemPara, dataUpdate);
			
			//<Output>----------------------------------------------------------
			this.printListMenuItem();
			System.out.println("\nUpdate Sucess!");
			break;
			
		//Remove---------------------------------------------------------------
		case 3:
			//Existence Check
			if(menuitem.isEmpty()){
				System.out.println("No item to remove!");
				return;
			}
			
			//<Input>----------------------------------------------------------
			//Select Item
			validationMenu =  false;
			do {
				if(validationMenu == true){
					System.out.println("ID does not exist");
				}
				System.out.println("\nPlease choose the item to remove : ");
				this.printListMenuItem();
				System.out.println("<Key in -1 to go back to main menu>");
				System.out.print("Choice : ");
				itemIndex = customScanner.readOnlyIntegers(sc);
				if(itemIndex == -1) {
					return;
				}
				validationMenu = true;
			} while(null == getAlacartObject(itemIndex));
			
			//<Output>----------------------------------------------------------
			//Remove Process
			RemoveMenu(itemIndex);
			//this.printListMenuItem();
			System.out.println("\nRemove Sucess!");
			break;
		default:
			System.out.println("Invalid Input!");
		}
		createMenu();
	}
	
	/**
	 * This is to update the alacarte item in the main menu alarcarte item list.
	 * 
	 * All alacarte item details in alacarte item/s list and promotion's alarcate item/s in promotion/s list are share the same details except their price.
	 * This write data into both PromoItem.dat and MenuItems.dat.
	 * @param itemIndex This is new alacarte item's ID.
	 * @param itemPara This is the chosen option from one of alacarte item's detail (name,price,category,description)
	 * @param dataUpdate This is new alacarte item's category.
	 */
	public void UpdateMenu(int itemIndex, int itemPara, Object dataUpdate) 
	{
		//ArrayList<MenuItem> menu_Item = (ArrayList) DataManager.readSerializedObject("MenuItems.db");
		int i = 0;
		//Find the menuitem index
		for(AlaCarteItem menuitem : this.getMenuitem()) {
			//Update the item when found
			if(menuitem.getMenuID() == itemIndex) //-1 because 1st is 1
			{
				switch (itemPara) {
					case 1: //name;
						menuitem.setName((String) dataUpdate); 
						break;
					case 2: //price
						menuitem.setPrice((double) dataUpdate);
						break;
					case 3: //type
						menuitem.setCat(Type.values()[(int) dataUpdate - 1]);
						break;
					case 4: //description
						menuitem.setDesc((String) dataUpdate);
						break;
				}
				break;
			}
		}
		for(Promotion p :this.getPromoitem()){
			   for(AlaCarteItem ala: p.alaCartList()){
				   if(ala.getMenuID()==itemIndex){
					   if(ala.getMenuID() == itemIndex) //-1 because 1st is 1
						{
							switch (itemPara) {
								case 1: //name;
									ala.setName((String) dataUpdate); 
									break;
								case 3: //type
									ala.setCat(Type.values()[(int) dataUpdate - 1]);
									break;
								case 4: //description
									ala.setDesc((String) dataUpdate);
									break;
							}
							break;
						}
				   }
			   }
		}
		//this.printListMenuItem();
		System.out.println("\nUpdate Sucess!");
		DataManager.writeSerializedObject("MenuItems.dat",this.getMenuitem());
		DataManager.writeSerializedObject("PromoItem.dat",this.getPromoitem());
	}
	
	/**
	 * This is to remove the alacarte item in the main menu's alarcarte item's list.
	 * This also applies promotion's alacarte item, after removed if promotion contain no alacarte item then it will also be removed as well. 
	 * This write data into both PromoItem.dat and MenuItems.dat.
	 * @param itemIndex This is the alarcarte item's ID.
	 */
	public void RemoveMenu(int itemIndex) 
	{
		for(AlaCarteItem ala : this.getMenuitem()){
			if(ala.getMenuID()==itemIndex){
				this.getMenuitem().remove(ala);
				break;
			}
		}
		Iterator<Promotion> iterator = this.getPromoitem().iterator();
		while(iterator.hasNext()){
			Promotion p = iterator.next();
			for(AlaCarteItem ala: p.alaCartList()){
				if(ala.getMenuID()==itemIndex){
					p.alaCartList().remove(ala);
					break;
				}
			}
			//Delete promotion if consist of 0 menu item
			if(p.alaCartList().isEmpty()){
				System.out.println("Removing " + p.getPromoID());
				iterator.remove();
			}
		}
		
		DataManager.writeSerializedObject("MenuItems.dat",this.getMenuitem());
		DataManager.writeSerializedObject("PromoItem.dat",this.getPromoitem());
	}
	//--------------------------------------------------------------------------------------------------------------	
	//--------------------------------------Promotion---------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	/**
	 * This is to create/update/remove options in the main menu for promotion.
	 * All options requires promotion item's details and alacarte item's details depend on which options was chosen.
	 * 
	 * Unique IDs was randomly generated with a range between 10000 to 99999.
	 * User input "-1" within this method would go back to main menu.
	 * Invalid input will be re-prompt.
	 * If method process was completed it should print "created/updated/removed" - depend on which option chosen and  follow by "successfully" without error.
	 * Update and Remove options require promotion existence in order for it to work.
	 */
	public void createPromo() {
		//Existence Check
		if(menuitem.isEmpty()){
			System.out.println("No item to create promotion!");
			return;
		}
		
		//Initialize
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> itemIndexA, itemIndexA_Up = null, changedPriceItemsID;
		ArrayList<AlaCarteItem> updatedAla = new ArrayList<AlaCarteItem>();
		ArrayList<AlaCarteItem> selectedAla = new ArrayList<AlaCarteItem>();
		ArrayList<AlaCarteItem> listAla  = new ArrayList<AlaCarteItem>();
		String promoName, promoDesc, selectStr = "NULL", lines; 
		String [] strs;
		Object dataUpdate = "NULL";
		int option = 0, i, high, low; 
		int promoID,  promoIndex, promoPara;
		double promoPrice = 0;
		boolean validationPromo = false;
		
//		System.out.println("\nCreate/Update/Remove Promotion:" 
//				+ "\n<Key in -1 to go back to main menu>"
//				+ "\n(1) Create an Promotion"
//				+ "\n(2) Update an Promotion" 
//				+ "\n(3) Remove an Promotion" 
//				);
		
		System.out.println("+----------------------------------+");
		System.out.println("|CREATE/UPDATE/REMOVE Promotion    |");
		System.out.println("|Key in -1 to go back to main menu |");
		System.out.println("+----------------------------------+");
		System.out.println("|1. Create a Promotion             |");
		System.out.println("|2. Update a Promotion             |");
		System.out.println("|3. Remove a Promotion             |");
		System.out.println("+----------------------------------+");
		System.out.println("Please Enter Your Choice:       ");

		System.out.print("Choice : ");
		option = customScanner.readOnlyIntegers(sc);
		if(option == -1) {
			return;
		}
		switch (option) {
		//Create---------------------------------------------------------------
		case 1: 
			//<Input>----------------------------------------------------------
			//Name
			System.out.print("\nPlease Enter Promotion Name: ");
			promoName = customScanner.readOnlyString(sc,3,27);
			if(promoName.equals("-1")) {
				return;
			}
			validationPromo = false; 
			
			//Select Item for Promotion
			do {
				if(validationPromo == true){
					System.out.println("ID does not exist");
				}
				this.printListMenuItem();
				System.out.println("Please Select Item/s for Promotion");
				System.out.println("E.g : 1 3 7 ");
				System.out.print("Choices : ");
				lines = sc.nextLine();
				strs = lines.split("\\s+");
				itemIndexA = new ArrayList<Integer>();

				for(i = 0; i < strs.length; i++){
					if(strs[i].equals("-1")) {
						return;
					}
					try {
						itemIndexA.add(Integer.parseInt(strs[i]));
					}
					catch(NumberFormatException e) {
						System.out.println("Invalid");
						validationPromo=true;
					}
				}
				
				for(int IDs : itemIndexA){
					if(getAlacartObject(IDs) == null){
						validationPromo = true;
						break;
					}
					validationPromo = false;
				}
			}while(validationPromo == true);
			
			//Select which item's price to change
			do {
				selectedAla = printSelectiveMenuItems(itemIndexA);
				if(validationPromo == true)
				{
					System.out.println("ID does not exist");
				}
				System.out.println("Please select the item's price you would like to change by key in their IDs :");
				System.out.println("E.g : 1 3 7 ");
				System.out.print("Choices : ");
				lines = sc.nextLine();
				strs = lines.split("\\s+");
				changedPriceItemsID = new ArrayList<Integer>();
	
				for(i = 0; i < strs.length; i++){
					if(strs[i].equals("-1")) {
						return;
					}
					try {
						changedPriceItemsID.add(Integer.parseInt(strs[i]));
					}
					catch(NumberFormatException e) {
						System.out.println("Invalid");
						validationPromo=true;
					}
				}
				
				for(int IDs : changedPriceItemsID){
					if(getAlacartObject(IDs) == null){
						validationPromo = true;
						break;
					}
					validationPromo = false;
				}
			}while(validationPromo == true);
			
			//Update selected item's price to change
			updatedAla = updatePricePromoItems(selectedAla, changedPriceItemsID);
			if(updatedAla == null){
				return;
			}
			
			//Description
			System.out.print("\nPlease Enter Promotion Description : ");
			promoDesc = customScanner.readOnlyString(sc,5,50);
			if(promoDesc.equals("-1")) {
				return;
			}
			
			//Random ID
			System.out.print("\nGenerating Random ID...");
			int y = 0;
			do {
				promoID = genRandomInt();
				y = checkDuplicateIDs("promo",promoID);
			}while(y==-1);
			System.out.println(promoID);

			//Calculate price
			for(AlaCarteItem ala : updatedAla){
				promoPrice = promoPrice + ala.getPrice();
			}

			//Write Data
			createwritePromoData(promoName, promoID, promoDesc, updatedAla, promoPrice); 
			
			//<Input>----------------------------------------------------------
			this.printpromoItem();
			System.out.println("\nPromotion Successfully Added!");
			break;
			
		//Update---------------------------------------------------------------
		case 2: 
			//Existence Check
			if(promoitem.isEmpty()){
				System.out.println("No item to update!");
				return;
			}
			
			//<Input>----------------------------------------------------------
			//Select Item
			validationPromo = false;
			do {
				if(validationPromo == true)
				{
					System.out.println("ID does not exist");
				}
				System.out.println("\nPlease choose the promotion to update : ");
				this.printpromoItem();
				System.out.println("<Key in -1 to go back to main menu>");
				System.out.print("Choice : ");
				promoIndex = customScanner.readOnlyIntegers(sc);
				if(promoIndex == -1) {
					return;
				}
				validationPromo = true;
			} while(null == getPromoObject(promoIndex));
			
			//Select Item Parameter
			validationPromo = false;
			do {
				if(validationPromo == true){
					System.out.println("Invalid Input!");
				}
//				System.out.println("\nPlease choose update option : " 
//						+ "\n<Key in -1 to go back to main menu>"
//						+ "\n(1) Promotion Name"
//						+ "\n(2) Promotion Description" 
//						+ "\n(3) Promotion Item's Price" 
//						+ "\n(4) Promotion Change Set"
//						);
				
				System.out.println("+----------------------------------+");
				System.out.println("|PLEASE CHOOSE AN UPDATE OPTION    |");
				System.out.println("|Key in -1 to go back to main menu |");
				System.out.println("+----------------------------------+");
				System.out.println("|1. Promotion Name                 |");
				System.out.println("|2. Promotion Description          |");
				System.out.println("|3. Promotion Item's Price         |");
				System.out.println("|4. Promotion Change Set           |");
				System.out.println("+----------------------------------+");
				System.out.println("Please Enter Your Choice:           ");
				System.out.print("Choice : ");
				promoPara = customScanner.readOnlyIntegers(sc);
				if(promoPara == -1) {
					return;
				}
				validationPromo = true;
			}while(promoPara > 4 || promoPara < 1);

			//Set new data value
				switch(promoPara) {
					case 1: //name
						selectStr  = "name";
						System.out.print("\nSelect new promotion's " + selectStr + " : ");
						dataUpdate = customScanner.readOnlyString(sc,3,27);
						if(dataUpdate.equals("-1")) {
							return;
						}
						break;
					case 2: //description
						selectStr  = "description";
						System.out.print("\nSelect new promotion's " + selectStr + " : ");
						dataUpdate = customScanner.readOnlyString(sc,5,50);
						if(dataUpdate.equals("-1")) {
							return;
						}
						break;
					case 3: //price
						
						//Select which promotion to change price
						selectStr  = "price";					
						validationPromo = false;
						do {
							if(validationPromo == true){
								System.out.println("ID does not exist");
							}
							listAla = getPromoObject(promoIndex).alaCartList();
							printpromoItem(listAla);
							System.out.println("\nSelect new promotion's " + selectStr + " : ");
							System.out.println("<Key in -1 to go back to main menu>");
							System.out.println("E.g : 1 3 7 ");
							System.out.print("Choices : ");
							lines = sc.nextLine();
							strs = lines.split("\\s+");
							changedPriceItemsID = new ArrayList<Integer>();
				
							for(i = 0; i < strs.length; i++)
							{
								if(strs[i].equals("-1")) {
									return;
								}
								try {
									changedPriceItemsID.add(Integer.parseInt(strs[i]));
								}
								catch(NumberFormatException e) {
									System.out.println("Invalid");
									validationPromo=true;
								}
							}
							
							for(AlaCarteItem promoItems : listAla) { 
								for(int IDs : changedPriceItemsID){
									if(promoItems.getMenuID() != IDs){
										validationPromo = true;
										break;
									}
								}
								validationPromo = false;
							}
						}while(validationPromo == true);
						
						//Prompt for Update Promotion Item price accordingly
						listAla = updatePricePromoItems(listAla,changedPriceItemsID);
						break;
					case 4: //items
						
						//Reselect Menu Items for Promotion
						selectStr  = "items";					
						validationPromo = false;
						do {
							if(validationPromo == true){
								System.out.println("ID does not exist");
							}
							System.out.println("\nSelect new promotion's " + selectStr + " : ");
							printListMenuItem();
							System.out.println("<Key in -1 to go back to main menu>");
							System.out.println("E.g : 1 3 7 ");
							System.out.print("Choices : ");
							lines = sc.nextLine();
							strs = lines.split("\\s+");
							itemIndexA_Up = new ArrayList<Integer>();
				
							for(i = 0; i < strs.length; i++){
								if(strs[i].equals("-1")) {
									return;
								}
								try {
									itemIndexA_Up.add(Integer.parseInt(strs[i]));
								}
								catch(NumberFormatException e) {
									System.out.println("Invalid");
									validationPromo=true;
								}
							}
							
							for(AlaCarteItem menuItems : menuitem) { 
								for(int IDs : itemIndexA_Up){
									if(menuItems.getMenuID() == IDs){
										validationPromo = true;
										break;
									}
								}
								validationPromo = false;
							}
						}while(validationPromo == true);
						
						//Select which item's price to change
						do {
							selectedAla = printSelectiveMenuItems(itemIndexA_Up);
							if(validationPromo == true){
								System.out.println("ID does not exist");
							}
							System.out.println("Please select the item's price you would like to change by key in their IDs :");
							System.out.println("E.g : 1 3 7 ");
							System.out.print("Choices : ");
							lines = sc.nextLine();
							strs = lines.split("\\s+");
							changedPriceItemsID = new ArrayList<Integer>();
				
							for(i = 0; i < strs.length; i++){
								if(strs[i].equals("-1")) {
									return;
								}
								try {
									changedPriceItemsID.add(Integer.parseInt(strs[i]));
								}
								catch(NumberFormatException e) {
									System.out.println("Invalid");
									validationPromo=true;
								}
							}
							
							for(int IDs : changedPriceItemsID) {//checks
								if(getAlacartObject(IDs) == null){
									validationPromo = true;
									break;
								}
								validationPromo = false;
							}
						}while(validationPromo == true);
						
						//Update promotion item price
						listAla = updatePricePromoItems(selectedAla, changedPriceItemsID);
						if(listAla == null){
							return;
						}
						break;
					default:
						System.out.println("Invalid Input!");
						break;
			}
			
			//Update Process
			UpdatePromo(promoIndex, promoPara, dataUpdate, listAla);
			
			//<Output>----------------------------------------------------------
			this.printpromoItem();
			System.out.println("\nPromotion Successfully Updated!");
			break;
			
		//Remove---------------------------------------------------------------
		case 3: 
			//Existence Check
			if(promoitem.isEmpty()){
				System.out.println("No item to removed!");
				return;
			}
			
			//<Input>----------------------------------------------------------
			//Select Item
			do {
				if(validationPromo) {
					System.out.println("ID does not exist");
				}
				System.out.println("\nPlease choose the promotion to remove : ");
				this.printpromoItem();
				System.out.println("<Key in -1 to go back to main menu>");
				System.out.print("Choice : ");
				
				promoIndex = customScanner.readOnlyIntegers(sc);
				if(promoIndex == -1) {
					return;
				}
				validationPromo = true;
			}while(null == getPromoObject(promoIndex));
			
			//Remove Process
			RemovePromo(promoIndex);
			
			//<Output>----------------------------------------------------------
			this.printpromoItem();
			System.out.println("\nRemove Sucess!");
			
			break;
		default:
			System.out.println("Invalid Input!");
		}
		createPromo();
	}
	
	/**
	 * This is to update promotion in the main menu promotion list.
	 * Note: Promotion's alarcarte item's price in promotion list was independent from alarcarte item's price in alarcate list.
	 * This write data into PromoItem.dat
	 * @param promoIndex This is the new promotion's ID.
	 * @param promoPara This is the chosen option from one of promotion's detail (name,description,price,alacarte item/s).
	 * @param dataUpdate This is the new promotion's name/description depend on promoPara.
	 * @param UpdateItems This is the new promotion's alacarte item/s depend on promoPara.
	 */
	public void UpdatePromo(int promoIndex, int promoPara, Object dataUpdate, ArrayList<AlaCarteItem> UpdateItems) {
		double pricePromo = 0;
		//Search for respective options to Update
		for(Promotion promo : promoitem){
			if(promo.getPromoID()==promoIndex)
			{
				switch (promoPara) {
					case 1: //name;
						promo.setName((String) dataUpdate); 
						break;
					case 2: //description
						promo.setDesc((String) dataUpdate);
						break;
					case 3: case 4: //new menu items or menu item price changes
						promo.setMenuItem(UpdateItems);
						for(AlaCarteItem item : UpdateItems) { 
							pricePromo = pricePromo + item.getPrice();
						}
						promo.setPrice(pricePromo);
						break;
				}
			}
		}
		
		//Write Data
		DataManager.writeSerializedObject("PromoItem.dat",this.getPromoitem());
	}
	
	/**
	 * This is to remove promotion in the main menu promotion list.
	 * This write data into both PromoItem.dat.
	 * @param promoIndex This is the promotion's ID.
	 */
	public void RemovePromo(int promoIndex) {
		//Remove Promotion
		for(Promotion p:this.getPromoitem()){
			if(p.getPromoID()==promoIndex){
				this.getPromoitem().remove(p);
				break;
			}
		}
		
		//Write Data
		DataManager.writeSerializedObject("PromoItem.dat",this.getPromoitem());
	}
	
	/**
	 * This is to create promotion in the main menu promotion list.
	 * This write data into both PromoItem.dat.
	 * @param promoName This is the promotion's name.
	 * @param promoID This is the promotion's ID.
	 * @param promoDesc This is the promotion's description
	 * @param selectedMenuItems This is the promotion's alacarte item/s
	 * @param promoPrice This is the promotion's price
	 */
	public void createwritePromoData(String promoName, int promoID, String promoDesc, ArrayList<AlaCarteItem> selectedMenuItems, double promoPrice) {
		//Add promotion into new list
		System.out.println("\nCreating a promotion...");
		if(this.getPromoitem() == null){
			this.promoitem = new ArrayList<Promotion>();
		}
		this.getPromoitem().add(new Promotion(selectedMenuItems, promoName,  promoID, promoDesc, promoPrice));
		
		//Write Data
		DataManager.writeSerializedObject("PromoItem.dat", this.getPromoitem()); 
	}
	
	/**
	 * This print the selected alacarte item in the alacarte list.
	 * @param selectedIDs This is the selected alacarte's ID in the alarcarte list.
	 * @return this The selected alacarte item/s list.
	 */
	public ArrayList<AlaCarteItem> printSelectiveMenuItems(ArrayList<Integer> selectedIDs) {
		ArrayList<AlaCarteItem> selectedMenuItems = new ArrayList<AlaCarteItem>();
		DecimalFormat decim = new DecimalFormat("#00.00");
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		System.out.println("|ITEM ID |ITEM NAME                   |ITEM DESCRIPTION                                            |ITEM CATEGORY  |ITEM PRICE     |");
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		//Clone Selected data    
		for(int IDs : selectedIDs) {
			AlaCarteItem temp = getAlacartObject(IDs);
			AlaCarteItem clone = new AlaCarteItem();
			clone.setMenuID(temp.getMenuID());
			clone.setName(temp.getName());
			clone.setDesc(temp.getDesc());
			clone.setCat(temp.getCat());
			clone.setPrice(temp.getPrice());
			selectedMenuItems.add(clone);
		}
		//Print Selected Data 
		for(AlaCarteItem s_items : selectedMenuItems) {
			 System.out.format("| %-7d|%-28s|%-60s|%-15s|$%-10s    |",
					s_items.getMenuID(),s_items.getName(), s_items.getDesc(), s_items.getCat(), decim.format( s_items.getPrice()));
	        System.out.println();
		}
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		return selectedMenuItems; 
	}
	
	/**
	 * This update the promotion's alacarte item/s' price.
	 * @param selectedAla This is the selected alacarte item list.
	 * @param changedPriceItemsID This is the selected ID/s in the selected alacarte item list to change the alacarte item/s price respectively.
	 * @return this The updated promotion's alacarte item/s' price.
	 */
	public ArrayList<AlaCarteItem> updatePricePromoItems(ArrayList<AlaCarteItem> selectedAla, ArrayList<Integer> changedPriceItemsID)
	{
		ArrayList<AlaCarteItem> upPricePromoItems = (ArrayList)selectedAla.clone();
		Scanner scn = new Scanner(System.in);
		double price;
		
		//Among the selected item , change only the price updated base on changePriceItemsID
		System.out.println("<Key in -1 to go back to main menu>");
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		System.out.println("|ITEM ID |ITEM NAME                   |ITEM DESCRIPTION                                            |ITEM CATEGORY  |ITEM PRICE     |");
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		
		//Search for item's price    
		for(AlaCarteItem selectItems : upPricePromoItems){
			for(int IDs : changedPriceItemsID){
				if(selectItems.getMenuID() == IDs) { //Target Price Found
		
					System.out.format("| %-7d|%-28s|%-60s|%-15s",
							selectItems.getMenuID(),selectItems.getName(), selectItems.getDesc(), selectItems.getCat());
					System.out.print("$");
					
							//Prompt User to Key in New Price
							price = customScanner.readOnlyDoubles(scn);
							if(price == -1){
								return selectedAla = null;
							}
							selectItems.setPrice(price);	
				}
			}
		}
		return upPricePromoItems;
	}
	
	/**
	 * This print selected promotions in the main menu promotion list.
	 * @param promoItemList This is the selected promotions.
	 */
	public void printpromoItem(ArrayList<AlaCarteItem> promoItemList){
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		System.out.println("|ITEM ID |ITEM NAME                   |ITEM DESCRIPTION                                            |ITEM CATEGORY  |ITEM PRICE     |");
		System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
		DecimalFormat decim = new DecimalFormat("#00.00");
	    for(AlaCarteItem promoItem : promoItemList){
	        System.out.format("| %-7d|%-28s|%-60s|%-15s|$%-10s    |",
	        		promoItem.getMenuID(),promoItem.getName(), promoItem.getDesc(), promoItem.getCat(), decim.format(promoItem.getPrice()));
	        System.out.println();
	    }
	    System.out.println("+--------+----------------------------+------------------------------------------------------------+---------------+---------------+");
	}

	/**
	 * It will randomly generate ID from 10000 ~ 99999
	 * @return itemID ID which will uniquely identify the items
	 */
	@Override
	public int genRandomInt() {
		// TODO Auto-generated method stub
		int itemID = 0;
		int promoID = 0;

		Random r = new Random();
		int low = 10000; 
		int high = 99999;
		itemID = r.nextInt(high-low) + low;

		return itemID;
	}
	/**
	 * @param listType "promo" or "ala" to check duplicate for alacarte item or promotion item
	 * @param newRand The ID to check against the database if there exist the same ID
	 */
	@Override
	public int checkDuplicateIDs(String listType, int newRand) {
		// TODO Auto-generated method stub
		int intCheck = 0;
			
			switch(listType)
			{
			case "promo":
				if(getPromoObject(newRand) != null)
					return -1;
				break;
	
			case "ala":
				if(getAlacartObject(newRand) != null) {
					return -1;
				}
				break;
			}
			
		return newRand;
	}
}
