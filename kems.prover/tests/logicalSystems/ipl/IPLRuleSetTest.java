package logicalSystems.ipl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.Context;
import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import logicalSystems.c1.C1Connectives;
import logicalSystems.c1.C1Rules;
import logicalSystems.c1.C1Signs;

import org.junit.Before;
import org.junit.Test;


import rules.Rule;
import rules.OnePremiseTwoConclusionsRule;
import rules.TwoPremisesOneConclusionRule;
import rules.ipl.IPLRule;
import rules.patterns.C1ConsistencyAnyBinaryConnectivePattern;
import rules.patterns.C1ConsistencyPattern;
import rules.patterns.C1SignConsistencyAnyBinaryConnectivePattern;
import rules.patterns.C1SignConsistencyPattern;
import rules.patterns.C1_Sign_T_NOT_1_Pattern;

public class IPLRuleSetTest {

	SignedFormulaFactory sff;
	FormulaFactory ff;

	LabelledFormulaFactory lff;

	Formula x;
	Formula not_x;
	Formula CX;
	Formula not_CX;
	SignedFormula auxiliaryPremise;

	Formula x_and_not_x;
	Formula CX_and_not_CX;
	Formula not__x_and_not_x;
	Formula not__CX_and_not_CX;

	LabelledFormula zero_not_x;

	@Before
	public void setUp() {
		sff = new SignedFormulaFactory();
		ff = new FormulaFactory();
		lff = new LabelledFormulaFactory();

		x = ff.createAtomicFormula("X");
		CX = ff.createCompositeFormula(C1Connectives.AND, ff.createAtomicFormula("Y"), ff.createAtomicFormula("Z"));
		not_x = ff.createCompositeFormula(C1Connectives.NOT, x);
		not_CX = ff.createCompositeFormula(C1Connectives.NOT, CX);
		auxiliaryPremise = sff.createSignedFormula(C1Signs.TRUE, not_x);

		x_and_not_x = ff.createCompositeFormula(C1Connectives.AND, x, not_x);
		CX_and_not_CX = ff.createCompositeFormula(C1Connectives.AND, CX, not_CX);
		not__x_and_not_x = ff.createCompositeFormula(C1Connectives.NOT, x_and_not_x);
		not__CX_and_not_CX = ff.createCompositeFormula(C1Connectives.NOT, CX_and_not_CX);

		zero_not_x = lff.createLabelledFormula("c", auxiliaryPremise);

	}

	@Test
	public void testRule1FalseOR() {
		rules.ipl.OnePremiseTwoConclusionsRule falseOrRule = IPLRules.F_OR;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, x_or_y));
		SignedFormulaList lfl = new SignedFormulaList();
		lfl.add(main);

		SignedFormulaList conclusions;
		conclusions = falseOrRule.getPossibleConclusions(lff, ff, lfl);
		assertTrue(conclusions.size() == 2);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));
		assertTrue(conclusions.get(1).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSign().equals(C1Signs.FALSE));
		assertTrue(conclusions.get(1).getSign().equals(C1Signs.FALSE));

	}

	@Test
	public void testRule2TrueAnd() {
        /*
        T (A and B): Ci
        -----------------
        T A : Ci
        T B : Ci
        
        */
	    rules.ipl.OnePremiseTwoConclusionsRule rule = IPLRules.T_AND;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_and_y = ff.createCompositeFormula(IPLConnectives.AND, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_and_y));
		SignedFormulaList lfl = new SignedFormulaList();
		lfl.add(main);

		SignedFormulaList conclusions;
		conclusions = rule.getPossibleConclusions(lff, ff, lfl);
		assertTrue(conclusions.size() == 2);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
		assertTrue(conclusions.get(1).getSign().equals(C1Signs.TRUE));
		
		assertEquals(conclusions.get(0).getLabel(), conclusions.get(1).getLabel());
		assertEquals(conclusions.get(0).getLabel(), main.getLabel());

	}

	@Test
	public void testRule3TrueOr() {
		Rule rule = IPLRules.X_OR_F_LEFT;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_or_y));
		LabelledFormula aux = lff.createLabelledFormula(main.getLabel().getNextFormulaLabel(),
				sff.createSignedFormula(C1Signs.FALSE, x));

		SignedFormulaList lfl = new SignedFormulaList();
		lfl.add(main);
		lfl.add(aux);

		SignedFormulaList conclusions;
		conclusions = rule.getPossibleConclusions(lff, ff, lfl);
		assertTrue(conclusions.size() == 1);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
		assertTrue(conclusions.get(0).getFormula().equals(y));

	}

	@Test
	public void testRule4TrueOrFalseRight() {
		Rule rule = IPLRules.T_OR_F_RIGHT;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_or_y));
		LabelledFormula aux = lff.createLabelledFormula(main.getLabel().getNextFormulaLabel(),
				sff.createSignedFormula(C1Signs.FALSE, y));

		SignedFormulaList lfl = new SignedFormulaList();
		lfl.add(main);
		lfl.add(aux);

		SignedFormulaList conclusions;
		conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
		assertTrue(conclusions.size() == 1);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
		assertTrue(conclusions.get(0).getFormula().equals(x));

	}


	@Test
	public void testRule5() {
		/*
		T not(A or B): Ci
		-----------------
		T not A : Ci
		T not B : Ci
		
		*/
		IPLRule rule = IPLRules.T_NOT_A_OR_B;
		Formula x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula or = ff.createCompositeFormula(IPLConnectives.OR, x, y);
		Formula notOr = ff.createCompositeFormula(IPLConnectives.NOT, or);
	
		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, notOr));
		
		SignedFormulaList lfl = new SignedFormulaList();
		lfl.add(main);
		
		SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
		
		assertTrue(conclusions.size() >= 1);

		
	}


	@Test
	public void testRule6() {
		/*
		T A or B: Ci
		T not A : Cj
		Ci <= Cj or Cj <= Ci
		-----------------
		T  B : Ci
		
		*/
	    
	    Rule rule = IPLRules.T_A_OR_B;
		
		Formula x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula or = ff.createCompositeFormula(IPLConnectives.OR, x, y);
		Formula not = ff.createCompositeFormula(IPLConnectives.NOT, x);
		
		SignedFormula sOr = sff.createSignedFormula(C1Signs.TRUE, or);
		SignedFormula sNot = sff.createSignedFormula(C1Signs.TRUE, not);
		
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        FormulaLabel auxLabel = c.getNewFormulaLabelGreaterThan(mainLabel);
		
		LabelledFormula main = lff.createLabelledFormula(mainLabel, sOr);
		LabelledFormula aux = lff.createLabelledFormula(auxLabel, sNot);

		SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(y));
        assertTrue(conclusions.get(0).getLabel().equals(mainLabel));
        
	}

	   @Test
	    public void testRule6AlternateLabels() {
	        /*
	        T A or B: Ci
	        T not A : Cj
	        Ci <= Cj or Cj <= Ci
	        -----------------
	        T  B : Ci
	        
	        */
	        
	        Rule rule = IPLRules.T_A_OR_B;
	        
	        Formula x = ff.createAtomicFormula("X");
	        Formula y = ff.createAtomicFormula("Y");
	        Formula or = ff.createCompositeFormula(IPLConnectives.OR, x, y);
	        Formula not = ff.createCompositeFormula(IPLConnectives.NOT, x);
	        
	        SignedFormula sOr = sff.createSignedFormula(C1Signs.TRUE, or);
	        SignedFormula sNot = sff.createSignedFormula(C1Signs.TRUE, not);
	        
	        Context c = new Context();
	        FormulaLabel auxLabel = c.getNewFormulaLabel();
	        FormulaLabel mainLabel = c.getNewFormulaLabelGreaterThan(auxLabel);
	        
	        LabelledFormula main = lff.createLabelledFormula(mainLabel, sOr);
	        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sNot);

	        SignedFormulaList lfl = new SignedFormulaList();
	        lfl.add(main);
	        lfl.add(aux);

	        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
	        
	        assertTrue(conclusions.size() >= 1);
	        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
	        assertTrue(conclusions.get(0).getFormula().equals(y));
	        assertTrue(conclusions.get(0).getLabel().equals(mainLabel));
	        
	    }
	

    @Test
    public void testRule7() {
        /*
         * T A or B : CI
         * T not B: CJ
         * ci <= cj or cj <= ci
         * --------------------
         * TA: CI
         */
        
        Rule rule = IPLRules.T_A_OR_B_NOT_B;
        
        Formula x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        Formula or = ff.createCompositeFormula(IPLConnectives.OR, x, y);
        Formula not = ff.createCompositeFormula(IPLConnectives.NOT, y);
        
        SignedFormula sOr = sff.createSignedFormula(C1Signs.TRUE, or);
        SignedFormula sNot = sff.createSignedFormula(C1Signs.TRUE, not);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        FormulaLabel auxLabel = c.getNewFormulaLabelGreaterThan(mainLabel);
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sOr);
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sNot);

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(x));
        assertTrue(conclusions.get(0).getLabel().equals(mainLabel));
    }
    
    @Test
    public void testRule8() {
    /*
     * F A and B : cJ
     * T A : cI
     * cI <= cJ
     * ----------
     * F B : cJ
     */
        Rule rule = IPLRules.F_AND_LEFT;
        
        Formula x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        Formula and = ff.createCompositeFormula(IPLConnectives.AND, x, y);
        //Formula not = ff.createCompositeFormula(IPLConnectives.NOT, y);
        
        SignedFormula sOr = sff.createSignedFormula(C1Signs.FALSE, and);
        SignedFormula sNot = sff.createSignedFormula(C1Signs.TRUE, x);
        
        // main label greater than aux label
        Context c = new Context();
        FormulaLabel auxLabel = c.getNewFormulaLabel();
        FormulaLabel mainLabel = c.getNewFormulaLabelGreaterThan(auxLabel);
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sOr);
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sNot);

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.FALSE));
        assertTrue(conclusions.get(0).getFormula().equals(y));
        assertTrue(conclusions.get(0).getLabel().equals(mainLabel));
    }
    
	@Test
    public void testRule9() {
        /*
        F A and B: Cj
        T B : Ci
        Ci <= Cj
        -----------------
        F A : Cj
        
        */
       
        Rule rule = IPLRules.X_AND_T_RIGHT;
        
        Formula x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        Formula and = ff.createCompositeFormula(IPLConnectives.AND, x, y);
        //Formula not = ff.createCompositeFormula(IPLConnectives.NOT, y);
        
        SignedFormula sAnd = sff.createSignedFormula(C1Signs.FALSE, and);
        SignedFormula sY = sff.createSignedFormula(C1Signs.TRUE, y);
        
        Context c = new Context();
        FormulaLabel auxLabel = c.getNewFormulaLabel();
        FormulaLabel mainLabel = c.getNewFormulaLabelGreaterThan(auxLabel);
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sAnd);
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sY);

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        assertTrue(conclusions.size() >= 1);

        assertTrue(conclusions.get(0).getSign().equals(C1Signs.FALSE));
        assertTrue(conclusions.get(0).getFormula().equals(x));
        
        // Labels
        assertEquals(main.getLabel(), conclusions.get(0).getLabel());
        assertTrue(aux.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertNotEquals(main.getLabel(), aux.getLabel());
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(aux.getLabel()));

        
   }

	
	@Test
    public void testRule10() {
	    // Regla 10
	    /*
	    T not (A and B) : cI
	    T A : cJ
	    ci <= ck and cj <= cK
	    -----------------
	    T not B : cK
	    */
       
        Rule rule = IPLRules.T_NOT_A_AND_B;
        
        Formula x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        Formula and = ff.createCompositeFormula(IPLConnectives.AND, x, y);
        Formula not = ff.createCompositeFormula(IPLConnectives.NOT, and);
        
        SignedFormula sMain = sff.createSignedFormula(C1Signs.TRUE, not);
        SignedFormula sY = sff.createSignedFormula(C1Signs.TRUE, x);
        
        Context c = new Context();
        FormulaLabel conclussionLabel = c.getNewFormulaLabel();
        FormulaLabel auxLabel = c.getNewFormulaLabelGreaterThan(conclussionLabel);
        FormulaLabel mainLabel = c.getNewFormulaLabelGreaterThan(conclussionLabel);
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sMain);
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sY);

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        Formula not_y = ff.createCompositeFormula(IPLConnectives.NOT, y);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(not_y));
        
        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertTrue(aux.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertNotEquals(main.getLabel(), aux.getLabel());
        
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(aux.getLabel()));
        
   }	

    @Test
    public void testRule11() {
        // Regla 11
        /*
        T not (A and B) : cI
        T B : cJ
        ci <= ck and cj <= cK
        -----------------
        T not A : cK
        */
        Rule rule = IPLRules.T_NOT_AND_LEFT; // Replace
        
        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        
        Formula and = ff.createCompositeFormula(IPLConnectives.AND, x, y);
        Formula not_and = ff.createCompositeFormula(IPLConnectives.NOT, and);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        FormulaLabel auxLabel = c.getNewFormulaLabel();
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.TRUE, not_and));
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sff.createSignedFormula(C1Signs.TRUE, y));

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        System.out.println(conclusions);

        Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(not_x));

        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertTrue(aux.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertNotEquals(main.getLabel(), aux.getLabel());
        
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(aux.getLabel()));        
    }   

    
    
    @Test
    public void testRule12() {
        // Regla 12
        /*
        T A imples B : cI
        T A : cJ
        ci <= ck and cj <= cK
        -----------------
        T B : cK
        */
        Rule rule = IPLRules.T_IMPLIES_LEFT; // Replace
        
        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        
        Formula implies = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        FormulaLabel auxLabel = c.getNewFormulaLabel();
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.TRUE, implies));
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sff.createSignedFormula(C1Signs.TRUE, x));

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        assertTrue(conclusions.size() == 1);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(y));
        
        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertTrue(aux.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(aux.getLabel()));
    }    	

    @Test
    public void testRule13() {
        // Regla 13
        /*
        T A imples B : cI
        F B : cJ
        ci <= cJ
        -----------------
        F A : cJ
        */
        Rule rule = IPLRules.X_IMPLIES_F_RIGHT;
        
        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        
        Formula implies = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);
        
        LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, implies));
        LabelledFormula aux = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, y));

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        assertTrue(conclusions.size() == 1);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.FALSE));
        assertTrue(conclusions.get(0).getFormula().equals(x));

    }   

    
    
    @Test
    public void testRule14() {
        // Regla 14
        /*
        F A imples B: cI
        -----------------
        T A : cJ
        F B: cJ
        cI <= cJ
        */
        IPLRule rule = IPLRules.F_A_IMPLIES_B_TA_FB; // Replace
        
        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        
        Formula implies = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.FALSE, implies));
        
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);

        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        assertTrue(conclusions.size() == 2);
        
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(x));
        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        
        assertTrue(conclusions.get(1).getSign().equals(C1Signs.FALSE));
        assertTrue(conclusions.get(1).getFormula().equals(y));
        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(1).getLabel()));
        
        assertTrue(c.isEqual(conclusions.get(0).getLabel(), conclusions.get(1).getLabel()));
    }    
    
    @Test
    public void testRule15() {
        // Regla 15
        /*
        T not (A implies B) : cI
        -----------------
        T A: cK
        T not B : cK
        cI <= cK
        */
        IPLRule rule = IPLRules.T_NOT_A_IMPLIES_B_TA_FB; // Replace
        
        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        
        Formula implies = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);
        Formula not_implies = ff.createCompositeFormula(IPLConnectives.NOT, implies);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.TRUE, not_implies));
        
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);

        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        assertTrue(conclusions.size() == 2);
        
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(x));

        Formula not_y = ff.createCompositeFormula(IPLConnectives.NOT, y);
        assertTrue(conclusions.get(1).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(1).getFormula().equals(not_y));
    }	

    
    
    @Test
    public void testRule16() {
        // Regla 16
        /*
        T (A implies B) : cI
        T not B : cJ
        ci <= cj
        -----------------
        T not A : cJ
        */
        Rule rule = IPLRules.T_X_IMPLIES_Y_NOT_Y;
        
        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        
        Formula implies = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);
        Formula not_y = ff.createCompositeFormula(IPLConnectives.NOT, y);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        LabelledFormula main = lff.createLabelledFormula(
        		mainLabel, sff.createSignedFormula(C1Signs.TRUE, implies));
        LabelledFormula aux = lff.createLabelledFormula(
        	c.getNewFormulaLabelGreaterThan(mainLabel),
            //main.getLabel().getNextFormulaLabel(),
            sff.createSignedFormula(C1Signs.TRUE, not_y)
        );

        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        lfl.add(aux);

        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        
        System.out.println(conclusions);
        
        assertTrue(conclusions.size() == 1);
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        
        Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);
        assertTrue(conclusions.get(0).getFormula().equals(not_x));
        
        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertTrue(c.isEqual(aux.getLabel(), conclusions.get(0).getLabel()));
        
        assertTrue(aux.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));
        assertEquals(conclusions.get(0).getLabel(),aux.getLabel());
       
        
    
    }    
    
	    @Test
	    public void testRule17Not() { 
	        /*
	         * Regla 17
	         * F not A : cI
	         * ------------
	         *  T A : cJ
	         *  cI <= cJ
	         * 
	         * 
	         */
	    	Rule rule = IPLRules.F_NOT;

	        x = ff.createAtomicFormula("X");
	        Formula y = ff.createAtomicFormula("Y");
	        Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);
	        
	        Context c = new Context();
	        FormulaLabel mainLabel = c.getNewFormulaLabel();

	        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.FALSE, not_x));
	        
	        SignedFormulaList lfl = new SignedFormulaList();
	        lfl.add(main);
	        
	        SignedFormulaList conclusions;
	        conclusions = rule.getPossibleConclusions((SignedFormulaFactory) lff, ff, lfl);
	        assertTrue(conclusions.size() == 1);

	        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
	        assertTrue(conclusions.get(0).getFormula().equals(x));

	        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));

	        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));
	        
	    }
	    
    @Test
    public void testRule18() {
        // Regla 18
        /*
        T not not A : cI
        -----------------
        T  A : cK
        cI <= cK
        */

        Rule rule = IPLRules.T_NOT_NOT; // Replace
        
        x = ff.createAtomicFormula("X");
        Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);
        Formula not_not_x = ff.createCompositeFormula(IPLConnectives.NOT, not_x);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.TRUE, not_not_x));
        
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);

        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions(lff,  ff, lfl);
        assertTrue(conclusions.size() == 1);
        
        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(x));
        
        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));
        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));
        
    }

    @Test
    public void testRule17ManyConclussions() {

        /*
         * Regla 17
         * F not A : cI
         * ------------
         *  T A : cJ
         *  cI <= cJ
         * 
         * 
         */
        Rule rule = IPLRules.F_NOT;

        x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();

        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.FALSE, not_x));
        
        // First phase
        SignedFormulaList lfl = new SignedFormulaList();
        lfl.add(main);
        
        SignedFormulaList conclusions;
        conclusions = rule.getPossibleConclusions((SignedFormulaFactory) lff, ff, lfl);
        assertTrue(conclusions.size() == 1);

        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(x));

        assertTrue(main.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));

        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(main.getLabel()));

        // Second phase
        LabelledFormula anotherMain = lff.createLabelledFormula(c.getNewFormulaLabel(), sff.createSignedFormula(C1Signs.FALSE, not_x));
        lfl = new SignedFormulaList();
        lfl.add(anotherMain);
        
        conclusions = rule.getPossibleConclusions((SignedFormulaFactory) lff, ff, lfl);
        assertTrue(conclusions.size() == 1);

        assertTrue(conclusions.get(0).getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getFormula().equals(x));

        assertTrue(anotherMain.getLabel().lowerOrEqualThan(conclusions.get(0).getLabel()));

        assertFalse(conclusions.get(0).getLabel().lowerOrEqualThan(anotherMain.getLabel()));

        
        
    }
    
	    
}
