package rules.ipl;

import java.util.List;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.patterns.ipl.IBinarySignedFormulaPattern;
import rules.patterns.ipl.ISubformulaPattern;
import rules.patterns.ipl.SignConnectiveRoleSubformulaPattern;

public class SubformulaRoleGetter implements KELabelledFormulaGetter, SubformulaGetter {

    ISubformulaPattern _pattern;

    KERuleRole _role;

    /**
     * @param _pattern
     * @param _role
     */
    public SubformulaRoleGetter(ISubformulaPattern _pattern, KERuleRole _role) {
        this._pattern = _pattern;
        this._role = _role;
    }



	@Override
	public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			LabelledFormulaList lfl) {
		System.out.println("Hola");
        //return getLabelledFormula(lff, sff, ff, lfl, _pattern.getMatchedSubformula(lfl));
		return null;
	}

	@Override
	public LabelledFormula getSignedFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl, Formula substituted) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SignedFormula getSignedFormula(SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl) {
		// TODO Auto-generated method stub
		return null;
	}

    public LabelledFormula getSignedFormula(LabelledFormulaFactory lff,SignedFormulaFactory sff,
            FormulaFactory ff, LabelledFormulaList sfl, Formula f) {

    	List<Formula> l = f.getImmediateSubformulas();
        //        System.out.println(f + " " + l);

        if (_role.equals(KERuleRole.OTHER)) {
            Formula auxFormula = sfl.get(1).getSignedFormula().getFormula();
            Formula left = (Formula) l.get(0);
            Formula right = (Formula) l.get(1);

            if (auxFormula.equals(left)) {
                return substitute(sff, ff, sfl, f, right);
            } else {
                if (auxFormula.equals(right)) {
                    return substitute(sff, ff, sfl, f, left);
                }
            }

        } else {
            //            System.err.println("LR");

            Formula substitution = (Formula) _role.getFormulas(f).get(0);
            //            System.out.println(substitution + " " + f + " " + sfl.get(0) + "
            // " + substitute(sff, ff, sfl, f, substitution));
            return substitute(sff, ff, sfl, f, substitution);
        }

        return null;

    }

    private SignedFormula substitute(SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormulaList sfl, Formula substituted,
            Formula replacement) {
        return sff.createSignedFormula(sfl.get(0).getSign(), ff
                .createFormulaBySubstitution(sfl.get(0).getFormula(),
                        substituted, replacement));
    }
    
	private LabelledFormula substitute(SignedFormulaFactory sff, FormulaFactory ff, LabelledFormulaList sfl, Formula f,
			Formula right) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
