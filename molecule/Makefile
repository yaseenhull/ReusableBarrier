
JAVAC=/usr/bin/javac
.SUFFIXES: .java .class


SRCDIR=src
BINDIR=bin
DOCDIR=doc
JVM=java

SOURCES=$(shell find $(SRCDIR) -name '*.java' -type f)
CLASSES=$(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class) 

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(SRCDIR) $<


compile: $(CLASSES)


clean:
	rm $(BINDIR)/molecule/*.class

temp:
	echo $(CLASSES)

run:	
	$(JVM) -cp $(BINDIR)/ molecule.RunSimulation 12 3

docs:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java
	

	




