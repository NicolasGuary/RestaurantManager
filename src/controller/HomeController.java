/*
 * 
 */
package controller;

import ui.Router;

/**
 * The Class HomeController.
 */
public class HomeController {

	/** The router. */
	private Router router = Router.getInstance() ;
	

	/**
	 * Handle read consummable.
	 */
	public void handleReadConsummable(){
		router.activate("readAllConsummables");
	}

	/**
	 * Handle read order.
	 */
	public void handleReadOrder(){
		router.activate("readAllOrders");
	}
	
	/**
	 * Handle read room.
	 */
	public void handleReadRoom(){
		router.activate("readAllRooms");
	}
}
