
import unittest
from test_commons import PascalTestCase
from lxml import etree


class PascalImcodeTestCase(PascalTestCase):
    stage = "imcode"
    sem = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        super(PascalImcodeTestCase, cls).setUpClass()

        parser = etree.XMLParser(recover=True)
        cls.syn = etree.parse('imcode.xml', parser)
        cls.root = cls.syn.getroot()


class TestImcode(PascalImcodeTestCase):
    source = "imcode.pascal"

    def test_imcode(self):
        # we just abuse this to run things easily
        pass

if __name__ == '__main__':
    unittest.main()
