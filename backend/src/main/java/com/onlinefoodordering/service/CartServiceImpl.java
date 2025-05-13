package com.onlinefoodordering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.model.Cart;
import com.onlinefoodordering.model.CartItem;
import com.onlinefoodordering.model.Food;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.repository.CartItemRepository;
import com.onlinefoodordering.repository.CartRepository;
import com.onlinefoodordering.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);

        // ✅ Update total
        cart.setTotal(calculateCartTotal(cart));
        cartRepository.save(cart);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);

        CartItem updatedItem = cartItemRepository.save(item);

        // ✅ Update total in cart
        Cart cart = updatedItem.getCart();
        cart.setTotal(calculateCartTotal(cart));
        cartRepository.save(cart);

        return updatedItem;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem item = cartItemOptional.get();
        cart.getItems().remove(item);
        cartItemRepository.delete(item);

        // ✅ Update total after removal
        cart.setTotal(calculateCartTotal(cart));
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;

        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            throw new Exception("Cart not found with id " + id);
        }

        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(Long id) throws Exception {
        Cart cart = findCartById(id);
        cart.getItems().clear();

        // ✅ Set total to 0 after clearing
        cart.setTotal(0L);
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUserId(Long id) {
        return cartRepository.findByCustomerId(id);
    }

    @Override
    public Long calculateCartTotal(Cart cart) {
        Long total = 0L;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }
}
