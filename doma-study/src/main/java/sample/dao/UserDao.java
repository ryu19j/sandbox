package sample.dao;

import java.util.List;

import org.seasar.doma.BatchDelete;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import sample.config.SampleConfig;
import sample.domain.PhoneNumber;
import sample.entity.User;

@Dao(config = SampleConfig.class)
public interface UserDao {

	@Script
	void createTable();

	@Script
	void dropTable();

	@Select
	User selectByKey(Long id);

	@Select
	User selectByPhoneNumber(PhoneNumber phoneNumber);

	@Insert
	int insert(User user);

	@Update
	int update(User user);

	@Delete
	int delete(User user);

	@BatchInsert
	int[] batchInsert(List<User> users);

	@BatchUpdate
	int[] batchUpdate(List<User> users);

	@BatchDelete
	int[] batchDelete(List<User> users);
}
