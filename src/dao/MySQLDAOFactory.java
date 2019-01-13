package dao;

public class MySQLDAOFactory extends AbstractDAOFactory{

	public MySQLDAOFactory(){}
	
	@Override
	public UserDAOMySQL getUserDAO() {
		return new UserDAOMySQL();
	}
	
	@Override
	public ConsummableDAOMySQL getConsummableDAO() {
		return new ConsummableDAOMySQL();
	}

	@Override
	public OrderDAO getOrderDAO() {
		return new OrderDAOMySQL();
	}
	
	@Override
	public TypeDAO getTypeDAO() {
		return new TypeDAOMySQL();
	}
	
	@Override
	public CategoryDAO getCategoryDAO() {
		return new CategoryDAOMySQL();
	}
	
	@Override
	public RoomDAO getRoomDAO() {
		return new RoomDAOMySQL();
	}
	
	@Override
	public TableDAO getTableDAO() {
		return new TableDAOMySQL();
	}
}