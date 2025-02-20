/*
 * Created on 23/09/2004
 *
 */
package rules.patterns.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 *  
 */
public interface IBinarySignedFormulaPattern {

    /**
     * Verifies if two signed formulas satisfy a pattern.
     * 
     * @param main
     * @param auxiliary
     * @return
     */
    public boolean matches(LabelledFormula main, LabelledFormula auxiliary);

    /**
     * @param sff
     * @param ff
     * @param sfMain
     * @return
     */
    public SignedFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormula sfMain);

    /**
     * @param sfMain
     * @return
     */
    public boolean matchesMain(LabelledFormula sfMain);


}