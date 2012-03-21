
import unittest, os, subprocess
from lxml import etree

class PascalLexerTestCase(unittest.TestCase):
    source = "" # test input file
    lex = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        if cls.source != None:
            subprocess.call("java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main test/"+cls.source.replace(".pascal", ""),
                            shell=True,
                            stdout=None)

            parser = etree.XMLParser(recover=True)
            cls.lex = etree.parse('lexanal.xml', parser)

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
        self.assertEquals(self.lex.getroot().tag, 'lexanal')

        self.lex_test(self.lex.getroot()[0],
                      'terminal', 'BOOL_CONST', 'true', '1', '1')

class TestHelloWorld(PascalLexerTestCase):
    source = "helloworld.pascal"

    def test_program(self):
        self.lex_test(self.lex.getroot()[0],
                      'terminal', 'PROGRAM', line='2', column='1')

    def test_name(self):
        self.lex_test(self.lex.getroot()[1],
                      'terminal', 'IDENTIFIER', 'HelloWorld', '2', '9')
        self.lex_test(self.lex.getroot()[2],
                      'terminal', 'LPARENTHESIS', line='2', column='19')
        self.lex_test(self.lex.getroot()[3],
                      'terminal', 'IDENTIFIER', 'output', line='2', column='20')
        self.lex_test(self.lex.getroot()[4],
                      'terminal', 'RPARENTHESIS', line='2', column='26')
        self.lex_test(self.lex.getroot()[5],
                      'terminal', 'SEMIC', line='2', column='27')


    def test_begin(self):
        self.fail()

    def test_end(self):
        self.fail()

    def test_writeln(self):
        self.fail()


if __name__ == '__main__':
    unittest.main()
