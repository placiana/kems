/*
 * Created on 03/08/2005
 *
 */
package logicalSystems.ipl;

import logic.logicalSystem.ISignature;
import rules.structures.IRulesStructureBuilder;
import rules.structures.RulesStructure;

/**
 * Creator of RulesStructure for IPL.
 */
public class IPLRulesStructureBuilder implements IRulesStructureBuilder {

    /* (non-Javadoc)
     * @see ruleStructures.IRulesStructureBuilder#getRuleStructure(logic.ISignature)
     */
    public RulesStructure getRuleStructure(ISignature signature) {
        IPLRuleStructures crs = new IPLRuleStructures(signature);
        return crs.getRuleStructure();
    }

}
