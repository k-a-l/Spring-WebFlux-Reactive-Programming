package com.kalyan.reactivewebflux.service;

import com.kalyan.reactivewebflux.dao.CustomerDao;
import com.kalyan.reactivewebflux.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerDao customerDao;

    public List<CustomerDto> getCustomers() {
        long start = System.currentTimeMillis();
        List<CustomerDto> list = customerDao.findAll();
        long end = System.currentTimeMillis();
        log.info(new StringBuilder().append("The system took ").append(end - start).append("ms").toString());
        return list;

    }

    public Flux<CustomerDto> findAll() {
        long start = System.currentTimeMillis();
        Flux<CustomerDto> list = customerDao.findAllStream();
        long end = System.currentTimeMillis();
        log.info(new StringBuilder().append("The system took ").append(end - start).append("ms").toString());
        return list;
    }
}
