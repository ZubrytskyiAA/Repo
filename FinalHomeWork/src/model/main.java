package model;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class main {


	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File urlCustomer = new File("d:\\Customers.csv");
		File urlItem = new File("d:\\items.csv");

		
//		 List<Customer> customers= importCSV.createCustomersListAfterImport(urlCustomer);
//		 List<Item> items= importCSV.createItemsListAfterImport(urlItem);
		 
//		 MySQLOperation.rebildAllTables();
//		 
//		 MySQLOperation.insertImportDate(items);
//		 MySQLOperation.insertImportDate(customers);
		 
		 //Tast 3.c.1
		// MySQLOperation.popularGoodsByGenders(Genders.male);
	
			
		 //Tast 3.c.2
//		LocalDate localDate = LocalDate.parse("12.06.2017", DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH));
//		MySQLOperation.popularGoodsByWeek(localDate);
		
		 //Tast 3.d.2 (primaryItem)
//		List<Integer> dd =MySQLOperation.primaryItem();

		 //Tast 3.e	
		MySQLOperation.exportPrimaryItems();
		MySQLOperation.exportCandidateToRemove();
		
		
//		for (int x : dd){
//			System.out.println("for good " + x);
//		}
		 
		//Tast 3.d.2 (CandidatetoRemove)
//		List<Integer> dd1 =MySQLOperation.candidateToRemove();
//		
//		for (int x : dd1){
//			System.out.println("for remove " + x);
//		}
		
		
		 

		

	
		 
		 
	}

}
