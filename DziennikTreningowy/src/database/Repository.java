package database;

import components.DBField;
import components.IActivity;
import main.Main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repository class to operate on IActivity type objects.
 * @param <T> Type of object they implements IActivity interface
 */
public class Repository<T extends IActivity> {

    /**
     * This method take IActivity object, reads all the fields and values by the reflection mechanism.
     * At the end build sql query and add or update this object in sql database.
     * @param activity IActivity object
     */
    public void Save(T activity){
        //Search fields with annotation DBField and add to array
        //Bulid String with field names and string with field values
        String query;
        AtomicInteger id = new AtomicInteger();
        StringBuilder names = new StringBuilder();
        StringBuilder namesWithTypes = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder updates = new StringBuilder();
        Field[] fields = activity.getClass().getDeclaredFields();

        Arrays.stream(fields).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
            try {
                e.setAccessible(true);
                if(!e.getName().equals("id")){
                    names.append(e.getName() + ",");
                    namesWithTypes.append(e.getName() +" "+ getSQLType(e.getType().getSimpleName()) + ",");
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
        String activitySub = activity.getClass().getSimpleName().toLowerCase();
        String namesSub = names.substring(0, names.length()-1).toLowerCase();
        String valuesSub = values.substring(0, values.length()-1);
        String updatesSub = updates.substring(0, updates.length()-1);
        String namesWithTypesSub = namesWithTypes.substring(0, namesWithTypes.length()-1);
        //Check if table exists/create table
        if(!Main.connect.checkExists(activitySub)){
            String sql = "CREATE TABLE "+ activitySub +" (id INT PRIMARY KEY AUTO_INCREMENT,"+ namesWithTypesSub +");";
            Main.connect.executeUpdate(sql);
        }
        //Check if record exists/create or update record
        if(id.get() == 0) {
            query = "INSERT INTO " + activitySub + " (" + namesSub + ") VALUES (" + valuesSub + ");";
        }else{
            query = "UPDATE " + activitySub + " SET " + updatesSub + " WHERE id='"+ id.get() +"';";
        }
        Main.connect.executeUpdate(query);
    }

    /**
     * This method take IActivity object and remove it from sql database.
     * @param activity - IActivity object
     */
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
    }

    /**
     * Get all object by given class from database.
     * @param training specific training class
     * @return List<IActivity> Array with returned IActivity object from database
     */
    public List<IActivity> getAll(Class<T> training){
        List<IActivity> trainigs = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        String className = training.getSimpleName().toLowerCase();
        String query = "SELECT * FROM " + className + ";";
        Field[] fields = training.getDeclaredFields();
        Method[] methods = training.getDeclaredMethods();
        Map<String, Object> setters = new HashMap<>();

        Arrays.stream(fields).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
            e.setAccessible(true);
            columns.add(e.getName());
        });

        if(!Main.connect.checkExists(className)){
            return trainigs;
        }
        ResultSet result = Main.connect.executeQuery(query);

        try {
            while (result.next()){
                T obj = training.newInstance();
                for (int i=0;i<columns.size();i++){
                    Object r = result.getObject(columns.get(i));
                    setters.put(columns.get(i),r);
                }
                Arrays.stream(methods).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
                    try {
                        e.invoke(obj,setters.get(e.getName().substring(3).toLowerCase()));
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

    /**
     * Get one object by given id and class from database.
     * @param id training id
     * @param training training class type
     * @return IActivity
     */
    public IActivity Get(int id, Class<T> training){
        String className = training.getSimpleName().toLowerCase();
        String query = "SELECT * FROM " + className + " WHERE id='" + id + "';";
        List<String> columns = new ArrayList<>();
        Field[] fields = training.getDeclaredFields();
        Method[] methods = training.getDeclaredMethods();
        Map<String, Object> setters = new HashMap<>();

        Arrays.stream(fields).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
            e.setAccessible(true);
            columns.add(e.getName());
        });

        if(!Main.connect.checkExists(className)){
            return null;
        }
        ResultSet result = Main.connect.executeQuery(query);

        try {
            while (result.next()){
                T obj = training.newInstance();
                for (int i=0;i<columns.size();i++){
                    Object r = result.getObject(columns.get(i));
                    setters.put(columns.get(i),r);
                }
                Arrays.stream(methods).filter(e->e.isAnnotationPresent(DBField.class)).forEach(e->{
                    try {
                        e.invoke(obj,setters.get(e.getName().substring(3).toLowerCase()));
                    } catch (IllegalAccessException|InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                });
                return obj;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException  e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Convert JAVA types to SQL types.
     * @param typeName String with JAVA type name
     * @return String with SQL type name
     */
    private String getSQLType(String typeName){
        if(typeName.equals("String"))
            return "VARCHAR(255)";
        if(typeName.equals("int"))
            return "INTEGER";
        if(typeName.equals("double"))
            return "DOUBLE";

        return null;
    }


}
