package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.signedFormulas.SignedFormulaList;

public class MainLabelGetter extends LabelGetter {

	@Override
	public FormulaLabel getLabel(SignedFormulaList lfl) {
		return lfl.get(0).getLabel();
	}

}
