package com.muton.micro.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muton.micro.api.AuthorizationRequest;
import com.muton.micro.api.AuthorizationResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by rohanw on 8/8/2015.
 */
@Path("/authorization")
@Api(
        value = "Authorization",
        description = "Services for authorization of user activities",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/muton-service/user/"
)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthorizationResource {
    private final EntityManager em;
    private final ObjectMapper mapper;

    public AuthorizationResource(EntityManager em) {
        this.em = em;
        mapper = new ObjectMapper();
    }

    @GET
    @ApiOperation(
            value = "authorization",
            notes = "Authorize function",
            response = AuthorizationResponse.class
    )

    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthorizationResponse getLocation(@QueryParam("message") String message) {
        AuthorizationResponse response = new AuthorizationResponse();
        try {
            AuthorizationRequest request = mapper.readValue(message, AuthorizationRequest.class);
            //todo logic
            response.setStatus(0);
        } catch (IOException e) {
            response.setStatus(-1);
            response.setStatusDescription("Invalid input data : " + message);
            e.printStackTrace();
        }

        return response;
    }
}
