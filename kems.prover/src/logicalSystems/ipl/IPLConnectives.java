package logicalSystems.ipl;

import logic.formulas.Arity;
import logic.formulas.Connective;
import util.Messages;

/**
 * All IPL logic connectives are constants of this class.
 */
public class IPLConnectives {
    public static final Connective TOP = new Connective(Messages
            .getString("IPLConnectives.True"), Arity.ZEROARY); 
    public static final Connective BOTTOM = new Connective(Messages
            .getString("IPLConnectives.False"), Arity.ZEROARY); 
    public static final Connective NOT = new Connective(Messages
            .getString("IPLConnectives.Not"), Arity.UNARY); 
    public static final Connective AND = new Connective(Messages
            .getString("IPLConnectives.And"), Arity.BINARY); 
    public static final Connective OR = new Connective(Messages
            .getString("IPLConnectives.Or"), Arity.BINARY); 
    public static final Connective XOR = new Connective(Messages
            .getString("IPLConnectives.Xor"), Arity.BINARY); 
    public static final Connective IMPLIES = new Connective(Messages
            .getString("IPLConnectives.Implies"), Arity.BINARY); 
    public static final Connective BIIMPLIES = new Connective(Messages
            .getString("IPLConnectives.Biimplies"), Arity.BINARY); 
    public static final Connective ANDN = new Connective(Messages
            .getString("IPLConnectives.And_Nary"), Arity.NARY); 
    public static final Connective ORN = new Connective(Messages
            .getString("IPLConnectives.Or_Nary"), Arity.NARY); 
}
