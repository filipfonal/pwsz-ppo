package main;

import components.Gym;
import components.IActivity;
import components.Running;
import database.DataAccess;
import view.AppWindow;
import database.Repository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static DataAccess connect = new DataAccess();
    public static Repository resp = new Repository();

    public static void main(String[] args) throws NoSuchMethodException {

        AppWindow window = new AppWindow();

        JFrame frame = new JFrame("Dziennik Treningowy 1.0");
        frame.setContentPane(window.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setVisible(true);

        /*List<IActivity> trainigs = resp.getAll(Running.class);

        if (trainigs.size()>0){
            System.out.println(trainigs.get(0).getId());
            System.out.println(trainigs.get(0).getDescription());
        }else{
            System.out.println("Brak rekordow");
        }*/

    }
}
