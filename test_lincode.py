
import unittest
from test_commons import PascalTestCase
from lxml import etree


class PascalLincodeTestCase(PascalTestCase):
    stage = "lincode"
    sem = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        super(PascalLincodeTestCase, cls).setUpClass()

        parser = etree.XMLParser(recover=True)
        cls.syn = etree.parse('lincode.xml', parser)
        cls.root = cls.syn.getroot()


class TestLincode(PascalLincodeTestCase):
    source = "lincode.pascal"

    def test_lincode(self):
        # we just abuse this to run things easily
        pass

if __name__ == '__main__':
    unittest.main()

