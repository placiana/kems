package rules.patterns.ipl;

import logic.formulas.Connective;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logicalSystems.ipl.IPLConnectives;
import logicalSystems.ipl.IPLSigns;
import rules.KERuleRole;
import rules.ipl.labels.BinarySomeRelationLabelCondition;
import rules.ipl.labels.LabelCondition;

public class TwoLevelCompositeBinaryFormulaPattern implements IBinarySignedFormulaPattern {

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
        // TODO Auto-generated method stub
        return false;
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

}
