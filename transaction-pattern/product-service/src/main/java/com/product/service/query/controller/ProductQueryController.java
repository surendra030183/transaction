package com.product.service.query.controller;

import com.product.service.model.ProductRestModel;
import com.product.service.query.FindProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductQueryController {

    @Autowired
    private QueryGateway axonQueryGateway;

    @GetMapping
    public List<ProductRestModel> getProducts(){

        FindProductQuery findProductQuery = new FindProductQuery();

        List<ProductRestModel> list = axonQueryGateway.query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return list;
    }

}
