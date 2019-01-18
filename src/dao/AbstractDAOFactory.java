/*
 * @author Nicolas GUARY
 */
package dao;


/**
 * A factory for creating AbstractDAO objects.
 */
public abstract class AbstractDAOFactory {

    /**
     * Gets the user DAO.
     *
     * @return the user DAO
     */
    public abstract UserDAO getUserDAO();

	/**
	 * Gets the consummable DAO.
	 *
	 * @return the consummable DAO
	 */
	public abstract ConsummableDAO getConsummableDAO();
	
	/**
	 * Gets the order DAO.
	 *
	 * @return the order DAO
	 */
	public abstract OrderDAO getOrderDAO();

	/**
	 * Gets the type DAO.
	 *
	 * @return the type DAO
	 */
	public abstract TypeDAO getTypeDAO();

	/**
	 * Gets the category DAO.
	 *
	 * @return the category DAO
	 */
	public abstract CategoryDAO getCategoryDAO();
	
	/**
	 * Gets the room DAO.
	 *
	 * @return the room DAO
	 */
	public abstract RoomDAO getRoomDAO();
	
	/**
	 * Gets the table DAO.
	 *
	 * @return the table DAO
	 */
	public abstract TableDAO getTableDAO();
}