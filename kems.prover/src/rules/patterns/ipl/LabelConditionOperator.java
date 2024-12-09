package rules.patterns.ipl;

import logic.labelledFormulas.FormulaLabel;

public interface  LabelConditionOperator {
	
	
	public boolean isTrue(FormulaLabel main, FormulaLabel aux);

}
