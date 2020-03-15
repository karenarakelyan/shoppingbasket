package com.karen.shoppingbasket.rest.resources.product;

import com.karen.shoppingbasket.facade.product.ProductFacade;
import com.karen.shoppingbasket.restmodels.product.CreateProductModel;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.UpdateProductModel;
import com.karen.shoppingbasket.security.core.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(name = "Create Product", method = RequestMethod.POST)
    public Long createProduct(@RequestBody final CreateProductModel createProductModel) {
        return productFacade.createProduct(createProductModel);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(name = "Update Product", method = RequestMethod.PUT, path = "/{id}/")
    public ProductInformationResponseModel updateProduct(@PathVariable("id") final Long id, final UpdateProductModel updateProductModel) {
        return productFacade.updateProduct(id, updateProductModel);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @RequestMapping(name = "Get Product By Id", method = RequestMethod.GET, path = "/{id}")
    public ProductInformationResponseModel getById(@PathVariable("id") final Long id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Long userId = ((TokenAuthentication) authentication).getUser().getId();
        return productFacade.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(name = "Delete Product", method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") final Long id) {
        productFacade.delete(id);
    }


}
