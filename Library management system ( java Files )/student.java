import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.*;
/*Search Student by ID*/
class ssearch implements ActionListener{
	Lib l=new Lib();		
	public void actionPerformed(ActionEvent e)
			{
				JTable jt;
				int f1=0;
				String sno=l.tf.getText();
				try
				{
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from Students,issuebook where Students.st_no=issuebook.s_no");  
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
						if(sno.equals(rs.getString(1)))
						{
							Vector<String> row=new Vector<String>();
							row.add(rs.getString("s_no"));
							row.add(rs.getString("s_name"));
							row.add(rs.getString("s_email"));
							
							row.add(rs.getString("b_no1"));
							row.add(rs.getString("b_no2"));
							row.add(rs.getString("b_no3"));
							row.add(rs.getString("b_no4"));
							row.add(rs.getString("b_no5"));
							row.add(rs.getString("b_no6"));
							
							bbs.add(row);
							f1=1;	
						}
					}
					if(f1==1)
					{
						JFrame f=new JFrame("Student Information");
						f.setVisible(true);
						f.setExtendedState(JFrame.MAXIMIZED_BOTH);
						jt=new JTable(bbs,col);
						BorderLayout bl=new BorderLayout();
						f.setLayout(bl);
						jt.setVisible(true);
						jt.setBounds(100,300,150,50);
						f.add(jt);
						f.setSize(800,400);
						JScrollPane jp=new JScrollPane(jt);
						f.add(jp,BorderLayout.NORTH);
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Doesn't exist!!!");
					}
					con.close();  
				}catch(Exception e1)
				{
					System.out.print(e1);
				}
			}
}
	
