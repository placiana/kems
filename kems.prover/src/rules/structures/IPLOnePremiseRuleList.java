package rules.structures;

import java.util.List;

import logic.formulas.Connective;
import logic.signedFormulas.FormulaSign;
import rules.KERuleRole;
import rules.OneConclusionRule;
import rules.Rule;

public class IPLOnePremiseRuleList extends OnePremiseRuleList {

    private SignConnectiveRuleMultiMap connRules;

    public IPLOnePremiseRuleList() {
        connRules = new SignConnectiveRuleMultiMap();
    }
    
    
    public void add(FormulaSign sign, Connective conn, Rule rule) {
        add(rule);
        connRules.put(sign, conn, rule);
        
    }       
    public List<Rule> getMany(Connective conn, FormulaSign sign) {
        return connRules.get(sign, conn);
    }
}
