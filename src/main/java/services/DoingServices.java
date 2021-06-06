package services;

import model.Message;
import model.Doing;
import providers.DoingProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("doing")
public class DoingServices {
    

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Doing note) {
        try {
            DoingProvider provider = new DoingProvider();
            provider.createDoing(note);
            return Response
                    .ok(new Message("Nota creada"))
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("La nota no pudo ser creada"))
                    .header("Content-Type", "application/json")
                    .build();
        }
    }

    @GET
    @Path("all")
    public Response getAll(){
        try {
            DoingProvider provider = new DoingProvider();
            ArrayList<Doing> notes = provider.getAllDoingNotes();
            return Response
                    .ok(notes)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }


    // @PUT
    // @Path("edit")
    // @Consumes("application/json")
    // public Response edit(Teacher teacher){
    //     try {
    //         TeacherProvider provider = new TeacherProvider();
    //         provider.edit(teacher);
    //         return Response
    //                 .ok(new Message("La nota fue editada exitosamente"))
    //                 .header("Content-Type","application/json")
    //                 .build();
    //     } catch (SQLException exception) {
    //         exception.printStackTrace();
    //         return Response
    //                 .status(500)
    //                 .entity(new Message("La nota no pudo ser editada"))
    //                 .header("Content-Type","application/json")
    //                 .build();
    //     }
    // }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") int id){
        try {
            DoingProvider provider = new DoingProvider();
            provider.deleteByIdDoing(id);
            return Response
                    .ok(new Message("Nota eliminada"))
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("La nota no pudo ser eliminada"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

}
