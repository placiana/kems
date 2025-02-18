/*
 * Created on 21/10/2004
 *
 */
package main.proofTree;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 *
 */
public class LabelledFormulaNodeState implements State {
	
	public static final LabelledFormulaNodeState ANALYSED = 
		new LabelledFormulaNodeState ("analysed");  
	public static final LabelledFormulaNodeState NOT_ANALYSED = 
		new LabelledFormulaNodeState ("not analysed");  
	public static final LabelledFormulaNodeState FULFILLED = 
		new LabelledFormulaNodeState ("fulfilled");
		  
	String _value;
	
	private LabelledFormulaNodeState (String value){
		this._value = value;
	}

	/* (non-Javadoc)
	 * @see proofTree.State#getValue()
	 */
	public Object getValue() {
		return _value;
	}
	
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return _value;
    }

}
