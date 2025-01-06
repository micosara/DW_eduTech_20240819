package com.java.calc;

import com.java.operator.DivideOperator;
import com.java.operator.MinusOperator;
import com.java.operator.MultiOperator;
import com.java.operator.SumOperator;

public abstract class AbstractCalculator {
	
	protected SumOperator sum;
	protected MinusOperator minus;
	protected DivideOperator divde;
	protected MultiOperator multi;
	
	
	public abstract int sum(int a, int b);
	public abstract int minus(int a, int b);
	public abstract int multi(int a, int b);
	public abstract int divide(int a, int b);
	
	
	public SumOperator getSum() {
		return sum;
	}
	public void setSum(SumOperator sum) {
		this.sum = sum;
	}
	public MinusOperator getMinus() {
		return minus;
	}
	public void setMinus(MinusOperator minus) {
		this.minus = minus;
	}
	public DivideOperator getDivde() {
		return divde;
	}
	public void setDivde(DivideOperator divde) {
		this.divde = divde;
	}
	public MultiOperator getMulti() {
		return multi;
	}
	public void setMulti(MultiOperator multi) {
		this.multi = multi;
	}

	
	
}
