package com.kalyan.reactivewebflux.handler;

import com.kalyan.reactivewebflux.dao.CustomerDao;
import com.kalyan.reactivewebflux.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CustomerStreamHandler {
    private final CustomerDao customerDao;

    public Mono<ServerResponse> getCustomerResponse(ServerRequest req) {
        Flux<CustomerDto> throttled = customerDao.findAllList()
                .delayElements(Duration.ofSeconds(1))
                .onBackpressureBuffer(10, BufferOverflowStrategy.DROP_LATEST);

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(throttled, CustomerDto.class);

    }
}
