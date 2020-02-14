package MainSrc;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FullScreenCapture {
	private Date date;
	private SimpleDateFormat sdf;
	private String dateStr;
	private Rectangle rect;
	private Robot robot;
	private BufferedImage bufferedImage;
	private File file;
	private String filePath;
	
	public FullScreenCapture(){
		try {
			date = new Date();
			sdf = new SimpleDateFormat("yyMMdd_HHmmss");
			dateStr = sdf.format(date);
			robot = new Robot();
			rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			bufferedImage = robot.createScreenCapture(rect);
			file = new File("screen-capture_"+dateStr+".png");
			boolean status = ImageIO.write(bufferedImage, "png", file);	
			filePath = file.getAbsolutePath();
			System.out.println("Screen Captured? " + status + " ..File Path: " + filePath);
		} catch(AWTException | IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	
	
}
