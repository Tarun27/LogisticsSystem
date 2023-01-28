package main;

import java.util.*;

import repo.LogisticsRepo;
import service.LogisticsService;
import model.User;

/**
 * 
 * @author Neutron Star

Any order and booking will have 3 main details:
Sender (with sender address)
Recipient (with recipient address)
List of Packages (list of packages with details about each package)

Based on package details, mode of transportation should be chosen by logistics company

Based on above, price would be calculated, payment will be made. 
Once payment is successful then consignment number would be generated for whole order
and different package numbers would be assigned for each package.

Order tracking status would change once different events are received for package.
 */

public class LogisticsSystem {
	
	
	public static void main(String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		LogisticsService logisticsService = new LogisticsService();
		LogisticsRepo logisticsRepo = new LogisticsRepo();
		logisticsRepo.users = new ArrayList<User>();
		
		
		while(true) {
			
			System.out.println("\n------ Logistics system ------");
			
			System.out.println("Choose option: \n 1. existing user \n 2.register user ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				
				
				System.out.println("Enter user name");
				
				input = scan.nextLine();
				String userId = input;
				
				if(logisticsService.validateUserExists(userId,logisticsRepo)) {
					
					System.out.println("Choose option: \n 1. book \n 2.trackBookings \n 3. cancel booking ");
					
					String option = scan.nextLine();
					
					switch(option) {
					
					case "1": System.out.println("Enter items list separated by spaces : ");
					String[] items = scan.nextLine().split(" ");
					System.out.println("Enter source city and  destination city separated by spaces");
					String[] cities = scan.nextLine().split(" ");
					System.out.println("Enter weight and volume separated by spaces : ");
					String[] size = scan.nextLine().split(" ");
					int id = logisticsService.createOrder(userId,items,cities,logisticsRepo,size[0],size[1]);
					System.out.println("order with id:" + id +"  successfully created");

						break;
					case "2":
						logisticsService.getBookings(input,logisticsRepo);
						break;
					case "3":
						System.out.println("Enter order Id");
						input = scan.nextLine();
						logisticsService.cancelBooking(input,logisticsRepo);
						break;
					
					}
					
				}else {
					System.out.println("user with id " + input +"  does not exist ");
					
				}
				

				
			}else if(input.equals("2")) {
				
				System.out.println("Enter username");
				
				input = scan.nextLine();
				
				if(logisticsService.validateUserExists(input, logisticsRepo)) {
					System.out.println("Username already Exists");
					
					int attempts =3;
					
					while(attempts>0) {
					System.out.println("Enter username, you have "+attempts+" attempts left" );
					
					input = scan.nextLine().trim();
					if(!logisticsService.validateUserExists(input, logisticsRepo)) {
									
						logisticsRepo.users.add(logisticsService.createUser(input));
						System.out.println("User successfully created");
						break;
					}
					System.out.println("Username already Exists");

					attempts--;
					}
				}else {
					System.out.println("User successfully created");
					logisticsRepo.users.add(logisticsService.createUser(input));

				}
				
				
			}
		

		}
		
	}

}
