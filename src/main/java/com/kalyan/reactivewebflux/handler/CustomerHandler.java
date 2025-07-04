package com.kalyan.reactivewebflux.handler;

import com.kalyan.reactivewebflux.dao.CustomerDao;
import com.kalyan.reactivewebflux.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
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
        return ServerResponse.ok().body(allList, CustomerDto.class);

    }

    public Mono<ServerResponse> findCustomerById(ServerRequest serverRequest){
        int id = Integer.valueOf(serverRequest.pathVariable("id"));
        //customerDao.findAllList().filter(c -> c.getId() == id).take(1).single();
        Mono<CustomerDto> next = customerDao.findAllList().filter(c -> c.getId() == id).next();

        return ServerResponse.ok().body(next, CustomerDto.class);

        }

        public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest){
            Mono<CustomerDto> customer= serverRequest.bodyToMono(CustomerDto.class);
            Mono<String> map = customer.map(dto -> dto.getId() + " " + dto.getFirstName() + " " + dto.getLastName());
            return ServerResponse.ok().body(map, CustomerDto.class);
        }



}
