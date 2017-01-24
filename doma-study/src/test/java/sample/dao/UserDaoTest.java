package sample.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import sample.config.SampleConfig;
import sample.domain.PhoneNumber;
import sample.entity.User;

public class UserDaoTest {

	private final UserDao dao = new UserDaoImpl();

	@Before
	public void setup() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		tm.required(() -> {
			dao.dropTable();
			dao.createTable();
		});
	}

	@Test
	public void testSelect() {

		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User insertUser = new User();
		tm.required(() -> {
			insertUser.setId(1L);
			insertUser.setName("test");
			dao.insert(insertUser);
		});

		tm.required(() -> {
			User user = dao.selectByKey(1L);
			assertEquals(user.getName(), "test");
		});
	}

	@Test
	public void testInsert() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user = new User();

		tm.required(() -> {
			user.setId(2L);
			user.setName("test2");
			dao.insert(user);
		});

		tm.required(() -> {
			User user1 = dao.selectByKey(2L);
			assertEquals(user1.getName(), "test2");
		});

	}

	@Test
	public void testUpdate() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user = new User();

		tm.required(() -> {
			user.setId(3L);
			user.setName("test3");
			dao.insert(user);
		});

		tm.required(() -> {
			user.setId(3L);
			user.setName("test3Update");
			dao.update(user);
		});

		tm.required(() -> {
			User user1 = dao.selectByKey(3L);
			assertEquals(user1.getName(), "test3Update");
		});
	}

	@Test
	public void testDelete() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();

		tm.required(() -> {
			user1.setId(4L);
			user1.setName("test4");
			dao.insert(user1);
		});

		User user2 = new User();
		tm.required(() -> {
			user2.setId(4L);
			user2.setName("test4");
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
			user1.setId(5L);
			user1.setName("test5");
			user2.setId(6L);
			user2.setName("test6");
			dao.batchInsert(users);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(5L);
			assertEquals(user3.getName(), "test5");
			User user4 = dao.selectByKey(6L);
			assertEquals(user4.getName(), "test6");

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
			user1.setId(7L);
			user1.setName("test7");
			user2.setId(8L);
			user2.setName("test8");
			dao.batchInsert(users);
		});

		tm.required(() -> {
			user1.setId(7L);
			user1.setName("test7update");
			user2.setId(8L);
			user2.setName("test8update");
			dao.batchUpdate(users);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(7L);
			assertEquals(user3.getName(), "test7update");
			User user4 = dao.selectByKey(8L);
			assertEquals(user4.getName(), "test8update");

		});

	}

	@Test
	public void testBatchDelete() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		tm.required(() -> {
			user1.setId(9L);
			user1.setName("test9");
			user2.setId(10L);
			user2.setName("test10");
			dao.batchInsert(users);
		});

		tm.required(() -> {
			user1.setId(7L);
			user1.setName("test7update");
			user2.setId(8L);
			user2.setName("test8update");
			dao.batchDelete(users);
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(7L);
			assertEquals(user3, null);
			User user4 = dao.selectByKey(8L);
			assertEquals(user4, null);
		});
	}

	@Test
	public void testSavePoint() {
		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		tm.required(() -> {
			user1.setId(11L);
			user1.setName("test11");
			user2.setId(12L);
			user2.setName("test12");
			dao.batchInsert(users);

			tm.setSavepoint("save");

			dao.delete(user1);

			tm.rollback("save");
		});

		tm.required(() -> {
			User user3 = dao.selectByKey(11L);
			assertEquals(user3.getName(), "test11");
			User user4 = dao.selectByKey(12L);
			assertEquals(user4.getName(), "test12");
		});
	}

	@Test
	public void testSelectForDomain() {

		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		User insertUser = new User();
		tm.required(() -> {
			insertUser.setId(13L);
			insertUser.setName("test13");
			insertUser.setPhoneNumber(new PhoneNumber("0123-45-6789"));
			dao.insert(insertUser);
		});

		tm.required(() -> {
			PhoneNumber number = new PhoneNumber("0123-45-6789");
			User user = dao.selectByPhoneNumber(number);
			assertEquals(user.getPhoneNumber().getValue(), "0123-45-6789");
		});
	}
}
