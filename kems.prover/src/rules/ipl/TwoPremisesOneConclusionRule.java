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
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.getters.KESignedFormulaGetter;
import rules.getters.SubformulaGetter;
import rules.patterns.ISubformulaPattern;
import rules.patterns.ipl.IBinarySignedFormulaPattern;


/**
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 *  
 */
public class TwoPremisesOneConclusionRule extends OneConclusionRule {

	IBinarySignedFormulaPattern _premise;

	public TwoPremisesOneConclusionRule(String name,
			IBinarySignedFormulaPattern premise, KELabelledAction conclusion) {
		super(name, conclusion);
		_premise = premise;
	}



	
	/**
	 * @param sff
	 * @param ff
	 * @param sfMain
	 * @return
	 */
	public LabelledFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
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

	//    /**
	//     * @param sf
	//     * @param order
	//     * @return
	//     */
	//    public Formula getMainMatch(SignedFormula sf, int order) {
	//        return ((SubformulaPattern) _premise).getMainMatch(sf, order);
	//    }

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
	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff,
			FormulaFactory ff, SignedFormulaList sfl, CompositeFormula f) {
//		SignedFormula mainPremise = sfl.get(0);
//		SignedFormula auxPremise = sfl.get(1);
		SignedFormulaList result = new SignedFormulaList();
		result.add(((SubformulaGetter) getConclusion().getContent())
				.getSignedFormula(sff, ff, sfl, f));
		return result;
	}

	@Override
	public LabelledFormulaList getPossibleConclusions(
			LabelledFormulaFactory lff, SignedFormulaFactory sff, FormulaFactory ff,
			LabelledFormulaList lfl) {

		LabelledFormula mainPremise = lfl.get(0);
		LabelledFormula auxPremise = lfl.get(1);
		if (_premise.matches(mainPremise, auxPremise)) {
			LabelledFormulaList result = new LabelledFormulaList();
			result.add(( getConclusion().getLabelledFormula(lff, sff, ff, lfl)))
					;
			return result;
		} else
			return null;
		}




	@Override
	public SignedFormulaList getPossibleConclusions(SignedFormulaFactory sff, FormulaFactory ff,
			SignedFormulaList sfl) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}