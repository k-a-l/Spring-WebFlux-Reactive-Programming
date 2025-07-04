package com.kalyan.reactivewebflux.router;

import com.kalyan.reactivewebflux.handler.CustomerHandler;
import com.kalyan.reactivewebflux.handler.CustomerStreamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {
    private final CustomerHandler handler;
    private final CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> customerRouter() {
        return RouterFunctions.route()
                .GET("/route/customers", handler::findAllCustomers)
                .GET("/route/stream", streamHandler::getCustomerResponse)
                .GET("/customer/{id}",handler::findCustomerById)
                .POST("/router/save", handler::saveCustomer)
                .build();
    }
}
