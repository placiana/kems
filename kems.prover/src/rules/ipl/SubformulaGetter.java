/*
 * Created on 10/12/2004
 *
 */
package rules.ipl;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author pablo
 *
 */
public interface SubformulaGetter {
        
    public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff,
            FormulaFactory ff, LabelledFormulaList sfl);


}