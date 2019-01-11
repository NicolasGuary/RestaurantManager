package controller;

import java.util.ArrayList;

import model.Consummable;
import facade.ConsummableFacade;
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
}
