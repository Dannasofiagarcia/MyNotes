package providers;

import db.DBConnection;
import model.Doing;
import model.ToDo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ToDoProvider {

    public void createToDo(ToDo note) throws SQLException {
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String noteDate = formatter.format(date);
        String sql = "INSERT INTO toDoTaskDannaGarcia(nombre, descripcion, fecha) VALUES ('$NOMBRE','$DESCRIPCION', '$FECHA')";
        sql = sql.replace("$NOMBRE", note.getNombre());
        sql = sql.replace("$DESCRIPCION", note.getDescripcion());
        sql = sql.replace("$FECHA", noteDate);
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    public ArrayList<ToDo> getAllToDoNotes() throws SQLException {
        ArrayList<ToDo> output = new ArrayList<>();

        String sql = "SELECT * FROM toDoTaskDannaGarcia";
        DBConnection connection = new DBConnection();
        connection.connect();

        ResultSet resultSet = connection.getDataBySQL(sql);
        while (resultSet.next()){
            output.add(new ToDo(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)
            ));
        }
        connection.disconnect();
        return output;
    }

    public void deleteByIdToDo(int id) throws SQLException {
        String sql = "DELETE FROM toDoTaskDannaGarcia WHERE id="+id;
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }
}