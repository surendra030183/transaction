package com.product.service.command.interceptor;

import com.product.service.command.CreateProductCommand;
import com.product.service.data.ProductLookupEntity;
import com.product.service.repository.ProductLookupRepository;
import org.aspectj.bridge.ICommand;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {

        return (index, command) -> {
            LOGGER.info("Intercepted command: "+ command.getPayloadType());
            LOGGER.info("Intercepted command: "+ command.getPayload());

            if(CreateProductCommand.class.equals(command.getPayloadType())) {
                LOGGER.info("Intercepted command: TODO some specific task with CreateProductCommand");
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

                if(productLookupEntity != null) {
                    throw new IllegalStateException(String.format("Product with productId %s or title %s already exist",
                            productLookupEntity.getProductId(), productLookupEntity.getTitle()));
                }
            }

            return command;
        };
    }
}
