package domains;

public class Division implements Operand {

	@Override
	public float evaluate(float value1, float value2) {
		// TODO Auto-generated method stub
		// TODO need to check if value 2 is 0. If 0 need to catch throw error and show result as infinity or undefined or beyond its scope
		return value1/value2;
	}

}
