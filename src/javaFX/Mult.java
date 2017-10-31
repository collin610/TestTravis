package javaFX;

import javafx.scene.control.TextField;

public class Mult extends Operation{

	public Mult(Operation nextOp, TextField answer, long old) 
	{
		super(nextOp, answer, old);
		// TODO Auto-generated constructor stub
	}

	public long doOp(long old, long temp)
	{
		return old * temp;
	}
	
}
