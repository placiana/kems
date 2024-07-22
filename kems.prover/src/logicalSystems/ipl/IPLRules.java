/*
 * Created on 03/12/2004
 *
 */
package logicalSystems.ipl;

import rules.ActionType;
import rules.KEAction;
import rules.KERuleRole;
import rules.NamedRule;
import rules.OnePremiseOneConclusionRule;
import rules.OnePremissTwoConclusionsRule;
import rules.TwoPremisesOneConclusionRule;
import rules.getters.BinaryConnectiveGetter;
import rules.getters.BinaryTwoPremisesConnectiveGetter;
import rules.getters.SimpleSubformulaGetter;
import rules.getters.SubformulaConnectiveRoleGetter;
import rules.getters.SubformulaRoleGetter;
import rules.getters.UnaryConnectiveGetter;
import rules.patterns.SignConnectivePattern;
import rules.patterns.SignConnectiveRoleSubformulaPattern;
import rules.patterns.TwoConnectivesRoleSubformulaPattern;
import rules.patterns.TwoSignsConnectiveRolePattern;

/**
 * Rules (and patterns for these rules) for IPL.
 */
public class IPLRules {

	/** Rule for PB */
	public static final NamedRule PB = new NamedRule("PB");

	/** Rule for closing branches */
	public static final NamedRule CLOSE = new NamedRule("CLOSE");

	// rules with not

	public static final OnePremiseOneConclusionRule T_NOT = new OnePremiseOneConclusionRule("T_NOT",
			new SignConnectivePattern(IPLSigns.TRUE, IPLConnectives.NOT), new KEAction(
					ActionType.ADD_NODE, UnaryConnectiveGetter.FALSE));

	public static final OnePremiseOneConclusionRule F_NOT = new OnePremiseOneConclusionRule("F_NOT",
			new SignConnectivePattern(IPLSigns.FALSE, IPLConnectives.NOT), new KEAction(
					ActionType.ADD_NODE, UnaryConnectiveGetter.TRUE));

	static final SignConnectiveRoleSubformulaPattern pattern_X_NOT_T = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.NOT, IPLSigns.TRUE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_NOT_T = new TwoPremisesOneConclusionRule(
			"X_NOT_T", pattern_X_NOT_T, new KEAction(ActionType.ADD_NODE, new SimpleSubformulaGetter(
					pattern_X_NOT_T, IPLConnectives.BOTTOM)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_NOT_F = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.NOT, IPLSigns.FALSE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_NOT_F = new TwoPremisesOneConclusionRule(
			"X_NOT_F", pattern_X_NOT_F, new KEAction(ActionType.ADD_NODE, new SimpleSubformulaGetter(
					pattern_X_NOT_F, IPLConnectives.TOP)

			));

	// rules with and
	public static final OnePremissTwoConclusionsRule T_AND = new OnePremissTwoConclusionsRule(
			"T_AND", new SignConnectivePattern(IPLSigns.TRUE, IPLConnectives.AND),
			new KEAction(ActionType.ADD_NODE, BinaryConnectiveGetter.TRUE_LEFT), new KEAction(
					ActionType.ADD_NODE, BinaryConnectiveGetter.TRUE_RIGHT));

	public static final TwoPremisesOneConclusionRule F_AND_LEFT = new TwoPremisesOneConclusionRule(
			"F_AND_LEFT",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.AND,
					IPLSigns.TRUE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	static final SignConnectiveRoleSubformulaPattern pattern_X_AND_T_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.AND, IPLSigns.TRUE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_AND_T_LEFT = new TwoPremisesOneConclusionRule(
			"X_AND_T_LEFT", pattern_X_AND_T_LEFT,

			new KEAction(ActionType.ADD_NODE,

			new SubformulaRoleGetter(pattern_X_AND_T_LEFT, KERuleRole.RIGHT)

			));

	static final SignConnectiveRoleSubformulaPattern pattern_X_AND_T_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.AND, IPLSigns.TRUE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_AND_T_RIGHT = new TwoPremisesOneConclusionRule(
			"X_AND_T_RIGHT", pattern_X_AND_T_RIGHT,

			new KEAction(ActionType.ADD_NODE,

			new SubformulaRoleGetter(pattern_X_AND_T_RIGHT, KERuleRole.LEFT)

			));

	static final SignConnectiveRoleSubformulaPattern pattern_X_AND_F_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.AND, IPLSigns.FALSE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_AND_F_LEFT = new TwoPremisesOneConclusionRule(
			"X_AND_F_LEFT", pattern_X_AND_F_LEFT,

			new KEAction(ActionType.ADD_NODE,

			new SimpleSubformulaGetter(pattern_X_AND_F_LEFT, IPLConnectives.BOTTOM)

			));

	static final SignConnectiveRoleSubformulaPattern pattern_X_AND_F_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.AND, IPLSigns.FALSE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_AND_F_RIGHT = new TwoPremisesOneConclusionRule(
			"X_AND_F_RIGHT", pattern_X_AND_F_RIGHT,

			new KEAction(ActionType.ADD_NODE,

			new SimpleSubformulaGetter(pattern_X_AND_F_RIGHT, IPLConnectives.BOTTOM)

			));

	// rules with or

	public static final OnePremissTwoConclusionsRule F_OR = new OnePremissTwoConclusionsRule("F_OR",
			new SignConnectivePattern(IPLSigns.FALSE, IPLConnectives.OR), new KEAction(
					ActionType.ADD_NODE, BinaryConnectiveGetter.FALSE_LEFT), new KEAction(
					ActionType.ADD_NODE, BinaryConnectiveGetter.FALSE_RIGHT));

	public static final TwoPremisesOneConclusionRule T_OR_LEFT = new TwoPremisesOneConclusionRule(
			"T_OR_LEFT",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.OR,
					IPLSigns.FALSE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	static final SignConnectiveRoleSubformulaPattern pattern_X_OR_T_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.OR, IPLSigns.TRUE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_OR_T_LEFT = new TwoPremisesOneConclusionRule(
			"X_OR_T_LEFT", pattern_X_OR_T_LEFT,

			new KEAction(ActionType.ADD_NODE, new SimpleSubformulaGetter(pattern_X_OR_T_LEFT,
					IPLConnectives.TOP)

			));

	static final SignConnectiveRoleSubformulaPattern pattern_X_OR_T_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.OR, IPLSigns.TRUE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_OR_T_RIGHT = new TwoPremisesOneConclusionRule(
			"X_OR_T_RIGHT", pattern_X_OR_T_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SimpleSubformulaGetter(pattern_X_OR_T_RIGHT,
					IPLConnectives.TOP)

			));

	static final SignConnectiveRoleSubformulaPattern pattern_X_OR_F_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.OR, IPLSigns.FALSE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_OR_F_LEFT = new TwoPremisesOneConclusionRule(
			"X_OR_F_LEFT", pattern_X_OR_F_LEFT,

			new KEAction(ActionType.ADD_NODE,

			new SubformulaRoleGetter(pattern_X_OR_F_LEFT, KERuleRole.RIGHT)

			));

	static final SignConnectiveRoleSubformulaPattern pattern_X_OR_F_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.OR, IPLSigns.FALSE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_OR_F_RIGHT = new TwoPremisesOneConclusionRule(
			"X_OR_F_RIGHT", pattern_X_OR_F_RIGHT,

			new KEAction(ActionType.ADD_NODE,

			new SubformulaRoleGetter(pattern_X_OR_F_RIGHT, KERuleRole.LEFT)

			));

	// rules with implies
	public static final OnePremissTwoConclusionsRule F_IMPLIES = new OnePremissTwoConclusionsRule(
			"F_IMPLIES", new SignConnectivePattern(IPLSigns.FALSE, IPLConnectives.IMPLIES),
			new KEAction(ActionType.ADD_NODE, BinaryConnectiveGetter.TRUE_LEFT), new KEAction(
					ActionType.ADD_NODE, BinaryConnectiveGetter.FALSE_RIGHT));

	public static final TwoPremisesOneConclusionRule T_IMPLIES_LEFT = new TwoPremisesOneConclusionRule(
			"T_IMPLIES_LEFT",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.IMPLIES,
					IPLSigns.TRUE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	static final SignConnectiveRoleSubformulaPattern pattern_X_IMPLIES_T_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.IMPLIES, IPLSigns.TRUE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_IMPLIES_T_LEFT = new TwoPremisesOneConclusionRule(
			"X_IMPLIES_T_LEFT", pattern_X_IMPLIES_T_LEFT,

			new KEAction(ActionType.ADD_NODE, new SubformulaRoleGetter(pattern_X_IMPLIES_T_LEFT,
					KERuleRole.RIGHT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_IMPLIES_T_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.IMPLIES, IPLSigns.TRUE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_IMPLIES_T_RIGHT = new TwoPremisesOneConclusionRule(
			"X_IMPLIES_T_RIGHT", pattern_X_IMPLIES_T_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SimpleSubformulaGetter(pattern_X_IMPLIES_T_RIGHT,
					IPLConnectives.TOP))

	);

	static final SignConnectiveRoleSubformulaPattern pattern_X_IMPLIES_F_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.IMPLIES, IPLSigns.FALSE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_IMPLIES_F_LEFT = new TwoPremisesOneConclusionRule(
			"X_IMPLIES_F_LEFT", pattern_X_IMPLIES_F_LEFT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_IMPLIES_F_LEFT, IPLConnectives.TOP))

	);

	static final SignConnectiveRoleSubformulaPattern pattern_X_IMPLIES_F_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.IMPLIES, IPLSigns.FALSE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_IMPLIES_F_RIGHT = new TwoPremisesOneConclusionRule(
			"X_IMPLIES_F_RIGHT", pattern_X_IMPLIES_F_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SubformulaConnectiveRoleGetter(
					pattern_X_IMPLIES_F_RIGHT, IPLConnectives.NOT, KERuleRole.LEFT)));

	// rules with biimplies

	public static final TwoPremisesOneConclusionRule T_BIIMPLIES_LEFT_TRUE = new TwoPremisesOneConclusionRule(
			"T_BIIMPLIES_LEFT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.BIIMPLIES,
					IPLSigns.TRUE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static final TwoPremisesOneConclusionRule F_BIIMPLIES_LEFT_TRUE = new TwoPremisesOneConclusionRule(
			"F_BIIMPLIES_LEFT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.BIIMPLIES,
					IPLSigns.TRUE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule T_BIIMPLIES_LEFT_FALSE = new TwoPremisesOneConclusionRule(
			"T_BIIMPLIES_LEFT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.BIIMPLIES,
					IPLSigns.FALSE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule F_BIIMPLIES_LEFT_FALSE = new TwoPremisesOneConclusionRule(
			"F_BIIMPLIES_LEFT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.BIIMPLIES,
					IPLSigns.FALSE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	static final SignConnectiveRoleSubformulaPattern pattern_X_BIIMPLIES_T_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.BIIMPLIES, IPLSigns.TRUE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_BIIMPLIES_T_LEFT = new TwoPremisesOneConclusionRule(
			"X_BIIMPLIES_T_LEFT", pattern_X_BIIMPLIES_T_LEFT,

			new KEAction(ActionType.ADD_NODE, new SubformulaRoleGetter(pattern_X_BIIMPLIES_T_LEFT,
					KERuleRole.RIGHT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_BIIMPLIES_T_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.BIIMPLIES, IPLSigns.TRUE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_BIIMPLIES_T_RIGHT = new TwoPremisesOneConclusionRule(
			"X_BIIMPLIES_T_RIGHT", pattern_X_BIIMPLIES_T_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SubformulaRoleGetter(pattern_X_BIIMPLIES_T_RIGHT,
					KERuleRole.LEFT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_BIIMPLIES_F_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.BIIMPLIES, IPLSigns.FALSE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_BIIMPLIES_F_LEFT = new TwoPremisesOneConclusionRule(
			"X_BIIMPLIES_F_LEFT", pattern_X_BIIMPLIES_F_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaConnectiveRoleGetter(pattern_X_BIIMPLIES_F_LEFT, IPLConnectives.NOT,
							KERuleRole.RIGHT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_BIIMPLIES_F_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.BIIMPLIES, IPLSigns.FALSE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_BIIMPLIES_F_RIGHT = new TwoPremisesOneConclusionRule(
			"X_BIIMPLIES_F_RIGHT", pattern_X_BIIMPLIES_F_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SubformulaConnectiveRoleGetter(
					pattern_X_BIIMPLIES_F_RIGHT, IPLConnectives.NOT, KERuleRole.LEFT)));

	// rules with XOR

	public static final TwoPremisesOneConclusionRule T_XOR_LEFT_TRUE = new TwoPremisesOneConclusionRule(
			"T_XOR_LEFT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.XOR,
					IPLSigns.TRUE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule F_XOR_LEFT_TRUE = new TwoPremisesOneConclusionRule(
			"F_XOR_LEFT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.XOR,
					IPLSigns.TRUE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static final TwoPremisesOneConclusionRule T_XOR_LEFT_FALSE = new TwoPremisesOneConclusionRule(
			"T_XOR_LEFT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.XOR,
					IPLSigns.FALSE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static final TwoPremisesOneConclusionRule F_XOR_LEFT_FALSE = new TwoPremisesOneConclusionRule(
			"F_XOR_LEFT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.XOR,
					IPLSigns.FALSE, KERuleRole.LEFT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule T_XOR_RIGHT_TRUE = new TwoPremisesOneConclusionRule(
			"T_XOR_RIGHT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.XOR,
					IPLSigns.TRUE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule F_XOR_RIGHT_TRUE = new TwoPremisesOneConclusionRule(
			"F_XOR_RIGHT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.XOR,
					IPLSigns.TRUE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static final TwoPremisesOneConclusionRule T_XOR_RIGHT_FALSE = new TwoPremisesOneConclusionRule(
			"T_XOR_RIGHT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.XOR,
					IPLSigns.FALSE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static final TwoPremisesOneConclusionRule F_XOR_RIGHT_FALSE = new TwoPremisesOneConclusionRule(
			"F_XOR_RIGHT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.XOR,
					IPLSigns.FALSE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	// Xor simplification rules
	static final SignConnectiveRoleSubformulaPattern pattern_X_XOR_F_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.XOR, IPLSigns.FALSE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_XOR_F_LEFT = new TwoPremisesOneConclusionRule(
			"X_XOR_F_LEFT", pattern_X_XOR_F_LEFT,

			new KEAction(ActionType.ADD_NODE, new SubformulaRoleGetter(pattern_X_XOR_F_LEFT,
					KERuleRole.RIGHT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_XOR_F_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.XOR, IPLSigns.FALSE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_XOR_F_RIGHT = new TwoPremisesOneConclusionRule(
			"X_XOR_F_RIGHT", pattern_X_XOR_F_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SubformulaRoleGetter(pattern_X_XOR_F_RIGHT,
					KERuleRole.LEFT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_XOR_T_LEFT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.XOR, IPLSigns.TRUE, KERuleRole.LEFT);

	public static final TwoPremisesOneConclusionRule X_XOR_T_LEFT = new TwoPremisesOneConclusionRule(
			"X_XOR_T_LEFT", pattern_X_XOR_T_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaConnectiveRoleGetter(pattern_X_XOR_T_LEFT, IPLConnectives.NOT,
							KERuleRole.RIGHT)));

	static final SignConnectiveRoleSubformulaPattern pattern_X_XOR_T_RIGHT = new SignConnectiveRoleSubformulaPattern(
			IPLConnectives.XOR, IPLSigns.TRUE, KERuleRole.RIGHT);

	public static final TwoPremisesOneConclusionRule X_XOR_T_RIGHT = new TwoPremisesOneConclusionRule(
			"X_XOR_T_RIGHT", pattern_X_XOR_T_RIGHT,

			new KEAction(ActionType.ADD_NODE, new SubformulaConnectiveRoleGetter(pattern_X_XOR_T_RIGHT,
					IPLConnectives.NOT, KERuleRole.LEFT)));

	// rules with top

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_AND_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.LEFT, IPLConnectives.AND);

	public static final OnePremiseOneConclusionRule X_TOP_AND_LEFT = new OnePremiseOneConclusionRule(
			"X_TOP_AND_LEFT", pattern_X_TOP_AND_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_TOP_AND_LEFT, KERuleRole.RIGHT)

			));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_AND_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.RIGHT, IPLConnectives.AND);

	public static final OnePremiseOneConclusionRule X_TOP_AND_RIGHT = new OnePremiseOneConclusionRule(
			"X_TOP_AND_RIGHT", pattern_X_TOP_AND_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_TOP_AND_RIGHT, KERuleRole.LEFT)

			));

	// ////

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_OR_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.LEFT, IPLConnectives.OR);

	public static final OnePremiseOneConclusionRule X_TOP_OR_LEFT = new OnePremiseOneConclusionRule(
			"X_TOP_OR_LEFT", pattern_X_TOP_OR_LEFT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_TOP_OR_LEFT, IPLConnectives.TOP)));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_OR_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.RIGHT, IPLConnectives.OR);

	public static final OnePremiseOneConclusionRule X_TOP_OR_RIGHT = new OnePremiseOneConclusionRule(
			"X_TOP_OR_RIGHT", pattern_X_TOP_OR_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_TOP_OR_RIGHT, IPLConnectives.TOP)));

	// ////

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_IMPLIES_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.LEFT, IPLConnectives.IMPLIES);

	public static final OnePremiseOneConclusionRule X_TOP_IMPLIES_LEFT = new OnePremiseOneConclusionRule(
			"X_TOP_IMPLIES_LEFT", pattern_X_TOP_IMPLIES_LEFT, new KEAction(

			ActionType.ADD_NODE, new SubformulaRoleGetter(pattern_X_TOP_IMPLIES_LEFT, KERuleRole.RIGHT)

			));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_IMPLIES_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.RIGHT, IPLConnectives.IMPLIES);

	public static final OnePremiseOneConclusionRule X_TOP_IMPLIES_RIGHT = new OnePremiseOneConclusionRule(
			"X_TOP_IMPLIES_RIGHT", pattern_X_TOP_IMPLIES_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_TOP_IMPLIES_RIGHT, IPLConnectives.TOP)));

	// //
	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_BIIMPLIES_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.LEFT, IPLConnectives.BIIMPLIES);

	public static final OnePremiseOneConclusionRule X_TOP_BIIMPLIES_LEFT = new OnePremiseOneConclusionRule(
			"X_TOP_BIIMPLIES_LEFT", pattern_X_TOP_BIIMPLIES_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_TOP_BIIMPLIES_LEFT, KERuleRole.RIGHT)));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_BIIMPLIES_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.RIGHT, IPLConnectives.BIIMPLIES);

	public static final OnePremiseOneConclusionRule X_TOP_BIIMPLIES_RIGHT = new OnePremiseOneConclusionRule(
			"X_TOP_BIIMPLIES_RIGHT", pattern_X_TOP_BIIMPLIES_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_TOP_BIIMPLIES_RIGHT, KERuleRole.LEFT)));

	// //
	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_XOR_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.LEFT, IPLConnectives.XOR);

	public static final OnePremiseOneConclusionRule X_TOP_XOR_LEFT = new OnePremiseOneConclusionRule(
			"X_TOP_XOR_LEFT", pattern_X_TOP_XOR_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaConnectiveRoleGetter(pattern_X_TOP_XOR_LEFT, IPLConnectives.NOT,
							KERuleRole.RIGHT)

			));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_XOR_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.RIGHT, IPLConnectives.XOR);

	public static final OnePremiseOneConclusionRule X_TOP_XOR_RIGHT = new OnePremiseOneConclusionRule(
			"X_TOP_XOR_RIGHT", pattern_X_TOP_XOR_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SubformulaConnectiveRoleGetter(pattern_X_TOP_XOR_RIGHT, IPLConnectives.NOT,
							KERuleRole.LEFT)

			));

	// //

	static final TwoConnectivesRoleSubformulaPattern pattern_X_TOP_NOT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.TOP, KERuleRole.LEFT, IPLConnectives.NOT);

	public static final OnePremiseOneConclusionRule X_TOP_NOT = new OnePremiseOneConclusionRule(
			"X_TOP_NOT", pattern_X_TOP_NOT, new KEAction(ActionType.ADD_NODE, new SimpleSubformulaGetter(
					pattern_X_TOP_NOT, IPLConnectives.BOTTOM)

			));

	// rules with bottom

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_OR_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.LEFT, IPLConnectives.OR);

	public static final OnePremiseOneConclusionRule X_BOTTOM_OR_LEFT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_OR_LEFT", pattern_X_BOTTOM_OR_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_BOTTOM_OR_LEFT, KERuleRole.RIGHT)

			));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_OR_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.RIGHT, IPLConnectives.OR);

	public static final OnePremiseOneConclusionRule X_BOTTOM_OR_RIGHT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_OR_RIGHT", pattern_X_BOTTOM_OR_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_BOTTOM_OR_RIGHT, KERuleRole.LEFT)

			));

	// ////

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_AND_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.LEFT, IPLConnectives.AND);

	public static final OnePremiseOneConclusionRule X_BOTTOM_AND_LEFT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_AND_LEFT", pattern_X_BOTTOM_AND_LEFT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_BOTTOM_AND_LEFT, IPLConnectives.BOTTOM)));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_AND_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.RIGHT, IPLConnectives.AND);

	public static final OnePremiseOneConclusionRule X_BOTTOM_AND_RIGHT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_AND_RIGHT", pattern_X_BOTTOM_AND_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_BOTTOM_AND_RIGHT, IPLConnectives.BOTTOM)));

	// ////

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_IMPLIES_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.LEFT, IPLConnectives.IMPLIES);

	public static final OnePremiseOneConclusionRule X_BOTTOM_IMPLIES_LEFT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_IMPLIES_LEFT", pattern_X_BOTTOM_IMPLIES_LEFT, new KEAction(

			ActionType.ADD_NODE, new SimpleSubformulaGetter(pattern_X_BOTTOM_IMPLIES_LEFT,
					IPLConnectives.TOP)));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_IMPLIES_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.RIGHT, IPLConnectives.IMPLIES);

	public static final OnePremiseOneConclusionRule X_BOTTOM_IMPLIES_RIGHT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_IMPLIES_RIGHT", pattern_X_BOTTOM_IMPLIES_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SubformulaConnectiveRoleGetter(pattern_X_BOTTOM_IMPLIES_RIGHT,
							IPLConnectives.NOT, KERuleRole.LEFT)

			));

	// ////

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_BIIMPLIES_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.LEFT, IPLConnectives.BIIMPLIES);

	public static final OnePremiseOneConclusionRule X_BOTTOM_BIIMPLIES_LEFT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_BIIMPLIES_LEFT", pattern_X_BOTTOM_BIIMPLIES_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaConnectiveRoleGetter(pattern_X_BOTTOM_BIIMPLIES_LEFT,
							IPLConnectives.NOT, KERuleRole.RIGHT)

			));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_BIIMPLIES_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.RIGHT, IPLConnectives.BIIMPLIES);

	public static final OnePremiseOneConclusionRule X_BOTTOM_BIIMPLIES_RIGHT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_BIIMPLIES_RIGHT", pattern_X_BOTTOM_BIIMPLIES_RIGHT, new KEAction(
					ActionType.ADD_NODE, new SubformulaConnectiveRoleGetter(pattern_X_BOTTOM_BIIMPLIES_RIGHT,
							IPLConnectives.NOT, KERuleRole.LEFT)

			));

	// //
	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_XOR_LEFT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.LEFT, IPLConnectives.XOR);

	public static final OnePremiseOneConclusionRule X_BOTTOM_XOR_LEFT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_XOR_LEFT", pattern_X_BOTTOM_XOR_LEFT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_BOTTOM_XOR_LEFT, KERuleRole.RIGHT)));

	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_XOR_RIGHT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.RIGHT, IPLConnectives.XOR);

	public static final OnePremiseOneConclusionRule X_BOTTOM_XOR_RIGHT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_XOR_RIGHT", pattern_X_BOTTOM_XOR_RIGHT, new KEAction(ActionType.ADD_NODE,
					new SubformulaRoleGetter(pattern_X_BOTTOM_XOR_RIGHT, KERuleRole.LEFT)));

	// //
	static final TwoConnectivesRoleSubformulaPattern pattern_X_BOTTOM_NOT = new TwoConnectivesRoleSubformulaPattern(
			IPLConnectives.BOTTOM, KERuleRole.LEFT, IPLConnectives.NOT);

	public static final OnePremiseOneConclusionRule X_BOTTOM_NOT = new OnePremiseOneConclusionRule(
			"X_BOTTOM_NOT", pattern_X_BOTTOM_NOT, new KEAction(ActionType.ADD_NODE,
					new SimpleSubformulaGetter(pattern_X_BOTTOM_NOT, IPLConnectives.TOP)

			));

	// / additional rules for configurable

	public static TwoPremisesOneConclusionRule F_AND_RIGHT = new TwoPremisesOneConclusionRule(
			"F_AND_RIGHT",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.AND,
					IPLSigns.TRUE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static TwoPremisesOneConclusionRule T_OR_RIGHT = new TwoPremisesOneConclusionRule(
			"T_OR_RIGHT",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.OR,
					IPLSigns.FALSE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static TwoPremisesOneConclusionRule T_IMPLIES_RIGHT = new TwoPremisesOneConclusionRule(
			"T_IMPLIES_RIGHT",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.IMPLIES,
					IPLSigns.FALSE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	// rules with biimplies

	public static final TwoPremisesOneConclusionRule T_BIIMPLIES_RIGHT_TRUE = new TwoPremisesOneConclusionRule(
			"T_BIIMPLIES_RIGHT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.BIIMPLIES,
					IPLSigns.TRUE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

	public static final TwoPremisesOneConclusionRule F_BIIMPLIES_RIGHT_TRUE = new TwoPremisesOneConclusionRule(
			"F_BIIMPLIES_RIGHT_TRUE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.BIIMPLIES,
					IPLSigns.TRUE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule T_BIIMPLIES_RIGHT_FALSE = new TwoPremisesOneConclusionRule(
			"T_BIIMPLIES_RIGHT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.TRUE, IPLConnectives.BIIMPLIES,
					IPLSigns.FALSE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.FALSE_OTHER));

	public static final TwoPremisesOneConclusionRule F_BIIMPLIES_RIGHT_FALSE = new TwoPremisesOneConclusionRule(
			"F_BIIMPLIES_RIGHT_FALSE",

			new TwoSignsConnectiveRolePattern(IPLSigns.FALSE, IPLConnectives.BIIMPLIES,
					IPLSigns.FALSE, KERuleRole.RIGHT), new KEAction(ActionType.ADD_NODE,
					BinaryTwoPremisesConnectiveGetter.TRUE_OTHER));

}