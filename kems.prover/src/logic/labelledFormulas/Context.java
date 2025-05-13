package logic.labelledFormulas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The Context class represents a partial order context for FormulaLabels.
 * It maintains a set of labels and their relationships in terms of "greater than" and "less than".
 * 
 * <p>This class provides methods to add elements, define relations, and compare elements within the context.
 * It also includes utility methods to generate new labels and print the current relations for debugging purposes.</p>
 * 
 */
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
        if (isLowerThan(a, b)) {
            return -1; // a < b
        } else if (isGreaterThan(a, b)) {
            return 1; // a > b
        } else {
            return 0; // a and b are incomparable
        }
    }

    // Check if a < b
    public boolean isLowerThan(FormulaLabel a, FormulaLabel b) {
        return greaterThanMap.get(a).contains(b);
    }

    // Check if a > b
    public boolean isGreaterThan(FormulaLabel a, FormulaLabel b) {
        return lessThanMap.get(a).contains(b);
    }

    // Check if a == b (i.e., a and b are comparable and equal)
    public boolean isEqual(FormulaLabel a, FormulaLabel b) {
        
        if (a == b) {
            return true; // Same object reference
        }
        if (greaterThanMap.get(a).contains(b) || lessThanMap.get(a).contains(b)) {
            return false; // They are comparable but not equal
        }
        return false; // They are incomparable, thus not equal 

    }

    /**
     * Checks if two FormulaLabels are comparable.
     * 
     * @param label1 the first FormulaLabel
     * @param label2 the second FormulaLabel
     * @return true if the labels are comparable, false otherwise
     */
    public boolean areComparable(FormulaLabel label1, FormulaLabel label2) {
        // Implement the logic to determine if the labels are comparable
        return isGreaterThan(label1, label2) || isGreaterThan(label2, label1) || isEqual(label1, label2);
    }
    
    // Check if a <= b (a is less than or equal to b)
    public boolean isLowerOrEqualTo(FormulaLabel a, FormulaLabel b) {
        return isLowerThan(a, b) || isEqual(a, b);
    }

    // Check if a >= b (a is greater than or equal to b)
    public boolean isGreaterOrEqualTo(FormulaLabel a, FormulaLabel b) {
        return isGreaterThan(a, b) || isEqual(a, b);
    }

    // Create a new element greater than the given element
    public FormulaLabel setAsGreaterThan(FormulaLabel element, FormulaLabel newElement) {
        addElement(newElement);
        addRelation(element, newElement);
        return newElement;
    }

    // Create a new element less than the given element
    public FormulaLabel setAsLowerThan(FormulaLabel element, FormulaLabel newElement) {
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
        newLabel = new ContextFormulaLabel(this, this.labels.size());
        /*
        if (this.labels.isEmpty()) {
            newLabel = new ContextFormulaLabel(this, this.labels.size());

        } else {
            newLabel = this.labels.getLast().getNextFormulaLabel();
        }
        */
        this.labels.addLast(newLabel);

        addElement(newLabel);
        return newLabel;
    }

    /**
     * Generates a new formula label that is greater than the specified label.
     *
     * @param label the reference formula label to compare against
     * @return a new formula label that is greater than the specified label
     */
    public FormulaLabel getNewFormulaLabelGreaterThan(FormulaLabel label) {
        FormulaLabel newFormulaLabel = getNewFormulaLabel();
        setAsGreaterThan(label, newFormulaLabel);
        return newFormulaLabel;
    }


    /**
     * Generates a new FormulaLabel that is greater than all the labels in the provided collection.
     * 
     * This method first creates a new FormulaLabel using the getNewFormulaLabel method.
     * Then, it iterates over the given collection of FormulaLabels and sets the new label
     * as greater than each label in the collection.
     * 
     * @param labelCollection the collection of FormulaLabels to compare against
     * @return a new FormulaLabel that is greater than all the labels in the provided collection
     */
    public FormulaLabel getNewFormulaLabelGreaterThanCollection(Collection<FormulaLabel> labelCollection) {
        FormulaLabel newFormulaLabel = getNewFormulaLabel();

        for (FormulaLabel label : labelCollection) {
            setAsGreaterThan(label, newFormulaLabel);
        }
        return newFormulaLabel;
    }

	public FormulaLabel getNewFormulaLabelLowerThan(FormulaLabel label) {
        FormulaLabel newFormulaLabel = getNewFormulaLabel();
        setAsLowerThan(label, newFormulaLabel);
        return newFormulaLabel;
	}

}
