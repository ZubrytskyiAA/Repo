package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class importCSV {

	public static ArrayList<String[]> CSVReader(File url) {

		
		ArrayList<String[]> customeesTable = new ArrayList<>();
		BufferedReader bufReader;
		String l;

		try {
			bufReader = new BufferedReader(new FileReader(url.getAbsolutePath()));

			while ((l = bufReader.readLine()) != null) {
				if (l.contains(";")) {

					customeesTable.add(l.split(";"));

				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return customeesTable;
	}

	public static List<Customer> createCustomersListAfterImport(File url) {
		List<Customer> customersList = new ArrayList<>();
		ArrayList<String[]> customeesTable = CSVReader(url);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
		DateTimeFormatter formatPurchase = DateTimeFormatter.ofPattern("M/d/yyyy");

		String[] fildsName;
		if (customeesTable.size() > 1) {
			fildsName = customeesTable.get(0);

			for (int i = 1; i < customeesTable.size(); i++) {
				Customer customer = new Customer();

				for (int j = 0; j < fildsName.length; j++) {

					switch (fildsName[j]) {

					case "Name":
						customer.setName(customeesTable.get(i)[j]);
						break;

					case "DateOfBirth":
						customer.setDateOfBirth(LocalDate.parse(customeesTable.get(i)[j], format));
						break;

					case "Address":
						customer.setAddress(customeesTable.get(i)[j]);
						break;

					case "Gender":
						customer.setGender(customeesTable.get(i)[j]);
						break;

					case "PhoneNumber":
						customer.setPhoneNumber(customeesTable.get(i)[j]);
						break;

					case "LastPurchases":

						String[] stringArray = customeesTable.get(i)[j].replace('"', ' ').trim().split(",");
						int[] intArray = new int[stringArray.length];
						for (int k = 0; k < stringArray.length; k++) {
							intArray[k] = Integer.parseInt(stringArray[k]);

						}

						customer.setLastPurchases(intArray);
						break;

					case "DateOfLastPurchase":
						customer.setDateOfLastPurchase(LocalDate.parse(customeesTable.get(i)[j], formatPurchase));

						break;

					}
				}

				customersList.add(customer);

			}
		}
		return customersList;

	}

	public static List<Item> createItemsListAfterImport(File url) {
		List<Item> itemsList = new ArrayList<>();
		ArrayList<String[]> itemsTable = CSVReader(url);
System.out.println("");
		DateTimeFormatter formatdateOfLastUpdate = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");

		String[] fildsName;
		if (itemsTable.size() > 1) {
			fildsName = itemsTable.get(0);

			for (int i = 1; i < itemsTable.size(); i++) {
				Item item = new Item();

				for (int j = 0; j < fildsName.length; j++) {

					switch (fildsName[j]) {

					case "id":
						item.setId(Integer.parseInt(itemsTable.get(i)[j]));
						break;

					case "title":
						item.setTitle(itemsTable.get(i)[j]);
						break;

					case "code":
						item.setCode(Integer.parseInt(itemsTable.get(i)[j]));
						break;

					case "producer":
						item.setProducer(itemsTable.get(i)[j]);
						break;

					case "dateOfLastUpdate":
						item.setDateOfLastUpdate(LocalDateTime.parse(itemsTable.get(i)[j], formatdateOfLastUpdate));
						break;

					}
				}

				itemsList.add(item);

			}
		}
		return itemsList;

	}


	
	
	
	
	
	
}
