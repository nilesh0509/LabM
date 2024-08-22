//package awtProject;
import java.sql.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class awtProject {
//Declare frame, labels and text boxes and a text area for the window
JFrame f;
JLabel nameLabel, mailLabel, BranchLabel, RegNoLabel, MobLabel, InfoLabel;
JTextField nameField, mailField, BranchField, RegNoField, MobField;
JButton SaveButton, ClearButton, UpdateButton;
Connection connection;
awtProject()
{
//Initialize frame, label, field and buttons
f= new JFrame("Student Details Form");
nameLabel = new JLabel("Student Name:");
nameLabel.setBounds(50, 50, 100, 30);
mailLabel = new JLabel("College Email ID:");
mailLabel.setBounds(50, 120, 120, 30);
BranchLabel = new JLabel("Branch:");
BranchLabel.setBounds(50, 190, 50, 30);
RegNoLabel = new JLabel("Register Number:");
RegNoLabel.setBounds(420, 50, 70, 30);
MobLabel = new JLabel("Mobile No:");
MobLabel.setBounds(420, 120, 70, 30);
InfoLabel = new JLabel("Enter Register Number to View or Update");
InfoLabel.setBounds(50, 250, 300, 30);
nameField = new JTextField("name");
nameField.setBounds(150, 50, 130, 30);
mailField = new JTextField("mail");
mailField.setBounds(160, 120, 130, 30);
BranchField = new JTextField("branch");
BranchField.setBounds(120, 190, 100, 30);
RegNoField = new JTextField("regno");
RegNoField.setBounds(470, 50, 140, 30);
MobField = new JTextField("mob");
MobField.setBounds(490, 120, 130, 30);
SaveButton = new JButton("Save");
SaveButton.setBounds(50, 300, 100, 30);
ClearButton = new JButton("Clear");
ClearButton.setBounds(200, 300, 100, 30);
UpdateButton =new JButton("Update");
UpdateButton.setBounds(350, 300, 100, 30);
// Adding action listener
SaveButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
try{
Class.forName("com.mysql.cj.jdbc.Driver");//Loading Driver
connection= 
DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");//Establishing Connection
System.out.println("Connected Successfully");
//Creating PreparedStatement object
PreparedStatement preparedStatement=connection.prepareStatement("insert into student(SName,RegNo,email,Mobile,Branch) values(?,?,?,?,?)");
//Setting values for Each Parameter
preparedStatement.setString(1,nameField.getText());
preparedStatement.setString(2,RegNoField.getText());
preparedStatement.setString(3,mailField.getText());
preparedStatement.setString(4,MobField.getText());
preparedStatement.setString(5,BranchField.getText());
//Executing Query
preparedStatement.executeUpdate();
System.out.println("data inserted successfully");
connection.close();
}catch(Exception e1){
System.out.println(e1);
}
}
});
// Action listener to close the form
ClearButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
nameField.setText("");
RegNoField.setText("");
mailField.setText("");
MobField.setText("");
BranchField.setText("");
}
});
// Action listener to update the table
UpdateButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{ 
try{
Class.forName("com.mysql.cj.jdbc.Driver");//Loading Driver
connection= 
DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");//Establishing Connection
System.out.println("Connected Successfully");
//Using SQL UPDATE query to update data in the table
PreparedStatement preparedStatement=connection.prepareStatement("update Student set SName=?, email=?,Mobile=?,Branch=? where RegNo=?");
preparedStatement.setString(1,nameField.getText());
preparedStatement.setString(5,RegNoField.getText());
preparedStatement.setString(2,mailField.getText());
preparedStatement.setString(3,MobField.getText());
preparedStatement.setString(4,BranchField.getText());
preparedStatement.executeUpdate();
System.out.println("data updated successfully");
connection.close();
}catch(Exception e1){
System.out.println(e1);
}
} });
// Default method for closing the frame
f.addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
});
// Adding the created objects
// to the frame
f.add(nameLabel);
f.add(nameField);
f.add(mailLabel);
f.add(RegNoField);
f.add(BranchLabel);
f.add(mailField);
f.add(RegNoLabel);
f.add(MobField);
f.add(MobLabel);
f.add(InfoLabel);
f.add(BranchField);
f.add(SaveButton);
f.add(ClearButton);
f.add(UpdateButton);
f.setLayout(null);
f.setSize(700, 600);
f.setVisible(true);
}
public static void main(String args[])
{
awtProject student;
student=new awtProject ();
}
}