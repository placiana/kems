/*
 * Created on 14/12/2004
 *
 */
package logicalSystems.ipl;

import logic.formulas.Connective;
import logic.logicalSystem.ISignature;
import logic.signedFormulas.FormulaSign;
import rules.KERuleRole;
import rules.OnePremiseOneConclusionRule;
import rules.Rule;
import rules.TwoPremisesOneConclusionRule;
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
        topAndBottomRulesNew = initializeTBRuleList();
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
        addToPBRules(IPLSigns.TRUE, IPLConnectives.OR,
                IPLRules.T_OR_LEFT);
        addToPBRules(IPLSigns.TRUE, IPLConnectives.IMPLIES,
                IPLRules.T_IMPLIES_LEFT);

        /** rules for applying PB - with biimplication */
        addToPBRules(IPLSigns.TRUE, IPLConnectives.BIIMPLIES,
                IPLRules.T_BIIMPLIES_LEFT_TRUE,
                IPLRules.T_BIIMPLIES_LEFT_FALSE);
        addToPBRules(IPLSigns.FALSE, IPLConnectives.BIIMPLIES,
                IPLRules.F_BIIMPLIES_LEFT_TRUE,
                IPLRules.F_BIIMPLIES_LEFT_FALSE);

        /** rules for applying PB - with XOR */
        PBRules.add(IPLSigns.TRUE, IPLConnectives.XOR,
                        IPLRules.T_XOR_LEFT_TRUE,
                        IPLRules.T_XOR_LEFT_FALSE);
        PBRules.add(IPLSigns.FALSE, IPLConnectives.XOR,
                        IPLRules.F_XOR_LEFT_TRUE,
                        IPLRules.F_XOR_LEFT_FALSE);
        return PBRules;
    }

    private ConnectiveRoleSignRuleList initializeTwoPremiseRuleList() {
        twoPremiseRules = new ConnectiveRoleSignRuleList();

        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_AND_F_LEFT);
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.RIGHT,
                IPLSigns.FALSE, IPLRules.X_AND_F_RIGHT);
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.LEFT,
                IPLSigns.TRUE, IPLRules.X_AND_T_LEFT);
        addToTwoPremiseRules(IPLConnectives.AND, KERuleRole.RIGHT,
                IPLSigns.TRUE, IPLRules.X_AND_T_RIGHT);
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_OR_F_LEFT);
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.RIGHT,
                IPLSigns.FALSE, IPLRules.X_OR_F_RIGHT);
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.LEFT,
                IPLSigns.TRUE, IPLRules.X_OR_T_LEFT);
        addToTwoPremiseRules(IPLConnectives.OR, KERuleRole.RIGHT,
                IPLSigns.TRUE, IPLRules.X_OR_T_RIGHT);
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_IMPLIES_F_LEFT);
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.RIGHT,
                IPLSigns.FALSE, IPLRules.X_IMPLIES_F_RIGHT);
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.LEFT,
                IPLSigns.TRUE, IPLRules.X_IMPLIES_T_LEFT);
        addToTwoPremiseRules(IPLConnectives.IMPLIES, KERuleRole.RIGHT,
                IPLSigns.TRUE, IPLRules.X_IMPLIES_T_RIGHT);

        addToTwoPremiseRules(IPLConnectives.NOT, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_NOT_F);
        addToTwoPremiseRules(IPLConnectives.NOT, KERuleRole.LEFT,
                IPLSigns.TRUE, IPLRules.X_NOT_T);

        /** Two premise substitution rules with biimplication */
        addToTwoPremiseRules(IPLConnectives.BIIMPLIES, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_BIIMPLIES_F_LEFT);
        addToTwoPremiseRules(IPLConnectives.BIIMPLIES, KERuleRole.RIGHT,
                IPLSigns.FALSE, IPLRules.X_BIIMPLIES_F_RIGHT);
        addToTwoPremiseRules(IPLConnectives.BIIMPLIES, KERuleRole.LEFT,
                IPLSigns.TRUE, IPLRules.X_BIIMPLIES_T_LEFT);
        addToTwoPremiseRules(IPLConnectives.BIIMPLIES, KERuleRole.RIGHT,
                IPLSigns.TRUE, IPLRules.X_BIIMPLIES_T_RIGHT);

        /** Two premise substitution rules with XOR */

        addToTwoPremiseRules(IPLConnectives.XOR, KERuleRole.LEFT,
                IPLSigns.FALSE, IPLRules.X_XOR_F_LEFT);
        addToTwoPremiseRules(IPLConnectives.XOR, KERuleRole.RIGHT,
                IPLSigns.FALSE, IPLRules.X_XOR_F_RIGHT);
        addToTwoPremiseRules(IPLConnectives.XOR, KERuleRole.LEFT,
                IPLSigns.TRUE, IPLRules.X_XOR_T_LEFT);
        addToTwoPremiseRules(IPLConnectives.XOR, KERuleRole.RIGHT,
                IPLSigns.TRUE, IPLRules.X_XOR_T_RIGHT);
        return twoPremiseRules;
    }

    /**
     * @return
     */
    private TopBottomRoleRuleList initializeTBRuleList() {
        topAndBottomRulesNew = new TopBottomRoleRuleList();

        addToTBRules(IPLConnectives.TOP, IPLConnectives.AND,
                KERuleRole.LEFT, IPLRules.X_TOP_AND_LEFT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.AND,
                KERuleRole.RIGHT, IPLRules.X_TOP_AND_RIGHT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.OR,
                KERuleRole.LEFT, IPLRules.X_TOP_OR_LEFT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.OR,
                KERuleRole.RIGHT, IPLRules.X_TOP_OR_RIGHT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.IMPLIES,
                KERuleRole.LEFT, IPLRules.X_TOP_IMPLIES_LEFT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.IMPLIES,
                KERuleRole.RIGHT, IPLRules.X_TOP_IMPLIES_RIGHT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.NOT,
                KERuleRole.LEFT, IPLRules.X_TOP_NOT);

        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.AND,
                KERuleRole.LEFT, IPLRules.X_BOTTOM_AND_LEFT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.AND,
                KERuleRole.RIGHT, IPLRules.X_BOTTOM_AND_RIGHT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.OR,
                KERuleRole.LEFT, IPLRules.X_BOTTOM_OR_LEFT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.OR,
                KERuleRole.RIGHT, IPLRules.X_BOTTOM_OR_RIGHT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.IMPLIES,
                KERuleRole.LEFT, IPLRules.X_BOTTOM_IMPLIES_LEFT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.IMPLIES,
                KERuleRole.RIGHT, IPLRules.X_BOTTOM_IMPLIES_RIGHT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.NOT,
                KERuleRole.LEFT, IPLRules.X_BOTTOM_NOT);

        /** One premise rules with biimplication */
        addToTBRules(IPLConnectives.TOP, IPLConnectives.BIIMPLIES,
                KERuleRole.LEFT, IPLRules.X_TOP_BIIMPLIES_LEFT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.BIIMPLIES,
                KERuleRole.RIGHT, IPLRules.X_TOP_BIIMPLIES_RIGHT);

        addToTBRules(IPLConnectives.BOTTOM,
                IPLConnectives.BIIMPLIES, KERuleRole.LEFT,
                IPLRules.X_BOTTOM_BIIMPLIES_LEFT);
        addToTBRules(IPLConnectives.BOTTOM,
                IPLConnectives.BIIMPLIES, KERuleRole.RIGHT,
                IPLRules.X_BOTTOM_BIIMPLIES_RIGHT);

        /** One premise rules with XOR */
        addToTBRules(IPLConnectives.TOP, IPLConnectives.XOR,
                KERuleRole.LEFT, IPLRules.X_TOP_XOR_LEFT);
        addToTBRules(IPLConnectives.TOP, IPLConnectives.XOR,
                KERuleRole.RIGHT, IPLRules.X_TOP_XOR_RIGHT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.XOR,
                KERuleRole.LEFT, IPLRules.X_BOTTOM_XOR_LEFT);
        addToTBRules(IPLConnectives.BOTTOM, IPLConnectives.XOR,
                KERuleRole.RIGHT, IPLRules.X_BOTTOM_XOR_RIGHT);
        return topAndBottomRulesNew;
    }

    /**
     * @return
     */
    private OnePremiseRuleList initializeOnePremiseRuleList() {
        onePremiseRules = new OnePremiseRuleList();

        addToOnePremiseRules(IPLSigns.TRUE, IPLConnectives.NOT,
                IPLRules.T_NOT);
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.NOT,
                IPLRules.F_NOT);
        addToOnePremiseRules(IPLSigns.TRUE, IPLConnectives.AND,
                IPLRules.T_AND);
        addToOnePremiseRules(IPLSigns.FALSE, IPLConnectives.OR,
                IPLRules.F_OR);
        addToOnePremiseRules(IPLSigns.FALSE,
                IPLConnectives.IMPLIES, IPLRules.F_IMPLIES);
        return onePremiseRules;
    }

    private void addToPBRules(FormulaSign sign, Connective conn, Rule r1) {
        if (signature.contains(conn)) {
            addConnectiveRuleType(conn, r1, RuleType.PB);
            PBRules.add(sign, conn, r1);
        }
    }

    private void addToPBRules(FormulaSign sign, Connective conn, Rule r1,
            Rule r2) {
        if (signature.contains(conn)) {
            addConnectiveRuleType(conn, r1, RuleType.PB);
            addConnectiveRuleType(conn, r2, RuleType.PB);
            PBRules.add(sign, conn, r1, r2);
        }
    }

    protected void addToTwoPremiseRules(Connective conn, KERuleRole role,
            FormulaSign sign, TwoPremisesOneConclusionRule r) {
        if (signature.contains(conn)) {
            addConnectiveRuleType(conn, r, RuleType.SUBSTITUTION_2P);
            twoPremiseRules.add(conn, role, sign, r);
        }
    }

    private void addToTBRules(Connective conn1, Connective conn2,
            KERuleRole role, OnePremiseOneConclusionRule r) {
        if (signature.contains(conn1) && signature.contains(conn2)) {
            addConnectiveRuleType(conn1, r, RuleType.SUBSTITUTION_1P);
            addConnectiveRuleType(conn2, r, RuleType.SUBSTITUTION_1P);
            topAndBottomRulesNew.add(conn1, conn2, role, r);
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