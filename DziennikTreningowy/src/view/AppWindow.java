package view;

import javax.swing.*;
import java.awt.*;
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
        Color active = new Color(31,58,147);
        Color disable = new Color(28,53,134);
        ArrayList<JButton> buttons = new ArrayList();
        buttons.add(addButton);
        buttons.add(listButton);
        buttons.add(summaryButton);
        buttons.add(userButton);
        buttons.stream().forEach(e->e.setCursor(new Cursor(Cursor.HAND_CURSOR)));

        //Create listeners
        addButton.addActionListener(e->{
            buttons.stream().forEach(b->b.setBackground(disable));
            addButton.setBackground(active);
            dataPanel.removeAll();
            dataPanel.repaint();
            dataPanel.revalidate();
            dataPanel.add(addPanel);
            dataPanel.repaint();
            dataPanel.revalidate();
        });
        listButton.addActionListener(e->{
            buttons.stream().forEach(b->b.setBackground(disable));
            listButton.setBackground(active);
            dataPanel.removeAll();
            dataPanel.repaint();
            dataPanel.revalidate();
            dataPanel.add(listPanel);
            dataPanel.repaint();
            dataPanel.revalidate();
        });
        summaryButton.addActionListener(e->{
            buttons.stream().forEach(b->b.setBackground(disable));
            summaryButton.setBackground(active);
            dataPanel.removeAll();
            dataPanel.repaint();
            dataPanel.revalidate();
            dataPanel.add(summaryPanel);
            dataPanel.repaint();
            dataPanel.revalidate();
        });
        userButton.addActionListener(e->{
            buttons.stream().forEach(b->b.setBackground(disable));
            userButton.setBackground(active);
            dataPanel.removeAll();
            dataPanel.repaint();
            dataPanel.revalidate();
            dataPanel.add(userPanel);
            dataPanel.repaint();
            dataPanel.revalidate();
        });
    }

    private void createUIComponents() { }
}