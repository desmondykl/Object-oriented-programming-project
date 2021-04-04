package Class;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import wagu.Block;
import wagu.Board;
import wagu.Table;

import Data.DataManager;

/**
 * This is a OrderHistory Class
 * It represent the List of order record that the restaurant had.
 * Used for printing of reports
 * @author Yeo Kok Leong Desmond
 * @version 1.0
 * @since 2019-04-12
 *
 */
public class OrderHistory {
	/**
	 * ArrayList of paid Ordering history
	 */
	ArrayList<Ordering> orderHistory = new ArrayList<Ordering>();
	
	/**
	 * OrderHistory Constructor
	 * When OrderHistory is created it will load the order history data from OrderHistory.dat file
	 */
	public OrderHistory(){
		if(DataManager.readSerializedObject("OrderHistory.dat")==null)
			orderHistory = new ArrayList<Ordering>();
		else			
			orderHistory =(ArrayList) DataManager.readSerializedObject("OrderHistory.dat");
	}
	/**
	 * This function will update the orderHistory.dat file after the order payment is made
	 * @param orderHistory Ordering Object
	 */
	public void updateHistory(Ordering orderHistory){
		this.orderHistory.add(orderHistory);
		DataManager.writeSerializedObject("OrderHistory.dat",this.orderHistory);
	}

	/**
	 * View the financial report of the restaurant
	 * Staff will require to enter the start/end date for the financial report they want to view
	 * It will print the Total Revenue and orders made
	 * The top 3 most ordered Alacarteitem and Promotion
	 */
	public void viewreport(){
		Scanner sc = new Scanner(System.in);
		Date startdate = null;
		Date enddate = null;
		double totalRevenue = 0;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		String sd="";
		boolean Notsuccess = true;
		//Scan for start date
		System.out.println("Enter start date (dd/MM/yy)");
		System.out.println("<Enter -1 to back>");
		startdate = customScanner.readOnlyDate(sc);
		if(startdate==null){
			return;
		}
		System.out.println("Enter end date (dd/MM/yy)");
		System.out.println("<Enter -1 to back>");
		enddate = customScanner.readOnlyDate(sc);
		if(enddate==null){
			return;
		}
		boolean hasMenu = false;
		boolean hasPromo = false;
		System.out.println("Revenue report on "+formatter.format(startdate)+" to "+formatter.format(enddate));
		ArrayList<Ordering> orHistory = (ArrayList) DataManager.readSerializedObject("OrderHistory.dat");
		if(orHistory==null || orHistory.isEmpty()){
			System.out.println("There are no recorded found during this period");
			return ;
		}
		int count =0;
		int itemcount= 0;
		ArrayList<Integer> mostOrderID = new ArrayList<Integer>();
		ArrayList<Integer> pOrderID = new ArrayList<Integer>();
		ArrayList<Integer> uID = new ArrayList<Integer>();	
		ArrayList<Integer> upID = new ArrayList<Integer>();	
		ArrayList<Ordering> activeOrder = new ArrayList<Ordering>();
		for (Ordering order:orHistory){
			Date inDate = order.getDate();
			if ( (inDate.after(startdate) && inDate.before(enddate))
					||(formatter.format(inDate).equals(formatter.format(startdate)))
					||(formatter.format(inDate).equals(formatter.format(enddate)))){
				totalRevenue+=order.getTotalPrice();
				count++;
				activeOrder.add(order);
				for(AlaCarteItem ala: order.getMenuList().menuitem){
		        	boolean u = true;
		        	for(Integer i :uID){
		        		if(ala.getMenuID()==i)
		        			u=false;
		        	}
		        	if(u)
		        		uID.add(ala.getMenuID());
		        }
				for(Promotion p: order.getMenuList().promoitem){
		        	boolean u = true;
		        	for(Integer i :upID){
		        		if(p.getPromoID()==i)
		        			u=false;
		        	}
		        	if(u)
		        		upID.add(p.getPromoID());
		        }
			}
		}
		if(count!=0){
			for(Integer i : upID){
				hasPromo=true;
				itemcount=0;
				for (Ordering order:activeOrder){
					for(Promotion p: order.getMenuList().promoitem){
						if(p.getPromoID()==i)
							itemcount++;
						}
				}
				pOrderID.add(itemcount);
			}
			for(Integer i : uID){
				hasMenu=true;
				itemcount=0;
				for (Ordering order:activeOrder){
						for(AlaCarteItem ala: order.getMenuList().menuitem){
							if(ala.getMenuID()==i){
								itemcount++;
							}
						}
				}
				mostOrderID.add(itemcount);
			}
			int maxVal = 0,maxIdx = 0,maxItemID= 0;
			int maxVal2= 0,maxIdx2 = 0,maxItemID2= 0;
			int maxVal3= 0,maxIdx3= 0,maxItemID3= 0;
			AlaCarteItem MostorderAla = new AlaCarteItem(); AlaCarteItem MostorderAla2 = new AlaCarteItem(); AlaCarteItem MostorderAla3 = new AlaCarteItem();
			Promotion Mostorderpromo = new Promotion();
			List<List<String>> t2Rows= new ArrayList<List<String>>();
			List<List<String>> t3Rows= new ArrayList<List<String>>();

			if(!mostOrderID.isEmpty()){
				maxVal = Collections.max(mostOrderID);
				maxIdx = mostOrderID.indexOf(maxVal); 
				maxItemID =  uID.get(maxIdx);
				for (Ordering order:activeOrder){
					for(AlaCarteItem ala: order.getMenuList().menuitem){
						if(ala.getMenuID()==maxItemID){
							MostorderAla=ala;
							break;
						}
					}
				}
				String str[] = new String[4]; 
				str[0]=MostorderAla.getName();str[1]=String.valueOf(MostorderAla.getPrice());
				str[2]=String.valueOf(maxVal);str[3]=String.valueOf(maxVal*MostorderAla.getPrice());
				t2Rows.add(Arrays.asList(str));
				mostOrderID.remove(maxIdx);
				uID.remove(maxIdx);
			}

			if(!mostOrderID.isEmpty()){
				maxVal2 = Collections.max(mostOrderID);
				maxIdx2 = mostOrderID.indexOf(maxVal2); 
				maxItemID2 =  uID.get(maxIdx2);
				for (Ordering order:activeOrder){
					for(AlaCarteItem ala: order.getMenuList().menuitem){
						if(ala.getMenuID()==maxItemID2){
							MostorderAla2=ala;
							break;
						}
					}
				}
				String str2[] = new String[4]; 
				str2[0]=MostorderAla2.getName();str2[1]=String.valueOf(MostorderAla2.getPrice());
				str2[2]=String.valueOf(maxVal2);str2[3]=String.valueOf(maxVal2*MostorderAla2.getPrice());
				t2Rows.add(Arrays.asList(str2));
				mostOrderID.remove(maxIdx2);
				uID.remove(maxIdx2);
			}

			if(!mostOrderID.isEmpty()){
				maxVal3 = Collections.max(mostOrderID);
				maxIdx3 = mostOrderID.indexOf(maxVal3); 
				maxItemID3 =  uID.get(maxIdx3);
				for (Ordering order:orHistory){
					Date inDate = order.getDate();
					if ( (inDate.after(startdate) && inDate.before(enddate))
							||(formatter.format(inDate).equals(formatter.format(startdate)))
							||(formatter.format(inDate).equals(formatter.format(enddate)))){
						for(AlaCarteItem ala: order.getMenuList().menuitem){
							if(ala.getMenuID()==maxItemID3){
								MostorderAla3=ala;
								break;
							}
						}
					}
				}
				String str3[] = new String[4]; 
				str3[0]=MostorderAla3.getName();str3[1]=String.valueOf(MostorderAla3.getPrice());
				str3[2]=String.valueOf(maxVal3);str3[3]=String.valueOf(maxVal3*MostorderAla3.getPrice());
				t2Rows.add(Arrays.asList(str3));
			}
			//promotion
			for(int k = 0;k<3; k++){
				if(!upID.isEmpty()){
					maxVal = Collections.max(pOrderID);
					maxIdx = pOrderID.indexOf(maxVal); 
					maxItemID =  upID.get(maxIdx);
					for (Ordering order:activeOrder){
						for(Promotion p: order.getMenuList().promoitem){
							if(p.getPromoID()==maxItemID){
								Mostorderpromo=p;
								break;
							}
						}
					}
					String str[] = new String[4]; 
					str[0]=Mostorderpromo.getName();
					str[1]=String.valueOf(Mostorderpromo.getPrice());
					str[2]=String.valueOf(maxVal);
					str[3]=String.valueOf(maxVal*Mostorderpromo.getPrice());
					t3Rows.add(Arrays.asList(str));
					pOrderID.remove(maxIdx);
					upID.remove(maxIdx);
				}
				else
					break;
			}
			
			
			DecimalFormat df = new DecimalFormat("#.00"); 
			String t1Desc = "SUMMARY DETAILS";
			String summary ="Total Orders Made       |"+""+(count) +"\n"
					       +"Total Revenue           |"+"$"+df.format(totalRevenue);
			String t2Desc = "TOP 3 MOST ORDERED ITEM";
			String t3Desc = "TOP 3 MOST ORDERED PROMO";
			//top menu
			List<Integer> t2ColWidths = Arrays.asList(20, 9, 5, 12);
			List<String> t2Headers = Arrays.asList("ALACARTE ITEM", "PRICE($)", "QTY", "VALUE");
			List<String> t3Headers = Arrays.asList("PROMO ITEM", "PRICE($)", "QTY", "VALUE");
	        
			//06/04/19
			Board b = new Board(55);
	        b.setInitialBlock(new Block(b, 49, 1, t1Desc).setDataAlign(Block.DATA_CENTER));
	        Block nameBlock = new Block(b, 49, 2,summary);
	        b.getBlock(0).setBelowBlock((nameBlock.setDataAlign(Block.DATA_MIDDLE_LEFT)));
	        
	        //promo
	        if(hasPromo && hasMenu){
	        	b.getBlock(1).setBelowBlock(new Block(b, 49, 1, t2Desc).setDataAlign(Block.DATA_CENTER));
		        b.appendTableTo(2, Board.APPEND_BELOW, new Table(b, 55, t2Headers, t2Rows, t2ColWidths));
	        	b.getBlock(7).setBelowBlock(new Block(b, 49, 1, t3Desc).setDataAlign(Block.DATA_CENTER));
		        b.appendTableTo(11, Board.APPEND_BELOW, new Table(b, 55, t3Headers, t3Rows, t2ColWidths));
	        }
	        else if(hasMenu && !hasPromo){
	        	b.getBlock(1).setBelowBlock(new Block(b, 49, 1, t2Desc).setDataAlign(Block.DATA_CENTER));
		        b.appendTableTo(2, Board.APPEND_BELOW, new Table(b, 55, t2Headers, t2Rows, t2ColWidths));
	        }
	        else if(!hasMenu && hasPromo){
	        	b.getBlock(1).setBelowBlock(new Block(b, 49, 1, t3Desc).setDataAlign(Block.DATA_CENTER));
		        b.appendTableTo(2, Board.APPEND_BELOW, new Table(b, 55, t3Headers, t3Rows, t2ColWidths));
	        }
	        
	        
	        System.out.println(b.getPreview());
		}
		else{
			System.out.println("There are no recorded found during this period");
		}
	}
	
}
