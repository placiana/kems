package satpablo;
import java_cup.runtime.*;

%%

/*

  Adolfo Gustavo Serra Seca Neto, December 2004
  Lexical analyser for classical propositional logic formulas
  SATLIB format with signs, implication, biimplication 
  and without headers

  This is sats for the new formula and signed formula classes.

  What to change (in case you want to change the file format):
	name of the parser and of the symbols class
	  - sats5Lexer, satpablosym (several references!)
      productions and states (obviously)

  How to produce the lexer:

  - Run the command:

  	java JFlex.Main satlib-sat-s5.flex

  	which will create the following file:

	  	sats5Lexer.java



*/

/* Name of the parser class to be generated */
%class satpabloLexer
%public
%line
%column

%cup

%{

    StringBuffer number = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}


%eofval{
  return symbol(satpablosym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t\f]
String = [a-z_A-Z_1-9][a-z_A-Z_0-9_,]*
Biimplies = "<=>"
Xor = "%"
Implies = "->"
Sign = "T"|"F"
Top = "TOP"
Bottom = "BOT"
Consistency = "@"
Inconsistency = "#"
Label = "c"|"x"

%state SIGN FORMULA

%%

/* beginning of the lexical analyser */
<YYINITIAL>{

	{Label}  			{
	  						yybegin(SIGN);
						   	return symbol(satpablosym.LABEL, yytext());
						}

}
<SIGN>{
	{Sign}  			{
	  						yybegin(FORMULA);
						   	return symbol(satpablosym.SIGN, yytext());
						}

}

<FORMULA> {

    {LineTerminator}      {
    						yybegin(YYINITIAL);
    						return symbol(satpablosym.EOL);
	    				   }

    {Biimplies}        { return symbol(satpablosym.BIIMPLIES); }
    {Implies}          { return symbol(satpablosym.IMPLIES); }
    {Xor}              { return symbol(satpablosym.XOR); }
    "-"                { return symbol(satpablosym.NEG); }
    "*"                { return symbol(satpablosym.AND); }
    "+"                { return symbol(satpablosym.OR); }
    "("                { return symbol(satpablosym.LPAREN); }
    ")"                { return symbol(satpablosym.RPAREN); }
    {Top}              { return symbol(satpablosym.TOP); }
    {Bottom}           { return symbol(satpablosym.BOTTOM); }
    {Consistency}      { return symbol(satpablosym.CONSISTENCY); }
    {Inconsistency}      { return symbol(satpablosym.INCONSISTENCY); }
    

    {String}           { return symbol (satpablosym.STRING, yytext());}

    {WhiteSpace}       { /* just skip what was found, do nothing */ }

}

[^]                    { throw new Error("Illegal character <"+yytext()+ "> at line " + yyline + ", column " + yychar ); }


