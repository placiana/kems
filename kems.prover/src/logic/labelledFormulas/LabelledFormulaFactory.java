package logic.labelledFormulas;

import java.util.Map;
import java.util.TreeMap;

import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;

public class LabelledFormulaFactory {
	
	Map<String, SignedFormula> _signedFormulas;
	
    public LabelledFormulaFactory() {
        _signedFormulas = new TreeMap<String, SignedFormula>();
    }


	public LabelledFormula createLabelledFormula(String aLabelString, SignedFormula aSignedFormula) {
		FormulaLabel aLabel = FormulaLabel.constant(0);
		if (aLabelString == "c") {
			aLabel = FormulaLabel.constant(0);
		} else {
			aLabel = FormulaLabel.variable(0);
			
		}
		
		return new LabelledFormula(aLabel, aSignedFormula);
		
	}
	
	public LabelledFormula createLabelledFormula(Context aContext, FormulaSign aSign, Formula aFormula) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void cloneAll(SignedFormulaFactory signedFormulaFactory, FormulaFactory _ff) {
		// TODO Auto-generated method stub
		
	}

	public Map<String,LabelledFormula> getLabelledFormulas() {
		// TODO Auto-generated method stub
		return _signedFormulas;
	}

}
