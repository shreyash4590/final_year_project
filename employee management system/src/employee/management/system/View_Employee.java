package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Employee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceEMP;
    JButton searchbtn, print, update, back;
    JTextField searchByName;
    View_Employee(){

        getContentPane().setBackground(new Color(255,131,122));
        JLabel search = new JLabel("Search by employee id");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        JLabel searchByNameLabel = new JLabel("Search by name");
        searchByNameLabel.setBounds(350, 20, 150, 20);
        add(searchByNameLabel);

        searchByName = new JTextField();
        searchByName.setBounds(500, 20, 150, 20);
        add(searchByName);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while (resultSet.next()){
                choiceEMP.add(resultSet.getString("empId"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        try{
            conn c= new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,80,20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);


        setSize(900,700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn){
            String query = "select * from employee where empId = '"+choiceEMP.getSelectedItem()+"'";
            String nameQuery = "select * from employee where name like '%" + searchByName.getText() + "%'";

            /*try {
                conn c = new conn();
                ResultSet resultSet;
                if (!choiceEMP.getSelectedItem().isEmpty()) {
                    resultSet = c.statement.executeQuery(query);
                } else {
                    resultSet = c.statement.executeQuery(nameQuery);
                }
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }*/




            try {
                conn c = new conn();
                ResultSet resultSet;
                if (!choiceEMP.getSelectedItem().isEmpty()) {
                     resultSet = c.statement.executeQuery(query);
                }else {
                     resultSet= c.statement.executeQuery(nameQuery);
                }
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }




        } else if (e.getSource() == print) {
            try {
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == update){
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());
        } else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new View_Employee();
    }
}
