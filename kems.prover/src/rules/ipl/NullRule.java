/*
 * Created on 09/12/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author adolfo
 *
 */
public class NullRule extends IPLRule {

    /**
     * 
     */
    private NullRule() {
        super("NULL RULE");
    }
    
    public static final NullRule INSTANCE = new NullRule();


    /* (non-Javadoc)
     * @see rulesNew.Rule#getPossibleConclusions(signedFormulasNew.SignedFormulaFactory, formulasNew.FormulaFactory, signedFormulasNew.SignedFormulaList)
     */
    public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl) {
        return new SignedFormulaList();
    }


	@Override
	public SignedFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList lfl) {
		// TODO Auto-generated method stub
		return null;
	}

}
