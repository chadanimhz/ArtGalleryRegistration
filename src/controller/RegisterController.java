package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import dao.DbConnection;
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
		eventController.listEventsByDate();
		System.out.println(" Enter event id for which you want to register");
		int eventId = scanner.nextInt();
		System.out.println("Enter full name");
		String name= scanner.next();
		System.out.println("Enter address");
		String address= scanner.next();
		System.out.println("Enter your contact");
		double contact=scanner.nextDouble();
		System.out.println("Enter your age");
		int age=scanner.nextInt();
		
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
				age=scanner.nextInt();
			}
		}
		
		String confirmation=generateRandomString();
		
		Registration registration=new Registration( name, address, contact, age,confirmation,rate );
		System.out.println("Are you sure you want to confirm registratioin with following information? Type y or n");
		System.out.println(registration.toString());
		
		String confirm= scanner.next();
		System.out.println(confirm);
		if(confirm.equalsIgnoreCase("y")) {
			String sql="Insert into registration (name,address,contact_no,age,event_id,confirmation_no)"
					+ " values('"+name+"','"+address+"',"+contact+","+age+","+eventId+",'"+confirmation+"')";
			dbConnection.insert(sql);
			System.out.println("Registration !!!");
			
		}else {
			System.out.println("sadasdas");
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
}

