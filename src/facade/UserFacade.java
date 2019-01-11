package facade;

import model.User;

public class UserFacade {

	private User user = new User();
    
    public boolean login(String login, String password){
    	this.user = user.login(login, password);
    	return this.user.isConnected();
    }
}
