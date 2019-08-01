#! /usr/bin/env bash

rm -rf target/
mkdir target/

export CLASSPATH=.:./target

javac -d target/ Solver.java
python solver.py $1