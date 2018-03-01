package com.example.junitstudy.calc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CalcuratorTest {

	@Test
	public void multiplyで3と4の乗算結果が正しいこと() {
		Calcurator calcurator = new Calcurator();
		assertThat(calcurator.multiply(3, 4), is(12));
	}

	@Test
	public void multiplyで12と12の乗算結果が正しいこと() {
		assertThat(Calcurator.multiply(12, 12), is(144));
	}

	@Test
	public void divideで1と2の除算結果が正しいこと() {
		assertThat(Calcurator.divide(1, 2), is(0.5f));
	}

	@Test(expected = IllegalArgumentException.class)
	public void divideで0除算した時にIllegalArgumentExceptionがスローされること() {
		Calcurator.divide(1, 0);
	}
}
