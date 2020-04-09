package controller;

import java.util.Scanner;

public class AdminController {

	public static void admin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter following keys  \n "+
				"1 for list event,2 for add event, 3 for update event, 4 for  delete event, 5 for list registration, 6 for logout");
		int option = scanner.nextInt();
		EventController eventController=new EventController();
		switch (option) {
		case 1:
		  eventController.listEvents();
		  AdminController.admin();
		  break;
		case 2:
		  eventController.addEvent();
		  System.out.println("Event Added Successfully");
		  AdminController.admin();
		  break;
		case 3:
		  eventController.updateEvents();
		  AdminController.admin();
		  break;
		case 4:
		  eventController.deleteEvents();
		  AdminController.admin();
		  break;
		case 5:
		  eventController.listRegistration();
		  AdminController.admin();
		  break;
		case 6:
//		  logout();
		  LoginController.main(null);
		  break;
		}
				
	}
}
