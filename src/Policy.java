
public class Policy {
	private String target;
	private String action;
	private String leftOperand;
	private String operator;
	private String rightOperand;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(String rightOperand) {
		this.rightOperand = rightOperand;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Policy(String target, String action, String leftOperand, String operator, String rightOperand) {
		super();
		this.target = target;
		this.action = action;
		this.leftOperand = leftOperand;
		this.operator = operator;
		this.rightOperand = rightOperand;
	}

	@Override
	public String toString() {
		return "target=" + target + ", \n action=" + action + ",\n leftOperand=" + leftOperand + ", \n operator="
				+ operator + ",\n rightOperand=" + rightOperand;
	}

	public String toshortString() {
		return " operator=" + operator + ",\n rightOperand=" + rightOperand;
	}
}
