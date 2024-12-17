package rules.patterns.ipl;


import java.util.List;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.formulas.FormulaList;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;

public class SignConnectiveRoleSubformulaPattern implements IBinarySignedFormulaPattern, ISubformulaPattern {

    Connective _mainConnective;

    FormulaSign _auxiliarySign;

    KERuleRole _auxiliaryRole;

    Formula _match;
	
	public SignConnectiveRoleSubformulaPattern(Connective conn, FormulaSign sign, KERuleRole ruleRole) {
		_mainConnective = conn;
		_auxiliarySign = sign;
		_auxiliaryRole = ruleRole;
	}

	@Override
	public boolean matches(LabelledFormula main, LabelledFormula auxiliary) {
        return auxiliary.getSignedFormula().getSign().equals(_auxiliarySign)
                && recursivelyMatches(main.getSignedFormula().getFormula(), auxiliary);
	}

	private boolean recursivelyMatches(Formula main, LabelledFormula auxiliary) {
        if (matches(main, auxiliary)) {
            return true;
        } else {
            for (int i = 0; i < main.getImmediateSubformulas().size(); i++) {
                if (recursivelyMatches((Formula) main.getImmediateSubformulas()
                        .get(i), auxiliary)) {
                    return true;
                }
            }
        }
        return false;
	}

    private boolean matches(Formula main, LabelledFormula auxiliary) {

        boolean mainMatch = matchesConnective(main);
        //		System.err.println(mainMatch);

        if (mainMatch) {
        	List<Formula> l = _auxiliaryRole.getFormulas(main);

            for (int i = 0; i < l.size(); i++) {
                Formula f1 = (Formula) l.get(i);
                //				System.err.println(f1);
                //				System.err.println(auxiliary);
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
            return ((CompositeFormula) f).getConnective().equals(
                    _mainConnective);
	}

	@Override
	public LabelledFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
			FormulaFactory ff, SignedFormula sfMain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matchesMain(LabelledFormula sfMain) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Formula getMatchedSubformula(LabelledFormulaList sfl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormulaList getMainMatches(LabelledFormula lf) {
		// TODO Auto-generated method stub
		return null;
	}

}
