#created to test regexes, cuz its easier than Java

import re

"""
String kw = "inte|double|xar|string|if|for|while"; // regex for keyword
String id = "([a-zA-z]|_)+\w*"; // regex for identifier
String mathOp = "[+-*/%]|(\*\*)";
String attrOp = "==|>="
String op = "";
String bool = "true|false";
String integer = "\d+";
String doub = "\d+\.\d+";
String str = "\".*\"";
String xar = "'.'";
String lit = bool + "|" + integer + "|" + doub
             + "|" + str + "|" + xar; // regex for literal

String exp = "(" + id + "|" + lit + "|" + op + "|[()]|" + ")+";
"""

p = "abc|gtr"
ss = ["abc", "gtr"]

for s in ss:
    assert re.match(p, s)
