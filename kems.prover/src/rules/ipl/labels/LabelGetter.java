package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormulaList;

public abstract class LabelGetter {

	public static final LabelGetter MAIN = new MainLabelGetter();
	public static final LabelGetter AUX = new AuxiliarLabelGetter();
	public static final LabelGetter NEW = new NewLabelGetter();
	
	public abstract FormulaLabel getLabel(SignedFormulaList lfl);
}
