
import unittest
from test_commons import PascalTestCase
from lxml import etree


class PascalSyntaxTestCase(PascalTestCase):
    stage = "synanal"
    syn = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        super(PascalSyntaxTestCase, cls).setUpClass()

        parser = etree.XMLParser(recover=True)
        cls.syn = etree.parse('synanal.xml', parser)
        cls.root = cls.syn.getroot()


class TestSyntax(PascalSyntaxTestCase):
    source = "syntax.pascal"

    def test_syntax(self):
        # we just abuse this to run things easily
        pass


if __name__ == '__main__':
    unittest.main()
