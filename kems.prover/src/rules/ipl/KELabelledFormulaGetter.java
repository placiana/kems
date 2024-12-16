/*
 * Created on 22/10/2004
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

/**
 * @author Adolfo Gustavo Serra Seca Neto
 *
 */
public interface KELabelledFormulaGetter {
	
	public SignedFormula getSignedFormula (SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl);

	public LabelledFormula getLabelledFormula (LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff, LabelledFormulaList sfl);


}
