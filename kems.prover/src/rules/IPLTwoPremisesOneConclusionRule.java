package rules;

import logic.formulas.CompositeFormula;
import logic.formulas.FormulaFactory;
import logic.formulas.FormulaList;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.getters.SubformulaGetter;
import rules.ipl.KELabelledAction;
import rules.patterns.IBinarySignedFormulaPattern;
import rules.patterns.ISubformulaPattern;

public class IPLTwoPremisesOneConclusionRule extends TwoPremisesOneConclusionRule {

	public IPLTwoPremisesOneConclusionRule(String name, IBinarySignedFormulaPattern premise, KEAction conclusion) {
		super(name, premise, conclusion);
		// TODO Auto-generated constructor stub
	}

	public IPLTwoPremisesOneConclusionRule(String name, IBinarySignedFormulaPattern premise, KELabelledAction conclusion) {
		super(name, premise, conclusion);
		_premise = premise;
	}
	
	/**
	 * @param sfMain
	 * @return
	 */
	public boolean matchesMain(LabelledFormula sfMain) {
		return _premise.matchesMain(sfMain);
	}

	// /**
	// * @param sf
	// * @param order
	// * @return
	// */
	// public Formula getMainMatch(SignedFormula sf, int order) {
	// return ((SubformulaPattern) _premise).getMainMatch(sf, order);
	// }

	/**
	 * @param sf
	 * @return
	 */
	public FormulaList getMainMatches(SignedFormula sf) {
		return ((ISubformulaPattern) _premise).getMainMatches(sf);
	}

	/**
	 * @param signedFormulaFactory
	 * @param formulaFactory
	 * @param sflToApply
	 * @param f
	 * @return
	 */
	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff, SignedFormulaList sfl,
			CompositeFormula f) {
		SignedFormulaList result = new SignedFormulaList();
		result.add(((SubformulaGetter) getConclusion().getContent()).getSignedFormula(sff, ff, sfl, f));
		return result;
	}


	@Override
	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl) {
		SignedFormulaList result = new SignedFormulaList();
		LabelledFormula mainPremise = (LabelledFormula) sfl.get(0);
		LabelledFormula auxPremise = (LabelledFormula) sfl.get(1);
		if (_premise.matches(mainPremise, auxPremise)) {
			result.add((((KELabelledAction)getConclusion()).getLabelledFormula(sff, ff, sfl)));
		} 
		return result;
	}	
}
