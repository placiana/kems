package logicalSystems.ipl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

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
        assertFalse(c.isLowerThan(anotherLabel, label));
        assertFalse(c.isEqual(anotherLabel, label));
        assertFalse(c.isGreaterThan(label, anotherLabel));
    }

    @Test
    public void testNewLabelLessThan() {
        Context c = new Context();

        FormulaLabel label = c.getNewFormulaLabel();

        FormulaLabel anotherLabel = c.getNewFormulaLabelLowerThan(label);

        assertNotNull(label);
        assertTrue(c.isLowerThan(anotherLabel, label));
        assertFalse(c.isGreaterThan(anotherLabel, label));
        assertFalse(c.isEqual(anotherLabel, label));
        assertFalse(c.isLowerThan(label, anotherLabel));
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
        assertFalse(c.isLowerThan(anotherLabel, yetAnotherLabel));
        assertFalse(c.isEqual(anotherLabel, yetAnotherLabel));
    }

    @Test
    public void testNewLabelGreaterThanCollection() {
        Context c = new Context();

        FormulaLabel label = c.getNewFormulaLabel();
        FormulaLabel anotherLabel = c.getNewFormulaLabelGreaterThan(label);
        
        // Instantiate a collection of labels
        Collection<FormulaLabel> labelCollection = new ArrayList<FormulaLabel>();
        labelCollection.add(label);
        labelCollection.add(anotherLabel);


        FormulaLabel newLabel = c.getNewFormulaLabelGreaterThanCollection(labelCollection);
        
        assertTrue(c.isGreaterThan(newLabel, label));
        assertTrue(c.isGreaterThan(newLabel, anotherLabel));

        //

    }
    
    /* Two labels greater than another are not comparable */
    @Test
    public void testUncomparableLabels() {
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

    /* Two new labels are not comparable */
    @Test
    public void testUncomparableNewLabels() {
        Context c = new Context();

        FormulaLabel label = c.getNewFormulaLabel();
        FormulaLabel anotherLabel = c.getNewFormulaLabel();

        assertNotNull(label);
        assertNotNull(anotherLabel);

        // they are incomparable
        assertFalse(c.isGreaterThan(anotherLabel, label));
        assertFalse(c.isGreaterThan(label, anotherLabel));

        assertFalse(c.isLowerThan(label, anotherLabel));
        assertFalse(c.isLowerThan(anotherLabel, label));
        assertFalse(c.isEqual(label, anotherLabel));
    }

    // Test equal
    @Test
    public void testEqualLabels() {
        Context c = new Context();

        FormulaLabel label = c.getNewFormulaLabel();
        FormulaLabel sameLabel = label;
        FormulaLabel anotherLabel = c.getNewFormulaLabel();

        assertNotNull(label);
        assertNotNull(sameLabel);

        // they are equal
        assertTrue(c.isEqual(label, sameLabel));

        // they are not equal
        assertFalse(c.isEqual(label, anotherLabel));
    }


    @Test
    public void testAreComparable() {
        Context c = new Context();

        FormulaLabel label = c.getNewFormulaLabel();
        FormulaLabel anotherLabel = c.getNewFormulaLabelGreaterThan(label);
        FormulaLabel yetAnotherLabel = c.getNewFormulaLabelGreaterThan(label);

        assertNotNull(label);
        assertNotNull(anotherLabel);
        assertNotNull(yetAnotherLabel);

        // they are comparable
        assertTrue(c.areComparable(label, anotherLabel));
        assertTrue(c.areComparable(anotherLabel, label));

        assertTrue(c.areComparable(label, yetAnotherLabel));
        assertTrue(c.areComparable(yetAnotherLabel, label));
        
        // they are not comparable
        assertFalse(c.areComparable(anotherLabel, yetAnotherLabel));
        assertFalse(c.areComparable(yetAnotherLabel, anotherLabel));
    }
}
