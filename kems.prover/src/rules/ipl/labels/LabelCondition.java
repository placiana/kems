package rules.ipl.labels;

import logic.labelledFormulas.LabelledFormulaList;

public interface LabelCondition {

	
    public boolean matches(LabelledFormulaList lfl);
}
