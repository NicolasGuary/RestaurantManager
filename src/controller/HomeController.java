package controller;

import ui.Router;

public class HomeController {

	private Router router = Router.getInstance() ;
	
	public void handleConsReadAll(){
		System.out.println(router);
		router.activate("index2");
	}
}
