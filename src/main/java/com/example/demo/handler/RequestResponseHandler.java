package com.example.demo.handler;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static com.example.demo.constants.ApplicationConstants.FETCH_CUSTOMER_REQUEST_VALUE;

@Component
@Slf4j
public class RequestResponseHandler {

    @Autowired
    Vertx vertx;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void processEventConsumptions() {
        vertx.eventBus().consumer(FETCH_CUSTOMER_REQUEST_VALUE, this::onMessage);

    }

    private void onMessage(Message<Object> objectMessage) {
        final String customerId = objectMessage.body().toString();
        Optional<Customer> customer = customerService.getCustomerId(Integer.parseInt(customerId));
        customer.ifPresentOrElse( customer1 -> {
            log.info("customer object retrieved: "+customer1);
            objectMessage.reply(customer1);
        }, ()-> {
            log.info("customer object failed:");
            objectMessage.fail(400, "retrieval failed");
        });

    }
}
