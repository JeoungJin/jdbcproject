package com.shinhan.emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Setter
@AllArgsConstructor@Builder
public class Car {
	String model;
	int price;
	
	public static void main(String[] args) {
		Car c = Car.builder()
				.model("")
				.price(0)
				.build();
		Car c2 = new Car();
		c2.setModel("");
		c2.setPrice(0);
		
		Car c3 = new Car(null, 0);
		
		
		
		
	}
	
	
	
}
