
package university.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
public class StudentLeave  extends JFrame implements ActionListener{
    
    Choice crollno,ctime;
    JDateChooser datedob;
    JButton Submit,Cancel;
    StudentLeave(){
        
        setSize(600,500);
        setLocation(550,100);
         setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
          JLabel heading =new JLabel("Apply Leave(Student)");
        heading.setBounds(40, 50, 300, 30);
        heading .setFont(new Font("Tahoma",Font.BOLD,16));
          add(heading);
        
        //drop
         JLabel lbrollno =new JLabel("Search by Roll Number");
        lbrollno .setBounds(60, 100, 200, 20);
         lbrollno .setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbrollno );
        
        crollno = new Choice();
        crollno.setBounds(60, 130, 200, 20);
        add(crollno);
        try{
            Conn c= new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()){
              crollno.add(rs.getString("rollnum"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JLabel lbdate =new JLabel("Date");
         lbdate  .setBounds(60, 180, 200, 20);
         lbdate  .setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbdate);
        
         datedob=new JDateChooser();
        datedob.setBounds(60,210, 200, 30);
        add(datedob); 
        //TimeDuration
          JLabel lbtime =new JLabel("Time Duration");
          lbtime .setBounds(60, 260, 200, 20);
          lbtime .setFont(new Font("Tahoma",Font.PLAIN,16));
        add( lbtime );
        
        ctime = new Choice();
        ctime.setBounds(60, 290, 200, 20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);
        //Button
        Submit =new  JButton("Submit");
       Submit.setBounds(60, 350, 100, 25);
        Submit.setBackground(Color.BLACK);
        Submit.setForeground(Color.WHITE);
        Submit.addActionListener(this);
        Submit.setFont(new Font("Tahoma",Font.BOLD,16));
        add(Submit);
        
         Cancel =new  JButton("Cancel");
       Cancel.setBounds(200, 350, 120, 25);
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.addActionListener(this);
        Cancel.setFont(new Font("Tahoma",Font.BOLD,16));
        add(Cancel);
       
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==Submit){
           String rollno =  crollno.getSelectedItem();
           String date =((JTextField)datedob.getDateEditor().getUiComponent()).getText();
           String duration = ctime.getSelectedItem();
           
           
           String query ="insert into studentleave values('"+rollno+"','"+date+"','"+duration+"')";
           
           try{
               
               Conn c=new Conn();
               c.s.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "Leave Confirmed");
               setVisible(false);
           }catch(Exception e){
               e.printStackTrace();
           }
           
        }else{
            setVisible(false);
        }
    }
    
    
    public static void main(String args[]){
        new StudentLeave();
    }
    
    
    
}
