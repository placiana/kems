package logicalSystems.ipl;

import java.util.Iterator;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaBuilder;
import logic.signedFormulas.SignedFormulaCreator;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import logicalSystems.c1.C1RuleStructures;
import logicalSystems.c1.C1Signs;
import logicalSystems.classicalLogic.ClassicalConnectives;
import logicalSystems.classicalLogic.ClassicalSigns;
import main.newstrategy.mbc.simple.MBCSimpleStrategy;
import main.proofTree.SignedFormulaNode;
import main.proofTree.SignedFormulaNodeState;
import main.proofTree.origin.NamedOrigin;
import main.strategy.ClassicalProofTree;
import main.strategy.applicator.OnePremiseRuleApplicator;
import main.strategy.simple.FormulaReferenceClassicalProofTree;
import main.tableau.Method;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import proverinterface.RuleStructureFactory;

public class OnePremiseRuleApplicatorTest {

	private SignedFormula tTopFormula;
	SignedFormulaFactory sff;
	FormulaFactory ff;

	LabelledFormulaFactory lff;

	@Before
	public void setUp() throws Exception {
		sff = new SignedFormulaFactory();
		ff = new FormulaFactory();
		lff = new LabelledFormulaFactory();
	}

	@Test
	public void testOnePremiseApplicator() {

		Method method = new Method(RuleStructureFactory.createRulesStructure("C1"));
		OnePremiseRuleApplicator x = new OnePremiseRuleApplicator(
				new MBCSimpleStrategy(method),
				C1RuleStructures.ONE_PREMISE_RULES);

		Formula xx = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, xx, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, x_or_y));

		SignedFormulaCreator sfc = new SignedFormulaCreator("sats5");
		SignedFormulaList sfl = new SignedFormulaList();

		sfl.add(main);
		sfl.add(sfc.parseString("T A1"));
		sfl.add(sfc.parseString("T !(A2&!B2)"));
		sfl.add(sfc.parseString("T A3&B3"));
		sfl.add(sfc.parseString("F A4|B4"));
		sfl.add(sfc.parseString("F A5->B5"));
		sfl.add(sfc.parseString("T !!A6"));

		tTopFormula = sfc.getSignedFormulaFactory().createSignedFormula(ClassicalSigns.TRUE,
				sfc.getFormulaFactory().createCompositeFormula(ClassicalConnectives.TOP));

		ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
				new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

		SignedFormulaBuilder sfb = new SignedFormulaBuilder(sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());

		Iterator<SignedFormula> it = sfl.iterator();

		while (it.hasNext()) {
			cpt.addLast(
					new SignedFormulaNode(
							it.next(), 
							SignedFormulaNodeState.NOT_ANALYSED, 
							NamedOrigin.PROBLEM));
		}
//      System.out.println(cpt);
		x.applyAll(cpt, sfb);

      System.out.println(cpt.getNumberOfNodes());
		assertTrue(cpt.getNumberOfNodes() == 14);

//      System.out.println(cpt);

	}
}
