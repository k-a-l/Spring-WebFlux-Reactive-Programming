package com.kalyan.reactivewebflux.controller;

import com.kalyan.reactivewebflux.dto.CustomerDto;
import com.kalyan.reactivewebflux.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> findAll() {
        return customerService.getCustomers();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerDto> findAllStream() {
        return customerService.findAll();
    }
}
