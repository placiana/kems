package rules.patterns.ipl;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logicalSystems.c1.C1Connectives;
import rules.patterns.C1ConsistencyPattern;

public class LabelLowerConsistencyPattern {

	private FormulaLabel _label;

	public LabelLowerConsistencyPattern(FormulaLabel label) {
		_label = label;
	}

	public boolean matches(LabelledFormula lf) {
		return lf.getLabel().lowerThan(_label);
	}
	
}
