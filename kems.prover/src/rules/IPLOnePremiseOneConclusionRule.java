package rules;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.ipl.KELabelledAction;
import rules.patterns.IUnarySignedFormulaPattern;

public class IPLOnePremiseOneConclusionRule extends OneConclusionRule{

	IUnarySignedFormulaPattern _premise;

	public IPLOnePremiseOneConclusionRule(String name, IUnarySignedFormulaPattern premise, KELabelledAction conclusion) {
		super(name, conclusion);
		_premise = premise;
	}

	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl) {

	    return getPossibleConclusions((LabelledFormulaFactory) sff, sff, ff, sfl);
	}

	public SignedFormulaList getPossibleConclusions(LabelledFormulaFactory lff, SignedFormulaFactory sff,
			FormulaFactory ff, SignedFormulaList lfl) {
		LabelledFormula premise = (LabelledFormula) lfl.get(0);
		if (_premise.matches(premise.getSignedFormula())) {
			LabelledFormula lf = ((KELabelledAction)getConclusion()).getLabelledFormula(lff, ff, lfl);
			return new SignedFormulaList(lf);
		} else {
            System.err.println(this+ " null for " + lfl);
			return null;
		}
	}


}
