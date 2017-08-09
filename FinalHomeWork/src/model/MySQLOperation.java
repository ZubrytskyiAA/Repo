package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySQLOperation {

	
	private static int insert(Item item){
		
		
		PreparedStatement  stmt;
		Connection con = null; 
		int i = 0;
		String query = "INSERT INTO Items (ID, title, code,Producer,DateOfLastUpdate) VALUES (?, ?, ?, ?, ?);";

try {
			
	
	con = SQLConnection.getConnection();
	con.setAutoCommit(false);
	
	stmt =  con.prepareStatement(query);
			//rs = stmt.executeQuery(query);
			
		
			stmt.setInt(1,item.getId());  
			stmt.setString(2,item.getTitle());
			stmt.setInt(3,item.getCode());  
			stmt.setString(4,item.getProducer());  
			stmt.setString(5,item.getDateOfLastUpdate().toString());  
			
			i +=stmt.executeUpdate(); 
			con.commit();
				 
			
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			try {
				if(con != null)con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			
		}
return i;
		
		
		
		
		
		
		
		
		
		
	}
	
	private static int insert(Customer customer){
		
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		int i = 0;
		String query = "INSERT INTO Customers (Name, DateOfBirth, Address,Gender,PhoneNumber,DateOfLastPurchases) VALUES (?, ?, ?, ?, ?,?);";

try {
			
	
	con = SQLConnection.getConnection();
	con.setAutoCommit(false);
	
	stmt =  con.prepareStatement(query);
			//rs = stmt.executeQuery(query);
			stmt.setString(1,customer.getName());
			stmt.setString(2,customer.getDateOfBirth().toString());
			stmt.setString(3,customer.getAddress());
			stmt.setString(4,customer.getGender());
			stmt.setString(5,customer.getPhoneNumber());
			stmt.setString(6,customer.getDateOfLastPurchase().toString());
			  
			
			i +=stmt.executeUpdate(); 
			
			
			
		
			query = "select id from  customers where name = ?;";
			stmt =  con.prepareStatement(query);
			stmt.setString(1,customer.getName());
			rs = stmt.executeQuery();
				while (rs.next()) {
					customer.setId(rs.getInt("id"));
					
				}
			
			
			
			query = "INSERT INTO purchases (customerID, itemID) VALUES (?, ?);";
			
			for (int j = 0; j < customer.getLastPurchases().length; j++) {
				
				stmt =  con.prepareStatement(query);
				stmt.setInt(1,customer.getId());
				stmt.setInt(2,customer.getLastPurchases()[j]);
				i +=stmt.executeUpdate(); 
				
			}
			
			
			con.commit();
		
		 
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if(con != null)con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
return i;
		
		
		
		
		
		
		
	}
	
	
	public static void rebildAllTables(){
		
		PreparedStatement  stmt;
		Connection con = null; 
		int i = 0;
		
		

		try {

			con = SQLConnection.getConnection();
			con.setAutoCommit(false);

			stmt = con.prepareStatement("DROP TABLE if exists Purchases;");
			i += stmt.executeUpdate();

			stmt = con.prepareStatement("DROP TABLE if exists Customers;");
			i += stmt.executeUpdate();
			
			stmt = con.prepareStatement("DROP TABLE if exists Items;");
			i += stmt.executeUpdate();
			
			
			stmt = con.prepareStatement("CREATE TABLE if not exists Customers ("
					+ "	ID INT NOT NULL   auto_increment,"
					+ "	NAME nvarchar(50) NOT NULL,"
					+ "	DateOfBirth date NOT NULL,"
					+ "	Address nvarchar(100) NOT NULL,"
					+ "	Gender nvarchar(6) NOT NULL,"
					+ " PhoneNumber nvarchar(14),"
					+ " DateOfLastPurchases date NOT NULL,"
					+ " PRIMARY KEY (ID))");
			i += stmt.executeUpdate();
			
			
			stmt = con.prepareStatement("CREATE TABLE Items ("
					+ "	ID INT NOT NULL   auto_increment,"
					+ "	title nvarchar(50) NOT NULL,"
					+ "	code int NOT NULL,"
					+ " Producer nvarchar(50) NOT NULL,"
					+ "	DateOfLastUpdate datetime NOT NULL,"
					+ " PrimaryItem bool,"
					+ " CandidateToRomove bool,"
					+ "	PRIMARY KEY (ID))");
			i += stmt.executeUpdate();
			
			
			stmt = con.prepareStatement("CREATE TABLE Purchases ("
					+ "	ID INT NOT NULL   auto_increment,"
					+ "	customerID int NOT NULL,"
					+ "	itemID int NOT NULL,"
					+ "	PRIMARY KEY (ID),"
					+ "	foreign key(customerID) references Customers (ID),"
					+ " foreign key (itemID) references Items (ID))");
			i += stmt.executeUpdate();
			
			con.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void insertImportDate(List<?> obj){

		int i = 0;
		
		for (Object o : obj){
			if(o instanceof Item){
				i += insert((Item)o);
				}
			else if (o instanceof Customer){
				i += insert((Customer)o);
			}
		}
		
		System.out.println(i+" records inserted"); 
		
		
	}
	
	
	 //Tast 3.c.1
	public static Map popularGoodsByGenders(Genders gender){
		
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		Map<Integer,Integer> map = new HashMap<>();
		String query;

try {
			
	
	con = SQLConnection.getConnection();

			  query = " select p.itemID , count(p.itemID) volume"
					+ " from Customers c"
					+ " left join purchases p on p.customerID = c.id"
					+ " where Gender = ?"
					+ " group by p.itemID"
					+ " order by volume desc";
			stmt =  con.prepareStatement(query);
			stmt.setString(1,gender.toString());
			rs = stmt.executeQuery();
				while (rs.next()) {
					map.put(rs.getInt("itemID"), rs.getInt("volume"));
					
				}
			
			 
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}
// для наглядности
for (Map.Entry<Integer, Integer> m : map.entrySet()){
	 System.out.println( "ID of Goods is " + m.getKey() + ", Volume of Goods is " + m.getValue());
}

return map;
		
		
	}

	
	//Tast 3.c.2
	public static Map popularGoodsByWeek(LocalDate startDate){
		
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		Map<Integer,Integer> map = new HashMap<>();
		String query;


		
try {
			
	
	con = SQLConnection.getConnection();

			  query = " select p.itemID , count(p.itemID) volume"
			  		+ " from customers c"
			  		+ " left join purchases p  on p.customerID = c.id"
			  		+ " where c.DateOfLastPurchases >= ? and c.DateOfLastPurchases < ?"
			  		+ " group by p.itemID"
			  		+ " order by volume desc";
			stmt =  con.prepareStatement(query);
			stmt.setString(1,startDate.toString());
			stmt.setString(2,startDate.plusWeeks(1).toString());
			rs = stmt.executeQuery();
				while (rs.next()) {
					map.put(rs.getInt("itemID"), rs.getInt("volume"));
					
				}
			
			 
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}
// для наглядности
for (Map.Entry<Integer, Integer> m : map.entrySet()){
	 System.out.println( "ID of Goods is " + m.getKey() + ", Volume of Goods is " + m.getValue());
}

return map;
		
		
	}
	
	
	
	public static List<Integer> primaryItem(){
		
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		List<Integer> list = new ArrayList();
		String query;
		int i = 0;

try {
			
	
	con = SQLConnection.getConnection();

			  query = " select  p.itemID , count(p.itemID) volume"
			  		+ " from purchases p"
			  		+ " group by p.itemID"
			  		+ " order by volume desc"
			  		+ " limit 3";
			stmt =  con.prepareStatement(query);
			rs = stmt.executeQuery();
				while (rs.next()) {
					list.add(rs.getInt("itemID"));
					
				}
			
			 
				
				
				con.setAutoCommit(false);

				for (int j = 0; j < list.size(); j++) {
					
				stmt = con.prepareStatement("update items "
						+ "set PrimaryItem = 1 , CandidateToRomove = null "
						+ "where id = ?");
				stmt.setInt(1, list.get(j));
				i += stmt.executeUpdate();
				con.commit();
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}



return list;
		
		
		
		
		
	}
	
	
	public static List<Integer> candidateToRemove(){
		
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		List<Integer> list = new ArrayList();
		String query;
		int i = 0;

try {
			
	
	con = SQLConnection.getConnection();

			  query = " select  p.itemID , count(p.itemID) volume"
			  		+ " from purchases p"
			  		+ " group by p.itemID"
			  		+ " order by volume "
			  		+ " limit 3";
			stmt =  con.prepareStatement(query);
			rs = stmt.executeQuery();
				while (rs.next()) {
					list.add(rs.getInt("itemID"));
					
				}
			
			 
				
				
				con.setAutoCommit(false);

				for (int j = 0; j < list.size(); j++) {
					
				stmt = con.prepareStatement("update items "
						+ "set PrimaryItem = null , CandidateToRomove = 1 "
						+ "where id = ?");
				stmt.setInt(1, list.get(j));
				i += stmt.executeUpdate();
				con.commit();
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}



return list;
		
		
		
		
		
		
	}

	
	public static void exportPrimaryItems(){
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		//List<Integer> list = new ArrayList();
		String query;
		StringBuilder sb = new StringBuilder();
	

try {
			
	
	con = SQLConnection.getConnection();

	
			  query = " select ifnull(id , '') id, ifnull(title , '') title,ifnull(code , '') code ,ifnull(Producer , '') Producer  ,ifnull(DateOfLastUpdate , '') DateOfLastUpdate  , ifnull(PrimaryItem, '') PrimaryItem, ifnull(CandidateToRomove, '') CandidateToRomove"
			  		+ " from Items"
			  		+ " where PrimaryItem = 1;";
			stmt =  con.prepareStatement(query);
			rs = stmt.executeQuery();
			int colums = rs.getMetaData().getColumnCount();
			for (int i = 1; i < colums + 1; i++) {
				sb.append((rs.getMetaData().getColumnName(i)));
				if (i != colums){
					sb.append(";");
				}else{
					sb.append("\r\n");
				}
				
			}
			
			
				while (rs.next()) {
					for (int i = 1; i < colums + 1; i++) {
						sb.append(rs.getString(i));
						if (i != colums){
							sb.append(";");
						}
					}
					sb.append("\r\n");
				
					//list.add(rs.getInt("itemID"));
					
				}
			
			 
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}


saveToFile("PrimaryItems.csv", sb);

		
	}
	
	
	public static void exportCandidateToRemove(){
		
		PreparedStatement  stmt;
		ResultSet rs;
		Connection con = null; 
		List<Integer> list = new ArrayList();
		String query;
		StringBuilder sb = new StringBuilder();
	

try {
			
	
	con = SQLConnection.getConnection();

	
			  query = " select ifnull(id , '') id, ifnull(title , '') title,ifnull(code , '') code ,ifnull(Producer , '') Producer  ,ifnull(DateOfLastUpdate , '') DateOfLastUpdate  , ifnull(PrimaryItem, '') PrimaryItem, ifnull(CandidateToRomove, '') CandidateToRomove"
			  		+ " from Items"
			  		+ " where CandidateToRomove = 1;";
			stmt =  con.prepareStatement(query);
			rs = stmt.executeQuery();
			int colums = rs.getMetaData().getColumnCount();
			
			for (int i = 1; i < colums + 1; i++) {
				sb.append((rs.getMetaData().getColumnName(i)));
				if (i != colums){
					sb.append(";");
				}else{
					sb.append("\r\n");
				}
				
			}
			
			
				while (rs.next()) {
					for (int i = 1; i < colums + 1; i++) {
						sb.append(rs.getString(i));
						if (i != colums){
							sb.append(";");
						}
					}
					sb.append("\r\n");
				
					//list.add(rs.getInt("itemID"));
					
				}
			
			 
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}

saveToFile("CandidateToRemove.csv", sb);
		
	}
	
	
	private static void saveToFile(String name, StringBuilder sb){
	
		try(FileOutputStream fos = new FileOutputStream(name)){
			byte[] buffer = sb.toString().getBytes();
			
			fos.write(buffer , 0 ,buffer.length);
		}catch (IOException e) {
			// TODO: handle exception
			e.getMessage();
		}
		
	}
	
	public enum Genders{
		 female, male
	}

	
}



