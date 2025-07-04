package com.kalyan.reactivewebflux.handler;

import com.kalyan.reactivewebflux.dao.CustomerDao;
import com.kalyan.reactivewebflux.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerHandler {
    private final CustomerDao customerDao;

    public Mono<ServerResponse> findAllCustomers(ServerRequest serverRequest){
        Flux<CustomerDto> allList = customerDao.findAllList();
        System.out.println(allList);
        return ServerResponse.ok().body(allList, CustomerDto.class);

    }

}
