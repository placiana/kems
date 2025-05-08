package rules.ipl.labels;

import java.util.List;
import java.util.stream.Collectors;

import logic.labelledFormulas.ContextFormulaLabel;
import logic.labelledFormulas.FormulaLabel;
import logic.signedFormulas.SignedFormulaList;

public class NewLabelGetter extends LabelGetter {

	public static final String MAIN = "MAIN";
	public static final String AUX = "AUX";
	public static final String BOTH = "BOTH";
	

	private String getterType;
	private FormulaLabel computedLabel = null;
	
	// Constructor
	public NewLabelGetter(String getterType) {
		this.getterType = getterType;
	}

	public NewLabelGetter() { 
		this.getterType = "MAIN";
	}

		@Override
	public FormulaLabel getLabel(SignedFormulaList lfl) {
		//if (this.computedLabel != null)
		//    return this.computedLabel;
		    
		if (this.getterType == "MAIN") {
			this.computedLabel = lfl.get(0).getLabel().getGreaterFormulaLabel();
			return this.computedLabel;
		} else if (this.getterType == "AUX") {
			return lfl.get(1).getLabel().getGreaterFormulaLabel();
		} else if (this.getterType == "BOTH") {
			// map labelled formula list to a collection of formula labels
			List<FormulaLabel> labels = lfl.getList().stream().map(lf -> lf.getLabel()).collect(Collectors.toList());
			
			if (labels.get(0) instanceof ContextFormulaLabel) {
				// we should do something about this cast
				return ((ContextFormulaLabel)lfl.get(0).getLabel()).getContext().getNewFormulaLabelGreaterThanCollection(labels);
			} else {
				// get max index in labels list
				int maxIndex = labels.stream().mapToInt(l -> l.getIndex()).max().getAsInt();
				return new FormulaLabel(FormulaLabel.LabelType.CONSTANT, maxIndex + 1);

			}
		} else {
			return null;
		}

	}

}
