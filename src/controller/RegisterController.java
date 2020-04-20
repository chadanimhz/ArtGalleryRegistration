package controller;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.SecureRandom;
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
		String name= scanner.nextLine();
		System.out.println("Enter address");
		String address= scanner.nextLine();
		System.out.println("Enter your contact");
		String contactString=scanner.nextLine();
		while(!checkNumber(contactString)) {
			System.out.println("Enter valid contact number");
			contactString=scanner.nextLine();
		}
		Long contact = Long.parseLong(contactString);
		System.out.println("Enter your age");
		String ageString=scanner.nextLine();
		while(!checkNumber(ageString)) {
			System.out.println("Enter valid age");
			ageString=scanner.nextLine();
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
				ageString=scanner.nextLine();
				while(!checkNumber(ageString)) {
					System.out.println("Enter valid age");
					ageString=scanner.nextLine();
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
		String eventIdString=scanner.nextLine();
		while(!checkNumber(eventIdString)) {
			System.out.println("Enter valid event id");
			eventIdString=scanner.nextLine();
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
		System.out.println("Are you sure you want to confirm registration with following information?\nType y to confirm else type any other key");
		System.out.println(registration.toString());
		
		String confirm= scanner.nextLine();
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
			System.out.println("Do you want to change the event?Type y or any key to cancel registration ");
			String update= scanner.nextLine();
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

		String sql = "Select * from event where  id="+eventId;
		Event event = dbConnection.getEvent(sql);
		
		PrintWriter outputFile;
		try {
			outputFile = new PrintWriter("registration"+regId+".txt");
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
		  Long.parseLong(num);
		  return true;
		} catch (NumberFormatException e) {
		 return false;
		} 
	}
}


