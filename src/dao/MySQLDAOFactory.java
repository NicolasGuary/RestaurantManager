/*
 * @author Nicolas GUARY
 */
package dao;

/**
 * A factory for creating MySQLDAO objects.
 */
public class MySQLDAOFactory extends AbstractDAOFactory{

	/**
	 * Instantiates a new my SQLDAO factory.
	 */
	public MySQLDAOFactory(){}
	
	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getUserDAO()
	 */
	@Override
	public UserDAOMySQL getUserDAO() {
		return new UserDAOMySQL();
	}
	
	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getConsummableDAO()
	 */
	@Override
	public ConsummableDAOMySQL getConsummableDAO() {
		return new ConsummableDAOMySQL();
	}

	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getOrderDAO()
	 */
	@Override
	public OrderDAO getOrderDAO() {
		return new OrderDAOMySQL();
	}
	
	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getTypeDAO()
	 */
	@Override
	public TypeDAO getTypeDAO() {
		return new TypeDAOMySQL();
	}
	
	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getCategoryDAO()
	 */
	@Override
	public CategoryDAO getCategoryDAO() {
		return new CategoryDAOMySQL();
	}
	
	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getRoomDAO()
	 */
	@Override
	public RoomDAO getRoomDAO() {
		return new RoomDAOMySQL();
	}
	
	/* (non-Javadoc)
	 * @see dao.AbstractDAOFactory#getTableDAO()
	 */
	@Override
	public TableDAO getTableDAO() {
		return new TableDAOMySQL();
	}
}