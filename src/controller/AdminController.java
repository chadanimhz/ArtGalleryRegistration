package controller;

import java.util.Scanner;

public class AdminController {

	public static void admin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter following keys  \n"+
				"1 for list event, 2 for list registration,\n3 for add event, 4 for update event, \n5 for  delete event, 6 for logout");
		int option = scanner.nextInt();
		EventController eventController=new EventController();
		switch (option) {
		case 1:
			eventController.listEvents();
		    break;
		case 2:
			eventController.listRegistration();
			break;
		case 3:
		    eventController.addEvent();
		    System.out.println("Event Added Successfully");
		    break;
		case 4:
		    eventController.updateEvents();
		    break;
		case 5:
		    eventController.deleteEvents();
		    break;
		case 6:
		    LoginController.main(null);
		    break;
		default:
			System.out.println("Enter input is invalid please select from the list");
		}
		AdminController.admin();
				
	}
}
