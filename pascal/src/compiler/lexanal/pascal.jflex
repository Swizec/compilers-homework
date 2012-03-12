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

[ \n\t]+						{ }

"true"|"false"					{ return sym(PascalTok.BOOL_CONST); }
