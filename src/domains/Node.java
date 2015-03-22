package domains;

public class Node implements ExpressionEvaluation {
	
	Node node1 = null;
	Node node2 = null;
	float value = 0;
	Operand operand;
	boolean isRoot = false;
	boolean isLeaf = true;
	boolean inOperation = true;

	@Override
	public float evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasOtherNodes() {
		// TODO Auto-generated method stub
		return !this.isLeaf;
	}

	@Override
	public boolean hasEvaluateComplete() {
		// TODO Auto-generated method stub
		return !this.inOperation;
	}

	@Override
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return this.isRoot;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

}
