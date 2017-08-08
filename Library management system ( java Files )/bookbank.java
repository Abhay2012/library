import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;
import java.util.Vector;
/*Book Bank */
public class bookbank implements ActionListener{
	public static JPanel left;
	public static JPanel right;
	public static JTextField tf1;
	public static JTextField tf2;
	public void actionPerformed(ActionEvent ae){
		Lib l = new Lib();
		l.mp.removeAll();
		JLabel h1 = new JLabel("Book Bank");
		h1.setBounds(477,15,200,40);
		
		left =new JPanel();
		JLabel l1 = new JLabel("Enter Student No.");
		JLabel l2 = new JLabel("Enter Student No.");
		tf1 = new JTextField(30);
		JButton bs1 = new JButton("Search");
		l.mp.add(left);
		left.setBounds(2,55,520,398);
		left.setLayout(null);
		JLabel h2 = new JLabel("Book Bank Issue");
		h2.setBounds(220,15,200,40);
		left.add(h2);
		left.setBackground(Color.white);
		l1.setBounds(25,75,150,40);
		left.add(l1);
		left.add(tf1);
		tf1.setBounds(200,75,150,40);
		left.add(bs1);
		bs1.setBounds(375,80,100,30);
		bs1.setBackground(Color.decode("#930101"));
		bs1.setForeground(Color.decode("#ffffff"));
		bs1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		right = new JPanel();
		tf2 = new JTextField(30);
		JButton bs2 = new JButton("Search");
		l.mp.add(right);
		right.setBounds(520,55,518,398);
		right.setLayout(null);
		JLabel h3 = new JLabel("Book Bank Return");
		h3.setBounds(220,15,200,40);
		right.add(h3);
		right.setBackground(Color.white);
		l2.setBounds(25,75,150,40);
		right.add(l2);
		right.add(tf2);
		tf2.setBounds(200,75,150,40);
		right.add(bs2);
		bs2.setBounds(375,80,100,30);
		bs2.setBackground(Color.decode("#930101"));
		bs2.setForeground(Color.decode("#ffffff"));
		bs2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		bookbankissue bbi = new bookbankissue();
		bs1.addActionListener(bbi);
		bookbankreturn bbr = new bookbankreturn();
		bs2.addActionListener(bbr);
		l.mp.add(h1);
		l.mp.repaint();
		l.mp.revalidate();
	}

}
