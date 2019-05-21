#!/bin/bash

find -name "*.class" > sources
jar -cfe faustop.jar faustop.core.main.Wrapper @sources
echo "Write the faustop program."
echo -e "Press \e[1mCtrl+D\e[0m to save the file."
cat > ex_123.fau
java -jar faustop.jar ex_123.fau
rm -rf ex_123
