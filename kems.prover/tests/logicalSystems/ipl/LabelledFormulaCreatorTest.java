package logicalSystems.ipl;

import org.junit.Test;

import logic.labelledFormulas.LabelledFormulaCreator;
import logic.signedFormulas.SignedFormulaList;
import logicalSystems.c1.C1RuleStructures;
import logicalSystems.c1.MockSimpleStrategy;
import main.newstrategy.mbc.simple.TwoPremiseRuleApplicator;
import main.tableau.Method;
import proverinterface.RuleStructureFactory;

public class LabelledFormulaCreatorTest {

	@Test
	public void testAlgo() {
		Method method = new Method(RuleStructureFactory
				.createRulesStructure("C1"));
		MockSimpleStrategy mockStrategy = new MockSimpleStrategy(method);
        TwoPremiseRuleApplicator x = new TwoPremiseRuleApplicator(mockStrategy,
                C1RuleStructures.TWO_PREMISE_RULES);
        LabelledFormulaCreator sfc = new LabelledFormulaCreator("ipl");
        SignedFormulaList sfl = new SignedFormulaList();
        
        //sfl.add(sfc.parseString("0 T A1"));
	}

	@Test
	public void testParseString() {
		LabelledFormulaCreator sfc = new LabelledFormulaCreator("ipl");
		sfc.parseString("xT A1");
	}
}
