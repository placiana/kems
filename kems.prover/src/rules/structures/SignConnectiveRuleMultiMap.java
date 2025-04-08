/*
 * Created on 19/11/2004
 *
 */
package rules.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.formulas.Connective;
import logic.signedFormulas.FormulaSign;
import rules.IRule;
import rules.Rule;

/**
 * @author adolfo
 *  
 */
public class SignConnectiveRuleMultiMap {

    Map<SignConnectivePair,List<IRule>> _m = new HashMap<SignConnectivePair, List<IRule>>();

    List<SignConnectivePair> _pairs = new ArrayList<SignConnectivePair>();

    public List<Rule> get(FormulaSign fs, Connective conn) {
        List<IRule> iRuleList = _m.get(createSignConnectivePair(fs, conn));
        
        if (iRuleList == null) {
            return new ArrayList<>(); // Return an empty list if no rules are found
        }
        // Create a new list and cast each element
        List<Rule> ruleList = new ArrayList<>();
        for (IRule iRule : iRuleList) {
            if (iRule instanceof Rule) {
                ruleList.add((Rule) iRule);
            } else {
                throw new ClassCastException("Element is not an instance of Rule: " + iRule.getClass().getName());
            }
        }
        return ruleList;        


    }

    private SignConnectivePair createSignConnectivePair(FormulaSign fs,
            Connective conn) {
        SignConnectivePair scp;
        for (int i = 0; i < _pairs.size(); i++) {
            scp = (SignConnectivePair) _pairs.get(i);
            if (scp.getConnective().equals(conn) && scp.getSign().equals(fs)) {
                return scp;
            }
        }

        scp = new SignConnectivePair(fs, conn);
        _pairs.add(scp);
        return scp;
    }

    public void put(FormulaSign fs, Connective conn, Rule r) {
        
        
        SignConnectivePair pair = createSignConnectivePair(fs, conn);
        if (_m.containsKey(pair)) {
            _m.get(pair).add(r);
        } else {
            List<IRule> list = new ArrayList<IRule>() {{add(r);}};
            _m.put(pair, list);
        }
                
        
        //_m.put(createSignConnectivePair(fs, conn), r);
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return _m.toString();
	}
}


