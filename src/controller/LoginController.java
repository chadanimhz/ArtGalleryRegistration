package controller;
import java.util.Scanner;

import dao.DbConnection;

public class LoginController {
	
	public static void main(String[] args) {
//		EventController eventController=new EventController();
//		eventController.addEvent();
//		eventController.listEvents();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter 1 for login and any other key for register");
		String input=scanner.nextLine();
				
		if(input == "1") {
			System.out.println("Admin Panel.....Login....!!");
			System.out.println("Enter username");
			String username = scanner.nextLine();
			System.out.println("Enter Password");
			String password = scanner.nextLine();			
		}else {
			RegisterController registerController=new RegisterController();
			registerController.register();
		}
		
//		
//		boolean isValid=verifyUser(username,password);
		
//		String sql = "Insert into user values (1,'admin','admin')";
//
//		DbConnection dbConnection=new DbConnection();
//		dbConnection.insert(sql);
	}
	
	private static boolean verifyUser(String username,String password) {
		DbConnection dbConnection=new DbConnection();
		dbConnection.getUserDetailByUsername(username);
		
		return false;
	}

}
