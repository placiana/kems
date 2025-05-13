package rules.ipl;

import java.util.List;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.patterns.ISubformulaPattern;

public class IPLSubformulaRoleGetter implements KELabelledFormulaGetter, SubformulaGetter {

    ISubformulaPattern _pattern;

    KERuleRole _role;
    
    FormulaSign conclussionSign;

    /**
     * @param _pattern
     * @param _role
     */
    public IPLSubformulaRoleGetter(ISubformulaPattern _pattern, KERuleRole _role) {
        this._pattern = _pattern;
        this._role = _role;
        this.conclussionSign = null;
    }

    public IPLSubformulaRoleGetter(ISubformulaPattern _pattern, KERuleRole _role, FormulaSign sign) {
        this._pattern = _pattern;
        this._role = _role;
        this.conclussionSign = sign;
    }


	@Override
	public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList lfl) {
		System.out.println("Hola");
		Formula matchedSubformula = _pattern.getMatchedSubformula(lfl);
        return getLabelledFormula(lff, sff, ff, lfl, matchedSubformula);
		
	}


	public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, 
			SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl, Formula f) {
    	List<Formula> l = f.getImmediateSubformulas();

        if (_role.equals(KERuleRole.OTHER)) {
            Formula auxFormula = sfl.get(1).getFormula();
            Formula left = (Formula) l.get(0);
            Formula right = (Formula) l.get(1);

            if (auxFormula.equals(left)) {
                return substitute(lff, sff, ff, sfl, f, right);
            } else {
                if (auxFormula.equals(right)) {
                    return substitute(lff, sff, ff, sfl, f, left);
                }
            }

        } else {
            //            System.err.println("LR");

            Formula substitution = (Formula) _role.getFormulas(f).get(0);
            //            System.out.println(substitution + " " + f + " " + sfl.get(0) + "
            // " + substitute(sff, ff, sfl, f, substitution));
            return substitute(lff, sff, ff, sfl, f, substitution);
        }

        return null;

	}



	@Override
	public SignedFormula getSignedFormula(SignedFormulaFactory sff, 
			FormulaFactory ff, SignedFormulaList sfl) {
        return getSignedFormula(sff, ff, sfl, _pattern
                .getMatchedSubformula(sfl));
	}

	 public SignedFormula getSignedFormula(SignedFormulaFactory sff,
	            FormulaFactory ff, SignedFormulaList sfl, Formula f) {

	    	List<Formula> l = f.getImmediateSubformulas();
	        //        System.out.println(f + " " + l);

	        if (_role.equals(KERuleRole.OTHER)) {
	            Formula auxFormula = sfl.get(1).getFormula();
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
	            
	            if (this.conclussionSign == null) {
	                this.conclussionSign = sfl.get(0).getSign();
	            }
	            
	            return sff.createSignedFormula(this.conclussionSign, substitution);

	            //return substitute(sff, ff, sfl, f, substitution);
	        }

	        return null;

	    }

	
    public LabelledFormula getSignedFormula(LabelledFormulaFactory lff,SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormulaList sfl, Formula f) {

    	List<Formula> l = f.getImmediateSubformulas();
        //        System.out.println(f + " " + l);

        if (_role.equals(KERuleRole.OTHER)) {
            Formula auxFormula = sfl.get(1).getFormula();
            Formula left = (Formula) l.get(0);
            Formula right = (Formula) l.get(1);

            if (auxFormula.equals(left)) {
                return substitute(lff, sff, ff, sfl, f, right);
            } else {
                if (auxFormula.equals(right)) {
                    return substitute(lff, sff, ff, sfl, f, left);
                }
            }

        } else {
            //            System.err.println("LR");

            Formula substitution = (Formula) _role.getFormulas(f).get(0);
            return lff.createLabelledFormula(
                "c", 
                sff.createSignedFormula(sfl.get(0).getSign(), substitution));
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
    
	private LabelledFormula substitute(LabelledFormulaFactory lff, 
			SignedFormulaFactory sff, FormulaFactory ff, 
			SignedFormulaList sfl, Formula substituted,
			Formula replacement) {
        SignedFormula sf = sff.createSignedFormula(
        		sfl.get(0).getSign(), ff
                .createFormulaBySubstitution(
                		sfl.get(0).getFormula(),
                        substituted, replacement));

        return lff.createLabelledFormula("c", sf);
	}
	
	
}
