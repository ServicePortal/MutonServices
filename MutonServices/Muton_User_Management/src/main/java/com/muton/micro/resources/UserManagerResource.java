package com.muton.micro.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muton.micro.api.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rohanw on 8/8/2015.
 */
@Path("/manage_user")
@Api(
        value = "UserManager",
        description = "Services for manage users.",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/muton-service/user/"
)
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class UserManagerResource {
    private final EntityManager em;
    private final ObjectMapper mapper;

    public UserManagerResource(EntityManager em) {
        this.em = em;
        mapper = new ObjectMapper();
    }

    @GET
    @Path("/create")
    @ApiOperation(
            value = "create",
            notes = "Create User",
            response = User.class
    )
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@QueryParam("message") String message) {
        User user = null;
        try {
            user = mapper.readValue(message, User.class);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @GET
    @Path("/userList")
    @ApiOperation(
            value = "userList",
            notes = "Display User List",
            response = User.class
    )
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserList(@QueryParam("message") String message) {
        List<User> users = null;
        try{
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class);
            users = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }


    @POST
    @Path("/update")
    @ApiOperation(
            value = "update",
            notes = "Update User",
            response = User.class,
            httpMethod = "POST"
    )
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User user) {

        System.out.println("User Name : " + user.getFullName());
        return user;
//        try {
//            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
//            query.setParameter("username", user.getUsername());
//            List<User> users = query.getResultList();
//            if(users.size() > 0) {
//                User old = users.get(0);
//                if(user.getFullName() != null) old.setFullName(user.getFullName());
//                if(user.getEmail() != null) old.setEmail(user.getEmail());
//                if(user.getPassword() != null) old.setPassword(user.getPassword());
//
//                em.getTransaction().begin();
//                em.persist(old);
//                em.getTransaction().commit();
//                user = old;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}
