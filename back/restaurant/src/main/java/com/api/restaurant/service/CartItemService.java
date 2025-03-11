package com.api.restaurant.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.CartItemDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Cart;
import com.api.restaurant.model.CartItem;
import com.api.restaurant.model.Product;
import com.api.restaurant.repository.CartItemRepository;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);

    public CartItem findById(Long id) {
        logger.info("Searching for cartItem with ID: " + id);
        return cartItemRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("CartItem do not exists");
                return new ResourceNotFoundException("Cart Item", id);
            });
    }

    public CartItem create(CartItemDTO cartItemDTO) {
        logger.info("Creating cartItem");

        CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);

        Cart cart = cartService.findById(cartItemDTO.getCart());
        cartItem.setCart(cart);

        Product product = productService.findById(cartItemDTO.getProduct());
        cartItem.setProduct(product);

        return cartItemRepository.save(cartItem);
    }

    public CartItem update(CartItemDTO cartItemDTO) {
        logger.info("Checking if cartItem exists");
        CartItem initialCartItem = cartItemRepository.findById(cartItemDTO.getId())
            .orElseThrow(() -> {
                logger.error("CartItem do not exists");
                return new ResourceNotFoundException("Cart Item", cartItemDTO.getId());
            });

        CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);

        Cart cart = cartService.findById(cartItemDTO.getCart());
        cartItem.setCart(cart);

        Product product = productService.findById(cartItemDTO.getProduct());
        cartItem.setProduct(product);

        cartItem.setCartItemCustomizations(initialCartItem.getCartItemCustomizations());

        logger.info("Updating cartItem");
        return cartItemRepository.save(cartItem);
    }

    public void delete(Long id) {
        logger.info("Checking if cartItem exists");
        CartItem cartItemEntity = cartItemRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("CartItem do not exists");
                return new ResourceNotFoundException("Cart Item", id);
            });
        logger.info("Deleting cartItem");
        cartItemRepository.delete(cartItemEntity);
    }
}
