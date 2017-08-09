package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnection {

	
private static Connection con;
	

	private static void createConn(){
	
		Properties props = new Properties();
		InputStreamReader in;
				
		try {
			
			in = new InputStreamReader(new FileInputStream("appProperties.txt"), "UTF-8");
			props.load(in);
			in.close();
			
			String driver = props.getProperty("Driver");
			String connString = props.getProperty("DBConnectionString");
			String name = props.getProperty("Name");
			String password = props.getProperty("Password");
			Class.forName(driver);
			con = DriverManager.getConnection(connString , name , password);
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	
		
		
	}
	
	
	
	public static Connection getConnection()  {

		if (con == null){
			createConn();
		}
		return con;
	}
	
}
