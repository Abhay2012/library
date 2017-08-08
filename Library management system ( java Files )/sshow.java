import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/*display all students data*/
public class sshow implements ActionListener 
{ 
	JTable jt;
	
	public void actionPerformed(ActionEvent e)
	{
		JFrame f=new JFrame("Students Data");
		f.setVisible(true);
		BorderLayout bl=new BorderLayout();
		f.setLayout(bl);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		try{ 
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Library","root","abhay");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Students,issuebook  where 				   	Students.st_no=issuebook.s_no");  
			String sname=new String();
			String semail=new String();
			String ssnos=new String();
			Vector <Vector> bbs=new Vector <Vector>();
			Vector<String> col=new Vector<String>();
			
			String s="Student Id";
			String s1="Student Name";
			String s2="Student Email";
			String s3="Book 1";
			String s4="Book 2";
			String s5="Book 3";
			String s6="Book 4";
			String s7="Book 5";
			String s8="Book 6";
			
			col.addElement(s);
			col.addElement(s1);
			col.addElement(s2);
			col.addElement(s3);
			col.addElement(s4);
			col.addElement(s5);
			col.addElement(s6);
			col.addElement(s7);
			col.addElement(s8);
			
			while(rs.next())
			{	
				try{
				Vector<String> row=new Vector<String>();
				
				row.add(rs.getString("st_no"));
				row.add(rs.getString("s_name"));
				row.add(rs.getString("s_email"));
				row.add(rs.getString("b_no1"));
				row.add(rs.getString("b_no2"));
				row.add(rs.getString("b_no3"));
				row.add(rs.getString("b_no4"));
				row.add(rs.getString("b_no5"));
				row.add(rs.getString("b_no6"));
				bbs.add(row);
				
				}catch(Exception e1)
				{
					System.out.print(e1);
				}
			}
			
			jt=new JTable(bbs,col);
			jt.getColumnModel().getColumn(2).setPreferredWidth(125);
			jt.setVisible(true);
			jt.setBounds(100,300,150,50);
			f.add(jt);
			f.setSize(800,400);
			JScrollPane jp=new JScrollPane(jt);
			f.add(jp,BorderLayout.NORTH);
			con.close();  
		}catch(Exception e1){ System.out.println(e1);}
	}
}
