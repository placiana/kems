package logicalSystems.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;

public class SameLabelRole implements LabelRole {

	@Override
	public FormulaLabel getLabel(LabelledFormula lf) {
		return lf.getLabel();
	}

}
