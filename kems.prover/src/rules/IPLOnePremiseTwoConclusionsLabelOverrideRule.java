/*
 * Created on 10/11/2004
 *
 */
package rules;

import logic.formulas.FormulaFactory;
import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.ipl.KELabelledAction;
import rules.ipl.labels.LabelGetter;
import rules.patterns.ipl.IUnaryLabelledFormulaPattern;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 * 
 */
public class IPLOnePremiseTwoConclusionsLabelOverrideRule extends IPLOnePremiseTwoConclusionsRule {

	IUnaryLabelledFormulaPattern _premise;

	KELabelledAction _conclusion1, _conclusion2;

	private LabelGetter _labelGetter;

	public IPLOnePremiseTwoConclusionsLabelOverrideRule(String name, IUnaryLabelledFormulaPattern premise,
			KELabelledAction conclusion1, KELabelledAction conclusion2, LabelGetter labelGetter) {
		super(name, premise, conclusion2, conclusion2);
		_premise = premise;
		_conclusion1 = conclusion1;
		_conclusion2 = conclusion2;
		_labelGetter = labelGetter;
	}

	@Override
	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl) {
		return this.getPossibleConclusions((LabelledFormulaFactory) sff, sff, ff, sfl);
	}

	public SignedFormulaList getPossibleConclusions(LabelledFormulaFactory lff, SignedFormulaFactory sff,
			FormulaFactory ff, SignedFormulaList lfl) {
		LabelledFormula premise = (LabelledFormula) lfl.get(0);
		if (_premise.matches(premise)) {
			SignedFormulaList l = new SignedFormulaList();
			FormulaLabel label = _labelGetter.getLabel(lfl);
			l.add((_conclusion1.getLabelledFormula(sff, ff, lfl, label)));
			l.add((_conclusion2.getLabelledFormula(sff, ff, lfl, label)));
			return l;
		} else
			return null;
	}

}