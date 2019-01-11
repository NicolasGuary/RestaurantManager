package facade;



import dao.AbstractDAOFactory;
import dao.MySQLDAOFactory;
import dao.UserDAO;
import model.User;

public class UserFacade {

	private static UserFacade INSTANCE;

	
	private User userConnected = new User();
	
	private UserDAO udao;
    
	private UserFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.udao = f.getUserDAO();
	}
	
    public boolean login(String login, String password){
    	if(udao.find(login, password)){
    		userConnected = new User(login, true);
    		return true;
    	} else {
            return false;
    	}
    }
    
    public static UserFacade getInstance(){
    	if (INSTANCE == null)
        {   
        	INSTANCE = new UserFacade(); 
        }
        
        return INSTANCE;
    }
    
    public User getConnectedUser(){
    	return INSTANCE.userConnected;
    }
}
