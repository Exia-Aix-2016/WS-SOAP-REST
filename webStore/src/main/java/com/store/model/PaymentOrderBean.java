package com.store.model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Named(value = "PaymentOrderModel")
@RequestScoped
public class PaymentOrderBean {

    private final String baseURL = "http://localhost:11080/bankFacade-war-1.0-SNAPSHOT/bank/payment";
    private List<PaymentOrder> paymentOrders = new ArrayList<>();

    @Inject
    private Client client;

    @PostConstruct
    public void init(){
        loadAllPayments();
    }



    public List<PaymentOrder> getPaymentOrders(){
        return paymentOrders;
    }

    public void loadAllPayments(){
        paymentOrders.clear();
        URI serviceURI = UriBuilder.fromUri(baseURL).build();
        WebTarget target = client.target(serviceURI);
        target = target.path("payments");
        Response response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        String jsonContent = response.readEntity(String.class);

        response.close();

        try(JsonReader jsonReader = Json.createReader(new StringReader(jsonContent))){
            
            JsonArray jsonValues = jsonReader.readArray();

            for (JsonObject jo : jsonValues.getValuesAs(JsonObject.class)) {

                Long id = jo.getJsonNumber("id").longValue();
                Double amount = jo.getJsonNumber("amount").doubleValue();
                paymentOrders.add(new PaymentOrder(baseURL + "/" + id, amount, id));
            }
        }catch (Exception err){
            System.out.println(err.toString());
        }
    }

    public void removedPayment(Long id){

        URI serviceURI = UriBuilder.fromUri(baseURL).build();
        WebTarget target = client.target(serviceURI);

        target = target.path("{id}").resolveTemplate("id", id);
        target.request().accept(MediaType.APPLICATION_JSON_TYPE).delete();
        loadAllPayments();
    }

}
