package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DbConnection;
import model.Event;
import model.Price;
import model.Registration;

public class EventController {
	
	Scanner scanner = new Scanner(System.in);
	DbConnection dbConnection=new DbConnection();
	
	public void addEvent() {
		
		System.out.println("Add Event !!!! \n");
		System.out.println("Enter event name");
		String eventName = scanner.nextLine();
		System.out.println("Enter event date");
		String eventDate = scanner.nextLine();
		System.out.println("Enter event venue");
		String eventVenue = scanner.nextLine();
		System.out.println("Enter  Fee for Children");
		double childRate=scanner.nextDouble();
		System.out.println("Enter  Fee for Adult");
		double adultRate=scanner.nextDouble();
		System.out.println("Enter  Fee for Aged");
		double agedRate=scanner.nextDouble();
		
		String sqlEvent="Insert into Event (name,date,venue,child_rate,adult_rate,aged_rate) VALUES ('"+eventName+"','"+eventDate+"','"+eventVenue+"',"+childRate+","+adultRate+","+agedRate+")";
		dbConnection.insert(sqlEvent);
		
	}
	
	public void listEvents() {
		System.out.println("Event List");
//		ArrayList<Event> eventList=dbConnection.getEvents();
//		
//		for (Event event : eventList) {
//			System.out.print("\nEvent id : "+event.getId()+
//					",\t Event name : "+event.getName()+
//					",\t Event Date :"+event.getDate()+
//					",\t Event venue :"+event.getVenue()+
//					",\t Child  Fee :"+event.getChildRate()+
//					",\t Adult  Fee :"+event.getAdultRate()+
//					",\t Retired  Fee :"+event.getAgedRate()+
//					",");
//		}
		
		System.out.println("Completed Event !!!!");
		listCompletedEvents();

		System.out.println("Upcoming Event !!!!!");
		listUpcomingEvents();
		System.out.println("\n");
	}
	
	public void listUpcomingEvents() {
		ArrayList<Event> eventList=dbConnection.getUpcomingEvents();
		
		for (Event event : eventList) {
			System.out.print("\nEvent id : "+event.getId()+
					",\t Event name : "+event.getName()+
					",\t Event Date :"+event.getDate()+
					",\t Event venue :"+event.getVenue()+
					",\t Child  Fee :"+event.getChildRate()+
					",\t Adult  Fee :"+event.getAdultRate()+
					",\t Aged  Fee :"+event.getAgedRate()+
					",");
		}
		
		System.out.println("\n");
	}
	
	public void listCompletedEvents() {
		ArrayList<Event> eventList=dbConnection.getCompletedEvents();
		
		for (Event event : eventList) {
			System.out.print("\nEvent id : "+event.getId()+
					",\t Event name : "+event.getName()+
					",\t Event Date :"+event.getDate()+
					",\t Event venue :"+event.getVenue()+
					",\t Child  Fee :"+event.getChildRate()+
					",\t Adult  Fee :"+event.getAdultRate()+
					",\t Aged  Fee :"+event.getAgedRate()+
					",");
		}
		
		System.out.println("\n");
	}

	public void updateEvents() {
		System.out.println("Update Event .......!!!");
		int eventId=update();
		
	}

	public void deleteEvents() {
		System.out.println("Delete Event .......!!!");
		int eventId=delete();
		
	}
	
	public int update() {
		
		System.out.println("Event List !!!!!");
		listUpcomingEvents();
		
		System.out.println(" Enter event id ");
		int eventId = scanner.nextInt();
		String sql = "Select * from event where date('now') < date and id="+eventId;
		Event event = dbConnection.getEvent(sql);
		if(event==null) {
			System.out.println("Invalid Event ....!!!");
			update();
		}else {
			int regCount=dbConnection.getRegistrationCountByEvent(eventId);
			if(regCount!=0) {
				System.out.println("Invalid Event ....!!!");
				update();
			}
			System.out.println(" Are you sure you want to update the event with following information? Type y or n");
			System.out.println(event.toString());
			
			String confirm= scanner.next();
			if(confirm.equalsIgnoreCase("y")) {
				System.out.println("Enter following keys for updating information \n "+
									"1 for name,2 for date, 3 for venue, 4 for child rate, 5 for adult rate, 6 for aged rate");
				int option = scanner.nextInt();
				switch (option) {
				  case 1:
					  System.out.println("Enter Event name");
					  String name=scanner.next();
					  sql="Update event set name='"+ name +"' where id="+eventId;
					  dbConnection.update(sql);
					  break;
				  case 2:
					  System.out.println("Enter Event Date");
					  String date=scanner.next();
					  sql="Update event set date='"+ date +"' where id="+eventId;
					  dbConnection.update(sql);
					  break;
				  case 3:
					  System.out.println("Enter Event Venue");
					  String venue=scanner.next();
					  sql="Update event set venue='"+ venue +"' where id="+eventId;
					  dbConnection.update(sql);
					  break;
				  case 4:
					  System.out.println("Enter Rate for Children");
					  double childRate=scanner.nextDouble();
					  sql="Update event set child_rate='"+ childRate +"' where id="+eventId;
					  dbConnection.update(sql);
					  break;
				  case 5:
					  System.out.println("Enter Rate for Adult");
					  double adultRate=scanner.nextDouble();
					  sql="Update event set adult_rate='"+ adultRate +"' where id="+eventId;
					  dbConnection.update(sql);
					  break;
				  case 6:
					  System.out.println("Enter Rate for Aged");
					  double agedRate=scanner.nextDouble();
					  sql="Update event set aged_rate='"+ agedRate +"' where id="+eventId;
					  dbConnection.update(sql);
					  break;
				}
				System.out.println("Event Updated Successfully");
			}else {
				AdminController.admin();
			}
		}
		return eventId;
	}

	public int delete() {
		
		System.out.println("Event List !!!!!");
		listUpcomingEvents();
		
		System.out.println(" Enter event id ");
		int eventId = scanner.nextInt();
		String sql = "Select * from event where date('now') < date and id="+eventId;
		Event event = dbConnection.getEvent(sql);
		if(event==null) {
			System.out.println("Invalid Event ....!!!");
			delete();
		}else {
			System.out.println("Deleting event will delete all the registration under this event. \n Are you sure you want to delete the event with following information? Type y or n");
			System.out.println(event.toString());
			
			String confirm= scanner.next();
			if(confirm.equalsIgnoreCase("y")) {
				sql="Delete from registration where event_id="+eventId;
				dbConnection.executeSQLQuery(sql);
				sql="Delete from event where id="+eventId;
				dbConnection.executeSQLQuery(sql);
				System.out.println("Event Deleted Successfully !!!!");
				AdminController.admin();
			}else {
				AdminController.admin();
			}
		}
		return eventId;
	}

	public void listRegistration() {
		listEvents();
		System.out.println("Select the Event Id of which you want to List Registration. ");
		int eventId = scanner.nextInt();
		
		ArrayList<Registration> registrationList=dbConnection.getRegistrationsListByEvent(eventId);
		
		if(registrationList.isEmpty()) {
			System.out.println("No registration list found");
		}else {
			for (Registration registration : registrationList) {
				System.out.print("\nRegistration id : "+registration.getId()+
						",\t Name : "+registration.getName()+
						",\t Address :"+registration.getAddress()+
						",\t Age :"+registration.getAge()+
						",\t Contact :"+registration.getContact()+
						",\t Fee :"+registration.getRate()+
						",\t Confirmation no :"+registration.getConfirmationNo()+
						",");
			}
		}
		
		
		System.out.println("\n");
		
	}
}
