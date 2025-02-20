/*
 * Created on 10/11/2004
 *
 */
package rules.ipl;

import logic.formulas.CompositeFormula;
import logic.formulas.FormulaFactory;
import logic.formulas.FormulaList;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.getters.SubformulaGetter;
import rules.patterns.ISubformulaPattern;
import rules.patterns.ipl.IBinarySignedFormulaPattern;

/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 * 
 */
public class TwoPremisesOneConclusionRule extends OneConclusionIPLRule {

	IBinarySignedFormulaPattern _premise;

	public TwoPremisesOneConclusionRule(String name, IBinarySignedFormulaPattern premise, KELabelledAction conclusion) {
		super(name, conclusion);
		_premise = premise;
	}

	/**
	 * @param sff
	 * @param ff
	 * @param sfMain
	 * @return
	 */
	public SignedFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
			FormulaFactory ff, SignedFormula sfMain) {
		return _premise.getAuxiliaryCandidates(lff, sff, ff, sfMain);
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
			result.add((getConclusion().getLabelledFormula(sff, ff, sfl)));
		} 
		return result;
	}
}