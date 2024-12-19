package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormulaList;

public class MainLabelGetter extends LabelGetter {

	@Override
	public FormulaLabel getLabel(LabelledFormulaList lfl) {
		return lfl.get(0).getLabel();
	}

}
