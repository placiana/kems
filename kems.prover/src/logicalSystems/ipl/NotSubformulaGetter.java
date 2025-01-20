package logicalSystems.ipl;

import java.util.List;

import logic.formulas.CompositeFormula;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.getters.KESignedFormulaGetter;

public class NotSubformulaGetter implements KESignedFormulaGetter {

	private KERuleRole role;
	private FormulaSign _sign;

	public NotSubformulaGetter(KERuleRole ruleRole, FormulaSign sign) {
		role = ruleRole;
		_sign = sign;
	}

	
    @Override
    public SignedFormula getSignedFormula(SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl) {
        // TODO Auto-generated method stub
        SignedFormula sf = sfl.get(0);
        if (sf.getFormula() instanceof CompositeFormula) {
            CompositeFormula formula = (CompositeFormula) sf.getFormula();
            
            //Formula sub = formula.getImmediateSubformulas().get(0);
            
            List<Formula> l = formula.getImmediateSubformulas();
            Formula sideFormula = null;
            if (role.equals(KERuleRole.LEFT)) {
                sideFormula = (Formula) l.get(0);
            }
            else if (role.equals(KERuleRole.RIGHT)) {
                sideFormula = (Formula) l.get(1);
            }
            Formula resultFormula = ff.createCompositeFormula(IPLConnectives.NOT, sideFormula);
            return sff.createSignedFormula(_sign, resultFormula);
        

        }
        
        return null;
    }
    /*
	@Override
	public SignedFormula getSignedFormula(SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl) {
		// TODO Auto-generated method stub
		SignedFormula sf = sfl.get(0);
		if (sf.getFormula() instanceof CompositeFormula) {
			CompositeFormula formula = (CompositeFormula) sf.getFormula();
			if (formula.getConnective().equals(IPLConnectives.NOT)) {
				Formula sub = formula.getImmediateSubformulas().get(0);
				
				List<Formula> l = sub.getImmediateSubformulas();
				Formula sideFormula = null;
	            if (role.equals(KERuleRole.LEFT)) {
	            	sideFormula = (Formula) l.get(0);
	            }
	            else if (role.equals(KERuleRole.RIGHT)) {
	            	sideFormula = (Formula) l.get(1);
	            }
	            Formula resultFormula = ff.createCompositeFormula(IPLConnectives.NOT, sideFormula);
	            return sff.createSignedFormula(_sign, resultFormula);
			}

		}
		
		return null;
	}
	*/
}
