package Controller;
import Model.Model;
import View.View;

public class Application {
	private Model model;
	private View view;
	
	/**
	 * Default constructor
	 */
	public Application(String gameType){
		this.model=new Model(gameType);
		this.view=new View(this.model);
		
	}
	public static void main(String []args) {
		new Application("untimed");
	}
	
	
}
