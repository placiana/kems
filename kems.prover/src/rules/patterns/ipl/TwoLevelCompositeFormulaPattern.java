package rules.patterns.ipl;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormula;
import logicalSystems.ipl.IPLConnectives;

public class TwoLevelCompositeFormulaPattern implements IUnaryLabelledFormulaPattern {

	private Connective first;
	private Connective second;

	public TwoLevelCompositeFormulaPattern(Connective firstLevelConnective,
			Connective secondLevelConnective) {
		this.first = firstLevelConnective;
		this.second  = secondLevelConnective;
	}
	
	@Override
	public boolean matches(SignedFormula sf) {
		if (sf.getFormula() instanceof CompositeFormula) {
			CompositeFormula formula =  (CompositeFormula) sf.getFormula();
			if (formula.getConnective().equals(IPLConnectives.NOT)) {
				Formula sub = formula.getImmediateSubformulas().get(0);
				if (sub instanceof CompositeFormula) {
					CompositeFormula subformula = (CompositeFormula) sub;
					if (subformula.getConnective().equals(IPLConnectives.OR)) {
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
