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

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 *  
 */
public class OnePremiseOneConclusionRule extends OneConclusionRule {

    IUnarySignedFormulaPattern _premise;

    public OnePremiseOneConclusionRule(String name,
            IUnarySignedFormulaPattern premise, KELabelledAction conclusion) {
    	super(name, conclusion);
        _premise = premise;
    }

    public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormulaList sfl) {
        SignedFormula premise = sfl.get(0);
        if (_premise.matches(premise)) {
            return new SignedFormulaList(((KESignedFormulaGetter) getConclusion()
                    .getContent()).getSignedFormula(sff, ff, sfl));
        } else {
//            System.err.println(this+ " null for " + sfl);
            return null;
        }
    }

	@Override
	public LabelledFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			LabelledFormulaList lfl) {
		LabelledFormula premise = lfl.get(0);
        if (_premise.matches(premise.getSignedFormula())) {
        	LabelledFormula lf = getConclusion().getLabelledFormula(lff, sff, ff, lfl);
    		//SignedFormula sf = ( getConclusion().getContent()).getSignedFormula(sff, ff, lfl.toSignedFormulaList())
            return new LabelledFormulaList(
            	lf	
            	//	((KESignedFormulaGetter) getConclusion().getContent()).getSignedFormula(sff, ff, lfl)
        	);
        } else {
//            System.err.println(this+ " null for " + sfl);
            return null;
        }
	}

}