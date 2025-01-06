package com.java.calc;

public class MyCalculator extends AbstractCalculator {

	@Override
	public int sum(int a, int b) {
		return sum.calc(a, b)+10;
	}

	@Override
	public int minus(int a, int b) {
		return minus.calc(a, b)-10;
	}

	@Override
	public int multi(int a, int b) {
		return multi.calc(a, b)*10;
	}

	@Override
	public int divide(int a, int b) {
		return divde.calc(a, b)/10;
	}

}
