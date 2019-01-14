package controller;

import ui.Router;

public class HomeController {

	private Router router = Router.getInstance() ;
	

	public void handleReadConsummable(){
		router.activate("readAllConsummables");
	}

	public void handleReadOrder(){
		router.activate("readAllOrders");
	}
	
	public void handleReadRoom(){
		router.activate("readAllRooms");
	}
}
