### Run
```
cd flappybird
cd target/classes
java org.game.Main
```

### GraaLVM 

*x64 C/C++ Redistributables Microsoft Visual Studio*

```
cd flappybird
mvn clean native:compile
```

```
native -jar flappybird-1.0-SNAPSHOT.jar
```

*Use JComponent instead of JLabel, use Streams instead of IO for native compilation.*
