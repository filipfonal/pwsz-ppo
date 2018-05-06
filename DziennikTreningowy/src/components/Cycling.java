package components;

/**
 * Cycling class for create cycling type activity
 */
public class Cycling implements IActivity {

    @DBField
    private int id = 0;
    @DBField
    private String type = "Rower";
    @DBField
    private String title;
    @DBField
    private String description;
    @DBField
    private String date;
    @DBField
    private int calories;
    @DBField
    private double distance;
    @DBField
    private String time;
    @DBField
    private int speed;

    //Interface methods
    @DBField
    public void setId(int id){
        this.id = id;
    }
    @DBField
    public void setType(String type){
        this.type = type;
    }
    @DBField
    public void setTitle(String title){
        this.title = title;
    }
    @DBField
    public void setDescription(String description){
        this.description = description;
    }
    @DBField
    public void setDate(String date){
        this.date = date;
    }
    @DBField
    public void setCalories(int calories){
        this.calories = calories;
    }

    public int getId(){ return id; }
    public String getType(){ return type; }
    public String getTitle(){ return title; }
    public String getDescription(){ return description; }
    public String getDate(){ return date; }
    public int getCalories(){ return  calories; }

    //Additional methods for this class
    @DBField
    public void setDistance(double distance){
        this.distance = distance;
    }
    @DBField
    public void setTime(String time){ this.time = time; }
    @DBField
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public double getDistance(){
        return distance;
    }
    public String getTime(){
        return time;
    }
    public int getSpeed(){
        return speed;
    }
}
