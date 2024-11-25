package logic.labelledFormulas;

import ConversorWagnerSATLIB.ConversorWagnerSATLIBLexer;
import ConversorWagnerSATLIB.ConversorWagnerSATLIBParser;
import logic.formulas.FormulaFactory;
import logic.problem.Problem;
import parsers.ParserUser;

public class LabelledFormulaCreator {


	private static final String IPL_PARSER = "satpablo.satpabloParser";
	private static final String IPL_LEXER = "satpablo.satpabloLexer";

	private LabelledFormulaFactory _sff;

	private FormulaFactory _ff;

	private Problem _problem;

	private String _packageName;

	boolean _twoPhases = false;

	/**
	 * Creates a SignedFormulaCreator for a default lib dir.
	 * 
	 * @param packageName
	 */
	public LabelledFormulaCreator(String packageName) {
		_sff = new LabelledFormulaFactory();
		_ff = new FormulaFactory();
		_packageName = packageName;
	}

	public void setTwoPhases(boolean option) {
		_twoPhases = option;
	}

	private String removeUselessCharacters(String s) {
		return s.trim();
	}

	public Problem parseText(String signedFormulasAsString) {
		signedFormulasAsString = removeUselessCharacters(signedFormulasAsString);

		String s;

		ParserUser pu1 = new ParserUser();

		_problem = (Problem) pu1.parseString(IPL_LEXER, IPL_PARSER,
				signedFormulasAsString);

		_sff.cloneAll(_problem.getSignedFormulaFactory(), _ff);

		return _problem;
	}

	/**
	 * Converts a string to a labelled formula.
	 * 
	 * @param signedFormulaAsString
	 * @return
	 */
	public LabelledFormula parseString(String labelledFormulaAsString) {

		this.parseText(labelledFormulaAsString);

		return (LabelledFormula) _sff.getLabelledFormulas().get(
				_problem.getSignedFormulaFactory().getLastSignedFormulaAdded()
						.toString());
	}

	public Problem parseFile(String completeFilename) {

		String s;
		if (_twoPhases) {
			ParserUser pu1 = new ParserUser();

			s = (String) pu1.parseFile(IPL_LEXER, IPL_PARSER, completeFilename);

			ParserUser pu2 = new ParserUser();
			_problem = (Problem) pu2.parseString(_packageName + "."
					+ _packageName + "Lexer", _packageName + "." + _packageName
					+ "Parser", s);
			_sff.cloneAll(_problem.getSignedFormulaFactory(), _ff);
		} else {
			ParserUser pu2 = new ParserUser();
			_problem = (Problem) pu2.parseFile(_packageName + "."
					+ _packageName + "Lexer", _packageName + "." + _packageName
					+ "Parser", completeFilename);

			_sff.cloneAll(_problem.getSignedFormulaFactory(), _ff);
		}

		_problem.setFilename(completeFilename);
		_problem.setName(completeFilename);

		return _problem;

	}

	public LabelledFormulaFactory getSignedFormulaFactory() {
		return _sff;
	}

	public FormulaFactory getFormulaFactory() {
		return _ff;
	}

	public LabelledFormulaFactory getLabelledFormulaFactory() {
		return _sff;
	}

}