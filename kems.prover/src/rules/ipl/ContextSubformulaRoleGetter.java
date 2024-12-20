/*
 * Created on 17/11/2004
 *
 */
package rules.ipl;

import java.util.List;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.getters.SubformulaRoleGetter;
import rules.patterns.ISubformulaPattern;

/**
 * 
 * Getter used for X_AND_2 rule.
 * 
 * @author adolfo
 *  
 */
public class ContextSubformulaRoleGetter extends SubformulaRoleGetter {

    ISubformulaPattern _pattern;

    KERuleRole _role;
    
    String _context;

    /**
     * @param _pattern
     * @param _role
     */
    public ContextSubformulaRoleGetter(ISubformulaPattern _pattern, KERuleRole _role) {
    	super(_pattern, _role);
        this._pattern = _pattern;
        this._role = _role;
    }

    public ContextSubformulaRoleGetter(ISubformulaPattern _pattern, KERuleRole _role, String context) {
    	super(_pattern, _role);
        this._pattern = _pattern;
        this._role = _role;
        this._context = context;
    }
    
    public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff,
            FormulaFactory ff, LabelledFormulaList lfl) {
    	SignedFormulaList sfl = lfl.toSignedFormulaList();
    	SignedFormula result = getSignedFormula(sff, ff, sfl);
    	
    	LabelledFormula main = lfl.get(0);
    	
    	FormulaLabel label;
    	if (_context == "MAIN") {
    		label = main.getLabel(); 
    	} else {
    		label = lfl.get(1).getLabel();
    	}
    	return lff.createLabelledFormula(label, result);
    	
    }
    
    
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



}