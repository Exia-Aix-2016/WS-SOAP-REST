package com.store.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class RestClientFactory {

    //1 seule instance client Jax-RS pour l'application webstore
    @Produces @ApplicationScoped
    public Client createRestClient(){
        return ClientBuilder.newClient();
    }
    public void close (@Disposes Client client){
        if(client!=null){
            client.close();
        }
    }
}

