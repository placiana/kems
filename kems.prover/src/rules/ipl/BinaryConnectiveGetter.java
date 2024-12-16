/*
 * Created on 10/11/2004
 *
 */
package rules.ipl;

import java.util.List;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import logicalSystems.classicalLogic.ClassicalSigns;
import logicalSystems.ipl.labels.LabelRole;
import rules.KERuleRole;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 *  
 */
public class BinaryConnectiveGetter implements  KELabelledFormulaGetter {
    FormulaSign _sign;

    KERuleRole _role;
    
    LabelRole _label;

    public static final BinaryConnectiveGetter FALSE_LEFT = new BinaryConnectiveGetter(
            ClassicalSigns.FALSE, KERuleRole.LEFT, LabelRole.SAME);

    public static final BinaryConnectiveGetter FALSE_RIGHT = new BinaryConnectiveGetter(
            ClassicalSigns.FALSE, KERuleRole.RIGHT, LabelRole.SAME);

    public static final BinaryConnectiveGetter TRUE_LEFT = new BinaryConnectiveGetter(
            ClassicalSigns.TRUE, KERuleRole.LEFT, LabelRole.SAME);

    public static final BinaryConnectiveGetter TRUE_RIGHT = new BinaryConnectiveGetter(
            ClassicalSigns.TRUE, KERuleRole.RIGHT, LabelRole.SAME);

    private BinaryConnectiveGetter(FormulaSign sign, KERuleRole role, LabelRole labelRole) {
        _sign = sign;
        _role = role;
        _label = labelRole;
    };

    public SignedFormula getSignedFormula(SignedFormulaFactory sff, FormulaFactory ff, 
            SignedFormulaList sfl) {
        return sff.createSignedFormula(_sign, ((Formula) (_role.getFormulas(sfl.get(0).getFormula())).get(0)));
    }


	@Override
	public LabelledFormula getLabelledFormula(LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff, LabelledFormulaList lfl) {
		LabelledFormula lformula = lfl.get(0);

		Formula newFormula = _role.getFormulas(lformula.getSignedFormula().getFormula()).get(0);
		SignedFormula newSignedFormula = sff.createSignedFormula(_sign, newFormula);

		return lff.createLabelledFormula(_label.getLabel(lformula), newSignedFormula);

	}    
}