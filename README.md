# FortranFormat

This is the [Fortran format package](http://www.j-paine.org/Formatter) by Jocelyn Ireson-Paine.

I took the original source code and
1. sorted the original source code into a Maven-compatible folder structure, 
2. used [javacc](https://github.com/javacc/javacc) via the [maven-javacc-plugin](https://github.com/mojohaus/javacc-maven-plugin) to auto-generate the parser code,
3. included the test output as resources and adjusted the test classes accordingly.

Planned actions include
1. to replace the `CJFormat` class by calls to `java.lang.String.format`
2. to split the `Formatter` class into the contained classes

# Maven coordinates

The final artifact will be available on Maven central:

```
<dependency>
	<groupId>de.labathome</groupId>
	<artifactId>FortranFormat</artifactId>
	<version>1.0.0</version>
</dependency>
```
