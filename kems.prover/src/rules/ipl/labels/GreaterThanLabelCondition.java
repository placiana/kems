package rules.ipl.labels;

import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaList;

/**
 * @author placiana
 *  Main formula label greater than aux formula
 */
public class GreaterThanLabelCondition implements LabelCondition {

    @Override
    public boolean matches(LabelledFormulaList lfl) {
        LabelledFormula main = lfl.get(0);
        LabelledFormula aux = lfl.get(1);
        
        return aux.getLabel().lowerOrEqualThan(main.getLabel());
    }

}
