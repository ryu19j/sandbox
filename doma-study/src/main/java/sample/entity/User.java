package sample.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class User {
	@Id
	public Long id;
	public String name;
}
