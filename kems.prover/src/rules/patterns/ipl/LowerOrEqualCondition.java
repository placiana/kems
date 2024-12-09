package rules.patterns.ipl;

import logic.labelledFormulas.FormulaLabel;

public class LowerOrEqualCondition implements LabelConditionOperator {

	@Override
	public boolean isTrue(FormulaLabel main, FormulaLabel aux) {
		return main.lowerOrEqualThan(aux);
	}

}
