package dao;


public abstract class AbstractDAOFactory {

    /**
     * @return
     */
    public abstract UserDAO getUserDAO();

	/**
	 * @return
	 */
	public abstract ConsummableDAO getConsummableDAO();
	
	/**
	 * @return
	 */
	public abstract OrderDAO getOrderDAO();

	/**
	 * @return
	 */
	public abstract TypeDAO getTypeDAO();

	/**
	 * @return
	 */
	public abstract CategoryDAO getCategoryDAO();
	
	/**
	 * @return
	 */
	public abstract RoomDAO getRoomDAO();
	
	/**
	 * @return
	 */
	public abstract TableDAO getTableDAO();
}