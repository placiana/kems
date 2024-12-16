package rules.patterns.ipl;

import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormula;
import rules.patterns.IUnarySignedFormulaPattern;

public interface IUnaryLabelledFormulaPattern extends IUnarySignedFormulaPattern {

    /** Verifies if a given signed formula matches the pattern.
     * 
     * @param sf
     * @return
     */
    public boolean matches (LabelledFormula lf);
    
	
}
