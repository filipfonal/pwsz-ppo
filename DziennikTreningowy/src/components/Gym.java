package components;

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
    private float calories;

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
    public void setCalories(float calories){
        this.calories = calories;
    }

    public int getId(){ return id; }
    public String getType(){ return type; }
    public String getTitle(){ return title; }
    public String getDescription(){ return description; }
    public String getDate(){ return date; }
    public float getCalories(){ return  calories; }

    //Additional methods for this class
}
