package javaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class FirstFX extends Application {
	
	private TextField answer;
	private long old = 0;
	private GridPane grid;
	private Button plus, minus, mult, div, equals, clear;
	private Operation nextOp;
	public static boolean pressedOp = false;
	
	int i = 0;
	
	
	public static void main(String[] args) {
//		 TODO Auto-generated method stub
		launch(args);
	}

	public Button createNumberButton(final String text, int col, int row)
	{
		Button temp = new Button(text);
		temp.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				changeField(text);
			}
		});
		grid.add(temp, col, row);
		return temp;
	}
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		
		stage.setTitle("JavaFX Calculator");
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		answer = new TextField();
		answer.setEditable(false);
		grid.add(answer, 0, 0, 5, 1);
		
		createNumberButton("1", 0, 1);
		createNumberButton("2", 1, 1);
		createNumberButton("3", 2, 1);
		createNumberButton("4", 0, 2);
		createNumberButton("5", 1, 2);
		createNumberButton("6", 2, 2);
		createNumberButton("7", 0, 3);
		createNumberButton("8", 1, 3);
		createNumberButton("9", 2, 3);
		createNumberButton("0", 1, 4);
		
		
		plus = new Button("+");
		plus.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				nextOp = new Plus(nextOp, answer, old);
				old = nextOp.handle();
			}
		});
		grid.add(plus, 4, 4);
		
		minus = new Button("-");
		minus.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				nextOp = new Minus(nextOp, answer, old);
				old = nextOp.handle();
			}
		});
		grid.add(minus, 4, 3);
		
		mult = new Button("x");
		mult.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				nextOp = new Mult(nextOp, answer, old);
				old = nextOp.handle();
			}
		});
		grid.add(mult, 4, 2);
		
		div = new Button("/");
		div.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				nextOp = new Div(nextOp, answer, old);
				old = nextOp.handle();
			}
		});
		grid.add(div, 4, 1);
		
		equals = new Button("=");
		equals.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				if(nextOp == null)
					return;
				String tempStr = answer.getText();
				long temp = Long.valueOf(tempStr);
				long newAnswer = nextOp.doOp(old, temp);
				answer.setText(Long.toString(newAnswer));
				old = 0;
				nextOp = null;
			}
		});
		grid.add(equals, 2, 4);
		
		clear = new Button("C");
		clear.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				nextOp = null;
				answer.setText("");
				old = 0;
				pressedOp = false;
			}
		});
		grid.add(clear, 0, 4);
		
		
		Scene scene = new Scene(grid, 300, 300);
		stage.setScene(scene);
	
		stage.show();
	}
	
	public void changeField(String toAdd)
	{
		String tempStr = answer.getText();
		if(pressedOp)
		{
			old = Long.valueOf(tempStr);
			answer.setText(toAdd);
			pressedOp = false;
		}
		else
		{
			answer.setText(tempStr + toAdd);
		}
	}
}
