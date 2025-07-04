package com.kalyan.reactivewebflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class MonoFluxTest {


    @Test
    public void testMono(){
        Mono<?> stringMono = Mono.just("Hello").then(Mono.error(new RuntimeException("Sad"))).log();
        stringMono.subscribe(System.out::println, (e)->e.printStackTrace());

    }

    @Test
    public void testFlux(){
        Flux<?> stringFlux = Flux.just("Hello", "Hi", "Greeting").concatWith(Flux.error(new RuntimeException("Bad"))).log();
        stringFlux.subscribe(System.out::println, (e)->e.printStackTrace());

    }
}
