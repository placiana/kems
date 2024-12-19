package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormulaList;

public class AuxiliarLabelGetter extends LabelGetter {

	@Override
	public FormulaLabel getLabel(LabelledFormulaList lfl) {
		return lfl.get(1).getLabel();
	}

}
