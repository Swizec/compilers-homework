
import unittest, os, subprocess
from lxml import etree

class PascalLexerTestCase(unittest.TestCase):
    source = "" # test input file
    lex = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        try:
            os.remove('lexanal.xml')
        except OSError:
            pass

        if cls.source != None:
            subprocess.call("java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main test/"+cls.source.replace(".pascal", ""),
                            shell=True,
                            stdout=None)

            parser = etree.XMLParser(recover=True)
            cls.lex = etree.parse('lexanal.xml', parser)
            cls.root = cls.lex.getroot()

    def lex_test(self, tag, name=None, token=None, lexeme=None, line=None, column=None):
        if name:
            self.assertEquals(tag.tag, name)

        if name:
            self.assertEquals(tag.tag, name)

        args = [token, lexeme, column, line]
        keys = ['token', 'lexeme', 'column', 'line']

        for arg, key in zip(args, keys):
            if arg:
                self.assertEquals(tag.get(key), arg)

class TestLol(PascalLexerTestCase):
    source = "lol.pascal"

    def test_first_boolean(self):
        self.assertEquals(self.root.tag, 'lexanal')

        self.lex_test(self.root[0],
                      'terminal', 'BOOL_CONST', 'true', '1', '1')

class TestHelloWorld(PascalLexerTestCase):
    source = "helloworld.pascal"

    def test_program(self):
        self.lex_test(self.root[0],
                      'terminal', 'PROGRAM', line='2', column='1')

    def test_name(self):
        self.lex_test(self.root[1],
                      'terminal', 'IDENTIFIER', 'HelloWorld', '2', '9')
        self.lex_test(self.root[2],
                      'terminal', 'LPARENTHESIS', line='2', column='19')
        self.lex_test(self.root[3],
                      'terminal', 'IDENTIFIER', 'output', line='2', column='20')
        self.lex_test(self.root[4],
                      'terminal', 'RPARENTHESIS', line='2', column='26')
        self.lex_test(self.root[5],
                      'terminal', 'SEMIC', line='2', column='27')


    def test_begin(self):
        self.lex_test(self.root[6],
                      'terminal', 'BEGIN', line='3', column='1')

    def test_writeln(self):
        self.lex_test(self.root[7],
                      'terminal', 'IDENTIFIER', 'Writeln')
        self.lex_test(self.root[8],
                      'terminal', 'LPARENTHESIS')
        self.lex_test(self.root[9],
                      'terminal', 'CHAR_CONST', "'H'")
        self.lex_test(self.root[10],
                      'terminal', 'RPARENTHESIS')
        self.lex_test(self.root[11],
                      'terminal', 'SEMIC')
        self.lex_test(self.root[12],
                      'terminal', 'IDENTIFIER', 'Writeln')
        self.lex_test(self.root[13],
                      'terminal', 'LPARENTHESIS')
        self.lex_test(self.root[14],
                      'terminal', 'CHAR_CONST', "''''")
        self.lex_test(self.root[15],
                      'terminal', 'RPARENTHESIS')

    def test_end(self):
        self.lex_test(self.root[16],
                      'terminal', 'END', line='6', column='1')
        self.lex_test(self.root[17],
                      'terminal', 'DOT', line='6', column='4')

class TestComments(PascalLexerTestCase):
    source = "comments.pascal"

    def test_comments(self):
        self.assertEquals(len(self.root), 0)

class TestOddities(PascalLexerTestCase):
    source = "oddities.pascal"

    def test_0begin(self):
        self.lex_test(self.root[0],
                      'terminal', 'INT_CONST', '0')
        self.lex_test(self.root[1],
                      'terminal', 'BEGIN')

    def test_signed_num(self):
        self.lex_test(self.root[2],
                      'terminal', 'SUB')
        self.lex_test(self.root[3],
                      'terminal', 'INT_CONST', '100')
        self.lex_test(self.root[4],
                      'terminal', 'ADD')
        self.lex_test(self.root[5],
                      'terminal', 'INT_CONST', '523')

    def test_odd_comment(self):
        sequence = ['FUNCTION', 'IDENTIFIER', 'LPARENTHESIS', 'IDENTIFIER', 'IDENTIFIER', 'RPARENTHESIS']
        for i in xrange(len(sequence)):
            self.lex_test(self.root[6+i], token=sequence[i])

class TestBigOne(PascalLexerTestCase):
    source = "big_one.pascal"

    def test_stuff(self):
        fixture = open('test/big_one.xml', 'r').read()
        output = open('lexanal.xml', 'r').read()

        self.assertEqual(fixture, output)


if __name__ == '__main__':
    unittest.main()
