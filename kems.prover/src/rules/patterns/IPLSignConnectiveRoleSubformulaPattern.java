package rules.patterns;

import java.util.List;

import logic.formulas.CompositeFormula;
import logic.formulas.Connective;
import logic.formulas.Formula;
import logic.formulas.FormulaFactory;
import logic.formulas.FormulaList;
import logic.labelledFormulas.LabelledFormula;
import logic.signedFormulas.FormulaSign;
import logic.signedFormulas.SignedFormula;
import logic.signedFormulas.SignedFormulaFactory;
import logic.signedFormulas.SignedFormulaList;
import rules.KERuleRole;
import rules.ipl.labels.LabelCondition;

public class IPLSignConnectiveRoleSubformulaPattern implements IBinarySignedFormulaPattern, ISubformulaPattern {
    Connective _mainConnective;

    FormulaSign _auxiliarySign;

    KERuleRole _auxiliaryRole;
    
    LabelCondition _labelCondition;

    Formula _match;
	

   public IPLSignConnectiveRoleSubformulaPattern(Connective conn, FormulaSign sign, KERuleRole ruleRole, LabelCondition labelCondition) {
        _mainConnective = conn;
        _auxiliarySign = sign;
        _auxiliaryRole = ruleRole;
        _labelCondition = labelCondition;
        
    }
	@Override
	public boolean matches(SignedFormula main, SignedFormula auxiliary) {
		LabelledFormula m = (LabelledFormula) main;
		LabelledFormula a = (LabelledFormula) auxiliary;
	    SignedFormulaList lfl = new SignedFormulaList();
	    lfl.add(main);
	    lfl.add(auxiliary);
	    boolean labelCondition = _labelCondition.matches(lfl); 
       return labelCondition && a.getSignedFormula().getSign().equals(_auxiliarySign)
               && recursivelyMatches(m.getSignedFormula().getFormula(), a);
	}

	private boolean recursivelyMatches(Formula main, LabelledFormula auxiliary) {
       if (matches(main, auxiliary)) {
           return true;
       } else {
           for (int i = 0; i < main.getImmediateSubformulas().size(); i++) {
               if (recursivelyMatches((Formula) main.getImmediateSubformulas()
                       .get(i), auxiliary)) {
                   return true;
               }
           }
       }
       return false;
	}

   private boolean matches(Formula main, LabelledFormula auxiliary) {

       boolean mainMatch = matchesConnective(main);
       //		System.err.println(mainMatch);

       if (mainMatch) {
       	List<Formula> l = _auxiliaryRole.getFormulas(main);

           for (int i = 0; i < l.size(); i++) {
               Formula f1 = (Formula) l.get(i);
               //				System.err.println(f1);
               //				System.err.println(auxiliary);
               if (f1.equals(auxiliary.getSignedFormula().getFormula())) {
                   return true;
               }

           }
       }

       return false;
   }
	
	private boolean matchesConnective(Formula f) {
       if (!(f instanceof CompositeFormula)) {
           return false;
       } else
           return ((CompositeFormula) f).getConnective().equals(
                   _mainConnective);
	}

	@Override
	public SignedFormulaList getAuxiliaryCandidates(SignedFormulaFactory sff,
			FormulaFactory ff, SignedFormula sfMain) {
		// TODO HAY QUE COMPLETAR ESTO !!!!
		
		List<Formula> formulas = _auxiliaryRole.getFormulas(sfMain.getFormula());
       SignedFormulaList sfl = new SignedFormulaList();

       for (int i = 0; i < formulas.size(); i++) {
       	
       	/*
       	sfl.add(lff.createLabelledFormula(
   			this._labelCondition.getAuxiliaryLabel((LabelledFormula)sfMain), 
   			sff.createSignedFormula(_auxiliarySign, (Formula) formulas.get(i)))
       	);
       	*/
       	sfl.add(
   			sff.createSignedFormula(_auxiliarySign, (Formula) formulas.get(i))
           );
       }

       return sfl;
	}

	/*
	@Override
	public boolean matchesMain(LabelledFormula sfMain) {
		// TODO Auto-generated method stub
		return false;
	}
	*/

	

	private Formula getMatchedSubformula(LabelledFormula main, LabelledFormula auxiliary) {
		 if (!(auxiliary.getSignedFormula().getSign().equals(_auxiliarySign))) {
	            return null;
	        } else {
	            return recursivelyGetMatchedSubformula(main.getSignedFormula().getFormula(), auxiliary);

	        }
	}

	private Formula recursivelyGetMatchedSubformula(Formula main, LabelledFormula auxiliary) {
       Formula tryMatch = getMatchedSubformula(main, auxiliary);
       if (tryMatch != null) {

           //			System.err.println(tryMatch + " aqui � tryMatch");
           return tryMatch;
       } else {
           for (int i = 0; i < main.getImmediateSubformulas().size(); i++) {
               tryMatch = recursivelyGetMatchedSubformula((Formula) main
                       .getImmediateSubformulas().get(i), auxiliary);
               if (tryMatch != null) {
                   //					System.err.println(main.getImmediateSubformulas().get(i)
                   // + " aqui � ...");
                   return tryMatch;
                   //					(Formula)main.getImmediateSubformulas().get(i);
               }
           }
       }
       return null;

	}

	private Formula getMatchedSubformula(Formula main, LabelledFormula auxiliary) {

       boolean mainMatch = matchesConnective(main);

       if (mainMatch) {
       	List<Formula> l = _auxiliaryRole.getFormulas(main);

           for (int i = 0; i < l.size(); i++) {
               Formula f1 = (Formula) l.get(i);
               //				System.err.println(f1);
               //				System.err.println(auxiliary);
               if (f1.equals(auxiliary.getSignedFormula().getFormula())) {
                   //					System.err.println(main + " aqui");
                   return main;
               }

           }
       }

       return null;

	}


	@Override
   public Formula getMatchedSubformula(SignedFormulaList sfl) {
       return getMatchedSubformula(sfl.get(0), sfl.get(1));
   }
	
	
   private Formula getMatchedSubformula(SignedFormula main,
           SignedFormula auxiliary) {
       if (!(auxiliary.getSign().equals(_auxiliarySign))) {
           return null;
       } else {
           //			System.err.println(recursivelyGetMatchedSubformula(main.getFormula(),
           // auxiliary)+" aqui � recGet");
           return recursivelyGetMatchedSubformula(main.getFormula(), auxiliary);

       }
   }
   
   private Formula recursivelyGetMatchedSubformula(Formula main,
           SignedFormula auxiliary) {

       Formula tryMatch = getMatchedSubformula(main, auxiliary);
       if (tryMatch != null) {

           //			System.err.println(tryMatch + " aqui � tryMatch");
           return tryMatch;
       } else {
           for (int i = 0; i < main.getImmediateSubformulas().size(); i++) {
               tryMatch = recursivelyGetMatchedSubformula((Formula) main
                       .getImmediateSubformulas().get(i), auxiliary);
               if (tryMatch != null) {
                   //					System.err.println(main.getImmediateSubformulas().get(i)
                   // + " aqui � ...");
                   return tryMatch;
                   //					(Formula)main.getImmediateSubformulas().get(i);
               }
           }
       }
       return null;
   }
   
   
   private Formula getMatchedSubformula(Formula main, SignedFormula auxiliary) {

        boolean mainMatch = matchesConnective(main);
       //		System.err.println(mainMatch);

       if (mainMatch) {
       	List<Formula> l = _auxiliaryRole.getFormulas(main);

           for (int i = 0; i < l.size(); i++) {
               Formula f1 = (Formula) l.get(i);
               //				System.err.println(f1);
               //				System.err.println(auxiliary);
               if (f1.equals(auxiliary.getFormula())) {
                   //					System.err.println(main + " aqui");
                   return main;
               }

           }
       }

       return null;
   }
	
	@Override
	public FormulaList getMainMatches(SignedFormula sf) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean matchesMain(SignedFormula sfMain) {
		// TODO Auto-generated method stub
		return false;
	}
}
