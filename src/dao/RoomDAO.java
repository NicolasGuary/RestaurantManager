package dao;

import java.util.ArrayList;

import model.Room;

public abstract class RoomDAO extends DAO<Room> {

	public abstract ArrayList<Room> readAll();
	
}
