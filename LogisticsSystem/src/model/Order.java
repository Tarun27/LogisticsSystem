package model;

import java.util.List;

public class Order {
	
	public List<String> items;
	public Float weight;
	public Float volume;
	public Integer cost;
	public String sourceLocation;
	public String destination;
	public Integer orderId;
	public Integer userId;
	public Status status;
	
}
