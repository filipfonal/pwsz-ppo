package view;

import components.Cycling;
import components.Gym;
import components.IActivity;
import components.Running;
import main.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private JTable trainingsTable;
    private JButton deleteButton;
    String[] columnNames = {
            "Data",
            "Typ",
            "Nazwa",
            "Opis",
            "Spalone Kalorie",
            "Akcja"};
    private DefaultTableModel model = new DefaultTableModel();

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

            model.setRowCount(0);
            List<IActivity> trainings = getTrainings();
            if (trainings != null && !trainings.isEmpty()) {
                trainings.stream().forEach(e1 -> {
                    model.addRow(new Object[]{e1.getDate(), e1.getType(), e1.getTitle(), e1.getDescription(), e1.getCalories()});
                });
            }

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

        model.setColumnIdentifiers(columnNames);
        trainingsTable.setModel(model);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<IActivity> trainings = getTrainings();
                Main.resp.Remove(trainings.get(trainingsTable.getSelectedRow()));
                model.setRowCount(0);
                List<IActivity> actualTrainings = getTrainings();
                if (actualTrainings != null && !actualTrainings.isEmpty()) {
                    actualTrainings.stream().forEach(e1 -> {
                        model.addRow(new Object[]{e1.getDate(), e1.getType(), e1.getTitle(), e1.getDescription(), e1.getCalories()});
                    });
                }
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
                //running.setCalories();
                Main.resp.Save(running);

            }
            if(activityType.getSelectedItem().equals("Rower")){
                Cycling cycling = new Cycling();
                cycling.setTitle(titleField.getText());
                cycling.setDescription(descriptionField.getText());
                cycling.setDate(dateField.getText());
                //running.setCalories();
                Main.resp.Save(cycling);
            }
            if(activityType.getSelectedItem().equals("Siłownia")){
                Gym gym = new Gym();
                gym.setTitle(titleField.getText());
                gym.setDescription(descriptionField.getText());
                gym.setDate(dateField.getText());
                //running.setCalories();
                Main.resp.Save(gym);
            }

            JOptionPane.showMessageDialog(new JFrame(), "Trening został zapisany !", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Wypełnij pole tytułu !", "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createUIComponents() { }

    private List<IActivity> getTrainings(){
        List<IActivity> trainigsRunning = Main.resp.getAll(Running.class);
        List<IActivity> trainigsCycling = Main.resp.getAll(Cycling.class);
        List<IActivity> trainigsGym = Main.resp.getAll(Gym.class);
        List<IActivity> trainigs = new ArrayList<>();

        if(trainigsRunning != null && !trainigsRunning.isEmpty())
            trainigs.addAll(trainigsRunning);
        if(trainigsCycling != null && !trainigsCycling.isEmpty())
            trainigs.addAll( trainigsCycling);
        if(trainigsGym != null && !trainigsGym.isEmpty())
            trainigs.addAll(trainigsGym);

        if (trainigs.size()>0){
            return trainigs;
        }

        return null;
    }
}