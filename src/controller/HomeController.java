package controller;

import facade.UserFacade;
import ui.Router;

public class HomeController {

	private Router router = Router.getInstance() ;
	private UserFacade uf = UserFacade.getInstance();
	
	
	public void handleConsReadAll(){
		router.activate("login");
	}

	public void handleReadConsummable(){
		router.activate("readAllConsummables");
	}

	public void handleReadOrder(){
		router.activate("readAllOrders");
	}
	
	public void handleHome(){
		router.activate("home");
	}
}
