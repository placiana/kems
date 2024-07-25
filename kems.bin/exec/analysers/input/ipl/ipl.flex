package ipl;
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
	  - iplLexer, iplsym (several references!)
      productions and states (obviously)

  How to produce the lexer:

  - Run the command:

  	java JFlex.Main satlib-sat-s5.flex

  	which will create the following file:

	  	iplLexer.java



*/

/* Name of the parser class to be generated */
%class iplLexer
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
  return symbol(iplsym.EOF);
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
Label = ("c"|"x")[0-9]+


%state FORMULA

%%

/* beginning of the lexical analyser */
<YYINITIAL>{

	{Sign}  			{
	  						yybegin(FORMULA);
						   	return symbol(iplsym.SIGN, yytext());
						}

}
<FORMULA> {

    {LineTerminator}      {
    						yybegin(YYINITIAL);
    						return symbol(iplsym.EOL);
	    				   }

    {Biimplies}        { return symbol(iplsym.BIIMPLIES); }
    {Implies}          { return symbol(iplsym.IMPLIES); }
    {Xor}              { return symbol(iplsym.XOR); }
    "-"                { return symbol(iplsym.NEG); }
    "*"                { return symbol(iplsym.AND); }
    "+"                { return symbol(iplsym.OR); }
    "("                { return symbol(iplsym.LPAREN); }
    ")"                { return symbol(iplsym.RPAREN); }
    {Top}              { return symbol(iplsym.TOP); }
    {Bottom}           { return symbol(iplsym.BOTTOM); }
    {Consistency}      { return symbol(iplsym.CONSISTENCY); }
    {Inconsistency}    { return symbol(iplsym.INCONSISTENCY); }
    {Label}            { return symbol(iplsym.LABEL, yytext()); }
    

    {String}           { return symbol (iplsym.STRING, yytext());}

    {WhiteSpace}       { /* just skip what was found, do nothing */ }

}

[^]                    { throw new Error("Illegal character <"+yytext()+ "> at line " + yyline + ", column " + yychar ); }


