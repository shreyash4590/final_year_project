package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAttendance extends JFrame {

    JTextArea textArea;

    ViewAttendance() {
        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("View Attendance");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 100, 800, 200);
        add(scrollPane);

        displayAttendanceData();

        setSize(900, 400);
        setLayout(null);
        setLocation(300, 50);
        setVisible(true);
    }

    private void displayAttendanceData() {
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from attendance");
            while (resultSet.next()) {
                int empID = resultSet.getInt("empID");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");

                // Append the data to the text area
                textArea.append("Emp ID: " + empID + ", Date: " + date + ", Status: " + status + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ViewAttendance();
    }
}
