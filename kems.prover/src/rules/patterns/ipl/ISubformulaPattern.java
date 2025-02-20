/*
 * Created on 17/11/2004
 *
 */
package rules.patterns.ipl;

import logic.formulas.Formula;
import logic.formulas.FormulaList;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author adolfo
 * 
 */
public interface ISubformulaPattern extends rules.patterns.ISubformulaPattern {

	public Formula getMatchedSubformula(SignedFormulaList sfl);

	/**
	 * @param sf
	 * @return
	 */
	public FormulaList getMainMatches(LabelledFormula lf);

}