/*
 * Created on 21/09/2005
 */
package rules.ipl;


/**
 * @author Adolfo Gustavo Serra Seca Neto
 */
public abstract class OneConclusionRule extends Rule {
	
	private KELabelledAction _conclusion;
	
	public KELabelledAction getConclusion(){
		return _conclusion;
	}
	
	public OneConclusionRule(String name, KELabelledAction conclusion){
		super(name);
		_conclusion = conclusion;
	}


}
