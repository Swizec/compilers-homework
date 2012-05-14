
import unittest
from test_commons import PascalTestCase
from lxml import etree


class PascalSemTestCase(PascalTestCase):
    stage = "semanal"
    sem = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        super(PascalSemTestCase, cls).setUpClass()

        parser = etree.XMLParser(recover=True)
        cls.syn = etree.parse('semanal.xml', parser)
        cls.root = cls.syn.getroot()


class TestAST(PascalSemTestCase):
    source = "frames.pascal"

    def test_syntax(self):
        # we just abuse this to run things easily
        pass

if __name__ == '__main__':
    unittest.main()
