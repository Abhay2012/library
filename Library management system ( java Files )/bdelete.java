import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/*book delete action*/
class bdelete implements ActionListener{
	JTextField td;
	public String bkno;
	public void actionPerformed(ActionEvent e)
	{
		Lib fd=new Lib();
		
		fd.mp.removeAll();
		fd.f.setTitle("Book Data Deletion");

		td=new JTextField(20);
		JLabel l=new JLabel("Enter Book no.");
		JLabel la=new JLabel("Book Data Deletion");
		
		td.setBounds(425,150,200,40);
		fd.mp.add(td);
		
		l.setBounds(240,150,150,40);
		fd.mp.add(l);
		
		fd.mp.add(la);
		la.setBounds(425,20,250,30);
		
		JButton bd=new JButton("Delete");
		fd.mp.add(bd);
		bd.setBounds(665,150,100,30);
		
		/*delete book data action*/
		class deletedata implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				int f=0;
				try{
				bkno=td.getText();
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date dateobj = new Date();
				String sdate=df.format(dateobj);

					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from Books");  
					String query2="insert into history (action,bk_no,date) values(?,?,?)";
				      PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				      preparedStmt2.setString (1,"Book Delete");
				      preparedStmt2.setString (2, bkno);
				      preparedStmt2.setString(3, sdate);
				      preparedStmt2.execute();
					while(rs.next())
					{
						if(bkno.equals(rs.getString(1)))
						{
							if(rs.getString(4)==null)
							{
								String query="delete from Books where b_no=?";
								PreparedStatement preparedStmt = con.prepareStatement(query);
							    preparedStmt.setString(1, bkno);
							    preparedStmt.execute();
							    JOptionPane.showMessageDialog(null, "Deletion Successful");
								f=1;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Deletion Unsuccessful!!! Book is issued to : "+rs.getString(4));
								f=1;
							}
						}
					}
					if(f==0)
					{
						JOptionPane.showMessageDialog(null, "Deletion Unsuccessful doesn't exist!!!");
					}
						con.close();  
				}catch(Exception e2){ System.out.println(e2);}
				}
			}
			deletedata dd=new deletedata();
			bd.addActionListener(dd);
			bd.setBackground(Color.decode("#930101"));
			bd.setForeground(Color.decode("#ffffff"));
			bd.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			fd.f.revalidate();
			fd.f.repaint();
		}
	}
