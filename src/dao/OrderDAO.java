package dao;

import java.util.ArrayList;

import model.Consummable;
import model.Order;
import model.Table;

public abstract class OrderDAO extends DAO<Order> {

	public abstract ArrayList<Order> readAll(boolean paid);
	public abstract Order create(float discount, float price, boolean paid, String note,  ArrayList<Consummable> consummablesOrder, Table table);
	
}
