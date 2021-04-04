package Class;

/**
* An interface for generation of ID
* @author Desmond95
* @version 9.0
* @since 2019/04/01
*/

public interface GenerateId {
   /**
    * This function will be used for generating int value for ID
    * @return int value of the generated ID
    */
   public abstract int genRandomInt();
   /**
    * This function will be used to check for duplicate ID in .dat file
    * @param listType Type of checking, eg OrderID, PromoID and AlacartID
    * @param newRand ID used for checking if duplicate exist 
    * @return int value of the duplicate IDs
    */
   public abstract int checkDuplicateIDs(String listType, int newRand);
}