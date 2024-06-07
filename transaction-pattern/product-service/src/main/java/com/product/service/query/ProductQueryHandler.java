package com.product.service.query;

import com.product.service.data.ProductEntity;
import com.product.service.model.ProductRestModel;
import com.product.service.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    @Autowired
    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery findProductQuery){
        List<ProductRestModel> list = new ArrayList<>();

        List<ProductEntity> entities = productRepository.findAll();

        for (ProductEntity entity: entities) {
            ProductRestModel productRestModel = new ProductRestModel();

            BeanUtils.copyProperties(entity, productRestModel);
            list.add(productRestModel);
        }
        return list;
    }
}
