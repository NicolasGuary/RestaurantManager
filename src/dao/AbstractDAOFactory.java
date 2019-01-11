package dao;


public abstract class AbstractDAOFactory {

    public abstract UserDAO getUserDAO();

	public abstract ConsummableDAO getConsummableDAO();
}