/*
 * Created on 21/10/2004
 *
 */
package main.proofTree;

import logic.labelledFormulas.LabelledFormula;
import main.proofTree.origin.IOrigin;
import main.proofTree.origin.NamedOrigin;

public class LabelledFormulaNode extends Node implements Cloneable {

    LabelledFormula _content;

    LabelledFormulaNodeState _state;

    IOrigin _origin = NamedOrigin.DEFINITION;

    public LabelledFormulaNode(LabelledFormula sf, LabelledFormulaNodeState state, IOrigin origin) {
        this._content = sf;
        this._state = state;
        this._origin = origin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see proofTree.Node#getContent()
     */
    public LabelledFormula getContent() {
        return _content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see proofTree.Node#getState()
     */
    public State getState() {
        return _state;
    }

    public void setState(LabelledFormulaNodeState s) {
        _state = s;
    }

    public IOrigin getOrigin() {
        return _origin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getContent() + ((getOrigin() != null) ? (
//                        " [" + getState() + "]" +
        "   Origin: [" + getOrigin().toString() + "]") : "");
    }

//    public Collection getXMLElements() {
//        List l = new ArrayList();
//
//        Element content = new Element("content");
//        content.setText(getContent().toString());
//        Element origin = new Element("origin");
//        origin.setText(getOrigin().toString());
//
//        l.add(content);
//        l.add(origin);
//
//        return l;
//
//    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see proofTree.Node#asXMLElement()
//     */
//    public Element asXMLElement() {
//        Element root = new Element("node");
//        root.addContent(getXMLElements());
//        return root;
//    }

    public void setOrigin(IOrigin _origin) {
        this._origin = _origin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        LabelledFormulaNode sf = new LabelledFormulaNode((LabelledFormula) this.getContent(),
                (LabelledFormulaNodeState) this.getState(), this.getOrigin());
        sf.setNext(this.getNext());
        sf.setBranch(this.getBranch());
        sf.setPrevious(this.getPrevious());
        return sf;
    }
}