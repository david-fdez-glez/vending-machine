Welcome to Vending Machine

These instructions assume that you already have installed Java 8
(JDK 1.8).

---- RUN IN AN IDE ----

* Open menu: File-> Import-> Maven-> Existing Maven Projects...


---- COMPILE AND RUN ON THE COMMAND LINE ----

cd <directory>

mvn clean install

java -jar target/vending-machine-1.0-SNAPSHOT.jar


            If you know how to compile programs on the command line, and if you have
            downloaded the examples, you can easily compile and run all the examples.
            Just change into one of the chapter directories inside "sources" and use
            the command
                              javac *.java

            to compile all the examples from that chapter.  As long as your compiler supports
            Java 7 or higher, there should be no errors.  (You might see some warnings, especially
            if you use a newer version of java, but warnings do not stop a program from being
            compiled or executed.)  You can then run individual programs using the java command.
            For example:

                              java HelloWorld
