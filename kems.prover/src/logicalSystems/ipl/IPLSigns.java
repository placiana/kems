/*
 * Created on 05/04/2005
 *
 */
package logicalSystems.ipl;

import logic.signedFormulas.FormulaSign;

/**
 * Signs for IPL.
 */
public class IPLSigns {
    public static final FormulaSign FALSE = new FormulaSign(0);

    public static final FormulaSign TRUE = new FormulaSign(1);
    
    static{
        FALSE.setOpposite(TRUE);
        TRUE.setOpposite(FALSE);
    }
}
