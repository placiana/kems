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
        return this._context.isLessThanOrEqualTo(this, aux);
    }
    public boolean lowerThan(FormulaLabel aux) {
        return this._context.isLessThan(this, aux);
    }
}
