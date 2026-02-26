Closest Sums
============

Task
----

Given a **S** of **N** integer numbers, and a sequence of **P** questions (_queries_), each indicating a number **Pi**, your task is to find out which is the sum of two different numbers of **S** that is closer to the number **Pi** of each question.


Input
-----

The first line of the input contains a single number **N** (1 ≤ N ≤ 1.000), indicating the size of the set of numbers **S**. The second line has the numbers of the set, separated by a space.

The third line has a number **P** (1 ≤ P ≤ 1.000), indicating the number of questions, followed in the fourth line by the numbers **Pi** of each question, separated by a space.

All numbers of the input (either from the set or from the questions) are integers between 1 and 1,000,000 (inclusive).


Output
------

The output must consist of **P** lines, one for each question, in the same order as they came in the input. Each line should indicate the sum closer to the respective question. If there are several sums at the same minimum distance, they must all come, by ascending order and separated by a space.


Example
-------

### Input

```txt
6
12 3 17 5 34 33
4
1 51 41 21
```

### Output

```txt
8
51
39
20 22
```


In this case, we have S = {3,5,12,17,33,34} and 4 questions.
For question 1, the answer is 8 (3+5), the sum of a pair closer to 1.
For question 51, the answer is 51 (17+34).
For question 41, the answer is 39 (5+34).
Finally, for question 21, the answer is 20 (3+17) and 22 (5+17), both at the same distance of 21.


Credits
-------

This problem is essentially a translation/adaptation of [Problem 10487](http://uva.onlinejudge.org/external/104/10487.html) of the UVA online Judge.
