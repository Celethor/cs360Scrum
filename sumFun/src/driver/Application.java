package driver;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import view.SplashGui;


public class Application {
	
	public static void main(String []args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			//UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new SplashGui();
	}
	
	
}
