# Scrabble Challenge

Welcome to the Scrabble challenge!

## Learning Objectives
- Build a simple program using functions, loops, and conditional statements
- Use `git` & GitHub to commit work and open a Pull Request


Given a word, compute the scrabble score for that word.

##### Letter Values

You'll need these:

| Letter                        | Value  |
| ----                          |  ----  |
| A, E, I, O, U, L, N, R, S, T  |     1  |
| D, G                          |     2  |
| B, C, M, P                    |     3  |
| F, H, V, W, Y                 |     4  |
| K                             |     5  |
| J, X                          |     8  |
| Q, Z                          |     10 |

Example
"cabbage" should be scored as worth 14 points:

- 3 points for C
- 1 point for A, twice
- 3 points for B, twice
- 2 points for G
- 1 point for E

And to total:

```
3 + 2x1 + 2x3 + 2 + 1
= 3 + 2 + 6 + 3
= 14
```

## Acceptance Criteria

```java
Scrabble s = new Scrabble("");
s.score(); // should return 0

Scrabble s = new Scrabble(" \t\n");
s.score(); // should return 0

Scrabble s = new Scrabble("a");
s.score(); // should return 1

Scrabble s = new Scrabble("f");
s.score(); // should return 4

Scrabble s = new Scrabble("street");
s.score(); // should return 6

Scrabble s = new Scrabble("quirky");
s.score(); // should return 22

Scrabble s = new Scrabble("OXyPHEnBUTaZoNE");
s.score(); // should return 41
```

## Extended Acceptance Criteria

For some of the extended criteria, you may need to look up functions and techniques you have not used before. There are tests for most of these, but if you spot any obvious omissions you can add them in too.

### Double and Triple Letter
Your solution should support the ability to define specific letters as double and triple score. Letters that count as double are enclosed in a pair of curly brackets `{}` - for example, the letter `o` would be defined as a double letter in in the word dog like this: `d{o}g`. Triple letters are enclosed in a set of square brackets - for example: `d[o]g`. Your solution should detect these brackets and apply the correct score modifications. 

### Double or Triple Word
Similar to above, your solution should support the ability to define double and triple word scores. To indicate a double word score, the submitted word will be enclose in a pair of curly brackets (for example `{dog}`), and for triple word score a pair of square brackets (`[dog]`). Your solution should detect these brackets and apply the correct score modifications. 

