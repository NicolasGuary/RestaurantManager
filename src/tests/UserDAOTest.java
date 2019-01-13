package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dao.UserDAO;
import facade.UserFacade;

import jdbc.ConnectionToDB;
import model.User;

class UserDAOTest {
	
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private static UserFacade uf = UserFacade.getInstance();
	private static UserDAO udao = uf.getudao();
	
	// this array will save all of the accounts created during the test 
	// in order to delete them after the test.
	private static ArrayList<Integer> idUsersCreated = new ArrayList<Integer>();
	
	@Test
	public void createUser() {
    	ResultSet resultSet;
    	ArrayList<User> result = new ArrayList<User>();
			try {
				resultSet = ConnectionToDB.getInstance().executeQuery("Insert into course (idUser,login,password,firstName,lastname,isSuperAdmin,isConnected) values"
			            + " ('"
			            + 045
			            + "', '"
			            + "testUser"
			            + "', '"
			            + "test"
			            + "', '"
			            + "Jean"
			            + "', '"
			            + "Dupond"
			            + "', '"
			            + false
			            + "', '"
			            + true
			            + "')");
				while(resultSet.next()){
					User tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin") );
					result.add(tmp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            close();
	        }
		} 
	
	@Test
	public void testAddPrivilege() {
		// Normale situation, the user to be upgraded is not superAdmin
		int id1 = uf.create("Jacques",  "123",  "Jacques",  "Dupond",  false, true).getIdUser();
		uf.addPrivilege(id1);
		assertTrue("User upgraded", udao.find(id1).isSuperAdmin());
		
		// The user to be upgraded is superAdmin
		int id2 = uf.create("Jacques",  "123",  "Jacques",  "Dupond",  true, true).getIdUser();
		uf.addPrivilege(id2);
		assertTrue("User already superAdmin", udao.find(id2).isSuperAdmin());
		
		//The user that want to upgrade someone is not superAdmin
		uf.getConnectedUser().setSuperAdmin(false);
		int id3 = uf.create("Jacques",  "123",  "Jacques",  "Dupond",  false, true).getIdUser();
		uf.addPrivilege(id3);
		assertFalse("If user that tries to upgrade another account is not superAdmin", udao.find(id3).isSuperAdmin());
		
		uf.getConnectedUser().setSuperAdmin(true);
		
		idUsersCreated.add(id1);
		idUsersCreated.add(id2);
		idUsersCreated.add(id3);
	}
	
	@Test
	public void testLogout() {
		// If the user is logged, it has to set the isConnected attribute to the false value
		uf.logout();
		assertFalse("normal logout", uf.getConnectedUser().isConnected() );
		
		// If the user is already logged out it has to not change the attribute isConnected
		uf.logout();
		assertFalse("normal logout", uf.getConnectedUser().isConnected() );
		
		uf.login("Quentin", "123");
	}

	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		uf.login("Quentin", "123");
		

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// We delete every user we have created since the beginning of the test
		for (Iterator<Integer> iter = idUsersCreated.iterator(); iter.hasNext();) {
			uf.delete(iter.next());
		}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
