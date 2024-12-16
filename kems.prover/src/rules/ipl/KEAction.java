/*
 * Created on 22/10/2004
 *
 */
package rules.ipl;

import rules.getters.BinaryConnectiveGetter;
import rules.getters.KESignedFormulaGetter;

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

	public KEAction(rules.ActionType addNode, BinaryConnectiveGetter falseLeft) {
		// TODO Auto-generated constructor stub
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
