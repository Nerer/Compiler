set -e
cd "$(dirname "$0")"
mkdir -p bin
find ./src/Compiler -name *.java | javac -d bin -classpath "lib/antlr-4.6-complete.jar" @/dev/stdin
