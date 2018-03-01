package com.example.junitstudy.calc;

public class Calcurator {

	public static int multiply(int x, int y) {
		return x * y;
	}

	public static float divide(int x, int y) {
		if (y == 0) {
			throw new IllegalArgumentException();
		}
		return (float) x / (float) y;
	}
}
