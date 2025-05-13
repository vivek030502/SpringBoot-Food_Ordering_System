package com.onlinefoodordering.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.model.Address;
import com.onlinefoodordering.model.Cart;
import com.onlinefoodordering.model.CartItem;
import com.onlinefoodordering.model.Order;
import com.onlinefoodordering.model.OrderItem;
import com.onlinefoodordering.model.Restaurant;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.repository.AddressRepository;
import com.onlinefoodordering.repository.OrderItemRepository;
import com.onlinefoodordering.repository.OrderRepository;
import com.onlinefoodordering.repository.UserRepository;
import com.onlinefoodordering.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository  orderitemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		// TODO Auto-generated method stub
		
		Address shippAddress=order.getDeliveryAddress();
				
		Address saveAddress=addressRepository.save(shippAddress);
		
		if(!user.getAddresses().contains(saveAddress)) {
			user.getAddresses().add(saveAddress);
			userRespository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		
		Order createdOrder= new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(saveAddress);
		createdOrder.setRestaurant(restaurant);
		
		Cart cart=cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem= orderitemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
	
	Long totalPrice = cartService.calculateCartTotal(cart);
	
	createdOrder.setItems(orderItems);
	createdOrder.setTotalPrice(totalPrice);
	
	Order savedOrder=orderRepository.save(createdOrder);
	restaurant.getOrders().add(savedOrder);
	
	
	
	return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);
		
		if(orderStatus.equals("OUT_FOR_DELIVERY") 
				|| orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")
		  ){
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		// TODO Auto-generated method stub
		throw new Exception("Please select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if(orderStatus!=null) {
		orders = orders.stream().filter(order->
		order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
	}
		return orders;
	}
	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> optionalOrder=orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new Exception("order not found");
		}
		return optionalOrder.get();
	}

}
