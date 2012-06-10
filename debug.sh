
export PASCALXSL=pascal/xsl

java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 abstree > /dev/null
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 semanal > /dev/null
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 frames > /dev/null
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 imcode > /dev/null
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 lincode
