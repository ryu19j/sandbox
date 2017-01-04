package sample.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import sample.config.SampleConfig;
import sample.entity.User;

@Dao(config = SampleConfig.class)
public interface UserDao {

	@Select
	User selectByKey(Long id);

	@Insert(sqlFile = true)
	int insert(User user);

}
