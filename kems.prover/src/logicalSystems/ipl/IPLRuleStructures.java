/*
 * Created on 14/12/2004
 *
 */
package logicalSystems.ipl;

import logic.formulas.Connective;
import logic.logicalSystem.ISignature;
import logic.signedFormulas.FormulaSign;
import rules.KERuleRole;
import rules.ipl.OnePremiseOneConclusionRule;
import rules.Rule;
import rules.ipl.TwoPremisesOneConclusionRule;
import rules.structures.ConnectiveRoleSignRuleList;
import rules.structures.ConnectiveRuleStructureFactory;
import rules.structures.OnePremiseRuleList;
import rules.structures.PBRuleList;
import rules.structures.RuleList;
import rules.structures.RuleType;
import rules.structures.RulesStructure;
import rules.structures.TopBottomRoleRuleList;

/**
 * Structures of rules for IPL KE with substitution
 */

public class IPLRuleStructures {

    private ISignature signature;

    private RulesStructure _rules;

    private OnePremiseRuleList onePremiseRules;

    private TopBottomRoleRuleList topAndBottomRulesNew;

    private ConnectiveRoleSignRuleList twoPremiseRules;

    private PBRuleList PBRules;

    private ConnectiveRuleStructureFactory crsf = new ConnectiveRuleStructureFactory();

    public static final String ONE_PREMISE_RULE_LIST = "onePremiseRules";

    public static final String TOP_BOTTOM_ONE_PREMISE_RULE_LIST = "topAndBottomRulesNew";

    public static final String TWO_PREMISE_RULE_LIST = "twoPremiseRulesNewII";

    public static final String PB_RULE_LIST = "PBRules";

    public IPLRuleStructures(ISignature signature) {
        this.signature = signature;
        /** one premise rules */
        onePremiseRules = initializeOnePremiseRuleList();
        /** one premise simplification rules */
        //topAndBottomRulesNew = initializeTBRuleList();
        /** Two premise substitution rules */
        twoPremiseRules = initializeTwoPremiseRuleList();
        /** rules for applying PB */
        PBRules = initializePBRuleList();
        /** Order od the sets of rules */
        // the order is important!
        _rules = new RulesStructure();
        _rules.add(ONE_PREMISE_RULE_LIST, onePremiseRules);
        _rules.add(TOP_BOTTOM_ONE_PREMISE_RULE_LIST, topAndBottomRulesNew);
        _rules.add(TWO_PREMISE_RULE_LIST, twoPremiseRules);
        _rules.add(PB_RULE_LIST, PBRules);
    }

    public RulesStructure getRuleStructure() {
        return _rules;
    }

    public RuleList getRules(Connective conn) {
        return crsf.createCRS(conn).getRules();
    }

    /**
     * @return
     */
    private PBRuleList initializePBRuleList() {
        PBRules = new PBRuleList();

        addToPBRules(IPLSigns.FALSE, IPLConnectives.AND,
                IPLRules.F_AND_LEFT);
        //addToPBRules(IPLSigns.TRUE, IPLConnectives.OR,
        //        IPLRules.T_OR_LEFT);
        addToPBRules(IPLSigns.TRUE, IPLConnectives.IMPLIES,
                IPLRules.T_IMPLIES_LEFT);

        /** rules for applying PB - with biimplication */
        /*
        addToPBRules(IPLSigns.TRUE, IPLConnectives.BIIMPLIES,
                IPLRules.T_BIIMPLIES_LEFT_TRUE,
                IPLRules.T_BIIMPLIES_LEFT_FALSE);
        addToPBRules(IPLSigns.FALSE, IPLConnectives.BIIMPLIES,
                IPLRules.F_BIIMPLIES_LEFT_TRUE,
                IPLRules.F_BIIMPLIES_LEFT_FALSE); 
		 */
        /** rules for applying PB - with XOR */
        /*
        PBRules.add(IPLSigns.TRUE, IPLConnectives.XOR,
                        IPLRules.T_XOR_LEFT_TRUE,
                        IPLRules.T_XOR_LEFT_FALSE);
        PBRules.add(IPLSigns.FALSE, IPLConnectives.XOR,
                        IPLRules.F_XOR_LEFT_TRUE,
                        IPLRules.F_XOR_LEFT_FALSE);
        */
        
        return PBRules;
    }

    private ConnectiveRoleSignRuleList initializeTwoPremiseRuleList() {
        twoPremiseRules = new ConnectiveRoleSignRuleList();

        // Regla 3
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_OR_F_LEFT);
        // Regla 4
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_OR_F_RIGHT);
        // Regla 6
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_A_OR_B);
        // Regla 7
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_A_OR_B_NOT_B);
        
        // Regla 8
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.F_AND_LEFT);
        // Regla 9
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_AND_T_RIGHT);
        // Regla 10
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_NOT_A_AND_B);
        // Regla 11
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_NOT_AND_LEFT);
        
        
        // Regla 12
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_IMPLIES_LEFT);
        
        // 13
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_IMPLIES_F_RIGHT);
        
        // Regla 16
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.T_X_IMPLIES_Y_NOT_Y);
        
        return twoPremiseRules;
    }

  

    /**
     * @return
     */
    private OnePremiseRuleList initializeOnePremiseRuleList() {
        onePremiseRules = new OnePremiseRuleList();

        // Regla 1
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.OR,
                IPLRules.F_OR);

        // Regla 2
        addToOnePremiseRules(IPLSigns.TRUE, IPLConnectives.AND,
                IPLRules.T_AND);

        // Regla 5?
        addToOnePremiseRules(IPLSigns.TRUE, IPLConnectives.AND,
                IPLRules.T_NOT_A_OR_B);

        // Regla 14
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.IMPLIES,
                IPLRules.F_A_IMPLIES_B_TA_FB);
        // Regla 15
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT,
                IPLRules.F_NOT_A_IMPLIES_B_TA_FB);
        // Regla 17
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT,
                IPLRules.F_NOT);
        // Regla 18
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT,
                IPLRules.T_NOT_NOT);

        return onePremiseRules;
    }

    private void addToPBRules(FormulaSign sign, Connective conn, Rule r1) {
        if (signature.contains(conn)) {
            addConnectiveRuleType(conn, r1, RuleType.PB);
            PBRules.add(sign, conn, r1);
        }
    }

    protected void addToTwoPremiseRules(Connective conn, KERuleRole role,
            FormulaSign sign, TwoPremisesOneConclusionRule r) {
        if (signature.contains(conn)) {
            addConnectiveRuleType(conn, r, RuleType.SUBSTITUTION_2P);
            twoPremiseRules.add(conn, role, sign, r);
        }
    }



    private void addToOnePremiseRules(FormulaSign sign, Connective conn, Rule r) {
        if (signature.contains(conn)) {
            addConnectiveRuleType(conn, r, RuleType.SIMPLE_1P);
            onePremiseRules.add(sign, conn, r);
        }
    }

    protected void addConnectiveRuleType(Connective conn, Rule r, RuleType rt) {
        crsf.createCRS(conn).add(r, rt);
    }

    public ISignature getSignature() {
        return signature;
    }

    public ConnectiveRoleSignRuleList getTwoPremiseRules() {
        return twoPremiseRules;
    }

    protected void setTwoPremiseRules(ConnectiveRoleSignRuleList list) {
        twoPremiseRules = list;
    }

}