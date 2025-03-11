package com.api.restaurant.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.CartDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Cart;
import com.api.restaurant.model.User;
import com.api.restaurant.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public Cart findById(Long id) {
        logger.info("Searching for cart with ID: " + id);
        return cartRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Cart do not exists");
                return new ResourceNotFoundException("Cart", id);
            });
    }

    public Cart getCartWithCustomizationsByUserId(Long id) {
        logger.info("Searching for cart and customizations with ID: " + id);
        return cartRepository.getCartWithCustomizationsByUser(id)
            .orElseThrow(() -> {
                logger.error("Cart do not exists");
                return new ResourceNotFoundException("Cart", id);
            });
    }

    public Cart create(CartDTO cartDTO) {
        logger.info("Creating cart");

        Cart cart = modelMapper.map(cartDTO, Cart.class);

        User user = userService.findById(cartDTO.getUser());
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    public Cart update(CartDTO cartDTO) {
        logger.info("Checking if cart exists");
        Cart initialCart = cartRepository.findById(cartDTO.getId())
            .orElseThrow(() -> {
                logger.error("Cart do not exists");
                return new ResourceNotFoundException("Cart", cartDTO.getId());
            });

        Cart cart = modelMapper.map(cartDTO, Cart.class);

        User user = userService.findById(cartDTO.getUser());
        cart.setUser(user);
        
        cart.setCartItems(initialCart.getCartItems());

        logger.info("Updating cart");
        return cartRepository.save(cart);
    }

    public void delete(Long id) {
        logger.info("Checking if cart exists");
        Cart cartEntity = cartRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Cart do not exists");
                return new ResourceNotFoundException("Cart", id);
            });
        logger.info("Deleting cart");
        cartRepository.delete(cartEntity);
    }
}
