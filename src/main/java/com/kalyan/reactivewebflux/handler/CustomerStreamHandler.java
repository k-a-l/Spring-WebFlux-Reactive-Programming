package com.kalyan.reactivewebflux.handler;

import com.kalyan.reactivewebflux.dao.CustomerDao;
import com.kalyan.reactivewebflux.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerStreamHandler {
    private final CustomerDao customerDao;

    public Mono<ServerResponse> getCustomerResponse(ServerRequest req){
        Flux<CustomerDto> allStream = customerDao.findAllStream();
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allStream, CustomerDto.class);
    }
}
