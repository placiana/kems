package rules.patterns.ipl;

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

public class SignCompositeRoleSubformulaPattern implements IBinarySignedFormulaPattern, ISubformulaPattern {

    Connective _mainConnective;

    FormulaSign _auxiliarySign;

    KERuleRole _auxiliaryRole;

    LabelCondition _labelCondition;

    Formula _match;

    public SignCompositeRoleSubformulaPattern(Connective conn, FormulaSign sign, KERuleRole ruleRole,
            LabelCondition labelCondition) {
        _mainConnective = conn;
        _auxiliarySign = sign;
        _auxiliaryRole = ruleRole;
        _labelCondition = labelCondition;

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


    @Override
    public FormulaList getMainMatches(LabelledFormula lf) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean matches(LabelledFormula main, LabelledFormula auxiliary) {
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(auxiliary);
        boolean labelCondition = _labelCondition.matches(lfl);
        return labelCondition && auxiliary.getSignedFormula().getSign().equals(_auxiliarySign)
                && recursivelyMatches(main.getSignedFormula().getFormula(), auxiliary);
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

    @Override
    public SignedFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormula sfMain) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean matchesMain(LabelledFormula sfMain) {
        // TODO Auto-generated method stub
        return false;
    }

}
