package com.bank.paymentmgmt.facade;

import com.bank.paymentmgmt.facade.domain.Payment;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.GeneratedValue;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.StringReader;
import java.util.List;

@Path("payment")
@RequestScoped
public class PaymentResource {
    @Context
    private UriInfo context;
    /*
        On utilise l’annotation @EJB pour injecter une vue remote car l’annotation @Inject liée
        à la spécification CDI ne supporte pas le remoting.
     */
    @EJB
    private IBankingServiceRemote bankingService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(String content){
        StringReader reader = new StringReader(content);

        String ccNumber;
        Double amount;

        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject paymentInfo = jsonReader.readObject();
            ccNumber = paymentInfo.getString("ccNumber");
            amount = paymentInfo.getJsonNumber("amount").doubleValue();



        }catch (Exception err){
            System.out.println(err.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        Boolean isValid = bankingService.createPayment(ccNumber, amount);

        if(isValid){
            return Response.status(Response.Status.OK).entity("The payment of " + amount + " has been accepted").build();
        }else{
            return Response.status(400).entity("invalide code card").build();
        }
    }
    @Path("{id}")//utilisation d'un template parameter dans le pattern d'URI
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getStoredPayment(@PathParam("id") Long paymentId){
        Payment storedPayment = bankingService.lookupStoredPayment(paymentId);
        return Response.accepted().entity(storedPayment).build();
    }

    @Path("{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public  Response deleteStoredPayment(@PathParam("id") Long paymentId){

        Payment deletedPayment = bankingService.deleteStoredPayment(paymentId);
        if(deletedPayment != null){
            return Response.accepted().entity(deletedPayment).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("entity not found").build();
        }

    }

    @Path("payments")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getStoredPayments(){
        List<Payment> payments = bankingService.lookupAllStoredPayments();

        GenericEntity<List<Payment>> genericEntity = new GenericEntity<List<Payment>>(payments){};

        return Response.ok(genericEntity).build();
    }

}
