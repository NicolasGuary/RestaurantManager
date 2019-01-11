package dao;

public class MySQLDAOFactory extends AbstractDAOFactory{

	public MySQLDAOFactory(){}
	
	@Override
	public UserDAOMySQL getUserDAO() {
		return new UserDAOMySQL();
	}
}