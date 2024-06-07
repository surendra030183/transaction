package com.product.service.command.controller;

import com.product.service.command.CreateProductCommand;
import com.product.service.model.CreateProductRestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductCommandController {

    private final Environment environment;
    private final CommandGateway axonServerCommandGateway;

    @Autowired
    public ProductCommandController(Environment environment, CommandGateway axonServerCommandGateway) {
        this.environment = environment;
        this.axonServerCommandGateway = axonServerCommandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel productRestModel){
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(productRestModel.getPrice())
                .title(productRestModel.getTitle())
                .quantity(productRestModel.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        String result;
        try {
            result = axonServerCommandGateway.sendAndWait(createProductCommand);
            // Note: CreateProductCommand will be handled in @ProductAggregate.class
        } catch (Exception e) {
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping
    public String updateProduct(){
        return "UPDATE Product";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "DELETE Product";
    }

}
