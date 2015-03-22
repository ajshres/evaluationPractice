/**
 * 
 */
package library;

import java.util.*;

import domains.Add;
import domains.Division;
import domains.Multiply;
import domains.Operand;
import domains.PowerOf;
import domains.Substract;

/**
 * @author Ajay Shrestha
 * Created Date: 13th March, 2015
 * Converts Infix expression to postfix expression
 * Throws Null Point Exception if expression is not set
 */
public class infixtopostfix {
	String expression=null;
	private Map<String,Integer> order;
	private Map<String,Float> inputs;
//	private String[] operators = { "+", "-", "*", "/", "^"};
	ArrayList<String> operators = new ArrayList<String>();
	ArrayList<String> stack = new ArrayList<String>();
	ArrayList<String> postfixStackstack = new ArrayList<String>();
	String postfix = "";
	/**
	 * Constructors
	 */
	public infixtopostfix(){
		
	}
	
	/**
	 * 
	 * @param expression => Arithmetic Expression
	 */
	public infixtopostfix(String expression) {
		this.expression = expression;
		this.createOrder();
		this.createOperators();
	}
	public String convert(){
		isValidExpression();
		char lastElement=' ';
		String tempVariable = this.expression;
		for (int j = 0; j < tempVariable.length(); j++) {
	         char ch = tempVariable.charAt(j);
	         switch(ch){
	         	case '(':
         			if(j>0 && !this.operators.contains(String.valueOf(lastElement)) && lastElement!='('){
         				this.pushIntoStack('*');
	         		}
	         		this.pushIntoStack(ch);
	         		break;
	         	case ')':
	         		while(this.stack.size()>0){
	         			char popValue = this.popFromStack();
	         			if(popValue=='('){
	         				break;
	         			}
	         			this.appendIntoPostfixVariable(popValue);
	         		}
	         		break;
	         	case '+':
	         	case '-':
	         	case '*':
	         	case '/':
	         	case '^':
	         		if(this.stack.size()==0||lastElement=='('){
	         			this.pushIntoStack(ch);
	         		} else{
	         			this.checkPrecedentInStack(ch,1);
	         		}
	         		break;
	         	default:
	         		this.appendIntoPostfixVariable(ch);
//	         		System.out.print(this.postfixStackstack);
//	         		System.out.print(this.postfixStackstack.contains(ch));
	         		if(!this.postfixStackstack.contains(String.valueOf(ch))){
	         			this.postfixStackstack.add(String.valueOf(ch));
	         		}
	         		break;
	         }
	         lastElement = ch;
//	         System.out.println("value "+this.postfix);
//	         System.out.println("stack "+this.stack);
	         
		}
		while(this.stack.size()>0){
  			this.appendIntoPostfixVariable(this.popFromStack());
  		}
		return this.postfix;
	}
	private void createOrder(){
		this.order = new HashMap<String,Integer>();
		order.put("-", 1);
		order.put("+", 2);
		order.put("*", 3);
		order.put("/", 4);
		order.put("^",5);
	}
	private Boolean isValidExpression(){
//		if(this.expression==null) 
//			throw new NullPointerException();
		return true;
	}
	private void createOperators(){
		this.operators.add("+");
		this.operators.add("-");
		this.operators.add("*");
		this.operators.add("/");
		this.operators.add("^");
	}
	private void checkPrecedentInStack(char ch,int index){
		char checkLastElement=this.lastElementOnStack();
		if(this.stack.size()>0 && checkLastElement!='('){
//			System.out.println(ch+" "+this.order.get(String.valueOf(ch)));
//			System.out.println(checkLastElement+" "+this.order.get(String.valueOf(checkLastElement)));
			if(this.order.get(String.valueOf(ch))<this.order.get(String.valueOf(checkLastElement))){
				popFromStack();
				checkPrecedentInStack(ch,index++);
				this.appendIntoPostfixVariable(checkLastElement);
			} else{
				pushIntoStack(ch);
				
			}
		} else{
			pushIntoStack(ch);
		}
		return;
	}
	
	public String getPostfixExpression(){
		return this.postfix;
	}
	
	public ArrayList<String> getPostfixStack(){
		return this.postfixStackstack;
	}
	
	public void pushIntoStack(char operand){
		this.stack.add(String.valueOf(operand));
	}
	
	public char popFromStack(){
		String popElement = "";
		int lastIndex = this.stack.size()-1;
		if(!stack.isEmpty()){
			popElement = this.stack.get(lastIndex);
			this.stack.remove(lastIndex);
			return popElement.charAt(0);
		}
		return ' ';
	}
	
	public char lastElementOnStack(){
		String popElement = "";
		int lastIndex = this.stack.size()-1;
		if(!stack.isEmpty()){
			popElement = this.stack.get(lastIndex);
			return popElement.charAt(0);
		}
		return ' ';
	}
	
	public void appendIntoPostfixVariable(char ch){
		this.postfix += ch;
	}
	
	public float evaluate(){
		String tempVariable = this.postfix;
		Operand operand;
		Float temp1,temp2;
		Deque<Float> variableStack = new ArrayDeque<Float>();
		for (int j = 0; j < tempVariable.length(); j++) {
	         char ch = tempVariable.charAt(j);
	         switch(ch){
	         	case '+':
	         		temp2=variableStack.pop();
	         		temp1=variableStack.pop();
	         		operand = new Add();
	         		variableStack.push(operand.evaluate(temp1, temp2));
	         		break;
	         	case '-':
	         		temp2=variableStack.pop();
	         		temp1=variableStack.pop();
	         		operand = new Substract();
	         		variableStack.push(operand.evaluate(temp1, temp2));
	         		break;
	         	case '*':
	         		temp2=variableStack.pop();
	         		temp1=variableStack.pop();
	         		operand = new Multiply();
	         		variableStack.push(operand.evaluate(temp1, temp2));
	         		break;
	         	case '/':
	         		temp2=variableStack.pop();
	         		temp1=variableStack.pop();
	         		operand = new Division();
	         		variableStack.push(operand.evaluate(temp1, temp2));
	         		break;
	         	case '^':
	         		temp2=variableStack.pop();
	         		temp1=variableStack.pop();
	         		operand = new PowerOf();
	         		variableStack.push(operand.evaluate(temp1, temp2));
	         		break;
	         	default:
	         		variableStack.push(inputs.get(String.valueOf(ch)));
	         		break;
	         }

		}
		return variableStack.pop();
	}
	
	public void askForInputs(){
		Map<String,Float> inputs = new HashMap<String, Float>();
		
		Integer temp;
		for(int i=0;i<this.postfixStackstack.size();i++){
			System.out.println("Enter value for "+this.postfixStackstack.get(i)+" :");
			Scanner in = new Scanner(System.in);
			inputs.put(this.postfixStackstack.get(i), in.nextFloat());
		}
		this.inputs = inputs;
		return; 
	}

}
