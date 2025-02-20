/*
 * Created on 10/11/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.patterns.ipl.IUnaryLabelledFormulaPattern;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 *  
 */
public class OnePremiseTwoConclusionsRule extends IPLRule {

	IUnaryLabelledFormulaPattern _premise;

	KELabelledAction _conclusion1, _conclusion2;

    public OnePremiseTwoConclusionsRule(String name,
    		IUnaryLabelledFormulaPattern premise, 
    		KELabelledAction conclusion1,
    		KELabelledAction conclusion2) {
    	super(name);
        _premise = premise;
        _conclusion1 = conclusion1;
        _conclusion2 = conclusion2;
    }


	@Override
	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl) {
        return this.getPossibleConclusions((LabelledFormulaFactory) sff, sff, ff, sfl);
	}

	@Override
	public SignedFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList lfl) {
        LabelledFormula premise = (LabelledFormula) lfl.get(0);
        if (_premise.matches(premise)) {
            SignedFormulaList l = new SignedFormulaList();
            l.add((_conclusion1.getLabelledFormula(sff, ff, lfl)));
            l.add((_conclusion2.getLabelledFormula(sff, ff, lfl)));
            return l;
        } else
            return null;
	}


}