package compiler.lexanal;

import java.io.*;

import compiler.report.*;
import compiler.synanal.*;

%%

%class      PascalLex
%public

%line
%column

/* Vzpostavimo zdruzljivost z orodjem Java Cup.
 * To bi lahko naredili tudi z ukazom %cup,
 * a v tem primeru ne bi mogli uporabiti razreda compiler.lexanal.PascalSym
 * namesto razreda java_cup.runtime.Symbol za opis osnovnih simbolov. */
%cupsym     compiler.synanal.PascalTok
%implements java_cup.runtime.Scanner
%function   next_token
%type       PascalSym
%eofval{
    return new PascalSym(PascalTok.EOF);
%eofval}
%eofclose

%{
    private PascalSym sym(int type) {
        return new PascalSym(type, yyline + 1, yycolumn + 1, yytext());
    }
%}

%eof{
%eof}

%%

\r|\n|\r\n | [ \t\f]	{ /* ignore */ }

"["   { return sym(PascalTok.LBRACKET); }
"]"   { return sym(PascalTok.RBRACKET); }
"("   { return sym(PascalTok.LPARENTHESIS); }
")"   { return sym(PascalTok.RPARENTHESIS); }

";"   { return sym(PascalTok.SEMIC); }
","   { return sym(PascalTok.COMMA); }

"<"   { return sym(PascalTok.LTH); }
">="   { return sym(PascalTok.GEQ); }
"<>"   { return sym(PascalTok.NEQ); }
">"   { return sym(PascalTok.GTH); }
"<="   { return sym(PascalTok.LEQ); }
":="   { return sym(PascalTok.ASSIGN); }
"="   { return sym(PascalTok.EQU); }

"."   { return sym(PascalTok.DOT); }
".."   { return sym(PascalTok.DOTS); }
"^"   { return sym(PascalTok.PTR); }
":"   { return sym(PascalTok.COLON); }

"*"   { return sym(PascalTok.MUL); }
"+"   { return sym(PascalTok.ADD); }
"-"   { return sym(PascalTok.SUB); }

"char"   { return sym(PascalTok.CHAR); }
"int"   { return sym(PascalTok.INT); }
"bool"   { return sym(PascalTok.BOOL); }


"function"   { return sym(PascalTok.FUNCTION); }
"const"   { return sym(PascalTok.CONST); }
"array"   { return sym(PascalTok.ARRAY); }
"for"   { return sym(PascalTok.FOR); }
"not"   { return sym(PascalTok.NOT); }
"and"   { return sym(PascalTok.AND); }
"record"   { return sym(PascalTok.RECORD); }
"type"   { return sym(PascalTok.TYPE); }
"nil"   { return sym(PascalTok.NIL); }
"or"   { return sym(PascalTok.OR); }
"begin"  { return sym(PascalTok.BEGIN); }
"div"   { return sym(PascalTok.DIV); }
"if" { return sym(PascalTok.IF); }
"of"   { return sym(PascalTok.OF); }
"program"   { return sym(PascalTok.PROGRAM); }
"else"   { return sym(PascalTok.ELSE); }
"to"   { return sym(PascalTok.TO); }
"while"   { return sym(PascalTok.WHILE); }
"then"   { return sym(PascalTok.THEN); }
"end"   { return sym(PascalTok.END); }
"var"   { return sym(PascalTok.VAR); }
"procedure"   { return sym(PascalTok.PROCEDURE); }
"do"   { return sym(PascalTok.DO); }


"true"|"false"	{ return sym(PascalTok.BOOL_CONST); }
[0-9]+   { return sym(PascalTok.INT_CONST); }
'[\x20-\x7E]*'   { return sym(PascalTok.CHAR_CONST); }

\{([\r\n]|.)*\}  {   }

[_a-zA-Z][0-9_a-zA-Z]*   { return sym(PascalTok.IDENTIFIER); }

/*   { return sym(PascalTok.error); }*/
