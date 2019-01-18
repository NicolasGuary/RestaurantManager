package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import model.Order;
import model.Table;
import model.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.OrderDAO;
import dao.UserDAO;
import facade.OrderFacade;
import facade.UserFacade;

public class OrderTests {
	
	private static OrderFacade of = OrderFacade.getInstance();
	private static OrderDAO odao = of.getOdao();
	private static ArrayList<Order> idOrdersCreated = new ArrayList<Order>();
	
	
	
	@Test
	public void testFind() {
		// We assume that delete and create work for the order class
		Order order1 = new Order(32, 23, 5, true, null, new Table(23,12,3, 6, 9, false));
		of.create(order1);
		assertTrue("User upgraded", odao.find(32) == order1);
		
		Order order2 = new Order(32, 23, 5, true, null, new Table(23,12,3, 6, 9, false));
		of.create(order2);
		assertTrue("User upgraded", odao.find(32) == order2);
		
		idOrdersCreated.add(order1);
		idOrdersCreated.add(order2);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// We delete every user we have created since the beginning of the test
				for (Iterator<Order> iter = idOrdersCreated.iterator(); iter.hasNext();) {
					of.delete(iter.next());
				}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
