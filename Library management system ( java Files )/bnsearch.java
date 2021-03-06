import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*Book search by name*/
		class bnsearch implements ActionListener{
			Lib l=new Lib();
			public void actionPerformed(ActionEvent e)
			{
				JTable jt;
				int f1=0;
				String bname=l.tf1.getText();
				String bnamel=bname.toLowerCase(); 
				try
				{
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from Books");  
					Vector <Vector> bbs=new Vector <Vector>();
					Vector<String> col=new Vector<String>();
					
					String s="Book Id";
					String s1="Book Name";
					String s2="Author Name";
					col.addElement(s);
					col.addElement(s1);
					col.addElement(s2);
					col.addElement("Student Id");
					col.addElement("Date of Issue");
					while(rs.next())
					{
						String lbname=rs.getString(2).toLowerCase();
						if(bname.equals(rs.getString(2))||bnamel.equals(lbname))
						{
							Vector<String> row=new Vector<String>();
							row.add(rs.getString("b_no"));
							row.add(rs.getString("b_name"));
							row.add(rs.getString("b_author"));
							row.add(rs.getString("s_no"));
							row.add(rs.getString("d_o_i"));
							bbs.add(row);
							f1=1;	
						}
					}
					if(f1==1)
					{
						JFrame f=new JFrame("Book Information");
						f.setExtendedState(JFrame.MAXIMIZED_BOTH);
						f.setVisible(true);
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
					if(f1==0)
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
