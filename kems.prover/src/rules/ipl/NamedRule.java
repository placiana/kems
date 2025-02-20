/*
 * Created on 10/12/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;

/**
 * Named rules do no contain implementation. They are implemented 
 * elsewhere. Here we have only a name for recordding their application.
 * 
 * @author Adolfo Gustavo Serra Seca Neto
 *
 */
public class NamedRule extends IPLRule {

    /**
     * Creates a name rule
     */
    public NamedRule(String name) {
        super(name);
    }

    /* (non-Javadoc)
     * @see rulesNew.Rule#getPossibleConclusions(signedFormulasNew.SignedFormulaFactory, formulasNew.FormulaFactory, signedFormulasNew.SignedFormulaList)
     */
    public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormulaList sfl) {
        return null;
    }

	@Override
	public SignedFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList lfl) {
		// TODO Auto-generated method stub
		return null;
	}

}
