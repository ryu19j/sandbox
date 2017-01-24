package sample.domain;

import org.seasar.doma.Domain;

import lombok.Value;

@Domain(valueType = String.class)
@Value
public class PhoneNumber {

	private final String value;

	public PhoneNumber(String value) {
		this.value = value;
	}

}
