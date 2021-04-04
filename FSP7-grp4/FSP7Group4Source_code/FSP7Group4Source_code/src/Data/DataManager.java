package Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the Data Control class
 * It is used for reading and wrting serializable code 
 * Read Function - Read the file and return as an array list
 * Write Function - Get the file and write it as an array list 
 * @author Muhammad Hisyam bin Jukifli
 * @version 1.0
 * @since 2019-04-18
 * 
 */
public class DataManager {

	/**
	 * Reading from the dat file and return as an array list 
	 * @param filename filename to read the .dat file 
	 * @return pDetails reading each object from the List
	 */
	public static List readSerializedObject(String filename) {
		
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		}catch (FileNotFoundException ex){
			return (ArrayList)pDetails;
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}finally {  }
		return pDetails;
	}
	
	/**
	 * Write to the dat file with all the arraylist items
	 * @param filename The Filename to write to the .dat file 
	 * @param list List to loop and add to the .dat file 
	 */
	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
