package com.karen.shoppingbasket.rest.resources.product;

import com.karen.shoppingbasket.facade.product.ProductFacade;
import com.karen.shoppingbasket.restmodels.product.CreateProductModel;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.UpdateProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author Karen Arakelyan
 */

@RestController(value = "/rest/product")
public class ProductResource {

    private final ProductFacade productFacade;

    @Autowired
    public ProductResource(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @RequestMapping(name = "Create Product", method = RequestMethod.POST)
    public Long createProduct(@RequestBody final CreateProductModel createProductModel) {
        return productFacade.createProduct(createProductModel);
    }

    @RequestMapping(name = "Create Product", method = RequestMethod.PUT, path = "/{id}/")
    public ProductInformationResponseModel updateProduct(@PathParam("id") final Long id, final UpdateProductModel updateProductModel) {
        return productFacade.updateProduct(id, updateProductModel);
    }

    @RequestMapping(name = "Create Product", method = RequestMethod.GET, path = "/{id}")
    public ProductInformationResponseModel getById(@PathParam("id") final Long id) {
        return productFacade.getById(id);
    }

    @RequestMapping(name = "Create Product", method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathParam("id") final Long id) {
        productFacade.delete(id);
    }


}
