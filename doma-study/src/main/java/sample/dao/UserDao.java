package sample.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import sample.config.SampleConfig;
import sample.entity.User;

@Dao(config = SampleConfig.class)
public interface UserDao {

	@Select
	User selectByKey(Long id);

	@Insert(sqlFile = true)
	int insert(User user);

	@Update(sqlFile = true)
	int update(User user);

	@Delete(sqlFile = true)
	int delete(User user);

	@BatchInsert(sqlFile = true)
	int[] batchInsert(List<User> users);

	@BatchUpdate(sqlFile = true)
	int[] batchUpdate(List<User> users);
}
