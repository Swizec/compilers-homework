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

[ \n\t]+	{ }

"true"|"false"	{ return sym(PascalTok.BOOL_CONST); }
/*   { return sym(PascalTok.CHAR_CONST); }
   { return sym(PascalTok.LTH); }
   { return sym(PascalTok.FUNCTION); }
   { return sym(PascalTok.LBRACKET); }
   { return sym(PascalTok.CONST); }
   { return sym(PascalTok.CHAR); }
   { return sym(PascalTok.SEMIC); }
   { return sym(PascalTok.INT); }
   { return sym(PascalTok.ARRAY); }
   { return sym(PascalTok.FOR); }
   { return sym(PascalTok.NOT); }
   { return sym(PascalTok.AND); }
   { return sym(PascalTok.RECORD); }
   { return sym(PascalTok.TYPE); }
   { return sym(PascalTok.NIL); }
   { return sym(PascalTok.OR); }
   { return sym(PascalTok.BOOL); }
   { return sym(PascalTok.COMMA); }
   { return sym(PascalTok.BEGIN); }
   { return sym(PascalTok.DIV); }
   { return sym(PascalTok.GEQ); }
   { return sym(PascalTok.IF); }
   { return sym(PascalTok.ASSIGN); }
   { return sym(PascalTok.DOT); }
   { return sym(PascalTok.PTR); }
   { return sym(PascalTok.OF); }
   { return sym(PascalTok.DOTS); }
   { return sym(PascalTok.EOF); }
   { return sym(PascalTok.RBRACKET); }
   { return sym(PascalTok.INT_CONST); }
*/
"program"   { return sym(PascalTok.PROGRAM); }
/*
   { return sym(PascalTok.error); }
   { return sym(PascalTok.MUL); }
   { return sym(PascalTok.ADD); }
   { return sym(PascalTok.LPARENTHESIS); }
   { return sym(PascalTok.NEQ); }
   { return sym(PascalTok.GTH); }
   { return sym(PascalTok.EQU); }
   { return sym(PascalTok.COLON); }
   { return sym(PascalTok.ELSE); }
   { return sym(PascalTok.TO); }
   { return sym(PascalTok.WHILE); }
   { return sym(PascalTok.THEN); }
   { return sym(PascalTok.RPARENTHESIS); }
   { return sym(PascalTok.LEQ); }
   { return sym(PascalTok.END); }
   { return sym(PascalTok.BOOL_CONST); }
   { return sym(PascalTok.SUB); }
   { return sym(PascalTok.VAR); }
   { return sym(PascalTok.PROCEDURE); }
   { return sym(PascalTok.DO); }
   { return sym(PascalTok.IDENTIFIER); }*/
