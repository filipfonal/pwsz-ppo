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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

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
    private JPanel cyclingPanel;
    private JPanel gymPanel;
    private JTable trainingsTable;
    private JButton deleteButton;
    private JSpinner runningTimeField;
    private JSpinner cyclingTimeField;
    private JSpinner gymTimeField;
    private JButton detailsButton;
    private JSpinner pulseField;
    private JSpinner cyclingDistanceField;
    private JSpinner speedField;
    private JSpinner excerciseCountField;
    private JSpinner kilogramsField;
    private JSpinner runningDistanceField;
    private JPanel chartPanel;
    private JProgressBar caloriesProgressBar;
    private JLabel caloriesSummaryLabel;
    private JScrollPane summaryScrollPane;
    private String[] columnNames = {
            "Data",
            "Typ",
            "Nazwa",
            "Opis",
            "Spalone Kalorie"};
    private DefaultTableModel model = new DefaultTableModel();
    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private SpinnerDateModel SpinnerMinutesModel = new SpinnerDateModel();
    private SpinnerNumberModel SpinnerKilometersModel = new SpinnerNumberModel(0.000,0.0,1000.0,0.100);
    private Calendar calendar = Calendar.getInstance();

    private int ID = 0;
    private int runningCount = 0;
    private int cyclingCount = 0;
    private int gymCount = 0;


    public AppWindow() {
        super();
        createUIComponents();
        createListeners();
    }

    private void createListeners(){

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
            createUIComponents();
            ID=0;
        });
        listButton.addActionListener(e->{
            getListener(buttons,listButton,listPanel);
            refreshTrainingsList();
        });
        summaryButton.addActionListener(e->{
            getListener(buttons,summaryButton,summaryPanel);
            createSummary();
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<IActivity> trainings = getTrainings();
                Main.resp.Remove(trainings.get(trainingsTable.getSelectedRow()));
                JOptionPane.showMessageDialog(new JFrame(), "Usunięto trening !", "Sukces",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTrainingsList();
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getListener(buttons,addButton, addPanel);
                List<IActivity> trainings = getTrainings();

                if(trainings.get(trainingsTable.getSelectedRow()).getType().equals("Bieganie")){
                    Running running = (Running) Main.resp.Get(trainings.get(trainingsTable.getSelectedRow()).getId(),
                            trainings.get(trainingsTable.getSelectedRow()).getClass());
                    activityType.setSelectedItem("Bieganie");
                    titleField.setText(running.getTitle());
                    descriptionField.setText(running.getDescription());
                    dateField.setText(running.getDate());
                    cyclingDistanceField.setValue(running.getDistance());

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                    try {
                        cal.setTime(format.parse(running.getTime()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    SpinnerDateModel SpinnerMinutesModel = new SpinnerDateModel();
                    SpinnerMinutesModel.setValue(cal.getTime());
                    runningTimeField.setModel(SpinnerMinutesModel);
                    JSpinner.DateEditor editor = new JSpinner.DateEditor(runningTimeField, "HH:mm:ss");
                    runningTimeField.setEditor(editor);

                    pulseField.setValue(running.getPulse());
                    ID = running.getId();

                }else if(trainings.get(trainingsTable.getSelectedRow()).getType().equals("Rower")){
                    Cycling cycling = (Cycling) Main.resp.Get(trainings.get(trainingsTable.getSelectedRow()).getId(),
                            trainings.get(trainingsTable.getSelectedRow()).getClass());
                    activityType.setSelectedItem("Rower");
                    titleField.setText(cycling.getTitle());
                    descriptionField.setText(cycling.getDescription());
                    dateField.setText(cycling.getDate());
                    cyclingDistanceField.setValue(cycling.getDistance());

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                    try {
                        cal.setTime(format.parse(cycling.getTime()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    SpinnerDateModel SpinnerMinutesModel = new SpinnerDateModel();
                    SpinnerMinutesModel.setValue(cal.getTime());
                    cyclingTimeField.setModel(SpinnerMinutesModel);
                    JSpinner.DateEditor editor = new JSpinner.DateEditor(cyclingTimeField, "HH:mm:ss");
                    cyclingTimeField.setEditor(editor);

                    speedField.setValue(cycling.getSpeed());
                    ID = cycling.getId();

                }else if(trainings.get(trainingsTable.getSelectedRow()).getType().equals("Siłownia")){
                    Gym gym = (Gym) Main.resp.Get(trainings.get(trainingsTable.getSelectedRow()).getId(),
                            trainings.get(trainingsTable.getSelectedRow()).getClass());
                    activityType.setSelectedItem("Siłownia");
                    titleField.setText(gym.getTitle());
                    descriptionField.setText(gym.getDescription());
                    dateField.setText(gym.getDate());
                    excerciseCountField.setValue(gym.getCount());
                    kilogramsField.setValue(gym.getKilograms());

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                    try {
                        cal.setTime(format.parse(gym.getTime()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    SpinnerDateModel SpinnerMinutesModel = new SpinnerDateModel();
                    SpinnerMinutesModel.setValue(cal.getTime());
                    gymTimeField.setModel(SpinnerMinutesModel);
                    JSpinner.DateEditor editor = new JSpinner.DateEditor(gymTimeField, "HH:mm:ss");
                    gymTimeField.setEditor(editor);
                    ID = gym.getId();
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
                running.setDistance((Double) runningDistanceField.getValue());
                running.setTime(runningTimeField.getValue().toString());
                running.setPulse((Integer) pulseField.getValue());
                running.setCalories(calculateCalories(activityType.getSelectedItem().toString(), runningTimeField.getValue().toString(), ((Double) runningDistanceField.getValue()).intValue()));
                running.setId(ID);
                Main.resp.Save(running);

            }
            if(activityType.getSelectedItem().equals("Rower")){
                Cycling cycling = new Cycling();
                cycling.setTitle(titleField.getText());
                cycling.setDescription(descriptionField.getText());
                cycling.setDate(dateField.getText());
                cycling.setDistance((Double) cyclingDistanceField.getValue());
                cycling.setTime(cyclingTimeField.getValue().toString());
                cycling.setSpeed((Integer) speedField.getValue());
                cycling.setCalories(calculateCalories(activityType.getSelectedItem().toString(), cyclingTimeField.getValue().toString(), (int)speedField.getValue()));
                cycling.setId(ID);
                Main.resp.Save(cycling);
            }
            if(activityType.getSelectedItem().equals("Siłownia")){
                Gym gym = new Gym();
                gym.setTitle(titleField.getText());
                gym.setDescription(descriptionField.getText());
                gym.setDate(dateField.getText());
                gym.setCount((Integer) excerciseCountField.getValue());
                gym.setKilograms((Integer) kilogramsField.getValue());
                gym.setTime(gymTimeField.getValue().toString());
                gym.setCalories(calculateCalories(activityType.getSelectedItem().toString(), gymTimeField.getValue().toString(), (int)excerciseCountField.getValue()));
                gym.setId(ID);
                Main.resp.Save(gym);
            }

            JOptionPane.showMessageDialog(new JFrame(), ID==0?"Trening został zapisany !":"Zaktualizowano trening !", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Wypełnij pole tytułu !", "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createUIComponents() {
        //training table
        model.setColumnIdentifiers(columnNames);
        trainingsTable.setModel(model);

        //set fields values
        dateField.setValue(getDate());
        titleField.setText("");
        descriptionField.setText("");
        runningDistanceField.setValue(0.0);
        cyclingDistanceField.setValue(0.0);
        pulseField.setValue(0);
        speedField.setValue(0);
        excerciseCountField.setValue(0);
        kilogramsField.setValue(0);

        //spinners model
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerMinutesModel.setValue(calendar.getTime());
        Arrays.asList(runningTimeField, cyclingTimeField, gymTimeField).stream().forEach(e->{
            e.setModel(SpinnerMinutesModel);
            JSpinner.DateEditor editor = new JSpinner.DateEditor(e, "HH:mm:ss");
            e.setEditor(editor);
        });

        Arrays.asList(runningDistanceField, cyclingDistanceField).stream().forEach(e->{
            e.setModel(SpinnerKilometersModel);
        });
    }

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

        Collections.sort(trainigs, (IActivity o1, IActivity o2) -> {
            try {
                Date date1 = format.parse(o1.getDate());
                Date date2 = format.parse(o2.getDate());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });
        Collections.reverse(trainigs);


        if (trainigs.size()>0){
            return trainigs;
        }

        return null;
    }

    private void refreshTrainingsList(){
        model.setRowCount(0);
        List<IActivity> actualTrainings = getTrainings();
        if (actualTrainings != null && !actualTrainings.isEmpty()) {
            actualTrainings.stream().forEach(e1 -> {
                model.addRow(new Object[]{e1.getDate(), e1.getType(), e1.getTitle(), e1.getDescription(), e1.getCalories()});
            });
        }
    }

    private String getDate() {
        Date date = new Date();
        return format.format(date);
    }

    private void createSummary(){
        //Chart
        List<IActivity> actualTrainings = getTrainings();
        DefaultCategoryDataset dataBar = new DefaultCategoryDataset();
        runningCount = actualTrainings.stream().filter(e->e.getType().equals("Bieganie")).toArray().length;
        cyclingCount = actualTrainings.stream().filter(e->e.getType().equals("Rower")).toArray().length;
        gymCount = actualTrainings.stream().filter(e->e.getType().equals("Siłownia")).toArray().length;
        dataBar.setValue(runningCount, "Bieganie", "Bieganie");
        dataBar.setValue(cyclingCount, "Rower", "Rower");
        dataBar.setValue(gymCount, "Siłownia", "Siłownia");

        JFreeChart chart = ChartFactory.createBarChart("", "","",dataBar, PlotOrientation.VERTICAL,false,true,true);
        chart.setPadding(new RectangleInsets(50.0,50.0,50.0,50.0));
        chartPanel.setLayout(new java.awt.BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        chartPanel.removeAll();
        chartPanel.add(CP);

        //Calories summary
        AtomicInteger calories = new AtomicInteger(0);
        actualTrainings.stream().forEach(e-> calories.getAndAdd(e.getCalories()));
        caloriesSummaryLabel.setText(calories.toString());

        //Calories progress bar
        AtomicInteger caloriesMonth = new AtomicInteger(0);
        actualTrainings.stream().filter(e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate trainingDate = LocalDate.parse(e.getDate(), formatter);
            LocalDate actualDate = LocalDate.now();
            return trainingDate.isAfter(actualDate.minusMonths(1));
        }).forEach(e->caloriesMonth.getAndAdd(e.getCalories()));
        int caloriesPercentage = (int) ((caloriesMonth.doubleValue()/6000)*100);
        caloriesProgressBar.setValue(caloriesPercentage);

    }

    private int calculateCalories(String type, String time, int data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        int minutes = dateTime.getMinute() + dateTime.getHour()*60;
        int calories = 0;
        if(type.equals("Bieganie")){
            calories = data*minutes;
        }
        if(type.equals("Rower")){
            calories = (int) (70*0.0066*minutes*data);

        }
        if(type.equals("Siłownia")){
            calories = minutes * (data>9 ? 9:7);
        }

        return calories;
    }
}