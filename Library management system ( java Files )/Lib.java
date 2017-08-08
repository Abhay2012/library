import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;
import java.util.Vector;
/*Home Page*/
public class Lib  
{
	public static String sscb[]={"By Student ID","By Student Name","All Students"};
	public static String sbcb[]={"By Book ID","By Book Name","All Books"};
	public static JComboBox scb;
	public static JComboBox bcb;
	public static JTextField  tf;
	public static JTextField  tf1;
	public static JFrame f;
	public static JLabel la1;
	public static JLabel lab1;
	public static JPanel mp;
	public static JButton search;
	public static JButton searchb;
	public static JPanel spn;
	public static JPanel bpn;
	public static void main(String args[])
	{
		f=new JFrame("Library");
		JButton home=new JButton("Home");
		JButton b =new JButton("Add Student");
		JButton b1 =new JButton("Delete Student");
		JButton b2 =new JButton("Add Book");
		JButton b3 =new JButton("Delete Book");
		JButton b4 =new JButton("Issue Book");
		JButton b5 =new JButton("Return Book");
		JButton ri=new JButton("Reissue Book");
		JButton bb=new JButton("Book Bank");
		JButton hi=new JButton("History");
		JPanel p=new JPanel();
		
		JLabel sl=new JLabel("Students Information");
		JLabel bl=new JLabel("Books Information");
		JLabel t=new JLabel("  Welcome to Library ");	
		
		la1=new JLabel("Enter Student Id");
		search=new JButton("Search");
		tf=new JTextField(30);
		searchb=new JButton("Search");
		tf1=new JTextField(30);
		lab1=new JLabel("Enter Book Id");
		JLabel lan1=new JLabel("Enter Student Name");

		JLabel labn1=new JLabel("Enter Book Name");
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon i1=new ImageIcon(Lib.class.getResource("resources/head.gif"));
		ImageIcon i=new ImageIcon(Lib.class.getResource("resources/rsz_header.gif"));
		JPanel pi=new JPanel();
		pi.setBounds(15,5,1275,142);
		pi.setVisible(true);
		pi.setBackground(Color.decode("#8e0404"));
		pi.setLayout(null);
		f.add(pi);
		
		JLabel li=new JLabel(i);
		pi.add(li);
		li.setBounds(15, 15, 195, 102);
		
		JLabel li1=new JLabel(i1);
		pi.add(li1);
		li1.setBounds(240, 15, 823, 102);
		f.setBackground(Color.white);
		
		spn=new JPanel();
		bpn=new JPanel();
		
		scb=new JComboBox(sscb);
		bcb=new JComboBox(sbcb);
		scombo sc=new scombo();
		scb.addActionListener(sc);
		bcombo bc=new bcombo();
		bcb.addActionListener(bc);
		
		mp=new JPanel();
		f.add(mp);
		mp.setVisible(true);
		mp.setBackground(Color.WHITE);
		mp.setLayout(null);
		mp.setBounds(250,152, 1040, 455);
		mp.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		mp.add(spn);
		spn.setVisible(true);
		spn.setBackground(Color.WHITE);
		spn.setLayout(null);
		spn.setBounds(140,90, 630, 160);
		
		mp.add(bpn);
		bpn.setVisible(true);
		bpn.setBackground(Color.WHITE);
		bpn.setLayout(null);
		bpn.setBounds(140,250, 630, 160);

		spn.add(la1);
		spn.add(tf);
		spn.add(search);
		ssearch s=new ssearch();
		search.addActionListener(s);
			
		la1.setBounds(100,50,200,40);
		tf.setBounds(275, 50, 200, 40);
		search.setBounds(505,50, 100, 40);
		search.setBackground(Color.decode("#930101"));
		search.setForeground(Color.decode("#ffffff"));
		search.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		bpn.add(lab1);
		bpn.add(tf1);
		bpn.add(searchb);
		bsearch bs=new bsearch();
		searchb.addActionListener(bs);
		searchb.setBackground(Color.decode("#930101"));
		searchb.setForeground(Color.decode("#ffffff"));
		searchb.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
		lab1.setBounds(100,50,200,40);
		tf1.setBounds(275, 50, 200, 40);
		searchb.setBounds(505, 50, 100, 40);
		
		mp.add(t);
		t.setBounds(425,20,150,30);
		
		spn.add(scb);
		scb.setBounds(100, 0,165,25);
		
		bpn.add(bcb);
		bcb.setBounds(100,0,165,25);
		
				
		f.add(p);
		
		p.setBounds(15,152,230,455);
		p.setVisible(true);
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		p.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		home.setBounds(0, 0,230,35);
		p.add(home);
		home h=new home();
		home.addActionListener(h);
		home.setBackground(Color.decode("#930101"));
		home.setForeground(Color.decode("#ffffff"));
		home.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		sl.setBounds(25,35,230,35);
		p.add(sl);
		sl.setForeground(Color.decode("#930101"));
		
		b.setBounds(0,70,230,35);
		p.add(b);
		sinsert sins=new sinsert();
		b.addActionListener(sins);
		b.setBackground(Color.decode("#930101"));
		b.setForeground(Color.decode("#ffffff"));
		b.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		b1.setBounds(0,105,230,35);
		p.add(b1);
		sdelete sd=new sdelete();
		b1.addActionListener(sd);
		b1.setBackground(Color.decode("#930101"));
		b1.setForeground(Color.decode("#ffffff"));
		b1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		bl.setBounds(30,140,230,35);
		p.add(bl);
		bl.setForeground(Color.decode("#930101"));	
		
		p.add(b2);
		b2.setBounds(0,175,230,35);
		binsert bi=new binsert();
		b2.addActionListener(bi);
		b2.setBackground(Color.decode("#930101"));
		b2.setForeground(Color.decode("#ffffff"));
		b2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		p.add(b3);
		b3.setBounds(0,210,230,35);
		bdelete bd=new bdelete();
		b3.addActionListener(bd);
		b3.setBackground(Color.decode("#930101"));
		b3.setForeground(Color.decode("#ffffff"));
		b3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		b4.setBounds(0,280,230,35);
		p.add(b4);
		issuebook isb=new issuebook();
		b4.addActionListener(isb);
		b4.setBackground(Color.decode("#930101"));
		b4.setForeground(Color.decode("#ffffff"));
		b4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		ri.setBounds(0,315,230,35);
		p.add(ri);
		reissue r=new reissue();
		ri.addActionListener(r);
		ri.setBackground(Color.decode("#930101"));
		ri.setForeground(Color.decode("#ffffff"));
		ri.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		p.add(b5);
		b5.setBounds(0,350,230,35);
		returnbook ret=new returnbook();
		b5.addActionListener(ret);
		b5.setBackground(Color.decode("#930101"));
		b5.setForeground(Color.decode("#ffffff"));
		b5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		p.add(bb);
		bb.setBounds(0,385,230,35);
		bb.setBackground(Color.decode("#930101"));
		bb.setForeground(Color.decode("#ffffff"));
		bb.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		bookbank book = new bookbank();
		bb.addActionListener(book);
		
		hi.setBounds(0,420,230,35);
		p.add(hi);
		history his=new history();
		hi.addActionListener(his);
		hi.setBackground(Color.decode("#930101"));
		hi.setForeground(Color.decode("#ffffff"));
		hi.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		f.setLayout(null);
		f.setVisible(true);
		}
}
