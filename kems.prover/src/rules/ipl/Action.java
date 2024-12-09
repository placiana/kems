/*
 * Created on 22/10/2004
 *
 */
package rules.ipl;


/** 
 * @author Adolfo Gustavo Serra Seca Neto
 *
 */
public interface Action {

	public ActionType getActionType();
	public Object getContent();
}
