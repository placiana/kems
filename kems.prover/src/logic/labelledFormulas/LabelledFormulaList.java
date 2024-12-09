/*
 * Created on 12/11/2003, 09:47:19 
 *
 */
package logic.labelledFormulas;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaList;

/**
 * A class for representing lists of signed formulas. Uses Adaptor design
 * pattern.
 * 
 * @author Adolfo Neto
 *  
 */
public class LabelledFormulaList {

    private List<LabelledFormula> _sfl;

    public LabelledFormulaList() {
        _sfl = new LinkedList<LabelledFormula>();
    }

    public LabelledFormulaList(LabelledFormula sf) {
        this();
        _sfl.add(sf);
    }

    public LabelledFormulaList(List<LabelledFormula> sfl) {
    	this();
        _sfl.addAll(sfl);
    }

    public void add(LabelledFormula sf) {
        _sfl.add(sf);
    }

    public void addAll(LabelledFormulaList sfl) {
        _sfl.addAll(sfl.getList());
    }
    
    public void addAll(Collection<LabelledFormula> c) {
        Iterator<LabelledFormula> i = c.iterator();
        LabelledFormulaList sfl = new LabelledFormulaList();
        
        while(i.hasNext()){
        	LabelledFormula sf = (LabelledFormula) i.next();
            sfl.add(sf);
        }
        
        addAll(sfl);
    }
   

    public List<LabelledFormula> getList() {
        return _sfl;
    }

    public LabelledFormula get(int index) {
        return (LabelledFormula) _sfl.get(index);
    }

    public int size() {
        return _sfl.size();
    }

    public String toString() {
        return _sfl.toString();
    }

    public boolean contains(LabelledFormula sf) {
        return _sfl.contains(sf);
    }

    public boolean remove(LabelledFormula sf) {
        return _sfl.remove(sf);
    }

    public LabelledFormula remove(int index) {
        return (LabelledFormula) _sfl.remove(index);
    }

    public Iterator<LabelledFormula> iterator() {
        return _sfl.iterator();
    }

    /**
     * @param sf
     * @param i
     */
    public void add(int i, LabelledFormula sf) {
        _sfl.add(i, sf);
    }
    
//    public void sort (ISignedFormulaComparator comparator){
//		System.err.println("BEFORE SORT: "+ _sfl);
//
//    	if (comparator.getComparatorDescriptor().equals(NormalFormulaOrderSignedFormulaComparator.DESCRIPTOR)){
//            Collections.sort(_sfl);
//    	}
//    	else{
//        	if (comparator.getComparatorDescriptor().equals(ReverseInsertionOrderSignedFormulaComparator.DESCRIPTOR)){
//        		System.err.println(_sfl.size());
//        	}
//        	else{
//                Collections.sort(_sfl, comparator);
//        	}
//    	}
//		System.err.println("AFTER SORT:" +_sfl);
//
//    	
//    }

    /**
     * @return
     */
    public String show() {
        String result="";
        Iterator<LabelledFormula> it = this.iterator();
        while (it.hasNext()){
            result += it.next()+ System.getProperty("line.separator");
        }
        
        return result;
    }

	public SignedFormulaList toSignedFormulaList() {
	       // Convert List<LabelledFormula> to List<Formula>
        List<SignedFormula> formulas = this._sfl.stream()
                .map(LabelledFormula::getSignedFormula) // Extract Formula from each LabelledFormula
                .collect(Collectors.toList());   // Collect into a List

		return new SignedFormulaList(formulas);
		
	}

//	public void reverse() {
//		Collections.reverse(_sfl);
//	}
}