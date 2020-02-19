package MainSrc;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class alertDialog extends JFrame {
	private Container cPane;
	private JLabel lbl;
	
	public alertDialog(String str) {
		setSize(100,100);
		setTitle("caution");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cPane = getContentPane();
		lbl = new JLabel(str);
		cPane.add(lbl);
	}
}
