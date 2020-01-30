JFLAGS = -g
JC = javac
TARGET = ./target
SOURCE = ./src
JVM= java 
FILE=
RM= rm -v

ENTRY = Main

default:
	$(JC) -d $(TARGET) $(JFLAGS) $(SOURCE)/$(ENTRY).java

run:
	$(JVM) -cp $(TARGET) $(ENTRY) $(ARGS)

clean:
	$(RM) $(TARGET)/*.class
