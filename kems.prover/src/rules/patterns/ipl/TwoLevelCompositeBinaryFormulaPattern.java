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

public class TwoLevelCompositeBinaryFormulaPattern implements IBinarySignedFormulaPattern, ISubformulaPattern {

    private Connective first;
    private Connective second;
    private FormulaSign mainSign;
    private FormulaSign auxiliarySign;
    private LabelCondition labelCondition;
    private KERuleRole auxRole;
    
    /* 
     *                   IPLSigns.TRUE,     // main sign
                    IPLConnectives.AND, // main connective
                    IPLSigns.TRUE,      // aux sign
                    KERuleRole.LEFT,    // aux role
                    new BinarySomeRelationLabelCondition()
                    FormulaSign mainSign,
            Connective mainConnective, FormulaSign auxiliarySign,
            KERuleRole auxiliaryRole, LabelCondition labelCondition
     */
    
    public TwoLevelCompositeBinaryFormulaPattern(Connective mainFirstConnective, Connective mainSecondConnective, FormulaSign mainSign,
            FormulaSign auxSign, KERuleRole auxiliaryRole, LabelCondition lCondition) {
        first = mainFirstConnective;
        second = mainSecondConnective;
        this.mainSign = mainSign;
        auxiliarySign = auxSign;
        labelCondition = lCondition;
        auxRole = auxiliaryRole;
    }
    
    @Override
    public boolean matches(LabelledFormula main, LabelledFormula auxiliary) {
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(auxiliary);
        boolean lcMatch = labelCondition.matches(lfl);
        return lcMatch && auxiliary.getSignedFormula().getSign().equals(auxiliarySign)
                && main.getSignedFormula().getSign().equals(mainSign)
                && matches(main.getSignedFormula(), auxiliary.getSignedFormula());
    }

    private boolean matches(SignedFormula main, SignedFormula aux) {
        boolean mainMatch = matchesMain(main);
        boolean auxMatch = true;
        return mainMatch && auxMatch;
    }

    private boolean matchesMain(SignedFormula main) {
        return mainSign.equals(main.getSign()) &&
                matchesMainFirstConnective(main.getFormula()) &&
                matchesMainSecondConnective(main.getFormula());
    }

    private boolean matchesMainSecondConnective(Formula mainFormula) {
        if (mainFormula instanceof CompositeFormula) {
            List<Formula> subs = ((CompositeFormula) mainFormula).getImmediateSubformulas();
            return (subs.get(0) instanceof CompositeFormula) && ((CompositeFormula) subs.get(0)).getConnective().equals(second); 
        }
        return false;
    }

    private boolean matchesMainFirstConnective(Formula mainFormula) {
        if (mainFormula instanceof CompositeFormula) {
            return ((CompositeFormula) mainFormula).getConnective().equals(first); 
        }
        return false;
    }

    @Override
    public SignedFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
            FormulaFactory ff, SignedFormula sfMain) {
    	
    	// we go down one level
    	Formula mainFormula = sfMain.getFormula();
    	Formula subFormula = mainFormula.getImmediateSubformulas().get(0);
    	
		List<Formula> formulas = auxRole.getFormulas(subFormula);
        SignedFormulaList sfl = new SignedFormulaList();

        for (int i = 0; i < formulas.size(); i++) {

        	sfl.add(
    			sff.createSignedFormula(auxiliarySign, (Formula) formulas.get(i))
            );
        }

        return sfl;
    }

    @Override
    public boolean matchesMain(LabelledFormula sfMain) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Formula getMatchedSubformula(SignedFormulaList sfl) {
        SignedFormula signedMain = sfl.get(0);
        if (matchesMain(signedMain)) {
            List<Formula> subs = ((CompositeFormula) signedMain.getFormula()).getImmediateSubformulas();
            
            return subs.get(0);
        }
        return  null;
    }

    public Formula getMatchedSubformulaBak(SignedFormulaList sfl) {
        // TODO Auto-generated method stub
        LabelledFormula main = (LabelledFormula)sfl.get(0);
        return main.getSignedFormula().getFormula();
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

}
