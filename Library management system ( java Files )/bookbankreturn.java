import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.*;
import javax.swing.*;
import java.util.Date;
import java.util.Vector;

/*book bank return */
public class bookbankreturn implements ActionListener{
	public static Vector<Checkbox> v;
	public void actionPerformed(ActionEvent ae){
		bookbank bb = new bookbank();
		JButton ret = new JButton("Return");
		int f=0,f1=0;
		try{
			v=new Vector<Checkbox>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Library","root","abhay");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from bookbank");
			while(rs.next()){
				if(rs.getString(1).equals(bb.tf2.getText())){
					f=1;
					for(int i=2;i<=4;i++){
						if(rs.getString(i)!=null){
							f1=1;
							Checkbox c = new Checkbox(rs.getString(i));
							bb.right.add(c);
							c.setBounds(200,75+(65*(i-1)),150,40);
							v.add(c);
						}
					}
					bb.right.add(ret);
					ret.setBounds(375,135,100,30);
					ret.setBackground(Color.decode("#930101"));
					ret.setForeground(Color.decode("#ffffff"));
					ret.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				}
			}
			if(f==0){
				JOptionPane.showMessageDialog(null, "Invalid Student Id!!!!");
			}
			if(f==1&&f1==0){
				JOptionPane.showMessageDialog(null, "Book Bank Not Alloted!!!");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		class returnbank implements ActionListener{
			public void actionPerformed(ActionEvent ae){
				bookbankreturn bbr=new bookbankreturn();
				bookbank bb = new bookbank();
				try{
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");
					Statement stmt=con.createStatement();
					Statement stmt1=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from Books");
					DateFormat df = new SimpleDateFormat("dd/MM/yy  HH:mm:ss");
					Date dateobj = new Date();
					String sdate=df.format(dateobj);
					
					int s1=bbr.v.size();
					for(int i=0;i<s1;i++)
					{					
						Checkbox tc=new Checkbox();
						tc=bbr.v.get(i);
						if(tc.getState()==true)
						{					
							while(rs.next())
							{
								if(rs.getString(1).equals(tc.getLabel()))
								{														
									String query1="update Books set d_o_i=?,s_no=null where b_no="+rs.getString(1);
									PreparedStatement preparedStmt = con.prepareStatement(query1);
								    preparedStmt.setString(1,null);
								    preparedStmt.execute();
								    ResultSet rs1=stmt1.executeQuery("select * from bookbank");
								    while(rs1.next())
								    {
								    	if(bb.tf2.getText().equals(rs1.getString(1)))
								    	{
								    		for(int j=2;j<=5;j++)
								    		{
								    			if(tc.getLabel().equals(rs1.getString(j)))
								    			{
								    				String q1="update bookbank set date = null, b_no"+(j-1)+"=null where s_no="+bb.tf2.getText();
								    				PreparedStatement pStmt = con.prepareStatement(q1);
								    				pStmt.execute();
								    				break;
								    			}
								    		}
								    	}
								    }
								    String query2="insert into history (action,std_no,bk_no,date) values(?,?,?,?)";
								      PreparedStatement preparedStmt2 = con.prepareStatement(query2);
								      preparedStmt2.setString (1,"Book Bank Return");
								      preparedStmt2.setString (2, bb.tf2.getText());
								      preparedStmt2.setString(3, tc.getLabel());
								      preparedStmt2.setString(4, sdate);
								      preparedStmt2.execute();
								}
							}
						}
					}
					JOptionPane.showMessageDialog(null,"Return Succesfull");
					
					con.close();							
					}catch(Exception e1)
					{
						System.out.println(e1);
					}	
			}
		}
		returnbank rb = new returnbank();
		ret.addActionListener(rb);
	}
	
}
