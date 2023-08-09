package com.example.demo.router;

import com.example.demo.handler.CustomerDataStoreHandler;
import com.example.demo.handler.LoggingCustomerHandler;
import com.example.demo.handler.ReceipientHandler;
import com.example.demo.handler.SenderHandler;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderRouter {

    @Autowired
    Vertx vertx;

    @Autowired
    ReceipientHandler receipientHandler;

    @Autowired
    SenderHandler senderHandler;

    @Autowired
    CustomerDataStoreHandler customerDataStoreHandler;

    @Autowired
    LoggingCustomerHandler loggingCustomerHandler;

    public Router apiRouter() {
        Router router = Router.router(vertx);
        router.get("/getCustomer").handler(receipientHandler::getCustomerValue);
        router.get("/getCustomerPublish/:id").handler(senderHandler::getCustomerIdValuePublish);
        router.get("/getCustomerSend/:id").handler(senderHandler::getCustomerIdValuesSend);
        router.get("/getCustomersRxRequest/:id").handler(senderHandler::rxRequestToOtherEventsValue);
        router.get("/getCustomersRequest/:id").handler(senderHandler::requestToOtherEventsValue);
        router.post("/postCustomersRxRequest").handler(customerDataStoreHandler);
        router.post("/postCustomersRxRequest").handler(loggingCustomerHandler);
        router.get("/getCustomersHandlerRequest/:id").handler(customerDataStoreHandler::getData);
        return router;
    }
}
