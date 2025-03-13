package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author placiana
 * 
 * No condition
 */
public class NoLabelCondition implements LabelCondition {

    @Override
    public boolean matches(SignedFormulaList lfl) {
        return true;
    }

	@Override
	public FormulaLabel getAuxiliaryLabel(LabelledFormula main) {
		return main.getLabel();
	}

}
