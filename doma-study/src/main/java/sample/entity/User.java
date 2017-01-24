package sample.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;
import sample.domain.PhoneNumber;

@Table(name = "usr")
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
public class User {
	@Id
	private Long id;
	private String name;
	private PhoneNumber phoneNumber;
}
