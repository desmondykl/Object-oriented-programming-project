package RestaurantMain;

import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import Class.*;

import Data.DataManager;

/**
 * This is the MainApp class  
 * It represents the boundary that interacts with the controller (Main) 
 * It is also the main interface which the staff interacts with 
 * @author Muhammad Hisyam bin Jukifli
 * @version 1.0
 * @since 2019-04-18
 * 
 */
public class MainApp {
	/**
	 * This is the MainApp class  
	 * It represents the boundary that interacts with the controller (Main) 
	 * @param args Contains the supplied command-line arguments as an array of String objects.
	 */
	public static void main(String[] args) {
		
		ArrayList<Table> t = new ArrayList<Table>();
		int g=0;
		int i=0;
		for(g =1; g<6;g++){
			t.add(new Table(g,"Unoccupied",10,-1));
		}
		for(i=g; i<11;i++){
			t.add(new Table(i,"Unoccupied",8,-1));
		}
		for(g = i; g<21;g++){
			t.add(new Table(g,"Unoccupied",4,-1));
		}
		for(i=g; i<31;i++){
			t.add(new Table(i,"Unoccupied",2,-1));
		}
		DataManager.writeSerializedObject("Table.dat",t);
		
		Main Restaurant = new Main();		
		Scanner sc = new Scanner(System.in);
		
		int option = 0;
		
		String logoM = "  _____            _ _         __ _             _    _                          \n";
		logoM = logoM + "  \\_   \\__ _ _ __ (_) |_ ___  / _\\ |_ ___  __ _| | _| |__   ___  _   _ ___  ___ \n";
		logoM = logoM + "   / /\\/ _` | '_ \\| | __/ _ \\ \\ \\| __/ _ \\/ _` | |/ / '_ \\ / _ \\| | | / __|/ _ \\\n";
		logoM = logoM + "/\\/ /_| (_| | | | | | ||  __/ _\\ \\ ||  __/ (_| |   <| | | | (_) | |_| \\__ \\  __/\n";
		logoM = logoM + "\\____/ \\__, |_| |_|_|\\__\\___| \\__/\\__\\___|\\__,_|_|\\_\\_| |_|\\___/ \\__,_|___/\\___|\n";
		logoM = logoM + "       |___/                                                                    \n";
		logoM = logoM + "";
		logoM = logoM + "";
		logoM = logoM + "";
		logoM = logoM + "";
		logoM = logoM + "";
		logoM = logoM + "";
		
		System.out.print(logoM);

		System.out.println();
		String StaffName = Restaurant.selectStaff(true,"");
		for(int k = 0; k<2; k++){
			System.out.println();
		}
		
		while(option != 11){
			System.out.println();
			Restaurant.lateArrival();
			System.out.println("Welcome "+StaffName+"!" 
					+ "\n+----------------------------------------------------------------+"
					+ "\n|What would you like to perform:                                 |" 
					+ "\n+----------------------------------------------------------------+"
					+ "\n|(1) Create/Update/Remove menu item                              |"  
					+ "\n|(2) Create/Update/Remove promotion                              |"
					+ "\n|(3) Create new order                                            |" 
					+ "\n|(4) View order                                                  |" 
					+ "\n|(5) Add/Remove order item/s to/from order                       |" 
					+ "\n|(6) Create reservation booking                                  |"
					+ "\n|(7) Check/Cancel reservation booking                            |"
					+ "\n|(8) Check table availability                                    |" 
					+ "\n|(9) Pay/Print bill invoice                                      |"
					+ "\n|(10) Print sale revenue report by period (eg day or month)      |"
					+ "\n|(11) Exit                                                       |"
					+ "\n|(12) Change Staff                                               |"
					+ "\n+----------------------------------------------------------------+");
			
			System.out.print("Choice : ");
			option = customScanner.readOnlyIntegers(sc);
			switch (option) {
				case 1: //Interact with Menu Item
					Restaurant.menu();
					break;
				case 2: //Interact with Promotion
					Restaurant.promo();
					break;
				case 3:
					//Moon
					Restaurant.createOrder();
					break;
				case 4:
					Restaurant.viewOrder();
					break;
				case 5:
					Restaurant.addRemoveOrder();
					break;
				case 6: 
					 Restaurant.createResBooking();
					break;
				case 7: 
					System.out.println("Input your choice:");
					System.out.println("1: Check reservation.");
					System.out.println("2: Remove reservation.");
				
					int answer = customScanner.readOnlyIntegers(sc);
					
					do {
						if(answer == 1)
							Restaurant.checkRes();
						else if(answer == 2)
							Restaurant.removeRes();
						else
						{
							System.out.println("Please input 1 or 2 only: ");
							answer = customScanner.readOnlyIntegers(sc);
						}
					}while(answer != 1 && answer != 2);
					break;
				case 8:
					System.out.println("Number of pax?:");
					int pax = sc.nextInt();
					TableManager tm = new TableManager();
					tm.printListTableItem(tm.getAvailableTables(pax),1);
					break;
				case 9:
					Restaurant.payPrintBill();
					break;
				case 10:
					OrderHistory oh = new OrderHistory();
					oh.viewreport();
					break;
				case 11:
					System.out.println("Terminating Program...."); 
					System.exit(1);
					break;
				case 12:
					StaffName=Restaurant.selectStaff(false,StaffName);
					break;
			}
		}
		
	}

}
