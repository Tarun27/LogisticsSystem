package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Order;
import model.Status;
import model.User;
import repo.LogisticsRepo;

public class LogisticsService {

	public boolean validateUserExists(String input, LogisticsRepo logisticsRepo) {

		if(logisticsRepo.users.stream().anyMatch(usr->usr.userName.equals(input))) return true;
		
		return false;
	}

	public User createUser(String input) {
		User user = new User(input);
		return user;
	}

	public int createOrder(String userId, String[] items, String[] cities, LogisticsRepo logisticsRepo, String weight, String volume) {

		Order order = new Order();
		order.items = Arrays.asList(items);
		order.sourceLocation = cities[0];
		order.destination = cities[1];
		order.orderId = (int)Math.random()*100000;
		order.weight = Float.parseFloat(weight);
		order.volume = Float.parseFloat(volume);
		order.status = Status.ENROUTE;
		List<User> users = logisticsRepo.users.stream().filter(user->user.userName.equals(userId)).collect(Collectors.toList());
		if(users.get(0).orders ==  null) users.get(0).orders = new ArrayList<Order>();
		users.get(0).orders.add(order);
		return order.orderId;
	}

	public void getBookings(String userName, LogisticsRepo logisticsRepo) {
		List<User> users = logisticsRepo.users.stream().filter(user->user.userName.equals(userName)).collect(Collectors.toList());
		users.get(0).orders.forEach(o-> System.out.println(" orderId : "+ o.orderId + "  status " + o.status));
		
	}

	public void cancelBooking(String input, LogisticsRepo logisticsRepo) {
		// TODO Auto-generated method stub
		
	}


}
