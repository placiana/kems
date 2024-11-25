package logic.labelledFormulas;

import java.util.Objects;

import logic.signedFormulas.SignedFormula;

public class LabelledFormula {

	private FormulaLabel context;
	private SignedFormula signedFormula;

	public LabelledFormula(FormulaLabel aLabel, SignedFormula aSignedFormula) {
		this.signedFormula = aSignedFormula;
		this.context = aLabel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(context, signedFormula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabelledFormula other = (LabelledFormula) obj;
		return Objects.equals(context, other.context) && Objects.equals(signedFormula, other.signedFormula);
	}

}
