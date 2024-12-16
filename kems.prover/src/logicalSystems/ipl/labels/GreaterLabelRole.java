package logicalSystems.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;

public class GreaterLabelRole implements LabelRole {

	@Override
	public FormulaLabel getLabel(LabelledFormula lf) {
		// TODO Auto-generated method stub
		return lf.getLabel().getNextFormulaLabel();
	}

}
