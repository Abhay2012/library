import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
/*return Book*/
public class returnbook implements ActionListener{
	static public int s;
	static public Vector<Checkbox> vc;
	Lib li=new Lib();
	JTextField tf2;
	String s2;
	public void actionPerformed(ActionEvent e)
	{
		li.f.setTitle("Return Book");
		li.spn.removeAll();
		li.bpn.removeAll();
		li.mp.removeAll();
		tf2=new JTextField(30);
		
		li.mp.add(li.spn);
		li.spn.setVisible(true);
		li.spn.setBackground(Color.WHITE);
		li.spn.setLayout(null);
		li.spn.setBounds(5,30, 830, 160);
		
		li.mp.add(li.bpn);
		li.bpn.setVisible(true);
		li.bpn.setBackground(Color.WHITE);
		li.bpn.setLayout(null);
		li.bpn.setBounds(5,190, 830, 250);

		JLabel lt=new JLabel("Return Book");
		JLabel la=new JLabel("Enter Student Id");
		JLabel laq=new JLabel("Student Id");
		JButton b=new JButton("Search");
		li.spn.add(b);
		li.spn.add(lt);
		li.spn.add(la);
		li.spn.add(tf2);
		lt.setBounds(425,15,200,40);
		tf2.setBounds(425,100,150,40);
		la.setBounds(240,100,150,40);
		laq.setBounds(100,50,150,40);
		
		b.setBounds(665,100,120,40);
		class retsearch implements ActionListener{
			
			
			public void actionPerformed(ActionEvent e){
				JButton ri=new JButton("Return");
				try{
					li.bpn.removeAll();

					vc=new Vector<Checkbox>();
					int k=0,f=0,f1=0;
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Library","root","abhay");
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from issuebook");
					while(rs.next())
					{
						s2=tf2.getText();
						if(s2.equals(rs.getString(1)))
						{s=0;
							f=1;
							for(int i=2;i<=7;i++)
							{
								if(rs.getString(i)!=null)
								{
									f1=1;
									Checkbox c=new Checkbox(rs.getString(i));
									vc.add(c);
									s++;
									li.bpn.add(c);
									c.setBounds(490,k,100,20);
									k+=40;
								}
							}
						}						
					}
					con.close();
					if(f==0)
					{
						JOptionPane.showMessageDialog(null, "Invalid Student Id!!!!");
					}
					if(f==1&&f1==0)
					{
						JLabel laq=new JLabel("*No books issued");
						li.bpn.add(laq);
						laq.setBounds(300,0,300,20);
					}
					if(f==1&&f1==1)
					{
						li.bpn.add(ri);
						ri.setBounds(665,0,120,40);
					}
					li.bpn.repaint();
					li.bpn.revalidate();
					}catch(Exception e1)
					{
						System.out.println(e1);
					}
				class retbook implements ActionListener{
					public void actionPerformed(ActionEvent e)
					{
						returnbook rr=new returnbook();
						try{
							int tfine=0;
							String stfine;
							Class.forName("com.mysql.jdbc.Driver");  
							Connection con=DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/Library","root","abhay");
							Statement stmt=con.createStatement();
							Statement stmt1=con.createStatement();
							ResultSet rs=stmt.executeQuery("select * from Books");
							DateFormat df = new SimpleDateFormat("dd/MM/yy  HH:mm:ss");
							Date dateobj = new Date();
							String sdate=df.format(dateobj);
							
							int s1=rr.vc.size();
							for(int i=0;i<s1;i++)
							{					
								Checkbox tc=new Checkbox();
								tc=rr.vc.get(i);
								if(tc.getState()==true)
								{					
									while(rs.next())
									{
										if(rs.getString(1).equals(tc.getLabel()))
										{					
											fine f=new fine();
											String tpfine;
											
											int temp;
											temp=f.getFine(rs.getString(5),sdate);
											
											tfine+=temp;
											
											tpfine=Integer.toString(temp);
											
											String query1="update Books set d_o_i=?,s_no=null where b_no="+rs.getString(1);
											PreparedStatement preparedStmt = con.prepareStatement(query1);
										    preparedStmt.setString(1,null);
										    preparedStmt.execute();
										    ResultSet rs1=stmt1.executeQuery("select * from issuebook");
										    while(rs1.next())
										    {
										    	if(tf2.getText().equals(rs1.getString(1)))
										    	{
										    		for(int j=2;j<=7;j++)
										    		{
										    			if(tc.getLabel().equals(rs1.getString(j)))
										    			{
										    				String q1="update issuebook set b_no"+(j-1)+"=null where s_no="+tf2.getText();
										    				PreparedStatement pStmt = con.prepareStatement(q1);
										    				pStmt.execute();
										    				break;
										    			}
										    		}
										    	}
										    }
										    String query2="insert into history (action,std_no,bk_no,date,fine) values(?,?,?,?,?)";
										      PreparedStatement preparedStmt2 = con.prepareStatement(query2);
										      preparedStmt2.setString (1,"Book Return");
										      preparedStmt2.setString (2, tf2.getText());
										      preparedStmt2.setString(3, tc.getLabel());
										      preparedStmt2.setString(4, sdate);
										      preparedStmt2.setString(5, tpfine);
										      preparedStmt2.execute();
										}
									}
								}
							}
							stfine=Integer.toString(tfine);
							JOptionPane.showMessageDialog(null,"Fine to be paid : Rs "+stfine);
							
							con.close();							
							}catch(Exception e1)
							{
								System.out.println(e1);
							}
						}
					}
				retbook rr=new retbook();
				ri.addActionListener(rr);
				ri.setBackground(Color.decode("#930101"));
				ri.setForeground(Color.decode("#ffffff"));
				ri.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				}
			}
		retsearch ri=new retsearch();
		b.addActionListener(ri);
		b.setBackground(Color.decode("#930101"));
		b.setForeground(Color.decode("#ffffff"));
		b.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		li.spn.repaint();
		li.spn.revalidate();
		li.bpn.repaint();
		li.bpn.revalidate();
		li.mp.repaint();
		li.mp.revalidate();
	}
}
