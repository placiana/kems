package logic.labelledFormulas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Context {

    private LinkedList<FormulaLabel> labels;

    private final Map<FormulaLabel, Set<FormulaLabel>> greaterThanMap;
    private final Map<FormulaLabel, Set<FormulaLabel>> lessThanMap;

    public Context() {
        labels = new LinkedList<FormulaLabel>();
        this.greaterThanMap = new HashMap<>();
        this.lessThanMap = new HashMap<>();

    }

    // Add an element to the set
    public void addElement(FormulaLabel element) {
        greaterThanMap.putIfAbsent(element, new HashSet<>());
        lessThanMap.putIfAbsent(element, new HashSet<>());
    }

    // Define a relation: a < b
    public void addRelation(FormulaLabel a, FormulaLabel b) {
        addElement(a);
        addElement(b);

        greaterThanMap.get(a).add(b);
        lessThanMap.get(b).add(a);
    }

    // Compare two elements in the partial order
    public int compare(FormulaLabel a, FormulaLabel b) {
        if (isLessThan(a, b)) {
            return -1; // a < b
        } else if (isGreaterThan(a, b)) {
            return 1; // a > b
        } else {
            return 0; // a and b are incomparable
        }
    }

    // Check if a < b
    public boolean isLessThan(FormulaLabel a, FormulaLabel b) {
        return greaterThanMap.get(a).contains(b);
    }

    // Check if a > b
    public boolean isGreaterThan(FormulaLabel a, FormulaLabel b) {
        return lessThanMap.get(a).contains(b);
    }

    // Check if a == b (i.e., a and b are comparable and equal)
    public boolean isEqual(FormulaLabel a, FormulaLabel b) {
        return !isLessThan(a, b) && !isGreaterThan(a, b);
    }

    // Check if a <= b (a is less than or equal to b)
    public boolean isLessThanOrEqualTo(FormulaLabel a, FormulaLabel b) {
        return isLessThan(a, b) || isEqual(a, b);
    }

    // Check if a >= b (a is greater than or equal to b)
    public boolean isGreaterThanOrEqualTo(FormulaLabel a, FormulaLabel b) {
        return isGreaterThan(a, b) || isEqual(a, b);
    }

    // Create a new element greater than the given element
    public FormulaLabel createGreaterThan(FormulaLabel element, FormulaLabel newElement) {
        addElement(newElement);
        addRelation(element, newElement);
        return newElement;
    }

    // Create a new element less than the given element
    public FormulaLabel createLessThan(FormulaLabel element, FormulaLabel newElement) {
        addElement(newElement);
        addRelation(newElement, element);
        return newElement;
    }

    // For debugging purposes: print all relations
    public void printRelations() {
        System.out.println("Greater than relations:");
        for (Map.Entry<FormulaLabel, Set<FormulaLabel>> entry : greaterThanMap.entrySet()) {
            System.out.println(entry.getKey() + " > " + entry.getValue());
        }

        System.out.println("Less than relations:");
        for (Map.Entry<FormulaLabel, Set<FormulaLabel>> entry : lessThanMap.entrySet()) {
            System.out.println(entry.getKey() + " < " + entry.getValue());
        }
    }

    public FormulaLabel getNewFormulaLabel() {
        FormulaLabel newLabel;
        if (this.labels.isEmpty()) {
            newLabel = new ContextFormulaLabel(this, this.labels.size());

        } else {
            newLabel = this.labels.getLast().getNextFormulaLabel();
        }
        this.labels.addLast(newLabel);

        addElement(newLabel);
        return newLabel;
    }

    public FormulaLabel getNewFormulaLabelGreaterThan(FormulaLabel label) {
        FormulaLabel newFormulaLabel = getNewFormulaLabel();
        createGreaterThan(label, newFormulaLabel);
        return newFormulaLabel;
    }

}
