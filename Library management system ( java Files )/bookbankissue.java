import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.*;
import javax.swing.*;
import java.util.Date;
import java.util.Vector;
/*book bank issue */
public class bookbankissue implements ActionListener{
	public static JTextField tf1;
	public static JTextField tf2;
	public static JTextField tf3;
	public static JTextField tf4;
	public static JButton b; 
	public void actionPerformed(ActionEvent ae){
		bookbank bb = new bookbank();
		int f=0;
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Library","root","abhay");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from bookbank");
			while(rs.next()){
				if(rs.getString(1).equals(bb.tf1.getText())){
					f=1;
					tf1 = new JTextField(30);
					tf2 = new JTextField(30);
					tf3 = new JTextField(30);
					tf4 = new JTextField(30);	
					b =new JButton("Issue");
						
					tf1.setBounds(200,140,150,40);	
					tf2.setBounds(200,205,150,40);
					tf3.setBounds(200,270,150,40);
					tf4.setBounds(200,335,150,40);
					b.setBounds(375,135,100,30);
						
					bb.left.add(tf1);
					bb.left.add(tf2);
					bb.left.add(tf3);
					bb.left.add(tf4);
					bb.left.add(b);
					b.setBackground(Color.decode("#930101"));
					b.setForeground(Color.decode("#ffffff"));
					b.setBorder(BorderFactory.createLineBorder(Color.black, 2));
					}
				}
			if(f==0){
				JOptionPane.showMessageDialog(null,"Invalid Student Id");
			}
		}catch(Exception e){
			System.out.print(e);
		}
		
		class issue implements ActionListener{
			public void actionPerformed(ActionEvent ae){
				int f=0,f1=0,fl1=0,fl2=0,fl3=0,fl4=0,flg1=0,flg2=0,flg3=0,flg4=0;
				bookbank bb = new bookbank();
				bookbankissue bbi = new bookbankissue();
				try{
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					String sdate=df.format(dateobj);
					Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/Library","root","abhay");
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from bookbank");
				while(rs.next()){
					if(rs.getString(1).equals(bb.tf1.getText())){
						f=1;
						if(rs.getString(6)==null){
							f1=1;
							if(!(tf1.getText().isEmpty()) || !(tf2.getText().isEmpty()) || !(tf3.getText().isEmpty()) || !(tf4.getText().isEmpty())){
								String q1 ="update bookbank set date = ? where s_no = "+bb.tf1.getText()+";";
								PreparedStatement pdst1 = con.prepareStatement(q1);
								pdst1.setString(1,sdate);
								pdst1.execute();
							}
							Statement stmt1 = con.createStatement();
							ResultSet rs1 = stmt1.executeQuery("select * from Books");
							while(rs1.next()){
								if(!(tf1.getText().isEmpty())){
									if(rs1.getString(1).equals(tf1.getText())){
										fl1=1;
										if(rs1.getString(4)==null){
											flg1 = 1;
											String q ="update bookbank set b_no1 = ? where s_no = "+bb.tf1.getText()+";";
											PreparedStatement pdst = con.prepareStatement(q);
											pdst.setString(1,tf1.getText());
											pdst.execute();
											String q1 ="update Books set s_no = ?, d_o_i = ? where b_no = "+tf1.getText()+";";
											PreparedStatement pdst1 = con.prepareStatement(q1);
											pdst1.setString(1,bb.tf1.getText());
											pdst1.setString(2,sdate);
											pdst1.execute();
										}										
									}
								}
								if(!(tf2.getText().isEmpty())){
									if(rs1.getString(1).equals(tf1.getText())){
										fl2=1;
										if(rs1.getString(4)==null){
											flg2 = 1;
											String q ="update bookbank set b_no2 = ? where s_no = "+bb.tf1.getText()+";";
											PreparedStatement pdst = con.prepareStatement(q);
											pdst.setString(1,bbi.tf2.getText());
											pdst.execute();
											String q1 ="update Books set s_no = ?, d_o_i = ? where b_no = "+tf2.getText()+";";
											PreparedStatement pdst1 = con.prepareStatement(q1);
											pdst1.setString(1,bb.tf1.getText());
											pdst1.setString(2,sdate);
											pdst1.execute();
										}
									}
								}
								if(!(tf3.getText().isEmpty())){
									if(rs1.getString(1).equals(tf1.getText())){
										fl3 = 1;
										if(rs1.getString(4)==null){
											flg3 = 1;
											String q ="update bookbank set b_no3 = ? where s_no = "+bb.tf1.getText()+";";
											PreparedStatement pdst = con.prepareStatement(q);
											pdst.setString(1,bbi.tf3.getText());
											pdst.execute();
											String q1 ="update Books set s_no = ?, d_o_i = ? where b_no = "+tf3.getText()+";";
											PreparedStatement pdst1 = con.prepareStatement(q1);
											pdst1.setString(1,bb.tf1.getText());
											pdst1.setString(2,sdate);
											pdst1.execute();
										}
									}
								}
								if(!(tf4.getText().isEmpty())){
									if(rs1.getString(1).equals(tf1.getText())){
										fl4=1;
										if(rs1.getString(4)==null){
											flg4 = 1;
											String q ="update bookbank set b_no4 = ? where s_no = "+bb.tf1.getText()+";";
											PreparedStatement pdst = con.prepareStatement(q);
											pdst.setString(1,bbi.tf4.getText());
											pdst.execute();
											String q1 ="update Books set s_no = ?, d_o_i = ? where b_no = "+tf4.getText()+";";
											PreparedStatement pdst1 = con.prepareStatement(q1);
											pdst1.setString(1,bb.tf1.getText());
											pdst1.setString(2,sdate);
											pdst1.execute();
										}
									}
								}
							}
							if(fl1==0 && !(tf1.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf1+" Not Found!!!");
							}
							if(fl2==0 && !(tf2.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf2+" Not Found!!!");
							}
							if(fl3==0 && !(tf3.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf3+" Not Found!!!");
							}
							if(fl4==0 && !(tf4.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf4+" Not Found!!!");
							}
							if(flg1==0 && !(tf1.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf1+" Already Issued!!!");
							}
							if(flg2==0 && !(tf2.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf2+" Already Issued!!!");
							}
							if(flg3==0 && !(tf3.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf3+" Already Issued!!!");
							}
							if(flg4==0 && !(tf4.getText().isEmpty())){
								JOptionPane.showMessageDialog(null,"Book : "+tf4+" Already Issued!!!");
							}
						}
						}
					}
				if(f==0){
					JOptionPane.showMessageDialog(null,"Student Id Not Found");
				}
				if(f1==0){
					JOptionPane.showMessageDialog(null,"Student Already Have Book Bank");
				}
				if(f==1&&f1==1){
					JOptionPane.showMessageDialog(null,"Book Bank Successfully Allotted");
				}
				con.close();
				}catch(Exception e){
					System.out.println(e);
				}
				
			}
		}
		issue i = new issue();
		b.addActionListener(i);
		bb.left.revalidate();
		bb.left.repaint();
	}
}
