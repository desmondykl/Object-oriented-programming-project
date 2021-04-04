package Class;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wagu.Block;
import wagu.Board;

import Data.DataManager;

/**
 * This is a table class. 
 * It represents the table that the restaurant has.
 * It implements serializable to allow us to serialize and deserialize to a file (Table.dat)
 * 
 * @author Jess Tan Jing Yi
 * @version 1.0
 * @since 2019-04-12
 * 
 */
public class Table implements Serializable{
	private String status;
	private int tableID;
	private int pax;
	
	public Table() {

	}
	/**
	 * This is a table constructor.
	 * 
	 * @param tableID tableID of table
	 * @param status status of table
	 * @param pax pax of table 
	 * @param contactNo contact number of the customer 
	 */
	public Table(int tableID, String status, int pax, int contactNo) {
		super();
		this.status = status;
		this.tableID = tableID;
		this.pax = pax;
	}
	/**
	 * Get status of the table (Unoccupied/Occupied/Reserved)
	 * @return status status of the table 
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Set the status of the table (Unoccupied/Occupied/Reserved)
	 * @param status status of the table
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * the table ID of a particular table in the restaurant 
	 * @return tableID the table ID of a particular table in the restaurant 
	 */
	public int getTableID() {
		return tableID;
	}
	/**
	 * Sets the tableID 
	 * @param tableID the table ID of a particular table in the restaurant 
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	/**
	 * 
	 * @return pax the total amount of occupants at a table 
	 */
	public int getPax() {
		return pax;
	}
	/**
	 * Sets the pax size allowed for that table
	 * @param pax total capacity of table 
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}
	
	
	
}
