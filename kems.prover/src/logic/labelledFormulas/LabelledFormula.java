package logic.labelledFormulas;

import java.util.Objects;

import logic.signedFormulas.SignedFormula;

public class LabelledFormula  extends SignedFormula {

	//private FormulaLabel _label;
	private SignedFormula signedFormula;

	public LabelledFormula(FormulaLabel aLabel, SignedFormula aSignedFormula) {
		super(aSignedFormula.getSign(), aSignedFormula.getFormula(), aLabel);
		this.signedFormula = aSignedFormula;
		//this._label = aLabel;
	}
	
	/*
	public FormulaLabel getLabel() {
		return _label;
	}
    */

	@Override
	public int hashCode() {
		return Objects.hash(this.getLabel(), signedFormula);
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
		return Objects.equals(this.getLabel(), other.getLabel()) && Objects.equals(signedFormula, other.signedFormula);
	}

	public SignedFormula getSignedFormula() {
		return this.signedFormula;
	}
	
    public String toString() {
        return signedFormula.toString() + " " + this.getLabel().toString();
    }

}
