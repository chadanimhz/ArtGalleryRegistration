package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Event;
import model.Price;
import model.Registration;
import model.User;

public class DbConnection {
	
	public Connection getCon() {
		Connection con;
		try {
			String dbPath = "jdbc:sqlite:C:\\Users\\chada\\OneDrive\\Documents\\artGalleryDb.db";
			con = DriverManager.getConnection(dbPath);
			return con;
		}catch (Exception e) {
			System.out.print(e);
		}
		return null;
	}
	
	public int insert(String sql) {
		Connection con=getCon();
		
		try {	
			PreparedStatement prepStat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepStat.execute();
			ResultSet rs = prepStat.getGeneratedKeys();
			int ret=0;
			if(rs.next()) {
				 ret= rs.getInt(1);
			}
			 con.close();
			 return ret;
			  
		}catch(Exception e) {
			System.out.print(e);
		}
		return 0;
	}
	
	public ArrayList<Event> getEvents() {
		Connection con=getCon();
		String sql="Select * from event";
		ArrayList<Event> eventList=new ArrayList<>();
		
		try {			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
					Event event=new Event();
					event.setId(rs.getInt("id"));
					event.setName(rs.getString("name"));
					event.setDate(rs.getString("date"));
					event.setVenue(rs.getString("venue"));
					event.setChildRate(rs.getDouble("child_rate"));
					event.setAdultRate(rs.getDouble("adult_rate"));
					event.setAgedRate(rs.getDouble("aged_rate"));
					eventList.add(event);
		      }
			con.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return eventList;
	}
	
	public double getPrice(String sql) {
		Connection con=getCon();
		try {			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			double rate =result.getDouble("rate");
			con.close();
			return rate;
		}catch(Exception e) {
			System.out.print(e);
		}
		return 0;
	}

	
	public Registration getRegistrationDetail(int id) {
		Connection con=getCon();
		String sql="Select * from registration where id = '"+id+"'";
		Registration registration=new Registration();
		try {			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			registration.setId(Integer.parseInt(result.getString("id")));
			registration.setAddress(result.getString("address"));
			registration.setName(result.getString("name"));
			registration.setAge(Integer.parseInt(result.getString("age")));
			registration.setContact(Integer.parseInt(result.getString("contact")));
			con.close();
		}catch(Exception e) {
			System.out.print(e);
		}
		return registration;
	}
	
	public User getUserDetailByUsername(String username) {
		Connection con=getCon();
		String sql="Select * from user where username = '"+username+"'";
		User user=new User();
		try {			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			user.setId(Integer.parseInt(result.getString("id")));
			user.setPassword(result.getString("password"));
			user.setUsername(result.getString("username"));
			con.close();
		}catch(Exception e) {
			System.out.print(e);
		}
		return user;
	}

}
