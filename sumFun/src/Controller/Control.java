package Controller;
import Model.Model;
import View.View;

public class Control {
	private Model model;
	private View view;
	
	/**
	 * Default constructor
	 */
	public Control(String gameType){
		this.model=new Model(gameType);
		this.view=new View();
	}
}
