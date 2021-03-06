import javax.swing.*;
import java.util.Date;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;;
/* issue book */
public class issuebook implements ActionListener{
	JTextField tf;
	JTextField tf1;
	public void actionPerformed(ActionEvent e){
		Lib l=new Lib();
		l.f.setTitle("Issue Book");
		l.mp.removeAll();
		JLabel lt=new JLabel("Issue Book");
		JLabel la=new JLabel("Enter Details :");
		JLabel la1=new JLabel("Student No :");
		JLabel la2=new JLabel("Book No :");
		tf=new JTextField(50);
		tf1=new JTextField(50);
		JButton b=new JButton("Issue");
		
		l.mp.add(tf);
		l.mp.add(lt);
		l.mp.add(tf1);
		l.mp.add(la2);
		l.mp.add(la1);
		l.mp.add(la);
		l.mp.add(b);
		
		lt.setBounds(425,20,250,30);
		la.setBounds(475,80,150,40);
		la1.setBounds(300,150,150,40);
		la2.setBounds(300,200,150,40);
		tf.setBounds(550,150,150,40);
		tf1.setBounds(550,200,150,40);
		b.setBounds(550,275,150,40);
		
		l.f.repaint();
		l.f.revalidate();
		
		class issue implements ActionListener{
			public void actionPerformed(ActionEvent ae){
				try{
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					String sdate=df.format(dateobj);

					int f=0,f1=0,f2=0,f3=0;
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");
					Statement stmt=con.createStatement();
					Statement stmt1=con.createStatement();  
					  
					ResultSet rs=stmt.executeQuery("select * from issuebook");  
					while(rs.next())
					{
						if((rs.getString(1)).equals(tf.getText()))
						{
							f=1;
							for(int i=2;i<=7;i++)
							{
								if(rs.getString(i)==null)
								{
									f1=1;
									ResultSet rs1=stmt1.executeQuery("select * from Books");
									while(rs1.next())
									{
										if(rs1.getString(1).equals(tf1.getText()))
										{
											f2=1;
											if(rs1.getString(4)==null)
											{
												String query="update issuebook set b_no"+(i-1)+"=? where s_no=?";
												String query1="update Books set s_no=? where b_no=?";
												String query2="update Books set d_o_i=? where b_no=?";
												PreparedStatement preparedStmt = con.prepareStatement(query);
											    preparedStmt.setString(1, tf1.getText());
											    preparedStmt.setString(2, tf.getText());
											    preparedStmt.execute();
											    
											    PreparedStatement preparedStmt1 = con.prepareStatement(query1);
											    preparedStmt1.setString(1, tf.getText());
											    preparedStmt1.setString(2, tf1.getText());
											    preparedStmt1.execute();
											    
											    PreparedStatement preparedStmt2 = con.prepareStatement(query2);
											    preparedStmt2.setString(1, sdate);
											    preparedStmt2.setString(2, tf1.getText());
											    preparedStmt2.execute();
											    
											    String query3="insert into history (action,std_no,bk_no,date) values(?,?,?,?)";
											      PreparedStatement preparedStmt3 = con.prepareStatement(query3);
											      preparedStmt3.setString (1,"Book Issue");
											      preparedStmt3.setString (2, tf.getText());
											      preparedStmt3.setString(3, tf1.getText());
											      preparedStmt3.setString(4, sdate);
											      preparedStmt3.execute();

											    JOptionPane.showMessageDialog(null, "Issue Successful");
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Issue Unsuccessful Already issued!!!");
											}
										}
									}
									if(f2==0)
									{
										JOptionPane.showMessageDialog(null, "Book Number not Valid!!!");
									}									
									break;
								}
							}
							if(f1==0)
							{
								JOptionPane.showMessageDialog(null, "Student Already have issued 6 books");
							}
						}
					}
					if(f==0)
					{
						JOptionPane.showMessageDialog(null, "Student ID not valid");

					}
					con.close();
				}catch(Exception e)
				{
					System.out.print(e);
				}
			}
		}
		issue i=new issue();
		b.addActionListener(i);
		b.setBackground(Color.decode("#930101"));
		b.setForeground(Color.decode("#ffffff"));
		b.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
}
