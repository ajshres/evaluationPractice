package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreatePostfix {

	public CreatePostfix() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String expression = "(A+B)*(C+D)^D*E";
		infixtopostfix postfix = new infixtopostfix(expression);
		String result = postfix.convert();
		ArrayList<String> postfixStackstack = postfix.getPostfixStack();
		postfix.askForInputs();
		System.out.println( "Result : " + postfix.evaluate() );
	}
	

}
