package logicalSystems.ipl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.Context;
import logic.labelledFormulas.FormulaLabel;
import logic.labelledFormulas.LabelledFormula;
import logic.labelledFormulas.LabelledFormulaFactory;
import logic.labelledFormulas.LabelledFormulaList;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import logicalSystems.c1.C1Connectives;
import logicalSystems.c1.C1Rules;
import logicalSystems.c1.C1Signs;

import org.junit.Before;
import org.junit.Test;

import rules.OnePremiseTwoConclusionsRule;
import rules.TwoPremisesOneConclusionRule;
import rules.ipl.Rule;
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
		rules.ipl.OnePremiseTwoConclusionsRule falseOrRule = IPLRulesPablo.F_OR;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, x_or_y));
		LabelledFormulaList lfl = new LabelledFormulaList();
		lfl.add(main);

		LabelledFormulaList conclusions;
		conclusions = falseOrRule.getPossibleConclusions(lff, sff, ff, lfl);
		assertTrue(conclusions.size() == 2);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));
		assertTrue(conclusions.get(1).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.FALSE));
		assertTrue(conclusions.get(1).getSignedFormula().getSign().equals(C1Signs.FALSE));

	}

	@Test
	public void testRule2TrueAnd() {
		rules.ipl.OnePremiseTwoConclusionsRule rule = IPLRulesPablo.T_AND;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_and_y = ff.createCompositeFormula(IPLConnectives.AND, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_and_y));
		LabelledFormulaList lfl = new LabelledFormulaList();
		lfl.add(main);

		LabelledFormulaList conclusions;
		conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
		assertTrue(conclusions.size() == 2);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.TRUE));
		assertTrue(conclusions.get(1).getSignedFormula().getSign().equals(C1Signs.TRUE));

	}

	@Test
	public void testRule3TrueOr() {
		Rule rule = IPLRulesPablo.X_OR_F_LEFT;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_or_y));
		LabelledFormula aux = lff.createLabelledFormula(main.getLabel().getNextFormulaLabel(),
				sff.createSignedFormula(C1Signs.FALSE, x));

		LabelledFormulaList lfl = new LabelledFormulaList();
		lfl.add(main);
		lfl.add(aux);

		LabelledFormulaList conclusions;
		conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
		assertTrue(conclusions.size() == 1);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.TRUE));
		assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(y));

	}

	@Test
	public void testRule4TrueOrFalseRight() {
		Rule rule = IPLRulesPablo.T_OR_F_RIGHT;

		x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);

		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_or_y));
		LabelledFormula aux = lff.createLabelledFormula(main.getLabel().getNextFormulaLabel(),
				sff.createSignedFormula(C1Signs.FALSE, y));

		LabelledFormulaList lfl = new LabelledFormulaList();
		lfl.add(main);
		lfl.add(aux);

		LabelledFormulaList conclusions;
		conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
		assertTrue(conclusions.size() == 1);

		assertTrue(conclusions.get(0).getLabel().equals(main.getLabel()));

		assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.TRUE));
		assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(x));

	}


	@Test
	public void testRule5() {
		/*
		T not(A or B): Ci
		-----------------
		T not A : Ci
		T not B : Ci
		
		*/
		Rule rule = IPLRulesPablo.T_NOT_A_OR_B;
		Formula x = ff.createAtomicFormula("X");
		Formula y = ff.createAtomicFormula("Y");
		Formula or = ff.createCompositeFormula(IPLConnectives.OR, x, y);
		Formula notOr = ff.createCompositeFormula(IPLConnectives.NOT, or);
	
		LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, notOr));
		
		LabelledFormulaList lfl = new LabelledFormulaList();
		lfl.add(main);
		
		LabelledFormulaList conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
		
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
	    
	    Rule rule = IPLRulesPablo.T_A_OR_B;
		
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

		LabelledFormulaList lfl = new LabelledFormulaList();
        lfl.add(main);
        lfl.add(aux);

        LabelledFormulaList conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(y));
        assertTrue(conclusions.get(0).getLabel().equals(mainLabel));
        
	}


    @Test
    public void testRule7() {
        /*
         * T A or B : ci
         * T not B: cj
         * ci <= cj or cj <= ci
         * --------------------
         * TA: Ci
         */
        
        Rule rule = IPLRulesPablo.T_A_OR_B_NOT_B;
        
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

        LabelledFormulaList lfl = new LabelledFormulaList();
        lfl.add(main);
        lfl.add(aux);

        LabelledFormulaList conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.TRUE));
        assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(x));
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
        Rule rule = IPLRulesPablo.F_AND_LEFT;
        
        Formula x = ff.createAtomicFormula("X");
        Formula y = ff.createAtomicFormula("Y");
        Formula and = ff.createCompositeFormula(IPLConnectives.AND, x, y);
        //Formula not = ff.createCompositeFormula(IPLConnectives.NOT, y);
        
        SignedFormula sOr = sff.createSignedFormula(C1Signs.FALSE, and);
        SignedFormula sNot = sff.createSignedFormula(C1Signs.TRUE, x);
        
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        FormulaLabel auxLabel = c.getNewFormulaLabelGreaterThan(mainLabel);
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sOr);
        LabelledFormula aux = lff.createLabelledFormula(auxLabel, sNot);

        LabelledFormulaList lfl = new LabelledFormulaList();
        lfl.add(main);
        lfl.add(aux);

        LabelledFormulaList conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
        
        assertTrue(conclusions.size() >= 1);
        assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.FALSE));
        assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(y));
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
       
        Rule rule = IPLRulesPablo.X_AND_T_RIGHT;
        
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

        LabelledFormulaList lfl = new LabelledFormulaList();
        lfl.add(main);
        lfl.add(aux);

        LabelledFormulaList conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
        
        assertTrue(conclusions.size() >= 1);

        assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.FALSE));
        assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(x));
        
   }

	    @Test
	    public void testRule17Not() {
	        Rule rule = IPLRulesPablo.F_NOT;

	        x = ff.createAtomicFormula("X");
	        Formula y = ff.createAtomicFormula("Y");
	        Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);

	        LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, not_x));
	        
	        LabelledFormulaList lfl = new LabelledFormulaList();
	        lfl.add(main);
	        
	        LabelledFormulaList conclusions;
	        conclusions = rule.getPossibleConclusions(lff, sff, ff, lfl);
	        assertTrue(conclusions.size() == 1);
	        
	        assertTrue(conclusions.get(0).getSignedFormula().getSign().equals(C1Signs.TRUE));
	        assertTrue(conclusions.get(0).getSignedFormula().getFormula().equals(x));
	        
	        
	        
	    }
	    

}
