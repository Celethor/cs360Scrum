package View;

import Model.Model;

public class View {
	private GUI gui;
	
	public View(Model model){
		gui=new GUI(model);
	}
}
