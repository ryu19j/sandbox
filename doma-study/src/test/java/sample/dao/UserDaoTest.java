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
}
