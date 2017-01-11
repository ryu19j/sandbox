package sample.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import sample.config.SampleConfig;
import sample.entity.User;

public class UserDaoTest {

	private final UserDao dao = new UserDaoImpl();

	@Test
	public void testSelect() {

		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User insertUser = new User();
		tm.required(() -> {
			insertUser.id = 1L;
			insertUser.name = "test";
			dao.insert(insertUser);
		});

		tm.required(() -> {
			User user = dao.selectByKey(1L);
			assertEquals(user.name, "test");
		});
	}

	@Test
	public void testInsert() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user = new User();

		tm.required(() -> {
			user.id = 2L;
			user.name = "test2";
			dao.insert(user);
		});

		tm.required(() -> {
			User user1 = dao.selectByKey(2L);
			assertEquals(user1.name, "test2");
		});

	}

	@Test
	public void testUpdate() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user = new User();

		tm.required(() -> {
			user.id = 3L;
			user.name = "test3";
			dao.insert(user);
		});

		tm.required(() -> {
			user.id = 3L;
			user.name = "test3Update";
			dao.update(user);
		});

		tm.required(() -> {
			User user1 = dao.selectByKey(3L);
			assertEquals(user1.name, "test3Update");
		});
	}

	@Test
	public void testDelete() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();

		tm.required(() -> {
			user1.id = 4L;
			user1.name = "test4";
			dao.insert(user1);
		});

		User user2 = new User();
		tm.required(() -> {
			user2.id = 4L;
			user2.name = "test4";
			dao.delete(user2);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(4L);
			assertEquals(user3, null);
		});

	}

	@Test
	public void testBatchInsert() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		tm.required(() -> {
			user1.id = 5L;
			user1.name = "test5";
			user2.id = 6L;
			user2.name = "test6";
			dao.batchInsert(users);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(5L);
			assertEquals(user3.name, "test5");
			User user4 = dao.selectByKey(6L);
			assertEquals(user4.name, "test6");

		});
	}

	@Test
	public void testBatchUpdate() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		tm.required(() -> {
			user1.id = 7L;
			user1.name = "test7";
			user2.id = 8L;
			user2.name = "test8";
			dao.batchInsert(users);
		});

		tm.required(() -> {
			user1.id = 7L;
			user1.name = "test7update";
			user2.id = 8L;
			user2.name = "test8update";
			dao.batchUpdate(users);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(7L);
			assertEquals(user3.name, "test7update");
			User user4 = dao.selectByKey(8L);
			assertEquals(user4.name, "test8update");

		});

	}

	@Test
	public void testBatchUDelete() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		tm.required(() -> {
			user1.id = 9L;
			user1.name = "test9";
			user2.id = 10L;
			user2.name = "test10";
			dao.batchInsert(users);
		});

		tm.required(() -> {
			user1.id = 7L;
			user1.name = "test7update";
			user2.id = 8L;
			user2.name = "test8update";
			dao.batchDelete(users);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(7L);
			assertEquals(user3, null);
			User user4 = dao.selectByKey(8L);
			assertEquals(user4, null);
		});
	}
}
