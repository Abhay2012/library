import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/*student delete action*/
class sdelete implements ActionListener{
	JTextField td;
	public String stno;
	Lib fd=new Lib();
	public void actionPerformed(ActionEvent e)
	{
		//fd.f.getContentPane().removeAll();
		//fd.f.remove(fd.mp);
		fd.mp.removeAll();
		fd.f.setTitle("Student Data Deletion");
		td=new JTextField(20);
		JLabel l=new JLabel("Enter Student no.");
		JLabel la=new JLabel("Students Data Deletion");
		
		fd.mp.add(td);
		fd.mp.add(l);
		l.setBounds(240,150,150,40);
		td.setBounds(425,150,200,40);
		JButton bd=new JButton("Delete");
		fd.mp.add(bd);
		bd.setBounds(665,150,100,30);
		
		
		fd.mp.add(la);
		la.setBounds(425,20,250,30);
		/*student delete data action*/
		class bdeletedata implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				int f=0;
				try{
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					String sdate=df.format(dateobj);

				stno=td.getText();
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");  
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from Students,issuebook where Students.st_no=issuebook.s_no");  
					String query2="insert into history (action,std_no,date) values(?,?,?)";
				      PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				      preparedStmt2.setString (1,"Student Delete");
				      preparedStmt2.setString (2, stno);
				      preparedStmt2.setString(3, sdate);
				      preparedStmt2.execute();
				      
					while(rs.next())
					{
						if(stno.equals(rs.getString(1)))
						{
							String s="";
							if(rs.getString(5)==null)
							{
								String query="delete from Students where st_no=?";
								PreparedStatement preparedStmt = con.prepareStatement(query);
							    preparedStmt.setString(1, stno);
							    preparedStmt.execute();
							    String query1="delete from issuebook where s_no=?";
								PreparedStatement preparedStmt1 = con.prepareStatement(query1);
							    preparedStmt1.setString(1, stno);
							    preparedStmt1.execute();
							    String query3 = "delete from bookbank where s_no=?";
							    PreparedStatement preparedStmt3 = con.prepareStatement(query3);
							    preparedStmt3.setString(1, stno);
							    preparedStmt3.execute();
							    f=1;
							    JOptionPane.showMessageDialog(null, "Deletion Successful");
							}
							else
							{
								f=1;
								for(int o=5;o<=10;o++)
								{
									if(rs.getString(o)!=null)
									{
										s=s+rs.getString(o)+",";
									}	
								}
								JOptionPane.showMessageDialog(null, "Deletion Usuccessful!!! Student have Books issued : "+s);
							}
						}
					}
					if(f==0)
					{
						JOptionPane.showMessageDialog(null, "Deletion Unuccessful Doesn't Exist");
					}
				    con.close();  
				}catch(Exception e2){ System.out.println(e2);}
				}
			}
			bdeletedata dd=new bdeletedata();
			bd.addActionListener(dd);
			bd.setBackground(Color.decode("#930101"));
			bd.setForeground(Color.decode("#ffffff"));
			bd.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			fd.f.repaint();
			fd.f.revalidate();
			}

}
