# FortranFormat

This is the [Fortran format package](http://www.j-paine.org/Formatter) by Jocelyn Ireson-Paine.

The main class is `Formatter`.
To use the package, you create an instance of `Formatter` and associate it with a format.
You can then call the instance's `write` methods to output data against the format,
or the instance's `read` methods to input data. 

# Example 1

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo1.java)

The argument to the constructor `Formatter(...)` is a format string.
The constructor parses it and converts it into an internal representation.
The parse will fail if the string is invalid, so the constructor is declared as throwing `InvalidFormatException`.
You need to catch this.
The write method takes a `Vector` and a `PrintStream` and iterates through the format,
finding each format element (a specification such as `I3` or `F12.7`) in turn,
and writing the next element in the vector against it.
The write may fail, for example if a number is too big for a field, or if there are no more elements in the vector.
The `write` method reports such errors by throwing one of a variety of exceptions.
These are all subclasses of `OutputFormatException`, so you need to catch this.

The call to `println` is to output a newline after the `f.write`,
otherwise you won't see any output. You could, alternatively, put a slash in the format string:

```java
Formatter f = new Formatter( "I3, F12.7, E12.7/" );
```

# Example 2

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo2.java)

For writing single numbers, there is a simpler `write` method which takes a single number and a `PrintStream`. 

# Example 3

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo3.java)

This example demonstrates input.
We construct a new `Formatter` as before.
We then call its `read` method to read data from standard input into a `Vector`.
This time, we check for `InputFormatException` as well as `InvalidFormatException`.
The former would be thrown if, for example, a putative number contained illegal characters. 

# Example 4

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo4.java)

This example is similar to example 2, but for input.
There is a simpler `read` method that takes a `DataInputStream` and returns the single object it has read.
In the example, we read the object from a String via a `ByteArrayInputStream`.
However, that is only for purposes of demonstration, and reading from a `DataInputStream` connected to a file will work just as well (and is what one would normally do). 

# Example 5

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo5.java)

This example enables us to read into a `Hashtable`.
We pass an array of strings to read.
When reading the i'th item `i`<sub>i</sub> from input, read looks up the i'th key `k`<sub>i</sub>, and puts the item into the hashtable, indexed by `k`<sub>i</sub>. 

# Example 6

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo6.java)

This example uses a `FormatMap` to translate text on input.
If you create a `FormatMap` and pass it to your `Formatter`'s `setFormatMap` method,
then the input routines will use it to try translating each input field before they check it.

Specifically, they chop out a slice of the needed width and column position from the input line,
and then pass it to the `FormatMap`'s `getMapping` method.
If this returns `null`, they use the original slice.
But if it returns a string, they use that instead.
So your `FormatMap`'s `getMapping` method could, for example, check for `X`'s in the input, and replace them all by zeroes.

# Example 7

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo7.java)

This example demonstrates how to check for end of file.
If the format reading routines discover end of file immediately after starting the read,
they throw an ` EndOfFileWhenStartingReadException`.
You can check for this in a catch.

Note that if they discover an end of file later on, they throw a different exception, `LineMissingOnReadException`
(if the end of file occurs at the start of a line) or `DataMissingOnReadException` (if it occurs part of the way through).
The point is that I assume it's a genuine error if, once you have started to read data with a format, there isn't enough data to finish the read.
However, if there's no data at all, the `EndOfFileWhenStartingReadException` can just be used as a convenient way to decide whether to terminate the loop. 

# Example 8

- [Source code](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/java/examples/FormatDemo8.java)

This example demonstrates a different way to check for end of file.
Here, we just use the built-in method `available` to terminate the loop. 

# Error checking

There are three main classes of exception, and some important subclasses thereof:

An `InvalidFormatException` is thrown if the format string is syntactically invalid.
You need to catch this every time you construct a `Formatter` from a format string.

An `OutputFormatException` is thrown if an error is detected on output.
There are various subclasses of this, each corresponding to a particular kind of error,
such as a number that is too big to fit in the width specified for it,
or an output list that terminates before formatting is finished.

An `InputFormatException` is thrown if an error is detected on input.
Error types include invalid numbers and premature end of input.

The `EndOfFileWhenStartingReadException` is a subclass of `InputFormatException`.
It is thrown if an end of file is detected as soon as we try a read.
See [Example 8](#example-8).
This is probably the only subclass of `InputFormatException` that you might want to test for explicitly.

On input, error reports look like this:

```
InvalidNumberOnReadException: Invalid number while reading formatted data:
  Number = "xyz"
  Index  = 0
  Format = I3
  Line number = 1:
xyz
^
Lexical error at line 1, column 1.  Encountered: "x" (120), after : ""
```

The thing in quotes (Number) is the string which the formatter is attempting to convert.
Index is the position it would occupy in the input list.
We then see the format against it was being read, and the line number and line.
The final message is generated by the parser used to check the syntax of numbers.
In this, the line and column number are relative to the beginning of the string being converted ("xyz" in the above),
and not to the start of the input as a whole.
Note that some error messages can't show the current line because there isn't one:
some will show the last line successfully read by the formatter, in an attempt to provide some context.

Error messages on output are similar, but don't show the line being constructed.
They do show the object being output. 

## End of file and missing data

If an input error exception occurs, all the data read into the input list up to that point is preserved, so you can use it.
However, be careful in doing so.
The error might result from a corruption of the input file earlier on - perhaps a missing block or similar - which has shifted the position of later data.
So it would be very unwise to assume that the data you do succeed in reading is going to end up in its intended position.

The formatter does input by maintaining a line buffer.
When you do a read, the formatter reads each line as soon as it encounters a format element that needs it, and not before.
The `/` format element causes it to read a newline.
This means that if you use the format `5(5I5/)` to read five rows of five integers from a file,
it will actually try reading six lines - the final one being read by the final `/` .
If your file actually does contain only five lines, this will cause an end-of-file error.

# Format strings

In essentials, a format is a sequence of format elements, an element being something like `I3`, `F12.3`, `E12.3`, `X`, `/`, or a quoted string `'Title'`.

Some format elements can be repeated by putting an integer repetition factor before them: `2I3`, `6F12.3`, `2X`.
You can't do this with quoted strings or slashes.

Entire formats can also be repeated, if enclosed in brackets: `2(F12.3,2X,3I3)`.

Such groups can be nested: `2(F12.3,2(I5/),2X,3I3)`.

Those format elements that start with letters - `X`, `Iw`, `Fw.d`, `Ew.d` - must be separated by commas in the format.
This is desirable anyway, since it prevents errors made by accidentally juxtaposing the field width of one element and the repetition factor of the next.

Format elements that don't start with letters - slashes and strings - need not be separated by commas: `2I5,/'next line'3I5///,3(F14.5)`.
This means that a format can be viewed as a sequence of groups separated by commas.
Each group contains a format element (possibly repeated), and may have slashes or strings on either side without intervening commas.

But you can separate slashes and strings by commas if you want: `2I5,/,'next line',3I5/,/,/,3(F14.5)`.

Formally, the grammar is given [here](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/javacc/FormatParser.jj),
defined by [Sun's JavaCC parser-generator](https://javacc.org/). 

# How data is input

An `Aw` element accepts a field of width *w*.
This is returned as a string of the same width.
In Fortran, the reading routines would pad or truncate the field to fit the character variable it is being read into.
In our routines, this "variable" is just a location in a `Vector` and does not have a width, so the field is returned just as it is.

An `Iw` element accepts a field of width *w*. Numbers will be converted to Long.

An `Fw.d` element accepts a field of width *w*. Numbers will be converted to Double.

An `Ew.d` element behaves just like an `Fw.d` element.

The grammar is given [here](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/javacc/NumberParser.jj).

# How data is output

An `Aw` element accepts a String and outputs it in a field of width *w*.
Short strings are right-justified with spaces; an error is given if the string is too long.

An `Iw` element accepts an Integer or Long, and outputs it in a field of width *w*.
Positive or zero numbers are output with no sign and no leading zeroes.
Negative numbers have a sign. All integers are right-justified with spaces.

An `Fw.d` element accepts an Integer, Long, Float, or Double and outputs it in a field of width *w*.

An `Ew.d` element accepts an Integer, Long, Float, or Double and outputs it in a field of width *w*.

# Implementation

The structure is simple in essence, but complicated by the need to pass information around for extensive error-checking.

A `Formatter` contains an instance of class `Format`, which represents a complete format.
`Format` is defined to implement a sequence of (possibly repeated) `FormatElements`.
Some of these are `FormatIOElements`, meaning that they transfer data.
These are further subclassed as `FormatI`, `FormatF` and so on.
Non-IO elements include `FormatString` (for embedded string literals) and `FormatSlash` (for / elements).

All these format classes have read and write methods.
At the top level, these just deal with sequencing and repetition of elements.
Once we get down to class `FormatIOElement`, they start defining the IO conversions specific to each kind of element - an `Iw`, an `Fw.d`, etc.

To write elements, we convert them to strings, using methods defined in the class `CJFormat`.
This was taken from a public implementation off 'printf' written and placed on the Web by Gary Cornell and Cay S. Horstmann, for their "Core Java" book.
We check that the strings aren't too large to fit in the field, and throw an exception if they are.
I chose this one over the other versions of printf available because the Java FAQ states that it is the only implementation known to deal with all formats correctly.

To read elements, we use a buffered input stream which contains its own input buffer and buffer pointer.
This is class `InputStreamAndBuffer`. We extract an appropriate width slice from the buffer.
Next, we parse it using the grammar defined in [NumberParser.jj](https://github.com/jonathanschilling/FortranFormat/blob/master/src/main/javacc/NumberParser.jj).
This just enables us to ensure that the data being input is a syntactically valid integer or real.
If so, we use Java's built-in conversion facilities to convert to a number.
We then put this in the next free location in the input list.

To output data, the user passes it in a `Vector`.
We convert this into a `VectorAndPointer`, where the pointer keeps track of which item is currently being output.

To input data, the user passes either a `Vector`, or a `Vector` and a `Hashtable`.
In the first case, we read items sequentially into the vector, again converting it into a `VectorAndPointer` to keep track of the next free slot. 

# Modifications

I ([J. Schilling](https://github.com/jonathanschilling)) took the original source code and
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
