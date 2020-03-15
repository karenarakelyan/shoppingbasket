package com.karen.shoppingbasket.rest.resources.order;

import com.karen.shoppingbasket.facade.order.OrderFacade;
import com.karen.shoppingbasket.restmodels.order.CreateOrderModel;
import com.karen.shoppingbasket.restmodels.order.MultipleOrdersResponseModel;
import com.karen.shoppingbasket.restmodels.order.OrderResponseModel;
import com.karen.shoppingbasket.restmodels.order.OrderStatus;
import com.karen.shoppingbasket.security.core.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Karen Arakelyan
 */

@RestController
@RequestMapping(path = "/rest/orders")
public class OrderResource {

    private final OrderFacade orderFacade;

    @Autowired
    public OrderResource(final OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(name = "Create Order", method = RequestMethod.POST)
    public Long createOrder(@RequestBody final CreateOrderModel createOrderModel) {
        return orderFacade.createOrder(createOrderModel, getUserId());
    }

    @RequestMapping(name = "Get all orders", method = RequestMethod.GET)
    public MultipleOrdersResponseModel getAllOrders() {
        return new MultipleOrdersResponseModel(orderFacade.getAllOrders(getUserId()));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @RequestMapping(name = "Get order by id", method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<OrderResponseModel> getOrder(@PathVariable(value = "id") final Long orderId) {
        final OrderResponseModel order = orderFacade.getOrder(orderId, getUserId());
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(name = "Get order by id", method = RequestMethod.POST, path = "{id}/status")
    public void changeOrderStatus(@PathVariable(value = "id") final Long orderId, @RequestBody final OrderStatus orderStatus) {
        orderFacade.changeOrderStatus(orderId, orderStatus);
    }

    private Long getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((TokenAuthentication) authentication).getUser().getId();
    }


}
