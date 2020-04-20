package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Event;

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
			prepStat.close();
			con.close();
			return ret;
			  
		}catch(Exception e) {
			System.out.print(e);
		}
		return 0;
	}
	
	public int update(String sql) {
		Connection con=getCon();
		
		try {	
			PreparedStatement prepStat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepStat.execute();
			ResultSet rs = prepStat.getGeneratedKeys();
			int ret=0;
			if(rs.next()) {
				 ret= rs.getInt(1);
			}
			prepStat.close();
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
			statement.close();
			con.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return eventList;
	}
	
	public ArrayList<Event> getUpcomingEvents() {
		Connection con=getCon();
		String sql="Select * from event where date('now') < date";
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
			statement.close();
			con.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return eventList;
	}
	
	public ArrayList<Event> getCompletedEvents() {
		Connection con=getCon();
		String sql="Select * from event where date('now') > date";
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
			statement.close();
			con.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return eventList;
	}
	
	public Event getEvent(String sql) {
		Connection con=getCon();
		Event event=new Event();
		try {			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				event.setId(Integer.parseInt(result.getString("id")));
				event.setName(result.getString("name"));
				event.setVenue(result.getString("venue"));
				event.setAgedRate(Integer.parseInt(result.getString("aged_rate")));
				event.setAdultRate(Integer.parseInt(result.getString("adult_rate")));
				event.setChildRate(Integer.parseInt(result.getString("child_rate")));
				event.setDate(result.getString("date"));
				statement.close();
				con.close();
				return event;
			}
			statement.close();
			con.close();
			
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return null;
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
			registration.setContact(Long.parseLong(result.getString("contact")));
			statement.close();
			con.close();
		}catch(Exception e) {
			System.out.print(e);
		}
		return registration;
	}
	
	public ArrayList<Registration> getRegistrationsListByEvent(int eventid) {
		Connection con=getCon();
		String sql="Select registration.*,child_rate,adult_rate,aged_rate from registration LEFT JOIN event on event.id=registration.event_id where event_id = '"+eventid+"'";
		ArrayList<Registration> registrationList=new ArrayList<>();
		
		try {			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
					Registration registration=new Registration();
					registration.setId(Integer.parseInt(rs.getString("id")));
					registration.setAddress(rs.getString("address"));
					registration.setName(rs.getString("name"));
					registration.setAge(Integer.parseInt(rs.getString("age")));
					if(registration.getAge()>10 && registration.getAge() <=16) {
						registration.setRate(rs.getDouble("child_rate"));
					}else if(registration.getAge() >16 && registration.getAge() <=60) {
						registration.setRate(rs.getDouble("adult_rate"));
					}else {
						registration.setRate(rs.getDouble("aged_rate"));
					}
					registration.setContact(Long.parseLong(rs.getString("contact_no")));
					registration.setConfirmationNo(rs.getString("confirmation_no"));
					registrationList.add(registration);
		      }
			statement.close();
			con.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return registrationList;
	}
	
	public User getUserDetailByUsername(String username,String password) {
		Connection con=getCon();
		String sql="Select * from user where username = '"+username+"' and password = '"+password+"'";
		User user=new User();
		try {			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				user.setId(Integer.parseInt(result.getString("id")));
				user.setPassword(result.getString("password"));
				user.setUsername(result.getString("username"));
				statement.close();
				con.close();
				return user;
			}
			statement.close();
			con.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return null;
	}

	public void executeSQLQuery(String sql) {
		Connection con=getCon();
		
		try {	
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			con.close();
			  
		}catch(Exception e) {
			System.out.print(e);
		}
	}

	public int getRegistrationCountByEvent(int eventId) {
		Connection con=getCon();
		String sql="Select count(*) as count from registration where event_id = '"+eventId+"'";
		
		try {			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int count=Integer.parseInt(rs.getString("count"));
			statement.close();
			con.close();
			return count;
		}catch(Exception e) {
			System.out.print(e);
		}
		return 0;
	}

}
