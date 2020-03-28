package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DbConnection;
import model.Event;
import model.Price;

public class EventController {
	
	Scanner scanner = new Scanner(System.in);
	DbConnection dbConnection=new DbConnection();
	
	public void addEvent() {
		
		System.out.println("Enter event name");
		String eventName = scanner.nextLine();
		System.out.println("Enter event date");
		String eventDate = scanner.nextLine();
		System.out.println("Enter event venue");
		String eventVenue = scanner.nextLine();
		System.out.println("Enter Rate for Children");
		double childRate=scanner.nextDouble();
		System.out.println("Enter Rate for Adult");
		double adultRate=scanner.nextDouble();
		System.out.println("Enter Rate for Aged");
		double agedRate=scanner.nextDouble();
		
		String sqlEvent="Insert into Event (name,date,venue,child_rate,adult_rate,aged_rate) VALUES ('"+eventName+"','"+eventDate+"','"+eventVenue+"',"+childRate+","+adultRate+","+agedRate+")";
		dbConnection.insert(sqlEvent);
		
	}
	
	public void listEvents() {
		ArrayList<Event> eventList=dbConnection.getEvents();
		
		for (Event event : eventList) {
			System.out.print("\nEvent id : "+event.getId()+
					",\t Event name : "+event.getName()+
					",\t Event Date :"+event.getDate()+
					",\t Event venue :"+event.getVenue()+
					",\t Child Rate :"+event.getChildRate()+
					",\t Adult Rate :"+event.getAdultRate()+
					",\t Aged RAte :"+event.getAgedRate()+
					",");
		}
		
		System.out.println("\n");
	}

}
