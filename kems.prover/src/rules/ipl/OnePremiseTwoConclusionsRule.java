/*
 * Created on 10/11/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.getters.KESignedFormulaGetter;
import rules.patterns.IUnarySignedFormulaPattern;
import rules.patterns.SignConnectivePattern;
import rules.patterns.ipl.IUnaryLabelledFormulaPattern;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 *  
 */
public class OnePremiseTwoConclusionsRule extends Rule {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabelledFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			LabelledFormulaList lfl) {
        LabelledFormula premise = lfl.get(0);
        if (_premise.matches(premise)) {
            LabelledFormulaList l = new LabelledFormulaList();
            l.add((_conclusion1.getLabelledFormula(lff, sff, ff, lfl)));
            l.add((_conclusion2.getLabelledFormula(lff, sff, ff, lfl)));
            return l;
        } else
            return null;
	}


}