package sample.dao;

import static org.junit.Assert.assertEquals;

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
}
