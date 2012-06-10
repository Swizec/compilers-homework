
export PASCALXSL=pascal/xsl

java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 abstree
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 semanal
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 imcode
java -cp pascal/bin/java_cup/runtime:lib/java-cup-11a.jar:pascal/bin/compiler/.. compiler.Main $1 lincode
