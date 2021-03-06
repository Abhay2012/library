import java.awt.Color;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/* Action after clicking on home button */
public class home implements ActionListener{
	public void actionPerformed(ActionEvent e)
	{
		Lib l=new Lib();
		l.mp.removeAll();
		l.spn.removeAll();
		l.bpn.removeAll();
		JLabel t=new JLabel("  Welcome to Library ");
		JButton stsearch= new JButton("Search");
		JButton bksearch= new JButton("Search");		
		
		l.mp.add(t);
		t.setBounds(425,20,150,30);
		
		l.f.setTitle("Library Home");
		l.mp.add(l.spn);
		l.spn.setVisible(true);
		l.spn.setBackground(Color.WHITE);
		l.spn.setLayout(null);
		l.spn.setBounds(140,90, 630, 160);
		
		l.mp.add(l.bpn);
		l.bpn.setVisible(true);
		l.bpn.setBackground(Color.WHITE);
		l.bpn.setLayout(null);
		l.bpn.setBounds(140,250, 630, 160);

		
		l.spn.add(l.la1);
		l.spn.add(l.tf);
		l.spn.add(stsearch);
		ssearch s=new ssearch();
		stsearch.addActionListener(s);
		stsearch.setBackground(Color.decode("#930101"));
		stsearch.setForeground(Color.decode("#ffffff"));
		stsearch.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
		l.la1.setBounds(100,50,200,40);
		l.tf.setBounds(275, 50, 200, 40);
		stsearch.setBounds(505,50, 100, 40);
		
		l.bpn.add(l.lab1);
		l.bpn.add(l.tf1);
		l.bpn.add(bksearch);
		bsearch bs=new bsearch();
		bksearch.addActionListener(bs);
		bksearch.setBackground(Color.decode("#930101"));
		bksearch.setForeground(Color.decode("#ffffff"));
		bksearch.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		l.lab1.setBounds(100,50,200,40);
		l.tf1.setBounds(275, 50, 200, 40);
		bksearch.setBounds(505, 50, 100, 40);
		
		l.spn.add(l.scb);
		l.scb.setBounds(100, 0,165,25);
		
		l.bpn.add(l.bcb);
		l.bcb.setBounds(100,0,165,25);
		
		scombo sc=new scombo();
		l.scb.addActionListener(sc);
		bcombo bc=new bcombo();
		l.bcb.addActionListener(bc);

		l.mp.revalidate();
		l.mp.repaint();
		l.spn.revalidate();
		l.spn.repaint();
		l.bpn.revalidate();
		l.bpn.repaint();

	}
}
