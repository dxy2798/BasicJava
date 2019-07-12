package com.atguigu.javase.lesson12;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator{

	@Override
	public int add(int i, int j) {
		int result = i + j;
		return result;
	}

	@Override
	public int sub(int i, int j) {
		int result = i - j;
		return result;
	}

	@Override
	public void mul(int i, int j) {
		int result = i * j;
		System.out.println(result);
	}

	@Override
	public void div(int i, int j) {
		int result = i / j;
		System.out.println(result);
	}

}
