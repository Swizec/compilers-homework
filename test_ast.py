
import unittest
from test_commons import PascalTestCase
from lxml import etree


class PascalSyntaxTestCase(PascalTestCase):
    stage = "abstree"
    syn = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        super(PascalSyntaxTestCase, cls).setUpClass()

        parser = etree.XMLParser(recover=True)
        cls.syn = etree.parse('abstree.xml', parser)
        cls.root = cls.syn.getroot()


class TestAST(PascalSyntaxTestCase):
    source = "ast.pascal"

    def test_syntax(self):
        # we just abuse this to run things easily
        pass

if __name__ == '__main__':
    unittest.main()
