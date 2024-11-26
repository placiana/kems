package logic.labelledFormulas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Context {

	private LinkedList<FormulaLabel> labels;

	public Context() {
		labels = new LinkedList<FormulaLabel>();
	}
	
	public FormulaLabel getNewFormulaLabel() {
		FormulaLabel newLabel = this.labels.getLast().getNextFormulaLabel();
		this.labels.addLast(newLabel);
		return newLabel;
	}


}
