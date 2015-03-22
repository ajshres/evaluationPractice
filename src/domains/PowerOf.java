package domains;
import java.lang.Math;

public class PowerOf implements Operand {

	public PowerOf() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float evaluate(float value1, float value2) {
		// TODO Auto-generated method stub
		return (float)Math.pow(value1, value2);
	}

}
