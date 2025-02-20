/*
 * Created on 22/10/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.Rule;


public abstract class IPLRule extends Rule {


	public IPLRule(String name) {
		super(name);
	};


	abstract public SignedFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl);

}