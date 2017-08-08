import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/* book combo box on home page */
public class bcombo implements ActionListener{
	public void actionPerformed(ActionEvent e)
	{
		Lib l=new Lib();
		JButton asearchb=new JButton("Search");
		JButton nsearchb=new JButton("Search");
		
		JLabel lan1=new JLabel("Enter Book Name");
		Object ob;
		String sb;
		ob=l.bcb.getItemAt(l.bcb.getSelectedIndex());  
		sb=ob.toString();
		l.bpn.removeAll();
		l.tf1.setText(null);
		if(sb.equals("By Book Name"))
		{
			
			l.bpn.add(lan1);
			lan1.setBounds(100,50,200,40);

			l.bpn.add(l.tf1);
			l.bpn.add(nsearchb);
			bnsearch s=new bnsearch();
			nsearchb.addActionListener(s);
			nsearchb.setBackground(Color.decode("#930101"));
			nsearchb.setForeground(Color.decode("#ffffff"));
			nsearchb.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			l.tf1.setBounds(275, 50, 200, 40);
			nsearchb.setBounds(505,50, 100, 40);
			l.bpn.add(l.bcb);
			l.bcb.setBounds(100, 0,165,25);

		}
		else if(sb.equals("All Books"))
		{
			l.bpn.add(asearchb);
			bshow s=new bshow();
			asearchb.addActionListener(s);
			asearchb.setBounds(275,50, 100, 40);
			asearchb.setBackground(Color.decode("#930101"));
			asearchb.setForeground(Color.decode("#ffffff"));
			asearchb.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			l.bpn.add(l.bcb);
			l.bcb.setBounds(100, 0,165,25);

		}
		else if(sb.equals("By Book ID"))
		{
			l.bpn.add(l.lab1);
			l.lab1.setBounds(100,50,200,40);

			l.bpn.add(l.tf1);
			l.bpn.add(l.searchb);
			bsearch s=new bsearch();
			l.searchb.addActionListener(s);
			l.tf1.setBounds(275, 50, 200, 40);
			l.searchb.setBounds(505,50, 100, 40);
			l.bpn.add(l.bcb);
			l.bcb.setBounds(100, 0,165,25);

		}
		l.bpn.repaint();
		l.bpn.revalidate();
	}
}
