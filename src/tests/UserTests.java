package tests;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import dao.UserDAO;
import facade.UserFacade;

class UserDAOTest {
	
	
	private static UserFacade uf = UserFacade.getInstance();
	private static UserDAO udao = uf.getudao();
	
	// this array will save all of the accounts created during the test 
	// in order to delete them after the test.
	private static ArrayList<Integer> idUsersCreated = new ArrayList<Integer>();
	
	@org.junit.Test
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
	
	@org.junit.Test
	public void testLogout() {
		// If the user is logged, it has to set the isConnected attribute to the false value
		uf.logout();
		assertFalse("normal logout", uf.getConnectedUser().isConnected() );
		
		// If the user is already logged out it has to not change the attribute isConnected
		uf.logout();
		assertFalse("normal logout", uf.getConnectedUser().isConnected() );
		
		uf.login("Quentin", "123");
	}

	
	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		uf.login("Quentin", "123");
		
	}

	@After
	static void tearDownAfterClass() throws Exception {
		// We delete every user we have created since the beginning of the test
		for (Iterator<Integer> iter = idUsersCreated.iterator(); iter.hasNext();) {
			uf.delete(iter.next());
		}
	}

	@Before
	void setUp() throws Exception {
	}

	@AfterClass
	void tearDown() throws Exception {
		uf.logout();
	}

	@org.junit.Test
	void test() {
		fail("Not yet implemented");
	}

}