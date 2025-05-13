package com.onlinefoodordering.service;

import com.onlinefoodordering.model.Cart;
import com.onlinefoodordering.model.CartItem;
import com.onlinefoodordering.request.AddCartItemRequest;

public interface CartService {
	
	public CartItem addItemToCart(AddCartItemRequest req, String jwt)throws Exception;
	
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws Exception;
	 
	public Cart removeItemFromCart(Long cartItemId,String jwt)throws Exception;
	
	public Long calculateCartTotals(Cart cart)throws Exception;
	
	public Cart findCartById(Long id)throws Exception;
	
	public Cart findCartByUserId(String jwt)throws Exception;
	
	public Cart clearCart(Long id)throws Exception;

	public Cart findCartByUserId(Long id);

	public Long calculateCartTotal(Cart cart);
	
}
