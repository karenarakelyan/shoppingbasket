package com.karen.shoppingbasket.facade.order.impl;

import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.OrderProduct;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.facade.product.impl.ProductMapperFacade;
import com.karen.shoppingbasket.restmodels.order.OrderProductResponseModel;
import com.karen.shoppingbasket.restmodels.order.OrderResponseModel;
import com.karen.shoppingbasket.restmodels.order.OrderStatus;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.services.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Karen Arakelyan
 */

@Component
public class OrderMapperFacade {

    private final ProductService productService;

    private final ProductMapperFacade productMapperFacade;

    public OrderMapperFacade(final ProductService productService, final ProductMapperFacade productMapperFacade) {
        this.productService = productService;
        this.productMapperFacade = productMapperFacade;
    }

    public OrderResponseModel generateOrderResponseModel(final Order order) {
        final List<OrderProductResponseModel> orderProductResponseModels = order.getOrderProducts()
                .stream()
                .map(this::generateOrderProductResponseModel)
                .collect(Collectors.toList());
        return new OrderResponseModel(orderProductResponseModels, OrderStatus.valueOf(order.getStatus().name()));
    }

    public OrderProductResponseModel generateOrderProductResponseModel(final OrderProduct orderProduct) {
        final Product product = productService.findOne(orderProduct.getProduct().getId());
        final ProductInformationResponseModel productInformationResponseModel = productMapperFacade.generateResponseModel(product);
        return new OrderProductResponseModel(productInformationResponseModel, orderProduct.getQuantity());
    }


}
