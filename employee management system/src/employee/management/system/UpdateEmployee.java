package employee.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateEmployee extends JFrame implements ActionListener {
    JTextField teducation,tfname,taddress,tphone,taadhar,temail,tsalary,tdesignation;
    JLabel tempid;

    JButton add,back;
    String number;
    UpdateEmployee(String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));
        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320,30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,25));
        add(heading);

        JLabel name=new JLabel("Name");
        name.setBounds(50,150,150,30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(name);

        JLabel tname= new JLabel();
        tname.setBounds(200,150,150,30);
        tname.setBackground(new Color(177,252,197));
        add(tname);

        JLabel fname=new JLabel("Father's Name");
        fname.setBounds(400,150,150,30);
        fname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(fname);

        tfname= new JTextField();
        tfname.setBounds(600,150,150,30);
        tfname.setBackground(new Color(177,252,197));
        add(tfname);


       JLabel dob = new JLabel("Date Of Birth");
       dob.setBounds(50,200,150,30);
       dob.setFont(new Font("SAN_SERIF", Font.BOLD,20));
       add(dob);


        JLabel tdob = new JLabel("Date of birth");
        tdob.setBounds(200,200,150,30);
        tdob.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(tdob);


        JLabel salary=new JLabel("Salary");
        salary.setBounds(400,200,150,30);
        salary.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(salary);

        tsalary= new JTextField();
        tsalary.setBounds(600,200,150,30);
        tsalary.setBackground(new Color(177,252,197));
        add(tsalary);


        JLabel address=new JLabel("Address");
        address.setBounds(50,250,150,30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(address);

        taddress= new JTextField();
        taddress.setBounds(200,250,150,30);
        taddress.setBackground(new Color(177,252,197));
        add(taddress);

        JLabel phone=new JLabel("Phone No");
        phone.setBounds(400,250,150,30);
        phone.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(phone);

        tphone= new JTextField();
        tphone.setBounds(600,250,150,30);
        tphone.setBackground(new Color(177,252,197));
        add(tphone);

        JLabel email=new JLabel("Email");
        email.setBounds(50,300,150,30);
        email.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(email);

        temail= new JTextField();
        temail.setBounds(200,300,150,30);
        temail.setBackground(new Color(177,252,197));
        add(temail);


        JLabel education=new JLabel("Higher Education");
        education.setBounds(400,300,150,30);
        education.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(education);

        teducation= new JTextField();
        teducation.setBounds(600,300,150,30);
        teducation.setBackground(new Color(177,252,197));
        add(teducation);




        JLabel aadhar=new JLabel("Addhar No");
        aadhar.setBounds(400,350,150,30);
        aadhar.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(aadhar);

        JLabel taadhar= new JLabel();
        taadhar.setBounds(600,350,150,30);
        taadhar.setBackground(new Color(177,252,197));
        add(taadhar);

        JLabel empid=new JLabel("Employee Id");
        empid.setBounds(50,400,150,30);
        empid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(empid);

        tempid= new JLabel();
        tempid.setBounds(200,400,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setForeground(Color.red);
        add(tempid);

        JLabel designation=new JLabel("Designation");
        designation.setBounds(50,350,150,30);
        designation.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(designation);

        tdesignation= new JTextField();
        tdesignation.setBounds(200,350,150,30);
        tdesignation.setBackground(new Color(177,252,197));
        add(tdesignation);

        try{
            conn c = new conn();
            String query = "Select * from employee where empID='"+number+"'";
            ResultSet resultset = c.statement.executeQuery(query);
            while(resultset.next()){
                tname.setText(resultset.getString("name"));
                tfname.setText(resultset.getString("fname"));
                tdob.setText(resultset.getString("dob"));
                taddress.setText(resultset.getString("address"));
                tsalary.setText(resultset.getString("salary"));
                tphone.setText(resultset.getString("phone"));
                temail.setText(resultset.getString("email"));
                teducation.setText(resultset.getString("education"));
                taadhar.setText(resultset.getString("aadhar"));
                tempid.setText(resultset.getString("empID"));
                tdesignation.setText(resultset.getString("designation"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        add = new JButton("Update");
        add.setBounds(450,550,150,40);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBounds(250,550,150,40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setSize(900,700);
        setLayout(null);
        setLocation(300,50);
        setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==add){
            String fname = tfname.getText();
            String salary = tsalary.getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = teducation.getUIClassID();
            String designation = tdesignation.getText();


            try{
                conn c=new conn();
                String query = "update employee set fname = '"+fname+"',salary = '"+salary+"',address ='"+address+"',phone ='"+phone+"',email = '"+email+"',education ='"+education+"',designation ='"+designation+"'where empID ='"+number+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Details Update Sucessfully");
                setVisible(false);
                new Main_class();

            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
            new View_Employee();
        }


    }

    public static void main(String[] args) {
        new UpdateEmployee("");

    }
}
