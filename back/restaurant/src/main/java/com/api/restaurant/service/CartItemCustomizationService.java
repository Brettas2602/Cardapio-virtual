package com.api.restaurant.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.CartItemCustomizationDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.CartItem;
import com.api.restaurant.model.CartItemCustomization;
import com.api.restaurant.model.ProductCustomizationValue;
import com.api.restaurant.repository.CartItemCustomizationRepository;

@Service
public class CartItemCustomizationService {
    @Autowired
    private CartItemCustomizationRepository cartItemCustomizationRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    ProductCustomizationValueService productCustomizationValueService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CartItemCustomizationService.class);

    public CartItemCustomization create(CartItemCustomizationDTO cartItemCustomizationDTO) {
        logger.info("Creating cartItemCustomization");

        CartItemCustomization cartItemCustomization = modelMapper.map(cartItemCustomizationDTO, CartItemCustomization.class);

        CartItem cartItem = cartItemService.findById(cartItemCustomizationDTO.getCartItem());
        cartItemCustomization.setCartItem(cartItem);

        ProductCustomizationValue productCustomizationValue = productCustomizationValueService.findById(cartItemCustomizationDTO.getCustomizationValue());
        cartItemCustomization.setCustomizationValue(productCustomizationValue);

        return cartItemCustomizationRepository.save(cartItemCustomization);
    }

    public CartItemCustomization update(CartItemCustomizationDTO cartItemCustomizationDTO) {
        logger.info("Checking if cartItemCustomization exists");
        cartItemCustomizationRepository.findById(cartItemCustomizationDTO.getId())
            .orElseThrow(() -> {
                logger.error("CartItemCustomization do not exists");
                return new ResourceNotFoundException("Cart Item Customization", cartItemCustomizationDTO.getId());
            });

        CartItemCustomization cartItemCustomization = modelMapper.map(cartItemCustomizationDTO, CartItemCustomization.class);

        CartItem cartItem = cartItemService.findById(cartItemCustomizationDTO.getCartItem());
        cartItemCustomization.setCartItem(cartItem);

        ProductCustomizationValue productCustomizationValue = productCustomizationValueService.findById(cartItemCustomizationDTO.getCustomizationValue());
        cartItemCustomization.setCustomizationValue(productCustomizationValue);

        logger.info("Updating cartItemCustomization");
        return cartItemCustomizationRepository.save(cartItemCustomization);
    }

    public void delete(Long id) {
        logger.info("Checking if cartItemCustomization exists");
        CartItemCustomization cartItemCustomizationEntity = cartItemCustomizationRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("CartItemCustomization do not exists");
                return new ResourceNotFoundException("Cart Item Customization", id);
            });
        logger.info("Deleting cartItemCustomization");
        cartItemCustomizationRepository.delete(cartItemCustomizationEntity);
    }
}
