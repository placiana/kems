/*
 * Created on 06/04/2005
 *
 */
package logicalSystems.ipl;

import logic.formulas.ConnectiveCode;
import logic.logicalSystem.Signature;

/**
 * Factory of signatures for IPL. It is a singleton.
 */
public class IPLSignatureFactory {

    public static ConnectiveCode TOP = new ConnectiveCode();

    public static ConnectiveCode BOTTOM = new ConnectiveCode();

    public static ConnectiveCode NOT = new ConnectiveCode();

    public static ConnectiveCode AND = new ConnectiveCode();

    public static ConnectiveCode OR = new ConnectiveCode();

    public static ConnectiveCode IMPLIES = new ConnectiveCode();

    public static ConnectiveCode XOR = new ConnectiveCode();

    public static ConnectiveCode BIIMPLIES = new ConnectiveCode();

    private static IPLSignatureFactory __instance = null;

    private Signature clausal, normal, normal_b, normal_b_x;

    private IPLSignatureFactory() {
    }

    public static IPLSignatureFactory getInstance() {
        if (__instance == null) {
            __instance = new IPLSignatureFactory();
        }
        return __instance;
    }

    public Signature getClausalSignature() {
        if (clausal == null) {
            clausal = new Signature();
            clausal.add(IPLSignatureFactory.TOP, IPLConnectives.TOP);
            clausal.add(IPLSignatureFactory.BOTTOM, IPLConnectives.BOTTOM);
            clausal.add(IPLSignatureFactory.NOT, IPLConnectives.NOT);
            clausal.add(IPLSignatureFactory.AND, IPLConnectives.AND);
            clausal.add(IPLSignatureFactory.OR, IPLConnectives.OR);
        }
        return clausal;
    }

    public Signature getNormalSignature() {
        if (normal == null) {
            normal = new Signature(getClausalSignature());
            normal.add(IPLSignatureFactory.IMPLIES, IPLConnectives.IMPLIES);
        }
        return normal;
    }

    public Signature getNormalBSignature() {
        if (normal_b == null) {
            normal_b = new Signature(getNormalSignature());
            normal_b.add(IPLSignatureFactory.BIIMPLIES, IPLConnectives.BIIMPLIES);
        }
        return normal_b;
    }

    public Signature getNormalBXSignature() {
        if (normal_b_x == null) {
            normal_b_x = new Signature(getNormalBSignature());
            normal_b_x.add(IPLSignatureFactory.XOR, IPLConnectives.XOR);
        }
        return normal_b_x;
    }
}
