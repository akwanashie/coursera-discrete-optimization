#! /usr/bin/env bash

rm -rf target/
mkdir target/

javac -d target/ Solver.java

export CLASSPATH=./target
python solver.py $1