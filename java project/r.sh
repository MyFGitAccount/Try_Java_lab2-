#!/bin/bash

javac Ray.java
java --enable-native-access=ALL-UNNAMED -Djava.library.path=. Ray
