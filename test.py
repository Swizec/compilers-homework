
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

            cls.lex = etree.parse('lexanal.xml')

class TestLol(PascalLexerTestCase):
    source = "lol.pascal"

    def test_first_boolean(self):
        self.assertEquals(self.lex.getroot().tag, 'lexanal')

        lol = self.lex.getroot()[0]

        self.assertEquals(lol.tag, 'terminal')
        self.assertEquals(lol.get('token'), 'BOOL_CONST')
        self.assertEquals(lol.get('lexeme'), 'true')
        self.assertEquals(lol.get('column'), '1')
        self.assertEquals(lol.get('line'), '1')

class TestHelloWorld(PascalLexerTestCase):
    source = "helloworld.pascal"

    def test_program(self):
        self.fail()

    def test_begin(self):
        self.fail()

    def test_end(self):
        self.fail()

    def test_writeln(self):
        self.fail()


if __name__ == '__main__':
    unittest.main()
