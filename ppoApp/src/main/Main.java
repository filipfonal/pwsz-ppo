package main;

import components.Running;
import view.AppWindow;
import database.Repository;

import javax.swing.*;
import java.awt.*;

public class Main {
    //public static DataAccess connect = new DataAccess();

    public static void main(String[] args) {

        AppWindow window = new AppWindow();
        //connect.setData("Filip");
        //window.textArea1.append(connect.getData());



        JFrame frame = new JFrame("Dziennik Treningowy 1.0");
        frame.setContentPane(window.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setVisible(true);


        Repository resp = new Repository();
        resp.getFields(new Running());


    }
}
