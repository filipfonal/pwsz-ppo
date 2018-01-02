package view;

import components.Cycling;
import components.Gym;
import components.IActivity;
import components.Running;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class AppWindow extends JPanel {
    public JPanel mainPanel;
    private JButton addButton;
    private JButton listButton;
    private JButton summaryButton;
    private JButton userButton;
    private JPanel menuPanel;
    private JPanel dataPanel;
    private JPanel runningPanel;
    private JPanel listPanel;
    private JPanel summaryPanel;
    private JPanel userPanel;
    private JLabel logoLabel;
    private JTextField titleField;
    private JFormattedTextField dateField;
    private JButton saveButton;
    private JPanel addPanel;
    private JTextArea descriptionField;
    private JComboBox activityType;
    private JTextField distanceField;
    private JTextField runningTimeField;
    private JButton runningButton;
    private JButton gymButton;
    private JButton cyclingButton;
    private JPanel cyclingPanel;
    private JPanel gymPanel;
    private JLabel caloriesField;
    private JTextField exercisesField;
    private JTextField kilogramsField;
    private JTextField gymTimeField;

    public AppWindow() {
        super();

        //Buttons properties
        ArrayList<JButton> buttons = new ArrayList();
        buttons.add(addButton);
        buttons.add(listButton);
        buttons.add(summaryButton);
        buttons.add(userButton);
        buttons.stream().forEach(e->e.setCursor(new Cursor(Cursor.HAND_CURSOR)));

        //Create listeners
        addButton.addActionListener(e->{
            getListener(buttons,addButton, addPanel);
        });
        listButton.addActionListener(e->{
            getListener(buttons,listButton,listPanel);
        });
        summaryButton.addActionListener(e->{
            getListener(buttons,summaryButton,summaryPanel);
        });
        userButton.addActionListener(e->{
            getListener(buttons,userButton,userPanel);
        });
        saveButton.addActionListener(e->{
            Save();
        });

        activityType.addItemListener(e->{
            if(activityType.getSelectedItem().equals("Bieganie")){
                runningPanel.setVisible(true);
                cyclingPanel.setVisible(false);
                gymPanel.setVisible(false);
            }
            if(activityType.getSelectedItem().equals("Rower")){
                runningPanel.setVisible(false);
                cyclingPanel.setVisible(true);
                gymPanel.setVisible(false);
            }
            if(activityType.getSelectedItem().equals("Siłownia")){
                runningPanel.setVisible(false);
                cyclingPanel.setVisible(false);
                gymPanel.setVisible(true);

            }
        });
    }

    private void getListener(ArrayList<JButton> buttons, JButton button, JPanel panel){
        Color active = new Color(31,58,147);
        Color disable = new Color(28,53,134);
        buttons.stream().forEach(b->b.setBackground(disable));
        button.setBackground(active);
        dataPanel.removeAll();
        dataPanel.repaint();
        dataPanel.revalidate();
        dataPanel.add(panel);
        dataPanel.repaint();
        dataPanel.revalidate();
    }

    private void Save(){
        if(!titleField.getText().isEmpty()){
            if(activityType.getSelectedItem().equals("Bieganie")){
                Running running = new Running();
                running.setTitle(titleField.getText());
                running.setDescription(descriptionField.getText());
                running.setDate(dateField.getText());
                running.setCalories(123);
                Main.resp.Save(running);

            }
            if(activityType.getSelectedItem().equals("Rower")){
                Cycling cycling = new Cycling();
            }
            if(activityType.getSelectedItem().equals("Siłownia")){
                Gym gym = new Gym();
            }

            JOptionPane.showMessageDialog(new JFrame(), "Trening został zapisany !", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Wypełnij pole tytułu !", "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createUIComponents() { }
}