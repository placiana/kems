package rules.patterns;

import java.util.List;

import logic.formulas.AtomicFormula;
import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.ipl.labels.LabelCondition;

public class IPLTwoSignsConnectiveRolePattern implements IBinarySignedFormulaPattern {
    FormulaSign _mainSign, _auxiliarySign;

    Connective _mainConnective;

    KERuleRole _auxiliaryRole;
    
    LabelCondition _labelCondition;

    public IPLTwoSignsConnectiveRolePattern(FormulaSign mainSign,
            Connective mainConnective, FormulaSign auxiliarySign,
            KERuleRole auxiliaryRole, LabelCondition labelCondition) {
        _mainSign = mainSign;
        _mainConnective = mainConnective;
        _auxiliarySign = auxiliarySign;
        _auxiliaryRole = auxiliaryRole;
        _labelCondition = labelCondition;
        
    }
    
    public boolean roleMatches(Formula main, Formula auxiliary) {
        return _auxiliaryRole.getFormulas(main).contains(auxiliary);
    }

    /**
     * @param main
     * @return
     */
    public boolean matchesMain(SignedFormula main) {
        boolean mainMatch = _mainSign.equals(main.getSign())
                && matchesConnective(main.getFormula());
        return mainMatch;
    }

    private boolean matchesConnective(Formula f) {
        if (f instanceof AtomicFormula) {
            return false;
        } else
            return ((CompositeFormula) f).getConnective().equals(
                    _mainConnective);
    }



    @Override
    public boolean matches(SignedFormula main, SignedFormula auxiliary) {
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(auxiliary);
        boolean labelCondition = _labelCondition.matches(lfl);
        return labelCondition && auxiliary.getSign().equals(_auxiliarySign)
                && matchesSigned(main, auxiliary);
    }

    public boolean matchesSigned(SignedFormula main, SignedFormula auxiliary) {

        boolean mainMatch = matchesMain(main);

        boolean auxiliaryMatch = _auxiliarySign.equals(auxiliary.getSign())
                && roleMatches(main.getFormula(), auxiliary.getFormula());

        return mainMatch && auxiliaryMatch;
    }    
    
    @Override
    public SignedFormulaList getAuxiliaryCandidates(SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormula sfMain) {
		
		List<Formula> formulas = _auxiliaryRole.getFormulas(sfMain.getFormula());
        SignedFormulaList sfl = new SignedFormulaList();

        for (int i = 0; i < formulas.size(); i++) {
        	sfl.add(
    			sff.createSignedFormula(_auxiliarySign, (Formula) formulas.get(i))
            );
        }

        return sfl;
    
    
    }

}
