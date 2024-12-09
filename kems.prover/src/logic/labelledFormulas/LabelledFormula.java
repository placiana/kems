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
	
	public FormulaLabel getLabel() {
		return context;
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

	public SignedFormula getSignedFormula() {
		return this.signedFormula;
	}
	
    public String toString() {
        return signedFormula.toString() + " " + this.context.toString();
    }

	private String toString(SignedFormula signedFormula2, FormulaLabel context2) {
		// TODO Auto-generated method stub
		return null;
	}

}
