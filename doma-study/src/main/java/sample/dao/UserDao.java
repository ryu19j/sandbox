package sample.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import sample.config.SampleConfig;

@Dao(config = SampleConfig.class)
public interface UserDao {

	@Select
	int selectByKey(Long id);

}
