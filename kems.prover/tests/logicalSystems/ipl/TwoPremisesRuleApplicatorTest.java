package logicalSystems.ipl;

import java.util.Iterator;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.labelledFormulas.Context;
import logic.labelledFormulas.FormulaLabel;
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
import main.newstrategy.ipl.IPLOnePremiseRuleApplicator;
import main.newstrategy.ipl.TwoPremiseRuleApplicator;
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
import rules.KERuleRole;

public class TwoPremisesRuleApplicatorTest {

    private SignedFormula tTopFormula;
    SignedFormulaFactory sff;
    FormulaFactory ff;

    LabelledFormulaFactory lff;

    Formula x;
    Formula y;
    Formula x_or_y;
    Formula x_and_y;
    Method method;
    SignedFormulaCreator sfc;
    TwoPremiseRuleApplicator app;
    
    @Before
    public void setUp() throws Exception {
        sff = new SignedFormulaFactory();
        ff = new FormulaFactory();
        lff = new LabelledFormulaFactory();
        
        
        x = ff.createAtomicFormula("X");
        y = ff.createAtomicFormula("Y");
        x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);
        x_and_y = ff.createCompositeFormula(IPLConnectives.AND, x, y);

        method = new Method(RuleStructureFactory.createRulesStructure("IPL"));
        sfc = new SignedFormulaCreator("sats5");
        
        tTopFormula = sfc.getSignedFormulaFactory().createSignedFormula(ClassicalSigns.TRUE,
                sfc.getFormulaFactory().createCompositeFormula(ClassicalConnectives.TOP));

        app = new TwoPremiseRuleApplicator(new MBCSimpleStrategy(method),
                IPLRuleStructures.TWO_PREMISE_RULE_LIST);
        
        
    }

    
//  // Regla 3
//  addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
//          IPLSigns.FALSE, IPLRules.X_OR_F_LEFT);
    @Test
    public void testRule03() {
    	Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);
    	
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
    	
        LabelledFormula main = lff.createLabelledFormula(mainLabel, 
        		sff.createSignedFormula(C1Signs.TRUE, x_or_y));
        LabelledFormula aux = lff.createLabelledFormula(mainLabel.getNextFormulaLabel(), 
        		sff.createSignedFormula(C1Signs.FALSE, x));
        
        
        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);
        sfl.add(aux);
        //sfl.add(trueX);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        MBCSimpleStrategy strategy = new MBCSimpleStrategy(method);
        strategy.setCurrent(cpt);
       
        app = new TwoPremiseRuleApplicator(strategy,
                IPLRuleStructures.TWO_PREMISE_RULE_LIST);
        
        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 4);

    }

//    

//    // Regla 4
//    addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_OR_F_RIGHT);
    @Test
    public void testRule04() {
    	Formula x_or_y = ff.createCompositeFormula(IPLConnectives.OR, x, y);
    	
        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
    	
        LabelledFormula main = lff.createLabelledFormula(mainLabel, 
        		sff.createSignedFormula(C1Signs.TRUE, x_or_y));
        LabelledFormula aux = lff.createLabelledFormula(mainLabel.getNextFormulaLabel(), 
        		sff.createSignedFormula(C1Signs.FALSE, y));
        
        
        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);
        sfl.add(aux);
        //sfl.add(trueX);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        MBCSimpleStrategy strategy = new MBCSimpleStrategy(method);
        strategy.setCurrent(cpt);
       
        app = new TwoPremiseRuleApplicator(strategy,
                IPLRuleStructures.TWO_PREMISE_RULE_LIST);
        
        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 4);

    }

    
    
//    // Regla 6
//    addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_A_OR_B);
//    // Regla 7
//    addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_A_OR_B_NOT_B);
//    
//    // Regla 8
//    addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.F_AND_LEFT);
//    // Regla 9
//    addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.X_AND_T_RIGHT);
//    // Regla 10
//    addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_NOT_A_AND_B);
//    // Regla 11
//    addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_NOT_AND_LEFT);
//    
//    
//    // Regla 12
//    addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_IMPLIES_LEFT);
//    
//    // 13
//    addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.X_IMPLIES_F_RIGHT);
//    
//    // Regla 16
//    addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
//            IPLSigns.FALSE, IPLRules.T_X_IMPLIES_Y_NOT_Y);
    
    
    
    
    
}
