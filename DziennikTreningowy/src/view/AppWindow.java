package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AppWindow extends JPanel {
    public JPanel mainPanel;
    private JButton addButton;
    private JButton listButton;
    private JButton summaryButton;
    private JButton userButton;
    private JPanel menuPanel;
    private JPanel dataPanel;
    private JPanel addPanel;
    private JPanel listPanel;
    private JPanel summaryPanel;
    private JPanel userPanel;
    private JLabel logoLabel;

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
            getListener(buttons,addButton,addPanel);
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

    private void createUIComponents() { }
}