package providers;

import db.DBConnection;
import model.Doing;
import model.Done;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoingProvider {

    public void createDoing(Doing note) throws SQLException {
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String noteDate = formatter.format(date);
        String sql = "INSERT INTO doingTaskDannaGarcia(nombre, descripcion, fecha) VALUES ('$NOMBRE','$DESCRIPCION', '$FECHA')";
        sql = sql.replace("$NOMBRE", note.getNombre());
        sql = sql.replace("$DESCRIPCION", note.getDescripcion());
        sql = sql.replace("$FECHA", noteDate);
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

   

    public ArrayList<Doing> getAllDoingNotes() throws SQLException {
        ArrayList<Doing> output = new ArrayList<>();

        String sql = "SELECT * FROM doingTaskDannaGarcia";
        DBConnection connection = new DBConnection();
        connection.connect();

        ResultSet resultSet = connection.getDataBySQL(sql);
        while (resultSet.next()){
            output.add(new Doing(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)
            ));
        }
        connection.disconnect();
        return output;
    }

    public void deleteByIdDoing(int id) throws SQLException {
        String sql = "DELETE FROM doingTaskDannaGarcia WHERE id="+id;
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    //public void advanceToDone(Doing note) throws SQLException {
    //    Done note = new Done(note.getId, note.getNombre, note.getDescripcion);
    //    createDone(note);
    //    deleteByIdDoing(note.getId);
    //}

    //public void backToToDo(Doing note) throws SQLException {
    //    ToDo note = new ToDo(note.getId, note.getNombre, note.getDescripcion);
    //    createToDo(note);
    //    deleteByIdDoing(note.getId);
    //}
}
   
   
