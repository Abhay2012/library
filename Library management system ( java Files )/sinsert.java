import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*student insert action*/
public class sinsert implements ActionListener{
	public String sname;
	public String s_email;
	public String stno;
	JTextField t1=new JTextField(30);
	JTextField t2=new JTextField(30);
	JTextField t3=new JTextField(30);
	public void actionPerformed(ActionEvent e)
	{
		Lib fi=new Lib();
		fi.mp.removeAll();
		fi.f.setTitle("Student Data Insertion");
		JLabel l=new JLabel("Enter Details :");
		JLabel l1=new JLabel("student no.");
		JLabel l2=new JLabel("student name");
		JLabel l3=new JLabel("email id");
		JButton ba1=new JButton("Insert");
		JLabel la=new JLabel("Student Data Insertion");
		
		fi.mp.add(l1);
		fi.mp.add(l2);
		fi.mp.add(l3);
		fi.mp.add(t1);
		fi.mp.add(t2);
		fi.mp.add(t3);
		fi.mp.add(ba1);
		fi.mp.add(l);

		t1.setBounds(550,150,150,40);
		t2.setBounds(550,200,150,40);
		t3.setBounds(550,250,150,40);
		
		fi.mp.add(la);
		la.setBounds(425,20,250,30);
		
		ba1.setBounds(550,325,150,40);
		l.setBounds(475,80,150,40);
		l1.setBounds(300,150,150,40);
		l2.setBounds(300,200,150,40);
		l3.setBounds(300,250,150,40);
		
		fi.mp.repaint();
		fi.mp.revalidate();
		
		/*insert student data action*/
		class insertdata  implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				int f=0;
				try{
					 
					stno=t1.getText();
					sname=new String(t2.getText());
					s_email=t3.getText();
					
					}
					catch(Exception e1){System.out.println(e1);}
				
				try{  
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");  
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					String sdate=df.format(dateobj);
  
					String query = " insert into Students (st_no,s_name, s_email)"
					        + " values (?, ?, ?)";
					String query1="insert into issuebook (s_no) values(?)";
					String query2="insert into history (action,std_no,date) values(?,?,?)";
					String query3 = "insert into bookbank (s_no) values(?)";
					      PreparedStatement preparedStmt = con.prepareStatement(query);
					      preparedStmt.setString (1,stno);
					      preparedStmt.setString (2,sname);
					      preparedStmt.setString(3,s_email);
					      preparedStmt.execute();
					      PreparedStatement preparedStmt1 = con.prepareStatement(query1);
					      preparedStmt1.setString (1,stno);
					      preparedStmt1.execute();
					      PreparedStatement preparedStmt2 = con.prepareStatement(query2);
					      preparedStmt2.setString (1,"Student Insert");
					      preparedStmt2.setString (2,stno);
					      preparedStmt2.setString(3, sdate);
					      preparedStmt2.execute();
					      PreparedStatement preparedStmt3 = con.prepareStatement(query3);
					      preparedStmt3.setString (1,stno);
					      preparedStmt3.execute();
					      
					 
					con.close();  
				}catch(com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e2){
					f=1;
					JOptionPane.showMessageDialog(null, "Insertion Unsuccessful already exist!!!");
				}
				catch(Exception e1){ System.out.println(e1);}
				if(f==0)
				{
					JOptionPane.showMessageDialog(null, "Insertion Successful");
					
				}
				}
		}
		
		insertdata i=new insertdata();
		ba1.addActionListener(i);
		ba1.setBackground(Color.decode("#930101"));
		ba1.setForeground(Color.decode("#ffffff"));
		ba1.setBorder(BorderFactory.createLineBorder(Color.black, 2));	
	}
}
