/*
 * Created on 22/10/2004
 *
 */
package rules.ipl;

import rules.ActionType;
import rules.getters.BinaryConnectiveGetter;
import rules.getters.KESignedFormulaGetter;
import rules.ipl.labels.LabelGetter;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 */
public class KELabelledAction extends KEAction {

	ActionType _at;
	KELabelledFormulaGetter _content;
	LabelGetter labelGetter;

	
	public KELabelledAction(ActionType at, KELabelledFormulaGetter content, LabelGetter labelGetter) {
		super(at, content);
		this.labelGetter = labelGetter;
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
