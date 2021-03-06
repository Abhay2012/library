import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*display all books data*/
public class bshow implements ActionListener 
{ 
	JTable jt;
	public void actionPerformed(ActionEvent e)
	{	
		JFrame f=new JFrame("Books Data");
		f.setVisible(true);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Library","root","abhay");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Books");  
		
			String bbname=new String();
			String bbaname=new String();
			String sno=new String();
			String doi=new String();
			String bbnos=new String();
			Vector<String> col=new Vector<String>();
			
			String s="Book no";
			String s1="Book name";
			String s2="Author name";
			col.addElement(s);
			col.addElement(s1);
			col.addElement(s2);
			col.addElement("Student no.");
			col.addElement("Date of Issue");
			Vector <Vector> bbs=new Vector <Vector>();
			while(rs.next())
			{	
				Vector<String> row=new Vector<String>();
				bbnos=rs.getString("b_no");
				bbname=rs.getString("b_name");
				bbaname=rs.getString("b_author");
				sno=rs.getString("s_no");
				doi=rs.getString("d_o_i");
				row.add(bbnos);
				row.add(bbname);
				row.add(bbaname);
				row.add(sno);
				row.add(doi);
				bbs.add(row);
			}
			BorderLayout bl=new BorderLayout();
			f.setLayout(bl);
			jt=new JTable(bbs,col);
			
			jt.setVisible(true);
			jt.setBounds(100,300,150,50);
			f.add(jt);
			f.setSize(600,400);
			JScrollPane jp=new JScrollPane(jt);
			f.add(jp,BorderLayout.NORTH);
			con.close();  
		}catch(Exception e1){ System.out.println(e1);}
	}
}
