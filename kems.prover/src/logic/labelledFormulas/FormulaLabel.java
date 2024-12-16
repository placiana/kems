package logic.labelledFormulas;

import java.util.Objects;

public class FormulaLabel implements Comparable<FormulaLabel>{
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
	
	public FormulaLabel getNextFormulaLabel() {
		
		return new FormulaLabel(LabelType.CONSTANT, this.index + 1);
	}

	@Override
	public String toString() {
		if (isEmpty()) return "";
		return (isConstant() ? "c" : "x") + this.index;
	}

	@Override
	public int compareTo(FormulaLabel o) {
		return this.index - o.getIndex();
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormulaLabel other = (FormulaLabel) obj;
		return index == other.index && type == other.type;
	}

	public boolean lowerOrEqualThan(FormulaLabel aux) {
		return this.index <= aux.getIndex();
	}
	public boolean lowerThan(FormulaLabel aux) {
		return this.index < aux.getIndex();
	}
}