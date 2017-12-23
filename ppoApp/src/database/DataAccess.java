package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getData(){
        try {
            List<String> imiona = new ArrayList<String>();

            String query = "select * from imiona";
            rs = st.executeQuery(query);
            System.out.println("Records from database");
            while(rs.next()){
                String name = rs.getString("imie");
                imiona.add(name);
            }
            return imiona;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void setData(String imie){
        try {
            st.executeUpdate("INSERT INTO imiona " + "VALUES ('" +imie+ "')");
        }catch (Exception e){
            System.out.println(e);
        }
    }


}