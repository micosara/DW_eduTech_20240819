package com.spring.aop;

//@Component
//@Aspect
public class Advice {

	//@After("execution(public * com.spring.aop..밥먹기*(..))")	
	public void chikachika() {

		System.out.println("열심히 양치질을 합니다.");
	}
}
