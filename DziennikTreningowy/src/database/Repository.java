package database;

import components.DBField;
import components.IActivity;
import main.Main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository<T extends IActivity> {

    public void Save(T activity){
        //Search fields with annotation DBField and add to array
        //Bulid String with field names and string with field values
        String query;
        AtomicInteger id = new AtomicInteger();
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder updates = new StringBuilder();
        Field[] fields = activity.getClass().getDeclaredFields();

        Arrays.stream(fields).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
            try {
                e.setAccessible(true);
                if(!e.getName().equals("id")){
                    names.append(e.getName() + ",");
                    values.append("'" + e.get(activity) + "',");
                    updates.append(e.getName() + "='" + e.get(activity) +"',");
                }else{
                    id.set((int)e.get(activity));
                }
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });

        //Building sql query
        String className = activity.getClass().getSimpleName().toLowerCase();
        String column = names.substring(0, names.length()-1).toLowerCase();
        String value = values.substring(0, values.length()-1);
        String update = updates.substring(0, updates.length()-1);
        if(id.get() == 0) {
            query = "INSERT INTO " + className + " (" + column + ") VALUES (" + value + ");";
        }else{
            query = "UPDATE " + className + " SET " + update + " WHERE id='"+ id.get() +"';";
        }
        Main.connect.executeUpdate(query);
        System.out.println(query);
        System.out.println(id.get());
    }

    public void Remove(T activity){
        //Search id field and get value
        String query;
        AtomicInteger id = new AtomicInteger();
        Field[] fields = activity.getClass().getDeclaredFields();

        Arrays.stream(fields).filter(e->e.isAnnotationPresent(DBField.class)&& e.getName().equals("id")).forEach(e->{
            try {
                e.setAccessible(true);
                id.set((int)e.get(activity));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });

        //Building sql query
        String className = activity.getClass().getSimpleName().toLowerCase();
        query = "DELETE FROM " + className + " WHERE id='"+ id.get() +"';";

        Main.connect.executeUpdate(query);
        System.out.println(query);
    }

    public List<IActivity> getAll(Class<T> training){
        List<IActivity> trainigs = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        String className = training.getSimpleName().toLowerCase();
        String query = "SELECT * FROM " + className + ";";
        Field[] fields = training.getDeclaredFields();
        Method[] methods = training.getDeclaredMethods();

        Arrays.stream(fields).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
            e.setAccessible(true);
            columns.add(e.getName());
        });

        ResultSet result = Main.connect.executeQuery(query);
        List setters = new ArrayList();

        try {
            while (result.next()){
                T obj = training.newInstance();
                for (int i=0;i<columns.size();i++){
                    Object r = result.getObject(columns.get(i));
                    setters.add(r);
                }
                Arrays.stream(methods).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
                    int count = 0;
                    try {
                        e.invoke(obj,setters.get(count));
                        count++;
                    } catch (IllegalAccessException|InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                });
                trainigs.add(obj);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException  e) {
            e.printStackTrace();
        }

        return trainigs;
    }
}
