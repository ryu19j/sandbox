package sample.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import sample.config.SampleConfig;
import sample.entity.User;

public class UserDaoTest {

	private final UserDao dao = new UserDaoImpl();

	@Test
	public void test() {

		TransactionManager tm = SampleConfig.singleton().getTransactionManager();
		tm.required(() -> {
			User user = dao.selectByKey(1L);
			assertEquals(user.name, "test");
		});
	}
}
