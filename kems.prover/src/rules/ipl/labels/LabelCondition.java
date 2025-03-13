package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormulaList;

public interface LabelCondition {

	
    public boolean matches(SignedFormulaList lfl);
    
    public FormulaLabel getAuxiliaryLabel(LabelledFormula main);
}
