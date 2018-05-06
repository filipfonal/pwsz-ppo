package components;
/**
 * Gym class for create gym type activity
 */
public class Gym implements IActivity {

    @DBField
    private int id = 0;
    @DBField
    private String type = "Siłownia";
    @DBField
    private String title;
    @DBField
    private String description;
    @DBField
    private String date;
    @DBField
    private int calories;
    @DBField
    private int count;
    @DBField
    private int kilograms;
    @DBField
    private String time;


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
    public void setCount(int count){
        this.count = count;
    }
    @DBField
    public void setKilograms(int kilograms){
        this.kilograms = kilograms;
    }
    @DBField
    public void setTime(String time){
        this.time = time;
    }
    public int getCount(){
        return count;
    }
    public int getKilograms(){
        return kilograms;
    }
    public String getTime(){
        return time;
    }
}
