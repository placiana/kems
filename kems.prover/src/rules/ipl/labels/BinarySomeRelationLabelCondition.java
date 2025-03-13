package rules.ipl.labels;

import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormulaList;

/**
 * @author placiana
 * Esta clase modela la condicion que exista una relacion entre Ci y Cj, en algun sentido.
 * Ci <= Cj or Cj <= Ci
 *
 */
public class BinarySomeRelationLabelCondition implements LabelCondition {

    @Override
    public boolean matches(SignedFormulaList lfl) {
        LabelledFormula main = (LabelledFormula) lfl.get(0);
        LabelledFormula aux = (LabelledFormula) lfl.get(1);
        return main.getLabel().lowerOrEqualThan(aux.getLabel()) || aux.getLabel().lowerOrEqualThan(main.getLabel());
    }

	@Override
	public FormulaLabel getAuxiliaryLabel(LabelledFormula main) {
		return main.getLabel().getGreaterFormulaLabel();
	}

}
