package components;

public interface IActivity {
    int getId();
    String getType();
    String getTitle();
    String getDescription();
    String getDate();
    float getCalories();

    void setId(int id);
    void setType(String type);
    void setTitle(String title);
    void setDescription(String description);
    void setDate(String date);
    void setCalories(float calories);
}
