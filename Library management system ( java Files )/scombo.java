import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/* Student Combo box on home page */
public class scombo implements ActionListener{
	public void actionPerformed(ActionEvent e)
	{
		JButton asearch=new JButton("Search");
		JButton nsearch=new JButton("Search");
		Lib l=new Lib();
		JLabel lan1=new JLabel("Enter Student Name");
		Object os;
		String ss;
		os=l.scb.getItemAt(l.scb.getSelectedIndex());  
		ss=os.toString();
		l.tf.setText(null);
		l.spn.removeAll();
		if(ss.equals("By Student Name"))
		{
			l.spn.add(lan1);
			lan1.setBounds(100,50,200,40);

			l.spn.add(l.tf);
			l.spn.add(nsearch);
			snsearch s=new snsearch();
			nsearch.addActionListener(s);
			nsearch.setBackground(Color.decode("#930101"));
			nsearch.setForeground(Color.decode("#ffffff"));
			nsearch.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			l.tf.setBounds(275, 50, 200, 40);
			nsearch.setBounds(505,50, 100, 40);
			l.spn.add(l.scb);
			l.scb.setBounds(100, 0,165,25);

		}
		else if(ss.equals("All Students"))
		{
			
			l.spn.add(asearch);
			sshow s=new sshow();
			asearch.addActionListener(s);
			asearch.setBackground(Color.decode("#930101"));
			asearch.setForeground(Color.decode("#ffffff"));
			asearch.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			asearch.setBounds(275,50, 100, 40);
			l.spn.add(l.scb);
			l.scb.setBounds(100, 0,165,25);

		}
		else if(ss.equals("By Student ID"))
		{
			l.spn.add(l.la1);
			l.la1.setBounds(100,50,200,40);

			l.spn.add(l.tf);
			l.spn.add(l.search);
			ssearch s=new ssearch();
			l.search.addActionListener(s);
			l.tf.setBounds(275, 50, 200, 40);
			l.search.setBounds(505,50, 100, 40);
			l.spn.add(l.scb);
			l.scb.setBounds(100, 0,165,25);
		}
		l.spn.repaint();
		l.spn.revalidate();
	}
}
