package logic.signedFormulas;

public class FormulaLabel {
	private enum LabelType {
		CONSTANT, VARIABLE, NONE
	}

	private final LabelType type;
	private final int index;

	private FormulaLabel(LabelType type, int index) {
		this.type = type;
		this.index = index;
	}

	public static FormulaLabel constant(int index) {
		return new FormulaLabel(LabelType.CONSTANT, index);
	}

	public static FormulaLabel variable(int index) {
		return new FormulaLabel(LabelType.VARIABLE, index);
	}

	public static FormulaLabel empty() {
		return new FormulaLabel(LabelType.NONE, 0);
	}

	public boolean isConstant() {
		return this.type == LabelType.CONSTANT;
	}

	public boolean isVariable() {
		return this.type == LabelType.VARIABLE;
	}

	public int getIndex() {
		return this.index;
	}

	public boolean isEmpty() {
		return this.type == LabelType.NONE;
	}

	@Override
	public String toString() {
		if (isEmpty()) return "";
		return (isConstant() ? "c" : "x") + this.index;
	}
}