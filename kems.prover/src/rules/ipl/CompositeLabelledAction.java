package rules.ipl;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.getters.KESignedFormulaGetter;

public class CompositeLabelledAction implements KESignedFormulaGetter {

    private KESignedFormulaGetter inner;
    private Connective connective;

    public CompositeLabelledAction(Connective connective, KESignedFormulaGetter innerGetter) {
        this.inner =  innerGetter;
        this.connective = connective;
    }

    @Override
    public SignedFormula getSignedFormula(SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl) {
        SignedFormulaList newSfl = new SignedFormulaList();
        
        for(SignedFormula sf: sfl.getList()) {
            if( sf.getFormula() instanceof CompositeFormula) {
                CompositeFormula formula = (CompositeFormula) sf.getFormula();
                if (formula.getConnective().equals(this.connective)) {
                    newSfl.add(sff.createCloseSignedFormula(sf.getSign(), formula.getImmediateSubformulas().get(0)));
                }
            }
        }
        
        
        //return getSignedFormula(sff, ff, sfl, sfl.get(0).getFormula());
        return inner.getSignedFormula(sff, ff, newSfl);
    }



}
