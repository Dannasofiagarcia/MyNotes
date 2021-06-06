package providers;

import db.DBConnection;
import model.Doing;
import model.Done;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoneProvider {

    public void createDone(Done note) throws SQLException {
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String noteDate = formatter.format(date);
        String sql = "INSERT INTO doneTaskDannaGarcia(nombre, descripcion, fecha) VALUES ('$NOMBRE','$DESCRIPCION', '$FECHA')";
        sql = sql.replace("$NOMBRE", note.getNombre());
        sql = sql.replace("$DESCRIPCION", note.getDescripcion());
        sql = sql.replace("$FECHA", noteDate);
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    public ArrayList<Done> getAllDoneNotes() throws SQLException {
        ArrayList<Done> output = new ArrayList<>();

        String sql = "SELECT * FROM doneTaskDannaGarcia";
        DBConnection connection = new DBConnection();
        connection.connect();

        ResultSet resultSet = connection.getDataBySQL(sql);
        while (resultSet.next()){
            output.add(new Done(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)
                    ));
        }
        connection.disconnect();
        return output;
    }

    public void deleteByIdDone(int id) throws SQLException {
        String sql = "DELETE FROM doneTaskDannaGarcia WHERE id="+id;
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

  
//    public void backToDoing(Done note) throws SQLException {
//        Doing note = new Doing(note.getId, note.getNombre, note.getDescripcion);
//        createDoing(note);
//        deleteByIdDone(note.getId);
//    }
   
   

    
}