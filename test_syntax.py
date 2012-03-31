
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


class TestLol(PascalSyntaxTestCase):
    source = "lol.pascal"

    def test_bool(self):
        print self.root


if __name__ == '__main__':
    unittest.main()
