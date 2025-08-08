package com.example.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Cart;
import com.example.bookstore.models.CartItem;
import com.example.bookstore.models.Users;
import com.example.bookstore.repositories.BookstoreRepo;
import com.example.bookstore.repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookstoreRepo bookstoreRepository;

    public void addToCart(Users user, Integer bookID, int quantity) {
        Book book = bookstoreRepository.findById(bookID).orElseThrow(() -> new RuntimeException("Book not found"));
        Cart cart = getCartForUser(user);
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(bookID)).findFirst();
        if (existingCartItem.isPresent()) {
            CartItem existingItem = existingCartItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            // cartItemRepository.save(existingItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setBook(book);
            newCartItem.setQuantity(quantity);
            newCartItem.setCart(cart);
            cart.getCartItems().add(newCartItem);
            //cartItemRepository.save(newCartItem);
        }
        cartRepository.save(cart);
    }

    public void reduceQuantityOfCartItem(Users user, Integer bookID) {
        Cart cart = getCartForUser(user);
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(bookID)).findFirst();
        if (existingCartItem.isPresent()) {
            CartItem existingItem = existingCartItem.get();
            if (existingItem.getQuantity() > 1) {
                existingItem.setQuantity(existingItem.getQuantity() - 1);
            }else{
                cartItems.remove(existingItem);
            }
        }
        cartRepository.save(cart);
    }

    public void clearCart(Users user){
        Cart cart=getCartForUser(user);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    public Cart getCartForUser(Users user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    public List<CartItem> getCartItems(Users user){
        return getCartForUser(user).getCartItems();
    }

    public double getCartTotal(Users user){
        Cart cart = getCartForUser(user);
        return cart.getCartItems().stream().mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }

}
