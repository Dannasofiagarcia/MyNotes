package services;

import model.Message;
import model.ToDo;
import providers.ToDoProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("toDo")
public class ToDoServices {

    @GET
    @Path("echo")
    public String echo() {
        return "echo";
    }
    
    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(ToDo note) {
        try {
            ToDoProvider provider = new ToDoProvider();
            provider.createToDo(note);
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
            ToDoProvider provider = new ToDoProvider();
            ArrayList<ToDo> notes = provider.getAllToDoNotes();
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
            ToDoProvider provider = new ToDoProvider();
            provider.deleteByIdToDo(id);
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
