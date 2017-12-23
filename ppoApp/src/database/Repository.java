package database;

import components.IActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Repository<T extends IActivity> {
    public void getFields(T activity){
        Method[] pola = activity.getClass().getDeclaredMethods();
        Arrays.stream(pola).filter(e->e.getName().contains("get")).forEach(e -> {
            try {
                System.out.println(e.invoke(activity, null));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        });
    }
}
