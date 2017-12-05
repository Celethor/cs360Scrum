package driver;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.SplashGui;


public class Application {
	
	public static void main(String []args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			//UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new SplashGui();
	}
	
	
}
