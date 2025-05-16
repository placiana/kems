package rules.patterns;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import rules.patterns.ipl.IUnaryLabelledFormulaPattern;

public class IPLTwoLevelCompositeFormulaPattern implements IUnaryLabelledFormulaPattern{
	private Connective first;
	private Connective second;
    private FormulaSign sign;

	public IPLTwoLevelCompositeFormulaPattern(Connective firstLevelConnective,
			Connective secondLevelConnective) {
		this.first = firstLevelConnective;
		this.second  = secondLevelConnective;
	}
    
	public IPLTwoLevelCompositeFormulaPattern(FormulaSign formulaSign, Connective firstLevelConnective,
            Connective secondLevelConnective) {
        this.first = firstLevelConnective;
        this.second  = secondLevelConnective;
        this.sign = formulaSign;
        
    }
	@Override
	public boolean matches(SignedFormula sf) {
		if (sf.getFormula() instanceof CompositeFormula) {
			CompositeFormula formula =  (CompositeFormula) sf.getFormula();
			if (formula.getConnective().equals(this.first)) {
				Formula sub = formula.getImmediateSubformulas().get(0);
				if (sub instanceof CompositeFormula) {
					CompositeFormula subformula = (CompositeFormula) sub;
					if (subformula.getConnective().equals(this.second)) {
						return true;
					}
					
				}
			}

			
		}
		return false;
	}

	@Override
	public boolean matches(LabelledFormula lf) {
		// TODO Auto-generated method stub
		
		return matches(lf.getSignedFormula());
	}

}
