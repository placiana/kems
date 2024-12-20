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
import rules.Action;
import rules.ActionType;
import rules.getters.BinaryConnectiveGetter;
import rules.getters.KESignedFormulaGetter;
import rules.ipl.labels.LabelGetter;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 */
public class KEAction implements Action {

	ActionType _at;
	KELabelledFormulaGetter _content;

	public KEAction(ActionType at, KELabelledFormulaGetter content) {
		_at = at;
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
	public KELabelledFormulaGetter getContent() {
		return _content;
	}



}
