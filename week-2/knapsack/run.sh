#! /usr/bin/env bash

rm -rf target/
mkdir target/

export CLASSPATH=.:./target

if [ "$2" = "--debug" ]; then
  export DEBUG=true
fi

javac -d target/ Solver.java

python solver.py $1