package logicalSystems.ipl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import main.newstrategy.mbc.simple.MBCSimpleStrategy;
import main.proofTree.INode;
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

    Formula x;
    Formula y;
    Formula x_or_y;
    Formula x_and_y;
    Method method;
    SignedFormulaCreator sfc;
    IPLOnePremiseRuleApplicator app;
    
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

        app = new IPLOnePremiseRuleApplicator(new MBCSimpleStrategy(method),
                IPLRuleStructures.ONE_PREMISE_RULE_LIST);
        
    }



    @Test
    public void testRule01() {
        LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.FALSE, x_or_y));

        SignedFormulaCreator sfc = new SignedFormulaCreator("sats5");
        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);
        //sfl.add(aux);
        //sfl.add(trueX);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 4);

    }

    @Test
    public void testRule02() {

        LabelledFormula main = lff.createLabelledFormula("c", sff.createSignedFormula(C1Signs.TRUE, x_and_y));

        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);
        //sfl.add(aux);
        //sfl.add(trueX);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 4);

    }

    // Regla 5?
    //addToOnePremiseRules(IPLSigns.TRUE, IPLConnectives.NOT, IPLRules.T_NOT_A_OR_B);
    @Test
    public void testRule05() {
    	
    	Formula not_x_or_y = ff.createCompositeFormula(IPLConnectives.NOT, x_or_y);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
        
        LabelledFormula main = lff.createLabelledFormula(mainLabel, 
                sff.createSignedFormula(C1Signs.TRUE, not_x_or_y));

        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);



        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 4);

    }

    
    // Regla 14
    //addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.IMPLIES, IPLRules.F_A_IMPLIES_B_TA_FB);
    @Test
    public void testRule14() {
    	
    	Formula x_implies_y = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
    	
        LabelledFormula main = lff.createLabelledFormula(mainLabel, sff.createSignedFormula(C1Signs.FALSE, x_implies_y));

        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);

        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        List<INode> nodes = cpt.getNodeSequence();
        
        System.out.println(nodes);
        LabelledFormula firstConclusion = (LabelledFormula) nodes.get(nodes.size()-2).getContent();

        LabelledFormula secondConclusion = (LabelledFormula)  nodes.get(nodes.size()-1).getContent();
        
        assertEquals(firstConclusion.getLabel(), secondConclusion.getLabel());
        
        assertTrue(c.isGreaterOrEqualTo(firstConclusion.getLabel(), mainLabel));

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 4);

    }
    
    // Regla 15
    //addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT, IPLRules.F_NOT_A_IMPLIES_B_TA_FB);
    @Test
    public void testRule15() {
    	
    	Formula x_implies_y = ff.createCompositeFormula(IPLConnectives.IMPLIES, x, y);
    	Formula not_x_implies_y = ff.createCompositeFormula(IPLConnectives.NOT, x_implies_y);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
    	
        LabelledFormula main = lff.createLabelledFormula(mainLabel, 
        		sff.createSignedFormula(C1Signs.TRUE, not_x_implies_y));

        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        
        List<INode> nodes = cpt.getNodeSequence();
        
        System.out.println(nodes);

        assertTrue(cpt.getNumberOfNodes() == 4);



    }
    
    // Regla 17
    //addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT, IPLRules.F_NOT);
    @Test
    public void testRule17() {
    	
    	Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
    	
        LabelledFormula main = lff.createLabelledFormula(mainLabel, 
        		sff.createSignedFormula(C1Signs.FALSE, not_x));

        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 3);
        
        // TODO: bien pero deberia ser c1 para las dos conclusiones

    }
    
    
    // Regla 18
    //addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT, IPLRules.T_NOT_NOT);
    @Test
    public void testRule18() {
    	
    	Formula not_x = ff.createCompositeFormula(IPLConnectives.NOT, x);
    	Formula not_not_x = ff.createCompositeFormula(IPLConnectives.NOT, not_x);

        Context c = new Context();
        FormulaLabel mainLabel = c.getNewFormulaLabel();
    	
        LabelledFormula main = lff.createLabelledFormula(mainLabel, 
        		sff.createSignedFormula(C1Signs.TRUE, not_not_x));

        SignedFormulaList sfl = new SignedFormulaList();

        sfl.add(main);


        ClassicalProofTree cpt = new FormulaReferenceClassicalProofTree(
                new SignedFormulaNode(tTopFormula, SignedFormulaNodeState.FULFILLED, NamedOrigin.DEFINITION));

        SignedFormulaBuilder sfb = new SignedFormulaBuilder(
                // sfc.getSignedFormulaFactory(), sfc.getFormulaFactory());
                new LabelledFormulaFactory(), sfc.getFormulaFactory());

        Iterator<SignedFormula> it = sfl.iterator();

        while (it.hasNext()) {
            cpt.addLast(new SignedFormulaNode(it.next(), SignedFormulaNodeState.NOT_ANALYSED, NamedOrigin.PROBLEM));
        }
        app.applyAll(cpt, sfb);
        System.out.println(cpt);

        System.out.println(cpt.getNumberOfNodes());
        assertTrue(cpt.getNumberOfNodes() == 3);
        
        // TODO: bien pero deberia ser c1 para las dos conclusiones

    }
    
    
}
