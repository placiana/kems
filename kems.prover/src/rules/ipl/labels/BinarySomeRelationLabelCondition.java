package rules.ipl.labels;

import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaList;

/**
 * @author placiana
 * Esta clase modela la condicion que exista una relacion entre Ci y Cj, en algun sentido.
 * Ci <= Cj or Cj <= Ci
 *
 */
public class BinarySomeRelationLabelCondition implements LabelCondition {

    @Override
    public boolean matches(LabelledFormulaList lfl) {
        LabelledFormula main = lfl.get(0);
        LabelledFormula aux = lfl.get(1);
        return main.getLabel().lowerOrEqualThan(aux.getLabel()) || aux.getLabel().lowerOrEqualThan(main.getLabel());
    }

}
