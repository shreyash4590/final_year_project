package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attendance extends JFrame implements ActionListener {

    JComboBox<String> empIdComboBox;
    JCheckBox presentCheckBox, absentCheckBox;
    JButton submitButton, backButton,viewButton;

    Attendance() {
        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Employee Attendance");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel empIdLabel = new JLabel("Employee ID");
        empIdLabel.setBounds(50, 150, 150, 30);
        empIdLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(empIdLabel);

        empIdComboBox = new JComboBox<>();
        empIdComboBox.setBounds(200, 150, 150, 30);
        add(empIdComboBox);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(400, 150, 150, 30);
        statusLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(statusLabel);

        presentCheckBox = new JCheckBox("Present");
        presentCheckBox.setBounds(600, 150, 150, 30);
        presentCheckBox.setBackground(new Color(163, 255, 188));
        add(presentCheckBox);

        absentCheckBox = new JCheckBox("Absent");
        absentCheckBox.setBounds(750, 150, 150, 30);
        absentCheckBox.setBackground(new Color(163, 255, 188));
        add(absentCheckBox);

        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(presentCheckBox);
        statusGroup.add(absentCheckBox);

        submitButton = new JButton("Submit");
        submitButton.setBounds(450, 250, 150, 40);
        submitButton.setBackground(Color.black);
        submitButton.setForeground(Color.white);
        submitButton.addActionListener(this);
        add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(250, 250, 150, 40);
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        add(backButton);

        viewButton = new JButton("View Attendance");
        viewButton.setBounds(700, 250, 150, 40);
        viewButton.setBackground(Color.black);
        viewButton.setForeground(Color.white);
        viewButton.addActionListener(this);
        add(viewButton);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select empID from employee");
            while (resultSet.next()) {
                empIdComboBox.addItem(resultSet.getString("empID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setSize(900, 400);
        setLayout(null);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String empID = (String) empIdComboBox.getSelectedItem();
            String status = presentCheckBox.isSelected() ? "Present" : "Absent";

            try {
                conn c = new conn();
                String query = "insert into attendance (empID, date, status) values('" + empID + "', curdate(), '" + status + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Attendance Recorded Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Main_class();
        } else if (e.getSource()==viewButton) {
            setVisible(false);
            new ViewAttendance();

        }
    }

    public static void main(String[] args) {
        new Attendance();
    }
}
