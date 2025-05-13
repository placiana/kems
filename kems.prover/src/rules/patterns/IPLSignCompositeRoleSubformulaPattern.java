package rules.patterns;

import java.util.List;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.formulas.FormulaList;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.ipl.labels.LabelCondition;

public class IPLSignCompositeRoleSubformulaPattern implements IBinarySignedFormulaPattern, ISubformulaPattern {
    Connective _mainConnective;
    FormulaSign _auxiliarySign;
    KERuleRole _auxiliaryRole;
    LabelCondition _labelCondition;
    Formula _match;
    
    public IPLSignCompositeRoleSubformulaPattern(Connective conn, FormulaSign sign, KERuleRole ruleRole,
            LabelCondition labelCondition) {
        _mainConnective = conn;
        _auxiliarySign = sign;
        _auxiliaryRole = ruleRole;
        _labelCondition = labelCondition;

    }
	
	@Override
	public boolean matches(SignedFormula main, SignedFormula auxiliary) {
		LabelledFormula _main = (LabelledFormula) main;
		LabelledFormula aux = (LabelledFormula) auxiliary;
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(auxiliary);
        boolean labelCondition = _labelCondition.matches(lfl);
        return labelCondition && aux.getSignedFormula().getSign().equals(_auxiliarySign)
                && recursivelyMatches(_main.getSignedFormula().getFormula(), aux);
	}

	@Override
	public SignedFormulaList getAuxiliaryCandidates(SignedFormulaFactory sff, FormulaFactory ff, SignedFormula sfMain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matchesMain(SignedFormula sfMain) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Formula getMatchedSubformula(SignedFormulaList sfl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormulaList getMainMatches(SignedFormula sf) {
		// TODO Auto-generated method stub
		return null;
	}
    private boolean recursivelyMatches(Formula main, LabelledFormula auxiliary) {
        if (matches(main, auxiliary)) {
            return true;
        } else {
            for (int i = 0; i < main.getImmediateSubformulas().size(); i++) {
                if (recursivelyMatches((Formula) main.getImmediateSubformulas().get(i), auxiliary)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean matches(Formula main, LabelledFormula auxiliary) {

        boolean mainMatch = matchesConnective(main);
        // System.err.println(mainMatch);

        if (mainMatch) {
            List<Formula> l = _auxiliaryRole.getFormulas(main);

            for (int i = 0; i < l.size(); i++) {
                Formula f1 = (Formula) l.get(i);
                // System.err.println(f1);
                // System.err.println(auxiliary);
                if (f1.equals(auxiliary.getSignedFormula().getFormula())) {
                    return true;
                }

            }
        }

        return false;
    }
    private boolean matchesConnective(Formula f) {
        if (!(f instanceof CompositeFormula)) {
            return false;
        } else
            return ((CompositeFormula) f).getConnective().equals(_mainConnective);
    }



}
