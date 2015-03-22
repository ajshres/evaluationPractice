package domains;

public interface ExpressionEvaluation {
	float evaluate();
	boolean hasOtherNodes();
	boolean hasEvaluateComplete();
	boolean isRoot();
	boolean isLeaf();
}
