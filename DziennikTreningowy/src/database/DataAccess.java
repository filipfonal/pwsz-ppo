package database;

import javax.swing.*;
import java.sql.*;

/**
 * DataAccess class to connect with mysql database
 */
public class DataAccess {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    /**
     * This is constructor to initialize DataAccess object with specific connection settings.
     * Required data to connect: url, user, password.
     */
    public DataAccess(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ppo", "filip", "vGUbGAcXSCqYkewc");
            st = con.createStatement();

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(new JFrame(), "Brak połącznia z bazą !", "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * To execute query String in mysql database.
     * @param query SQL query command
     * @return query result returned by database
     */
    public ResultSet executeQuery(String query){
        try {
            rs = st.executeQuery(query);
            return rs;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * To execute update command String in mysql database.
     * @param update SQL update command
     */
    public void executeUpdate(String update){
        try {
            st.executeUpdate(update);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * To checking if table exist in mysql database.
     * @param tableName name of table in database
     * @return True if table exist, False if table not exist
     */
    public boolean checkExists(String tableName){
        DatabaseMetaData dbm = null;
        try {
            dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}