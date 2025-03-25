package rules.ipl;

import java.util.List;

import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logicalSystems.ipl.IPLConnectives;
import rules.KERuleRole;

public class KEDecoratedRuleRole extends KERuleRole {

    private Connective connective;
    
    public KEDecoratedRuleRole(String name, Connective connective) {
        super(name);
        this.connective = connective;

    }
    public List<Formula> getFormulas(Formula f) {
        List<Formula> formulas = super.getFormulas(f);
        FormulaFactory ff = new FormulaFactory();
        formulas.replaceAll(form -> ff.createCompositeFormula(this.connective, form));
        return formulas;
        
    }
}
