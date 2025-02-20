/*
 * Created on 22/10/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.getters.KESignedFormulaGetter;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 *
 */
public interface KELabelledFormulaGetter extends KESignedFormulaGetter{
	
	//public SignedFormula getSignedFormula (SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl);

	public LabelledFormula getLabelledFormula (LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl);


}
