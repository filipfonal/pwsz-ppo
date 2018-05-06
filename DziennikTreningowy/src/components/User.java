package components;


import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User class for storing person information
 */

public class User {
    private String name;
    private int weight;
    private int trainingsPerMonth;
    private int caloriesPerTraining;

    /**
     * This is constructor to initialize person object
     * @param name an initial person name
     * @param weight an initial person weight
     * @param trainings sets user goal of trainings per month
     * @param calories sets user goal of calories per training
     */
    public User(String name, int weight, int trainings, int calories){
        this.name = name;
        this.weight = weight;
        this.trainingsPerMonth = trainings;
        this.caloriesPerTraining = calories;
    }

    /**
     * To save user data in json file
     */
    public void Save(){
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("weight", weight);
        obj.put("trainings", trainingsPerMonth);
        obj.put("calories", caloriesPerTraining);

        try (FileWriter file = new FileWriter("user.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * To get user object from file
     * @return User object
     */
    public static User getUser(){
        JSONParser parser = new JSONParser();
        File file = new File("user.json");

        if(file.exists()) {
            try {
                Object obj = parser.parse(new FileReader("user.json"));

                JSONObject jsonObject = (JSONObject) obj;

                String name = (String) jsonObject.get("name");
                int weight = jsonObject.get("weight").hashCode();
                int trainings = jsonObject.get("trainings").hashCode();
                int calories = jsonObject.get("calories").hashCode();

                User user = new User(name, weight, trainings, calories);
                return user;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        User user = new User("", 0, 0, 0);
        return user;

    }

    /**
     * Check if user data file exists
     */
    public void Check(){
        File file = new File("user.json");

        if (!file.exists()){
        JOptionPane.showMessageDialog(new JFrame(), "Wprowadź dane użytkownika !", "Witaj",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * To get user data
     * @return HashMap object
     */
    public Map<String, Object> getUserData(){
        Map<String, Object> data = new HashMap<>();
        data.put("name",name);
        data.put("weight",weight);
        data.put("trainings",trainingsPerMonth);
        data.put("calories",caloriesPerTraining);

        return data;
    }

}
