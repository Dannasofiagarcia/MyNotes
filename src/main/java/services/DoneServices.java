package services;

import model.Message;
import model.Done;
import providers.DoneProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("done")
public class DoneServices {
    

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Done note) {
        try {
            DoneProvider provider = new DoneProvider();
            provider.createDone(note);
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
            DoneProvider provider = new DoneProvider();
            ArrayList<Done> notes = provider.getAllDoneNotes();
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
            DoneProvider provider = new DoneProvider();
            provider.deleteByIdDone(id);
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
