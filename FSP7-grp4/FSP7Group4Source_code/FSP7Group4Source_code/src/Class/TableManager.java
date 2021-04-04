package Class;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wagu.Block;
import wagu.Board;
import Data.DataManager;

/**
 * TableManager Class that will managed the tables that the restaurant had
 * It will perform function like free table, printing available tables and setting table to occupied
 * 
 * @author Jess Tan Jing Yi
 * @version 1.0
 * @since 2019-04-12
 */
public class TableManager {
	/**
	 * ArrayList of table it will be static because there should only be 1 set of tablelist
	 */
	static ArrayList<Table> tableList = new ArrayList<Table>();
	
	/**
	 * TableManager constructor it will load the table.dat file when it is created
	 */
	public TableManager(){
		this.tableList = (ArrayList) DataManager.readSerializedObject("Table.dat");
	}
	
	/**
	 * Transform the ArrayList into List string for printing
	 * With table status list of 'Occupied' and 'Unoccupied'
	 * @param tabList ArrayList of tables
	 * @return t2Rows an object of the specified arraylist 
	 */
	public List<List<String>> GetStringArrayF(ArrayList<Table> tabList) {

		List<List<String>> t2Rows = new ArrayList<List<String>>();
		for (Table tab : tabList) {
			String str[] = new String[3];
			str[0] = String.valueOf(tab.getTableID());
			str[1] = tab.getStatus();
			str[2] = String.valueOf(tab.getPax());
			t2Rows.add(Arrays.asList(str));
		}
		return t2Rows;
	}
	/**
	 * Prints the Table List with the following headers
	 * Table ID, Status, Capacity
	 * @param tableLists ArrayList of tables
	 * @param type 
	 * - Type 1 is for inactive orders (No order for the table yet) 
	 * - Type 2 are for active orders (Orders that have been taken by the staff) 
	 */
	public void printListTableItem(ArrayList<Table> tableLists,int type) {
		// ArrayList<Table> tableListing = getAvailableTables();
		List<String> headersList = Arrays.asList("TABLE ID", "STATUS","CAPACITY");
		List<List<String>> data = null;
		if(type==1)
			data = GetStringArrayF(tableLists);
		else if(type==2)
			data = GetStringArrayU(tableLists);
		if (!data.isEmpty()) {
			Board board = new Board(100);
			wagu.Table table = new wagu.Table(board, 100, headersList, data);
			table.setGridMode(wagu.Table.GRID_COLUMN);
			// setting width and data-align of columns
			List<Integer> colWidthsList = Arrays.asList(10, 15, 12);
			List<Integer> colAlignList = Arrays.asList(Block.DATA_CENTER,
					Block.DATA_CENTER, Block.DATA_CENTER);
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
	 * Transform the ArrayList into List string for printing
	 * Instead of showing status 'Occupied' for table, show status of 'Unpaid' since it is already an active order 
	 * @param tabList ArrayList of tables 
	 * @return t2Rows (List(String)) of table details
	 */
	public static List<List<String>> GetStringArrayU(ArrayList<Table> tabList) {

		List<List<String>> t2Rows = new ArrayList<List<String>>();
		for (Table tab : tabList) {
			String str[] = new String[3];
			str[0] = String.valueOf(tab.getTableID());
			str[1] = "Unpaid";
			str[2] = String.valueOf(tab.getPax());
			t2Rows.add(Arrays.asList(str));
		}
		return t2Rows;
	}
	/**
	 * Method overloading
	 * get the available tables based on the pax
	 * @param pax the total customer pax
	 * @return AvailtableList a list of tables 
	 */
	public ArrayList<Table> getAvailableTables(int pax) {
		// Desmond
		tableList = setReservedTable();
		ArrayList<Table> AvailtableList = new ArrayList<Table>();
		for (Table t : tableList) {
			if (t.getStatus().equals("Unoccupied")) {
				if (t.getPax() >= pax) {
					AvailtableList.add(t);
				}
			}
		}
		
		if(AvailtableList.isEmpty())
		{
			System.out.println("No unoccupied tables for " + pax + " pax!");
		}
		return AvailtableList;
	}
	/**
	 * Method overloading
	 * get the available table based on the pax and TableList for different days
	 * 
	 * @param pax total customer pax 
	 * @param newTable arraylist of tables 
	 * @return AvailtableList a list of tables 
	 */
	public ArrayList<Table> getAvailableTables(int pax,ArrayList<Table> newTable) {
		// Desmond
		tableList = setReservedTable();
		ArrayList<Table> AvailtableList = new ArrayList<Table>();
		for (Table t : newTable) {
			if (t.getStatus().equals("Unoccupied")) {
				if (t.getPax() >= pax) {
					AvailtableList.add(t);
				}
			}
		}
		return AvailtableList;
	}
	
	/** 
	 * Sets reserved table for that day. 
	 * 
	 * Checks current session based on current time. 
	 * Loops through reservation list 
	 * If the session and date of that row in the list is the same as current date and time,
	 * 	   get table ID of that reservation
	 *     loops through list of table. if same table ID, sets that table's status as 'Reserved'
	 *     
	 * Returns tableList (list of table)
	 * 
	 * @return tableList ArrayList of tables 
	 */
	public ArrayList<Table> setReservedTable() {
		String session = null;

		Timestamp curDate = Main.getcurrentDate();
		int time = curDate.getHours();

		if(time <= 15)// time >= 11 && -remove
		{
			session = "AM";
		}
		else if(time >= 18)// && time <= 22
		{
			session = "PM";
		}
		int tableID = -1;

		ArrayList<Reservation> rsvpList = null;
		if (DataManager.readSerializedObject("ReservationList.dat") == null || DataManager.readSerializedObject("ReservationList.dat").isEmpty()) {
			return tableList;
		} else
			rsvpList = (ArrayList) DataManager.readSerializedObject("ReservationList.dat");

		for (Reservation r : rsvpList) {
			if (r.getSession().equals(session)
					&& r.getDate().getDay() == curDate.getDay()
					&& r.getDate().getMonth() == curDate.getMonth()
					&& r.getDate().getYear() == curDate.getYear()) {
				// same session, same date. thus got RSVP
				// update in table list: tableID = reserved

				tableID = r.getTableID();
				// update tableID = reserved
				for (Table t : tableList) {
					if (t.getTableID() == tableID) // if correct tableID
					{
						if(t.getStatus().equals("Unoccupied")) {
							t.setStatus("Reserved");
							break;
						}
					}
				}
			}
		}
		return tableList;
	}

	
	/**
	 * Checks if table is available for today, based on pax size
	 * 
	 * Loops through tableList
	 *     if table status is 'Unoccupied' and the table is able to accommodate to pax's size
	 *     set available = true
	 *     
	 * Returns available (boolean)
	 *     true = table is available 
	 *     false = table is not available
	 * 
	 * @param pax Total no of customer pax
	 * @return available True = Table Available , False = Table Unavailable 
	 */
	public boolean checkTableAvail(int pax) 
	{
		boolean available = false;
		ArrayList<Table> tableLister = getAvailableTables(pax);
		printListTableItem(tableLister,1);
		for (Table t : tableList) {
			if (t.getStatus().equals("Unoccupied")) {
				if (t.getPax() >= pax) {
					available = true;
					break;
				}
			}
		}
		return available;
	}
	/**
	 * Checks for occupied tables. 
	 * If exists an occupied table, there is an order and it will return hasOrder == true.
	 * Else, it will print error message and return hasOrder == false.
	 * 
	 * @return hasOrder returns true or false if the table has order or not  
	 */
	public boolean printUnpaidTable() {
		boolean hasOrder = false;
		ArrayList<Table> temp = new ArrayList<Table>();
		for (Table t : tableList) {
			if (t.getStatus().equals("Occupied")) {
				temp.add(t);
				hasOrder = true;
			}
		}
		if (!hasOrder) {
			System.out.println("No pending order!");
			return hasOrder;
		} else {
			printListTableItem(temp,2);
		}
		return hasOrder;
	}
	/**
	 * Set table status of that tableID as 'Occupied'
	 * @param id return a tableID with status occupied 
	 */
	public void setTableOcc(int id){
		for(Table t : tableList)
		{
			if(t.getTableID() == id)
			{
				t.setStatus("Occupied");
				break;
			}
		}
	}
	/**
	 * Set table status of that tableID as 'Unoccupied'
	 * @param id table ID return tableID with status unoccupied 
	 */
	public void freeTable(int id){
		for(Table t : tableList)
		{
			if(t.getTableID() == id)
			{
				t.setStatus("Unoccupied");
				break;
			}
		}
	}
}
