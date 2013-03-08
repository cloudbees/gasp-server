package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Restaurant;
import com.cloudbees.gasp.models.User;
import com.google.inject.persist.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author Kohsuke Kawaguchi
 */
@Path("/users")
public class UserResource extends ResourceCollection<User> {
    public UserResource() {
        super(User.class);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public User update(@PathParam("id") int id, Restaurant src) {
        User r = get(id);
        r.setName(src.getName());
        return manager.find(User.class,id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Transactional
    public Response create(User r) {
        manager.persist(r);
        return Response.created(URI.create(r.getId()+"")).build();
    }
}
