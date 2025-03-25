/*
 * Created on 30/11/2004
 *
 */
package rules.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.formulas.Connective;
import logic.signedFormulas.FormulaSign;
import rules.KERuleRole;
import rules.OneConclusionRule;
import rules.Rule;

/**
 * @author placiana
 * 
 * The same as ConnectiveRoleSignRuleMap but map returns a list of Rule instead of one instance.
 *  
 */
public class ConnectiveRoleSignRuleMultiMap {

    Map<ConnectiveRoleSignTrio,List<OneConclusionRule>> _m = new HashMap<ConnectiveRoleSignTrio, List<OneConclusionRule>>();

    List<ConnectiveRoleSignTrio> _pairs = new ArrayList<ConnectiveRoleSignTrio>();

    /**
     *  
     */
    public ConnectiveRoleSignRuleMultiMap() {
        super();
    }

    /**
     * @param conn
     * @param conn2
     * @param rulew
     */
    public void put(Connective conn, KERuleRole role, FormulaSign sign,
            OneConclusionRule rulew) {
        ConnectiveRoleSignTrio trio = createConnectiveRoleSignTrio(conn, role, sign);
    	if (_m.containsKey(trio)) {
    		_m.get(trio).add(rulew);
    	} else {
    		List<OneConclusionRule> list = new ArrayList<OneConclusionRule>() {{add(rulew);}};
    		_m.put(trio, list);
    	}
        
    }

    /**
     * @param conn
     * @param conn2
     * @return
     */
    public List<Rule> get(Connective conn, KERuleRole role, FormulaSign sign) {
        List<OneConclusionRule> aList = _m.get(createConnectiveRoleSignTrio(conn, role, sign));
        
        List<Rule> aRuleList;
        if (aList == null)
        	 aRuleList = new ArrayList<>();
        else 
        	aRuleList = new ArrayList<>(aList);
        return aRuleList;
    }

    private ConnectiveRoleSignTrio createConnectiveRoleSignTrio(Connective conn,
            KERuleRole role, FormulaSign sign) {
        ConnectiveRoleSignTrio ccp;
        for (int i = 0; i < _pairs.size(); i++) {
            ccp = (ConnectiveRoleSignTrio) _pairs.get(i);
            if (ccp.getConnective1().equals(conn) && ccp.getRole().equals(role)
                    && ccp.getSign().equals(sign)) {
                return ccp;
            }
        }

        ccp = new ConnectiveRoleSignTrio(conn, role, sign);
        _pairs.add(ccp);
        return ccp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return _m.toString();
    }

    class ConnectiveRoleSignTrio {
        public ConnectiveRoleSignTrio(Connective _conn, KERuleRole _role, FormulaSign sign) {
            super();
            this._conn = _conn;
            this._role = _role;
            this._sign = sign;
        }

        public Connective getConnective1() {
            return _conn;
        }

        public KERuleRole getRole() {
            return _role;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return "(" + _conn.toString() + "," + _role + ")";
        }

        Connective _conn;

        KERuleRole _role;
        /**
         * @return Returns the _sign.
         */
        public FormulaSign getSign() {
            return _sign;
        }
        FormulaSign _sign;
    }
}