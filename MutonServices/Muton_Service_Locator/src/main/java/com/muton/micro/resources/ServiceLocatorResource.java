package com.muton.micro.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muton.micro.api.LocationRequest;
import com.muton.micro.api.LocationResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by rohanw on 8/3/2015.
 */
@Path("/get_service")
@Api(
        value = "/get_service",
        description = "Service Discovery service <This text can be edited >",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/muton-service/service_locator/"
)
//@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceLocatorResource {
    private HashMap<String, String> serviceLocations = new HashMap<String, String>(10);

    ObjectMapper mapper = new ObjectMapper();
    @GET
    @ApiOperation(
            value = "getLocation",
            notes = "Service Discovery endpoint.",
            response = LocationResponse.class
    )
    @Timed
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LocationResponse getLocation(@QueryParam("message") String message) {
        LocationResponse response = new LocationResponse();
        try {
            LocationRequest request = mapper.readValue(message, LocationRequest.class);
            response.setServiceCode(request.getServiceCode());
            response.setServiceId(request.getServiceId());
            response.setVersion(request.getVersion());
            response.setUrl(serviceLocations.get(request.getServiceId()+ "#"+request.getVersion()));
            response.setStatus(0);
        } catch (IOException e) {
            response.setStatus(-1);
            response.setErrorMessage("Invalid input data : " + message);
            e.printStackTrace();
        }

        return response;
    }

    public ServiceLocatorResource() {
        serviceLocations.put("authentication#1.0", "http://192.168.16.210:8080/muton-service/authentication_v1/authenticate");
        serviceLocations.put("authentication#1.1", "http://192.168.16.210:8080/muton-service/authentication_v1.1/authenticate");
        serviceLocations.put("authentication#2.0", "http://192.168.16.210:8080/muton-service/authentication_v2/authenticate");
    }
}
