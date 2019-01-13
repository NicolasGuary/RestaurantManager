package dao;


public abstract class AbstractDAOFactory {

    public abstract UserDAO getUserDAO();

	public abstract ConsummableDAO getConsummableDAO();
	
	public abstract OrderDAO getOrderDAO();

	public abstract TypeDAO getTypeDAO();

	public abstract CategoryDAO getCategoryDAO();
	
	public abstract RoomDAO getRoomDAO();
}