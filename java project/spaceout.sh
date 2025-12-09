#!/bin/bash

javac SpaceOut.java
java --enable-native-access=ALL-UNNAMED -Djava.library.path=. SpaceOut
