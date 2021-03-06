import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/*book insert action*/
public class binsert implements ActionListener{
	public String b_name;
	Lib fi=new Lib();
	public String b_author;
	public String bno;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	public void actionPerformed(ActionEvent e)
	{
		
		fi.mp.removeAll();
		fi.f.setTitle("Book Data Insertion");

		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		JLabel l=new JLabel("Enter Details :");
		JLabel l1=new JLabel("Book no.");
		JLabel l2=new JLabel("Book name");
		JLabel l3=new JLabel("Author name");
		JButton ba1=new JButton(" Insert");
		JLabel la=new JLabel("Book Data Insertion");
		
		fi.mp.add(l1);
		fi.mp.add(l2);
		fi.mp.add(l3);
		fi.mp.add(t1);
		fi.mp.add(t2);
		fi.mp.add(t3);
		
		fi.mp.add(ba1);
		fi.mp.add(l);
		
		fi.mp.add(la);
		la.setBounds(425,20,250,30);

		t1.setBounds(550,150,150,40);
		t2.setBounds(550,200,150,40);
		t3.setBounds(550,250,150,40);
		
		ba1.setBounds(550,325,150,40);
		l.setBounds(475,80,150,40);
		l1.setBounds(300,150,150,40);
		l2.setBounds(300,200,150,40);
		l3.setBounds(300,250,150,40);
		fi.mp.revalidate();
		fi.mp.repaint();
		
		/*insert book data action*/
		class binsertdata  implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				
				int f=0;	
				try{
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					String sdate=df.format(dateobj);
					bno=t1.getText();
					b_name=new String(t2.getText());
					b_author=t3.getText();
					
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");  
					  String query = " insert into Books (b_no,b_name,b_author)"
					        + " values (?, ?, ?)";

					      // create the mysql insert preparedstatement
					      PreparedStatement preparedStmt = con.prepareStatement(query);
					      preparedStmt.setString (1, bno);
					      preparedStmt.setString (2, b_name);
					      preparedStmt.setString(3, b_author);
					      preparedStmt.execute();
					      String query2="insert into history (action,bk_no,date) values(?,?,?)";
					      PreparedStatement preparedStmt2 = con.prepareStatement(query2);
					      preparedStmt2.setString (1,"Book Insert");
					      preparedStmt2.setString (2, bno);
					      preparedStmt2.setString(3, sdate);
					      preparedStmt2.execute();
					 
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
		
		binsertdata bi=new binsertdata();
		ba1.addActionListener(bi);
		ba1.setBackground(Color.decode("#930101"));
		ba1.setForeground(Color.decode("#ffffff"));
		ba1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
}

