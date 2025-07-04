package com.kalyan.reactivewebflux.router;

import com.kalyan.reactivewebflux.handler.CustomerHandler;
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

    @Bean
    public RouterFunction<ServerResponse> customerRouter() {
        return RouterFunctions.route()
                .GET("/route/customers", handler::findAllCustomers)
                .build();
    }
}
