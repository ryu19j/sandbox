package sample.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import sample.domain.PhoneNumber;

@Table(name = "usr")
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class User {
	@Id
	public Long id;
	public String name;
	public PhoneNumber phoneNumber;
}
