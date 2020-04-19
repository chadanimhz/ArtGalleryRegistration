package controller;
import java.io.Console;
import java.util.Scanner;

import dao.DbConnection;
import model.User;

public class LoginController {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Console console=System.console();
		
		System.out.println("Art gallery, bookings made simple");
		System.out.println("Enter 1 for admin login and any other key for registration");
		String input=scanner.nextLine();
				
		if(input.equals("1")) {
			System.out.println("Admin Panel.....Login !!");
			System.out.println("Enter username");
			String username = scanner.nextLine();

			console.printf("Enter Password \n");
			char[] pass = console.readPassword();
			String password = String. valueOf(pass);
			verifyUser(username, password);
			
			AdminController.admin();
		}else {
			RegisterController registerController=new RegisterController();
			registerController.register();
		}
		
	}
	
	private static void verifyUser(String username,String password) {
		DbConnection dbConnection=new DbConnection();
		User user= dbConnection.getUserDetailByUsername(username,password);
		if(user==null) {
			System.out.println("Invalid username/password");
			LoginController.main(null);
		}
	}
	
	

}
