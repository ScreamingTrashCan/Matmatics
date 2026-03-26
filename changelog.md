# Change Log
*Whenever making a push to GitHub, please highlight the more specifics of what you did in this document making a new section.*

## Version 0.1c

### CLI

Cleaned up some spacing and coloring in the CLI to make it more consistent. Also added Newton's Method functionality to the CLI.

### Calculator

Made one very small change to Calculator just to make it a bit more accurate; Find At now returns -1 instead of 999 if it can't find something.

### Newton's Method

Changed the Calculate() function to no longer take in Original and Derivative, and now they are instead set through separate getter/setter functions.

## Version 0.1b

### Curve Approximator

Redid some things and math and operations in the Curve Approximator to make it accurate. Yay!

### CLI

Added Curve Approximator option to the CLI.

## Version 0.1a

### Curve Approximator

Completely overhauled the Curve Approximator script by rewriting it and hopefully it should be more accurate. In my single test of it, it was correct so...

Added Runner.DebugText() to the Curve Approximator to help with the debugging process if needed.

### Runner

Small changes to the Runner just to get rid of the testing functions and whatnot so it immediately starts with the CLI.