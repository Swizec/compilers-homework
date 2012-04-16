
import unittest, os, subprocess

class PascalTestCase(unittest.TestCase):
    source = "" # test input file
    stage = "lexanal" # compiler stage to test

    @classmethod
    def setUpClass(cls):
        try:
            os.remove('*.xml')
        except OSError:
            pass

        if cls.source != None:
            try:
                cls.output = subprocess.check_output("export PASCALXSL=pascal/xsl && java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main test/"+cls.source.replace(".pascal", "")+" "+cls.stage,
                                                     shell=True)
            except subprocess.CalledProcessError:
                print "There was a subprocess error"
                print "OUTPUT", cls.output
