package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormulaList;

public abstract class LabelGetter {

	public static final LabelGetter MAIN = new MainLabelGetter();
	public static final LabelGetter AUX = new AuxiliarLabelGetter();
	
	public abstract FormulaLabel getLabel(LabelledFormulaList lfl);
}
