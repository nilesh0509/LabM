import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LibMgmt extends JFrame 
{
JTextField id;
JTextField name;
JTextField Availability;
JButton Available;
JButton addnew,update;
JPanel p;
static ResultSet res;
static Connection connection;
public LibMgmt(){
super("Check Availability and Add New Books here");
Container c = getContentPane();
c.setLayout(new GridLayout(5,1));
id = new JTextField(20);
name = new JTextField(20);
Availability = new JTextField(20);
Available = new JButton("Check Availability");
addnew = new JButton("Add New");
update = new JButton("Update");
p = new JPanel();
c.add(new JLabel("ISBN",JLabel.CENTER));
c.add(id);
c.add(new JLabel("Book Name",JLabel.CENTER));
c.add(name);
c.add(new JLabel("Is Available", JLabel.CENTER));
c.add(Availability);
c.add(p);
p.add(Available);
//Available.addActionListener(this);
p.add(addnew);
p.add(update);
//pack();
setVisible(true);
// Adding action listener
Available.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
if(id.getText()=="")
{
System.out.println("Enter ISBN Number");
}
else
{
try{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
connection= DriverManager.getConnection("jdbc:ucanaccess://D:\\LibraryDBaccdb.accdb");//Establishing Connection
System.out.println("Connected Successfully");
//Creating PreparedStatement object
PreparedStatement preparedStatement=connection.prepareStatement("select Available,BookName from BookList where ISBN=?");
//Setting values for Each Parameter
preparedStatement.setString(1,id.getText());
//Executing Query
ResultSet rs;
rs= preparedStatement.executeQuery();
if(rs.next())
{
System.out.println("Values exist");
System.out.println("Book is:"+rs.getString(1));
Availability.setText(rs.getString(1));
name.setText(rs.getString(2));
}
else
{
System.out.println("No values here");
}
connection.close();
}catch(Exception e1){
System.out.println(e1);
}
}
}
});
//Adding action listener
addnew.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{
if(id.getText()=="")
{
System.out.println("Enter ISBN Number");
}
else
{
try{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
connection= DriverManager.getConnection("jdbc:ucanaccess://D:\\LibraryDBaccdb.accdb");//Establishing Connection
System.out.println("Connected Successfully");
//Creating PreparedStatement object
PreparedStatement preparedStatement=connection.prepareStatement("insert into BookList(ISBN,BookName,Available) values(?,?,?) ");
//Setting values for Each Parameter
preparedStatement.setString(1,id.getText());
preparedStatement.setString(2,name.getText());
preparedStatement.setString(3,Availability.getText());
//Executing Query
preparedStatement.executeUpdate();
System.out.println("New Book Added");
connection.close();
}catch(Exception e1){
System.out.println(e1);
}
}}
});
// Action listener
update.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e)
{ 
if(id.getText()=="")
{
System.out.println("Enter ISBN Number");
}
else
{
try{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
connection= DriverManager.getConnection("jdbc:ucanaccess://D:\\LibraryDBaccdb.accdb");//Establishing Connection
System.out.println("Connected Successfully");
//Using SQL UPDATE query to update data in the table
PreparedStatement preparedStatement=connection.prepareStatement("update BookList set BookName=?,Available=? where ISBN=?");
preparedStatement.setString(1,name.getText());
preparedStatement.setString(2,Availability.getText());
preparedStatement.setString(3,id.getText());
preparedStatement.executeUpdate();
System.out.println("data updated successfully");
connection.close();
//Executing Query
preparedStatement.executeUpdate();
System.out.println("New status Updated");
connection.close();
}catch(Exception e1){
System.out.println(e1);
}
}
}
});
addWindowListener(new WIN());
}
public static void main(String args[]) 
{
LibMgmt Lib = new LibMgmt();
}
//end of the main 
//Inner class WIN implemented
class WIN extends WindowAdapter 
{
public void windowClosing(WindowEvent w)
{JOptionPane jop = new JOptionPane(); 
jop.showMessageDialog(null,"LibraryDatabase","Thanks",JOptionPane.QUESTION_MESSAGE);
}
}
}



