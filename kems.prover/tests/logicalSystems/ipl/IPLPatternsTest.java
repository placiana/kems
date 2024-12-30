package logicalSystems.ipl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import logic.formulas.AtomicFormula;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormulaFactory;
import logicalSystems.c1.C1Signs;
import rules.patterns.ipl.IUnaryLabelledFormulaPattern;
import rules.patterns.ipl.TwoLevelCompositeFormulaPattern;

public class IPLPatternsTest {
	SignedFormulaFactory sff;
	FormulaFactory ff;
	LabelledFormulaFactory lff;

	@Before
	public void setUp() {
		sff = new SignedFormulaFactory();
		ff = new FormulaFactory();
		lff = new LabelledFormulaFactory();
	}
	
	@Test
	public void testTwoLevelCompositeFormulaPattern() {
		Formula x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula or = ff.createCompositeFormula(IPLConnectives.OR, x, y);
		Formula notOr = ff.createCompositeFormula(IPLConnectives.NOT, or);

		LabelledFormula main = lff.createLabelledFormula("c", 
				sff.createSignedFormula(C1Signs.FALSE, notOr));
		
		IUnaryLabelledFormulaPattern pattern = new TwoLevelCompositeFormulaPattern(
				IPLConnectives.NOT, IPLConnectives.OR);

		assertTrue(pattern.matches(main));
		
	}

	@Test
	public void testTwoLevelCompositeFormulaPatternAtomicNotMatch() {
		Formula x = ff.createAtomicFormula("X");

		LabelledFormula main = lff.createLabelledFormula("c", 
				sff.createSignedFormula(C1Signs.FALSE, x));
		
		IUnaryLabelledFormulaPattern pattern = new TwoLevelCompositeFormulaPattern(
				IPLConnectives.NOT, IPLConnectives.OR);

		assertFalse(pattern.matches(main));
		
	}

	@Test
	public void testTwoLevelCompositeFormulaPatternAtomicSubFormulaNotMatch() {
		Formula x = ff.createAtomicFormula("X");
		Formula notX = ff.createCompositeFormula(IPLConnectives.NOT, x);

		LabelledFormula main = lff.createLabelledFormula("c", 
				sff.createSignedFormula(C1Signs.FALSE, notX));
		
		IUnaryLabelledFormulaPattern pattern = new TwoLevelCompositeFormulaPattern(
				IPLConnectives.NOT, IPLConnectives.OR);

		assertFalse(pattern.matches(main));
		
	}
	
}
