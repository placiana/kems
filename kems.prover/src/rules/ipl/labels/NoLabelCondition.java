package rules.ipl.labels;

import logic.labelledFormulas.LabelledFormulaList;

/**
 * @author placiana
 * 
 * No condition
 */
public class NoLabelCondition implements LabelCondition {

    @Override
    public boolean matches(LabelledFormulaList lfl) {
        return true;
    }

}
