package logicalSystems.ipl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logic.labelledFormulas.Context;
import logic.labelledFormulas.FormulaLabel;

public class ContextTest {
	

	@Test
	public void testCreation() {
		Context c = new Context();
		
		assertNotNull(c);
	}

	@Test
	public void testNewLabel() {
		Context c = new Context();
		
		FormulaLabel label = c.getNewFormulaLabel();
		
		assertNotNull(label);
	}

	@Test
	public void testNewLabelGreaterThan() {
		Context c = new Context();
		
		FormulaLabel label = c.getNewFormulaLabel();
		
		FormulaLabel anotherLabel = c.getNewFormulaLabelGreaterThan(label);
		
		assertNotNull(label);
		assertTrue(c.isGreaterThan(anotherLabel, label));
	}

	@Test
	public void testTwoNewLabelGreaterThan() {
		Context c = new Context();
		
		FormulaLabel label = c.getNewFormulaLabel();
		
		FormulaLabel anotherLabel = c.getNewFormulaLabelGreaterThan(label);
		FormulaLabel yetAnotherLabel = c.getNewFormulaLabelGreaterThan(label);
		
		assertNotNull(label);
		assertTrue(c.isGreaterThan(anotherLabel, label));
		assertTrue(c.isGreaterThan(yetAnotherLabel, label));
		
		// they are incomparable
		assertFalse(c.isGreaterThan(anotherLabel, yetAnotherLabel));
	}


}
