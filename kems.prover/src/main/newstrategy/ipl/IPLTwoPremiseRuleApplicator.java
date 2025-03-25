/*
 * Created on 01/11/2005
 *
 */
package main.newstrategy.ipl;

import java.util.Iterator;
import java.util.List;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.PBCandidateList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaBuilder;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import main.newstrategy.ISimpleStrategy;
import main.proofTree.SignedFormulaNode;
import main.proofTree.SignedFormulaNodeState;
import main.proofTree.iterator.IProofTreeVeryBasicIterator;
import main.strategy.ClassicalProofTree;
import main.strategy.applicator.IRuleApplicator;
import rules.KERuleRole;
import rules.Rule;
import rules.ipl.TwoPremisesOneConclusionRule;
import rules.structures.ConnectiveRoleSignRuleList;
import rules.structures.IPLConnectiveRoleSignRuleList;

/**
 * A two premise rule applicator
 * 
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 */
public class IPLTwoPremiseRuleApplicator implements IRuleApplicator {

	private ISimpleStrategy strategy;

	private String ruleListName;

	/**
	 * @param strategy
	 * @param sfb
	 */
	public IPLTwoPremiseRuleApplicator(ISimpleStrategy strategy,
			String ruleListName) {
		super();
		this.strategy = strategy;
		this.ruleListName = ruleListName;
	}

	private PBCandidateList mainCandidates;
	private int counterMainCandidates;

	/*
	 * (non-Javadoc)
	 * 
	 * @seemain.strategy.applicator.IRuleApplicator#applyAll(main.strategy.
	 * ClassicalProofTree, logic.signedFormulas.SignedFormulaBuilder)
	 */
	public boolean applyAll(ClassicalProofTree current, SignedFormulaBuilder sfb) {
		// faz o seguinte:
		// para cada main, procurar referencias a um dos dois possiveis
		// auxiliary candidates
		// se encontrar, entao aplicar
		boolean hasApplied = false;

		IPLConnectiveRoleSignRuleList twoPremiseRules = (IPLConnectiveRoleSignRuleList) strategy
				.getMethod().getRules().get(ruleListName);

		SignedFormula mainCandidate;

		// TODO EH ISSO MESMO?
		initializeMainCandidates(current, null);

		while ((mainCandidate = nextMainCandidate(strategy.getProofTree(), null)) != null) {

//			System.out.println("MAIN CANDIDATE: " + mainCandidate);

			Connective mainConnective = ((CompositeFormula) mainCandidate
					.getFormula()).getConnective();
			FormulaSign mainSign = mainCandidate.getSign();


			/*
			Rule left_rule = twoPremiseRules.get(mainConnective,
					KERuleRole.LEFT, mainSign);
			Rule right_rule = twoPremiseRules.get(mainConnective,
					KERuleRole.RIGHT, mainSign);
			*/
			
			List<Rule> leftRules = twoPremiseRules.getMany(mainConnective,
					KERuleRole.LEFT, mainSign);
			List<Rule> rightRules = twoPremiseRules.getMany(mainConnective,
					KERuleRole.RIGHT, mainSign);

			for (Iterator<Rule> it = leftRules.iterator(); it.hasNext();) {
				if (hasApplied)
					break;
				Rule left_rule = it.next();
				for (Iterator<Rule> it2 = rightRules.iterator(); it2.hasNext();) {
					if (hasApplied)
						break;


					Rule right_rule = it2.next();
					boolean appliedLeft = false;
					// verifies if left rule can be applied. If it can apply it.
					if (left_rule != null) {
						appliedLeft = tryToApplyTwoPremiseRule(strategy
								.getCurrent(), sfb, mainCandidate, left_rule);
						hasApplied = hasApplied || appliedLeft;
						if (appliedLeft)
							counterMainCandidates--;
					}

					// verifies if right rule can be applied. If it can apply it.
					if (right_rule != null) {
						boolean appliedRight = tryToApplyTwoPremiseRule(strategy
								.getCurrent(), sfb, mainCandidate, right_rule);
						hasApplied = hasApplied || appliedRight;
						if (appliedRight &!appliedLeft)
							counterMainCandidates--;
					}
				}
			}

			/*
			boolean appliedLeft = false;
			// verifies if left rule can be applied. If it can apply it.
			if (left_rule != null) {
				appliedLeft = tryToApplyTwoPremiseRule(strategy
						.getCurrent(), sfb, mainCandidate, left_rule);
				hasApplied = hasApplied || appliedLeft;
				if (appliedLeft)
					counterMainCandidates--;
			}

			// verifies if right rule can be applied. If it can apply it.
			if (right_rule != null) {
				boolean appliedRight = tryToApplyTwoPremiseRule(strategy
						.getCurrent(), sfb, mainCandidate, right_rule);
				hasApplied = hasApplied || appliedRight;
				if (appliedRight &!appliedLeft)
					counterMainCandidates--;
			}
			*/

		}

		return hasApplied;
	}

	/**
	 * @param current
	 * @param object
	 */
	private void initializeMainCandidates(ClassicalProofTree proofTree,
			Object object) {
		mainCandidates = proofTree.getPBCandidates();
		counterMainCandidates = 0;
	}

	protected SignedFormula nextMainCandidate(ClassicalProofTree proofTree,
			SignedFormula auxCandidate) {
//		System.out.println(counterMainCandidates + ": " + mainCandidates);
		if (counterMainCandidates < mainCandidates.size()) {
//			System.out.println("Chosen:"
//					+ mainCandidates.get(counterMainCandidates));
			return mainCandidates.get(counterMainCandidates++);
		}
		// else {
		// System.out.println("Chosen: none");
		// }

		return null;
	}


	/**
	 * @param proofTree
	 * @param sfb
	 * @param mainCandidate
	 * @param rule
	 * @return
	 */
	private boolean tryToApplyTwoPremiseRule(ClassicalProofTree proofTree,
			SignedFormulaBuilder sfb, SignedFormula mainCandidate, Rule rule) {

		boolean hasApplied = false;

		TwoPremisesOneConclusionRule aRule = ((TwoPremisesOneConclusionRule) rule);

		SignedFormulaList sfl = aRule.getAuxiliaryCandidates(
				(LabelledFormulaFactory) sfb.getSignedFormulaFactory(),
				sfb.getSignedFormulaFactory(), 
				sfb.getFormulaFactory(),
				mainCandidate);

		/*
		public SignedFormulaList getAuxiliaryCandidates(LabelledFormulaFactory lff, SignedFormulaFactory sff,
				FormulaFactory ff, SignedFormula sfMain) {
			return _premise.getAuxiliaryCandidates(lff, sff, ff, sfMain);
		}
		*/
		
		SignedFormulaList result = getReferences(proofTree, sfl);

		if (result.size() > 0) {
			SignedFormula auxCandidate = (SignedFormula) result.get(0);

			hasApplied = applyTwoPremiseRule(proofTree, sfb, mainCandidate,
					aRule, result, auxCandidate);

		}

		return hasApplied;

	}

	/**
	 * Applies a two premise rulem given:
	 * 
	 * @param proofTree
	 * @param sfb
	 * @param mainCandidate
	 * @param rule
	 * @param aRule
	 * @param sfl
	 * @param auxCandidate
	 */
	private boolean applyTwoPremiseRule(ClassicalProofTree proofTree,
			SignedFormulaBuilder sfb, SignedFormula mainCandidate,
			TwoPremisesOneConclusionRule aRule, SignedFormulaList sfl,
			SignedFormula auxCandidate) {
		boolean hasApplied = false;
		sfl.add(0, mainCandidate);
		SignedFormulaList conclusion = (aRule.getPossibleConclusions(sfb
				.getSignedFormulaFactory(), sfb.getFormulaFactory(), sfl));

		// TODO sup�e apenas uma conclus�o
		if (conclusion!=null && proofTree.getNode(conclusion.get(0)) == null) {
			proofTree.addLast(new SignedFormulaNode((SignedFormula) conclusion
					.get(0), SignedFormulaNodeState.NOT_ANALYSED, strategy
					.createOrigin(aRule, proofTree.getNode(mainCandidate),
							proofTree.getNode(auxCandidate))));
			// System.err.println(proofTree.getBranchId()+ " " +
			// conclusion.get(0));
			// try {
			// System.in.read();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			
//			System.out.println("rule:"+aRule);
			// removes main from the list of PB candidates
			strategy.getCurrent().removeFromPBCandidates(mainCandidate,
					SignedFormulaNodeState.ANALYSED);

			hasApplied = true;
		}

		return hasApplied;
	}

	protected SignedFormulaList getReferences(ClassicalProofTree proofTree,
			SignedFormulaList sflInput) {

		// TODO do as in simple strategy? keep it in memory?
		// TODO do it top-down?

		SignedFormulaList sflResult = new SignedFormulaList();

		// iterates (bottom up) over the branch
		IProofTreeVeryBasicIterator it = proofTree.getTopDownIterator();

		while (it.hasNext()) {

			SignedFormulaNode sfn = (SignedFormulaNode) it.next();

			SignedFormula sf = (SignedFormula) sfn.getContent();
			//if (sflInput.contains((SignedFormula) sfn.getContent())) {
			if (formulaLevelContains(sflInput,sf)) {
				sflResult.add(sf);
			}

		}
		return sflResult;
	}
	
	private boolean formulaLevelContains(SignedFormulaList aList, SignedFormula aSignedFormula) {
		
		for (SignedFormula listFormula : aList.getList()) {
			if (listFormula.getFormula().equals(aSignedFormula.getFormula()) &&
				listFormula.getSign().equals(aSignedFormula.getSign())	) {
				return true;
			}
			
		}
		
		return false;
	}

}
