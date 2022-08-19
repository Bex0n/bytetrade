java -cp "target/GieldaPO-1.0-SNAPSHOT.jar:$(mvn -q dependency:build-classpath -Dmdep.outputFile=/dev/fd/1)" Main "$@"
