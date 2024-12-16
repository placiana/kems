package logicalSystems.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;

public interface LabelRole {

	LabelRole SAME = new SameLabelRole();

	public FormulaLabel getLabel(LabelledFormula lf);
}
