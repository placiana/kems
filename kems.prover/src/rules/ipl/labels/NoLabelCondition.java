package rules.ipl.labels;

import logic.signedFormulas.SignedFormulaList;

/**
 * @author placiana
 * 
 * No condition
 */
public class NoLabelCondition implements LabelCondition {

    @Override
    public boolean matches(SignedFormulaList lfl) {
        return true;
    }

}
