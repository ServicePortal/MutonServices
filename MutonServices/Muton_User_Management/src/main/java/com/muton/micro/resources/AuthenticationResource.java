package com.muton.micro.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muton.micro.api.AuthenticationRequest;
import com.muton.micro.api.AuthenticationResponse;
import com.muton.micro.api.AuthorizationResponse;
import com.muton.micro.api.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by rohanw on 8/8/2015.
 */

@Path("/authentication")
@Api(
        value = "Authentication",
        description = "Services for authenticate of users",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/muton-service/user/"
)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {
    private final EntityManager em;
    private final ObjectMapper mapper;

    public AuthenticationResource(EntityManager em) {
        this.em = em;
        mapper = new ObjectMapper();
    }

    @GET
    @Path("/authenticate")
    @ApiOperation(
            value = "authenticate",
            notes = "Authenticate function",
            response = AuthorizationResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "User Name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "string", paramType = "query")
    })
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse authenticate(@QueryParam("message") String message) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            AuthenticationRequest request = mapper.readValue(message, AuthenticationRequest.class);
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
            query.setParameter("username", request.getUsername());
            List<User> users = query.getResultList();
            if(users.size() > 0) {
                User user = users.get(0);
                if(user.getStatus() != 0){
                    response.setStatus(-1);
                    response.setUserStatus(user.getStatus());
                    response.setStatusDescription("User locked.");
                } else if(user.getPassword().equals(request.getPassword())) {
                    response.setStatus(1);
                    response.setUserStatus(user.getStatus());
                    response.setLastSuccessfulLogin(user.getLastSuccessfulLogin());
                    response.setLastLoginAttempt(user.getLastLoginAttempt());
                    response.setFailedAttempts(user.getFailedAttempts());
                    user.setFailedAttempts(0);
                    user.setLastSuccessfulLogin(new Date());
                } else {
                    response.setStatus(-1);
                    response.setStatusDescription("Invalid username/password.");
                    user.setFailedAttempts(user.getFailedAttempts() + 1);
                    response.setFailedAttempts(user.getFailedAttempts());
                    if(user.getFailedAttempts() > 3) {
                        user.setStatus(2);//locked
                        response.setStatusDescription("Invalid username/password. User locked.");
                    }
                }
                user.setLastLoginAttempt(new Date());
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();

            } else {
                response.setStatus(-1);
                response.setStatusDescription("Invalid username/password.");
            }

        } catch (IOException e) {
            response.setStatus(-1);
            response.setStatusDescription("Invalid input data : " + message);
            e.printStackTrace();
        }

        return response;
    }
}
