package sample.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Table(name = "usr")
@Entity
public class User {
	@Id
	public Long id;
	public String name;
}
