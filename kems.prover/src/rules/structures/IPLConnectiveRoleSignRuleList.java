package rules.structures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.formulas.Connective;
import logic.signedFormulas.FormulaSign;
import rules.KERuleRole;
import rules.OneConclusionRule;
import rules.Rule;
import rules.structures.ConnectiveRoleSignRuleMap.ConnectiveRoleSignTrio;

public class IPLConnectiveRoleSignRuleList extends ConnectiveRoleSignRuleList {

	private ConnectiveRoleSignRuleMultiMap connRules;

    public IPLConnectiveRoleSignRuleList() {
        connRules = new ConnectiveRoleSignRuleMultiMap();
    }
	
    public void add(Connective conn, KERuleRole role, FormulaSign sign,  OneConclusionRule rulew) {
        add(rulew);
        connRules.put(conn,role, sign, rulew);
        
    }	
    public List<Rule> getMany(Connective conn, KERuleRole role, FormulaSign sign) {
        return connRules.get(conn, role, sign);
    }
    
}
