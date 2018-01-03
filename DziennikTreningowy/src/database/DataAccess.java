package database;

import java.sql.*;

public class DataAccess {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DataAccess(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ppo", "filip", "53EOZSjGBrSQ1Zmk");
            st = con.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public ResultSet executeQuery(String query){
        try {
            rs = st.executeQuery(query);
            return rs;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void executeUpdate(String update){
        try {
            st.executeUpdate(update);
        }catch (Exception e){
            System.out.println(e);
        }
    }

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