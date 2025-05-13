package logicalSystems.ipl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.Context;
import logic.labelledFormulas.ContextFactory;
import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logicalSystems.c1.C1Connectives;
import logicalSystems.c1.C1Signs;
import rules.patterns.C1ConsistencyPattern;

public class LabelledFormulaTest {

	LabelledFormulaFactory lff;
	SignedFormulaFactory sff;
	FormulaFactory ff;

	Formula x;
	Formula not_x;
	Formula CX;
	Formula not_CX;
	SignedFormula auxiliaryPremise;
	Formula x_and_not_x;
	Formula CX_and_not_CX;
	Formula not__x_and_not_x;
	Formula not__CX_and_not_CX;
	
	@Before
	public void setUp() {
		sff = new SignedFormulaFactory();
		ff = new FormulaFactory();
		lff = new LabelledFormulaFactory();

		x = ff.createAtomicFormula("X");
		CX = ff.createCompositeFormula(C1Connectives.AND, ff
				.createAtomicFormula("Y"), ff.createAtomicFormula("Z"));
		not_x = ff.createCompositeFormula(C1Connectives.NOT, x);
		not_CX = ff.createCompositeFormula(C1Connectives.NOT, CX);
		auxiliaryPremise = sff.createSignedFormula(C1Signs.TRUE, not_x);

		x_and_not_x = ff.createCompositeFormula(C1Connectives.AND, x, not_x);
		CX_and_not_CX = ff
				.createCompositeFormula(C1Connectives.AND, CX, not_CX);
		not__x_and_not_x = ff.createCompositeFormula(C1Connectives.NOT,
				x_and_not_x);
		not__CX_and_not_CX = ff.createCompositeFormula(C1Connectives.NOT,
				CX_and_not_CX);

	}
	
	@Test
	public void testCreateLabelledFormula() {
		
		lff = new LabelledFormulaFactory();
		ContextFactory cf = new ContextFactory();
		
		LabelledFormula  lf = lff.createLabelledFormula(cf.getNewContext(), auxiliaryPremise);
		
		assertNotNull(lf);
	}
	
	@Test
	public void testLabelledFormulaAreEqual() {
		lff = new LabelledFormulaFactory();
		ContextFactory cf = new ContextFactory();
		
		LabelledFormula  lf = lff.createLabelledFormula(cf.getNewContext(), auxiliaryPremise);
		LabelledFormula  lfd = lff.createLabelledFormula(cf.getNewContext(), auxiliaryPremise);
		
		assertEquals(lf, lfd);
	
	}

	@Test
	public void testLabelledFormulaWithDifferentConstructorsAreEqual() {
		
		Context context = new Context();
		FormulaLabel label = context.getNewFormulaLabel();
		
		LabelledFormula  lf = lff.createLabelledFormula(label, auxiliaryPremise);
		LabelledFormula  lfd = lff.createLabelledFormula(label, sff.createSignedFormula(C1Signs.TRUE, not_x));
		
		assertEquals(lf, lfd);
	
	}
	
	@Test
	public void testToString() {
		String repr = auxiliaryPremise.toString();
		System.out.println(repr);
		
		Context context = new Context();
		LabelledFormula  lf = lff.createLabelledFormula(context.getNewFormulaLabel(), auxiliaryPremise);
		
		assertEquals(repr, "T !X ");
		assertEquals(lf.toString(), "T !X  c0");
	}
	
	
}
