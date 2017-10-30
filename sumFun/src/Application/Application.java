package Application;
import Model.Model;
import View.GUI;


public class Application {
	private Model model;
	//private View view;
	private GUI gui;
	/**
	 * Default constructor
	 */
	public Application(String gameType){
		this.model=new Model(gameType);
		//this.view=new View(this.model);
		this.gui=new GUI(this.model);
	}
	public static void main(String []args) {
		new Application("untimed");
	}
	
	
}
