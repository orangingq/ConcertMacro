package MainSrc;

import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class openImageFile {
	private JFrame IFrame;
	private ImageIcon image;
	private JLabel lbl;
	
	public openImageFile(FullScreenCapture screencapture, MouseListener listener) throws FileNotFoundException {
		IFrame = new JFrame("Ä¸Ã³ È­¸é");
		image = new ImageIcon(screencapture.getFilePath());
		lbl = new JLabel(image);
		IFrame.add(lbl);
		IFrame.pack();
		IFrame.setVisible(true);
		IFrame.setDefaultCloseOperation(IFrame.DISPOSE_ON_CLOSE);;
		System.out.println("file is opened.. path: "+ screencapture.getFilePath());
		lbl.addMouseListener(listener);
	}
	
	public void setFrameTxt(String s) {
		IFrame.setTitle(s);
	}
}
