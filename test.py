
#from unittest import *
import unittest
import os

class PascalLexerTestCase(unittest.TestCase):
    source = "" # test input file

    @classmethod
    def setUpClass(cls):
        if cls.source != None:
            os.system("java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main test/"+cls.source.replace(".pascal", ""))


class TestLol(PascalLexerTestCase):
    source = "lol.pascal"

    def test_first_boolean(self):
        self.fail("meow")


if __name__ == '__main__':
    unittest.main()
