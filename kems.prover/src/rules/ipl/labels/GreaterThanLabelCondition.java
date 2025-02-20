package rules.ipl.labels;

import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author placiana
 *  Main formula label greater than aux formula
 */
public class GreaterThanLabelCondition implements LabelCondition {

    @Override
    public boolean matches(SignedFormulaList lfl) {
        LabelledFormula main = (LabelledFormula) lfl.get(0);
        LabelledFormula aux = (LabelledFormula) lfl.get(1);
        
        return aux.getLabel().lowerOrEqualThan(main.getLabel());
    }

}
