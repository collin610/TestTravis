package javaFX;

import javafx.scene.control.TextField;

public abstract class Operation 
{
	Operation nextOp;
	TextField answer;
	long old;
	
	public Operation(Operation nextOp, TextField answer, long old)
	{
		this.nextOp = nextOp;
		this.answer = answer;
		this.old = old;
	}
	
	public Operation getNextOp() 
	{
		return nextOp;
	}
	
	public TextField getAnswer()
	{
		return answer;
	}
	
	public long getOld()
	{
		return old;
	}
	
	// Returns old value
	public long handle() 
	{
		FirstFX.pressedOp = true;
		long temp = Long.valueOf(answer.getText());
		long next;
		if(nextOp != null)
		{
			next = nextOp.doOp(old, temp);
			answer.setText(Long.toString(next));
			return next;
		}
		else
			return temp;
	}

	
	public abstract long doOp(long old, long temp);
	
}
