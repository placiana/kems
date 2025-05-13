package logic.labelledFormulas;

public class ContextFormulaLabel extends FormulaLabel {

    Context _context;

    public ContextFormulaLabel(Context context) {
        super(LabelType.CONSTANT, 0);
        _context = context;

    }

    public ContextFormulaLabel(Context context, int index) {
        super(LabelType.CONSTANT, index);
        _context = context;

    }

    public boolean lowerOrEqualThan(FormulaLabel aux) {
        return this._context.isLowerOrEqualTo(this, aux);
    }

    public boolean lowerThan(FormulaLabel aux) {
        return this._context.isLowerThan(this, aux);
    }

    public FormulaLabel getNextFormulaLabel() {
        return new ContextFormulaLabel(_context, getIndex() + 1);
    }

    public FormulaLabel getGreaterFormulaLabel() {
        return _context.getNewFormulaLabelGreaterThan(this);
    }

    public FormulaLabel getLowerFormulaLabel() {
        return _context.getNewFormulaLabelLowerThan(this);
    }
    
    public Context getContext() {
        return _context;
    }

}
