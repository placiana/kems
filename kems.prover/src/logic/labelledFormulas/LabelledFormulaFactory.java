package logic.labelledFormulas;

import logic.formulas.Formula;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;

public class LabelledFormulaFactory {

	public LabelledFormula createLabelledFormula(Context aContext, SignedFormula aSignedFormula) {
		return new LabelledFormula(aContext, aSignedFormula);
		// TODO Auto-generated method stub
		
	}
	
	public LabelledFormula createLabelledFormula(Context aContext, FormulaSign aSign, Formula aFormula) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
