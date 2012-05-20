
import unittest
from test_commons import PascalTestCase
from lxml import etree


class PascalFramesTestCase(PascalTestCase):
    stage = "frames"
    sem = etree.ElementTree(etree.Element('xml'))

    @classmethod
    def setUpClass(cls):
        super(PascalFramesTestCase, cls).setUpClass()

        parser = etree.XMLParser(recover=True)
        cls.syn = etree.parse('frames.xml', parser)
        cls.root = cls.syn.getroot()


class TestFrames(PascalFramesTestCase):
    source = "frames.pascal"

    def test_frames(self):
        # we just abuse this to run things easily
        pass

if __name__ == '__main__':
    unittest.main()
