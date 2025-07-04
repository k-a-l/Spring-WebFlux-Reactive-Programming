package com.kalyan.reactivewebflux.dao;

import com.kalyan.reactivewebflux.dto.CustomerDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CustomerDto> findAll() {
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::sleepExecution)
                .peek(i->System.out.println("processing coint: " +i))
                .mapToObj(i -> new CustomerDto(i,"customer" + i,"lastName"+i))
                .collect(Collectors.toList());

    }

    public Flux<CustomerDto> findAllStream() {
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i->System.out.println("Processing count " + i))
                .map(i -> new CustomerDto(i,"customer" + i,"lastName"+i));


    }

    public Flux<CustomerDto> findAllList() {
        return Flux.range(1,50)
                //.delayElements(Duration.ofSeconds(1))
                .doOnNext(i->System.out.println("Processing count " + i))
                .map(i -> new CustomerDto(i,"customer" + i,"lastName"+i));


    }



}
