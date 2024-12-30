/*
 * Created on 22/10/2004
 *
 */
package rules.ipl;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import rules.ActionType;
import rules.getters.BinaryConnectiveGetter;
import rules.getters.KESignedFormulaGetter;
import rules.ipl.labels.LabelGetter;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 */
public class KELabelledAction extends rules.KEAction {

	ActionType _at;
	KESignedFormulaGetter _content;
	LabelGetter labelGetter;

	
	public KELabelledAction(ActionType at, KESignedFormulaGetter content, LabelGetter labelGetter) {
		super(at, content);
		this.labelGetter = labelGetter;
		_content = content;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see rulesNew.Action#getActionType()
	 */
	public ActionType getActionType() {
		return _at;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rulesNew.Action#getContent()
	 */
	public KESignedFormulaGetter getContent() {
		return _content;
	}

	public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			LabelledFormulaList lfl) {
		SignedFormula sf = getContent().getSignedFormula(sff, ff, lfl.toSignedFormulaList());
		return lff.createLabelledFormula(this.labelGetter.getLabel(lfl), sf);
	}

}
