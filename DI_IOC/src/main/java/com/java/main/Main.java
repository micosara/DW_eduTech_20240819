package com.java.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.java.calc.MyCalculator;

public class Main {
	

	public static void main(String[] args) {
//		
//		AnotherSumOperator anotherSum =new AnotherSumOperator(); 
//		SumOperator sum = new SumOperator();
//		DivideOperator divide = new DivideOperator();
//		MultiOperator multi = new MultiOperator();
//		MinusOperator minus = new MinusOperator();		
//		
//		AbstractCalculator calc = new MyCalculator();
//		calc.setSum(anotherSum);
//		calc.setDivde(divide);
//		calc.setMulti(multi);
//		calc.setMinus(minus);
		
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/java/context/application-context.xml");
		MyCalculator calc = (MyCalculator)ctx.getBean("calc");
		
		System.out.println(calc.sum(1, 2));
	}

}






