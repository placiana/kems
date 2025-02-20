package rules.ipl.labels;

import logic.signedFormulas.SignedFormulaList;

public interface LabelCondition {

	
    public boolean matches(SignedFormulaList lfl);
}
