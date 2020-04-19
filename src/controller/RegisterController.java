package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import dao.DbConnection;
import model.Event;
import model.Registration;

public class RegisterController {
	
	 private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	 private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	 private static final String NUMBER = "0123456789";

	 private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	 private static SecureRandom random = new SecureRandom();
	
	Scanner scanner = new Scanner(System.in);
	
	DbConnection dbConnection=new DbConnection();

	public  void register() {

		EventController eventController=new EventController();
//		System.out.println("completed Events !!!!!");
//		eventController.listCompletedEvents();
		
		System.out.println("Upcoming Events !!!!!");
		
		int eventId = getEvent();
		System.out.println("Enter full name");
		String name= scanner.next();
		System.out.println("Enter address");
		String address= scanner.next();
		System.out.println("Enter your contact");
		String contactString=scanner.next();
		while(!checkNumber(contactString)) {
			System.out.println("Enter valid contact number");
			contactString=scanner.next();
		}
		Double contact = Double.parseDouble(contactString);
		System.out.println("Enter your age");
		String ageString=scanner.next();
		while(!checkNumber(ageString)) {
			System.out.println("Enter valid age");
			ageString=scanner.next();
		}
		int age = Integer.parseInt(ageString);
		
		double rate=0;
		while(rate==0) {
			if(age>10 && age <=16) {
				rate=dbConnection.getPrice("Select child_rate as rate from event where id="+eventId);
			}else if(age >16 && age <=60) {
				rate=dbConnection.getPrice("Select adult_rate as rate from event where id="+eventId);
			}else if(age >60 && age <100) {
				rate=dbConnection.getPrice("Select aged_rate as rate from event where id="+eventId);
			}else {
				System.out.println(" Invalid Age .....!!!!  \nEnter your age");
				ageString=scanner.next();
				while(!checkNumber(ageString)) {
					System.out.println("Enter valid age");
					ageString=scanner.next();
				}
				age = Integer.parseInt(ageString);

			}
		}
		
		String confirmation=generateRandomString();
		
		Registration registration=new Registration( name, address, contact, age,confirmation,rate );
		registerClient(registration, eventId);
		
	}
	
	public int getEvent() {
		EventController eventController=new EventController();
		
//		System.out.println("Select an option from Upcoming Events !!!!!");
		eventController.listUpcomingEvents();
		
		System.out.println(" Enter event id  for which you want to register");
		String eventIdString=scanner.next();
		while(!checkNumber(eventIdString)) {
			System.out.println("Enter valid event id");
			eventIdString=scanner.next();
		}
		int eventId = Integer.parseInt(eventIdString);
		
		String sql = "Select * from event where date('now') < date and id="+eventId;
		Event event = dbConnection.getEvent(sql);
		if(event==null) {
			System.out.println("Invalid Event .... Sorry Event is already completed !!!");
			getEvent();
		}
		return eventId;
	}
	
	public void registerClient(Registration registration,int eventId){
		System.out.println("Are you sure you want to confirm registratioin with following information? Type y or n");
		System.out.println(registration.toString());
		
		String confirm= scanner.next();
		String sql;
		if(confirm.equalsIgnoreCase("y")) {
			sql="Insert into registration (name,address,contact_no,age,event_id,confirmation_no)"
					+ " values('"+registration.getName()+"','"+registration.getAddress()+"',"+registration.getContact()+","+registration.getAge()+","+eventId+",'"+registration.getConfirmationNo()+"')";
			int regId = dbConnection.insert(sql);
			saveRegistration(regId,registration,eventId);
			System.out.println("Thank you for registration!!! \nHere is your confirmation number "+registration.getConfirmationNo());
			System.out.println("Please visit us again!!! \n \n");
			LoginController.main(null);
			
		}else {
			System.out.println("Do you want to change the event?Type y or n ");
			String update= scanner.next();
			if(update.equalsIgnoreCase("y")) {
				eventId = getEvent();
				registerClient(registration, eventId);
				
			}else {
				System.out.println("Thank you for visiting us !!!!");
				LoginController.main(null);
			}
		}
	}
	
	private void saveRegistration(int regId,Registration registration,int eventId) {
		System.out.println("test");
		String sql = "Select * from event where  id="+eventId;
		Event event = dbConnection.getEvent(sql);
		
		PrintWriter outputFile;
		try {
			outputFile = new PrintWriter("registration"+regId+".txt");
			outputFile.println(event.toString());
			outputFile.println(registration.toString());

			outputFile.println("Art gallery registration confirmation  \nEvent Detail:");
			outputFile.println("name : "+event.getName());
			outputFile.println("date : "+event.getDate());
			outputFile.println("venue : "+event.getVenue());
			
			outputFile.println("Registration Detail : ");
			outputFile.println("name : "+registration.getName());
			outputFile.println("total amount : "+registration.getRate());
			outputFile.println("confirmation : "+registration.getConfirmationNo());
			
			outputFile.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
	}
	
	public boolean checkNumber(String num) {
		try {
		  Integer.parseInt(num);
		  return true;
		} catch (NumberFormatException e) {
		 return false;
		} 
	}
}


