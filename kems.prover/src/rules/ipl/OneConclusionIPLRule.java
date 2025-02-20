/*
 * Created on 21/09/2005
 */
package rules.ipl;

import rules.OneConclusionRule;


public abstract class OneConclusionIPLRule extends OneConclusionRule {
	
	private KELabelledAction labelConclusion;
	
	public KELabelledAction getConclusion(){
		return labelConclusion;
	}
	
	public OneConclusionIPLRule(String name, KELabelledAction conclusion){
		super(name, conclusion);
		//super(name);
		labelConclusion = conclusion;
	}


}
