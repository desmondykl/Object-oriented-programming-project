package Class;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a Staff class. 
 * It represents the staff working in the restaurant 
 * It implements serializable to allow us to serialize and deserialize to a file (Staff.dat)
 * 
 * @author Muhammad Hisyam Jukifli
 * @version 1.0
 * @since 2019-04-18
 * 
 */
public class Staff implements Serializable{
	private String Name;
	private String Gender;
	private int EmployeeID;
	private String JobTitle;

	/**
	 * Gets the details of a particular the staff from the given staffList arraylist based on employee ID
	 * @param id The id of the particular staff
	 * @param staffList An Arraylist of StaffList
	 * @return s.getName Gets the stafff name
	 */
	public String getName(int id,ArrayList<Staff> staffList) {
		for(Staff s : staffList){
			if(s.getEmployeeID()==id)
				return s.getName();
		}
		return "";
	}
	
	/**
	 * Gets the name of particular the staff
	 * @return name of the staff working in the restaurant 
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Sets the staff name 
	 * @param name name of the staff working in the restaurant 
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * Gets the gender of particular the staff
	 * @return gender of the staff working in the restaurant 
	 */
	public String getGender() {
		return Gender;
	}
	/**
	 * Sets the staff gender 
	 * @param gender of the staff working in the restaurant 
	 */
	public void setGender(String gender) {
		Gender = gender;
	}
	/**
	 * Gets the employeeID of particular the staff
	 * @return EmployeeID staffID of the staff working at the restaurant 
	 */
	public int getEmployeeID() {
		return EmployeeID;
	}
	/**
	 * Sets the EmployeeID 
	 * @param employeeID staffID of the staff working at the restaurant 
	 */
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	/**
	 * Gets the job title of a particular the staff
	 * @return JobTitle job title of the staff working at the restaurant (Waiter, cook, cashier etc) 
	 */
	public String getJobTitle() {
		return JobTitle;
	}
	/**
	 * Sets the Job Title 
	 * @param jobTitle job title of the staff working at the restaurant (Waiter, cook, cashier etc) 
	 */
	public void setJobTitle(String jobTitle) {
		JobTitle = jobTitle;
	}
	
	/**
	 * Function to return a false or true value depending if the staff ID exists after looping through the staffList arraylist 
	 * @param id Staff id 
	 * @param staffList Arraylist of staff
	 * @return true Employee id exists 
	 */
	public boolean validStaff(int id,ArrayList<Staff> staffList){
		for(Staff s : staffList){
			if(s.getEmployeeID() == id){
				return true;
			}
		}
		return false;
	}

}
