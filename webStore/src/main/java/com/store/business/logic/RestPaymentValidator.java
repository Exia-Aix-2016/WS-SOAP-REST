package com.store.business.logic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.awt.*;
import java.net.URI;

@Stateless
@Rest
public class RestPaymentValidator implements IPaymentValidator{

    @Inject
    private Client client;

    @Override
    public Boolean process(String ccNumber, Double amount) {
        if(ccNumber == null || amount == null) return  false;


        JsonObjectBuilder paymentBuilder = Json.createObjectBuilder();
        JsonObject paymentObject = paymentBuilder.add("ccNumber",ccNumber)
                .add("amount",amount).build();


        URI serviceURI = UriBuilder.fromUri("http://localhost:11080/bankFacade-war-1.0-SNAPSHOT").build();
        WebTarget target = client.target(serviceURI);
        target = target.path("bank").path("payment");

        Response response = target.request().post(Entity.entity(paymentObject, MediaType.APPLICATION_JSON_TYPE));

        Boolean success = false;

        if(response.getStatus()==Response.Status.OK.getStatusCode()){
            success =  true;
        }
        response.close();



        return success;
    }

}
