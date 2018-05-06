package components;
/**
 * IActivity interface to modeling all activites
 */
public interface IActivity {
    /**
     * To get activity id
     * @return int
     */
    int getId();
    /**
     * To get type of activity
     * @return String
     */
    String getType();
    /**
     * To get title of activity
     * @return String
     */
    String getTitle();
    /**
     * To get description of activity
     * @return String
     */
    String getDescription();
    /**
     * To get date of activity
     * @return String
     */
    String getDate();
    /**
     * To get calories count of activity
     * @return int
     */
    int getCalories();

    /**
     * To set activity id
     * @param id training id
     */
    void setId(int id);
    /**
     * To set activity type
     * @param type training type: Running, Cycling, Gym
     */
    void setType(String type);
    /**
     * To set activity title
     * @param title training title
     */
    void setTitle(String title);
    /**
     * To set activity description
     * @param description training description
     */
    void setDescription(String description);
    /**
     * To set activity date
     * @param date training date
     */
    void setDate(String date);
    /**
     * To set calories count for activity
     *  @param calories calories count
     */
    void setCalories(int calories);
}
