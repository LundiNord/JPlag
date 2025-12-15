Freckles
========

Little Ritchie loves to connect the freckles on his father's back to form figures. He considers that the father's back can be thought of as a plan with freckles in various locations (x, y).


Task
----

Your task is to say Ritchie what the way to connect all freckles to minimize the amount of ink used. The little Ritchie connects the freckles drawing straight lines between pairs of freckles, possibly lifting the pen between lines. When Ritchie ends, there should be a sequence of lines between a freckle and any other freckles.


Input
-----

The first line contains a single integer **N**, the number of freckles (_2 ≤ N ≤ 100_).

Following **N** lines, each with two integers separated by a space indicating the position (x, y) of a freckle (_0 ≤ x,y ≤ 1000_).


Output
------

A single line with a number indicating the amount of ink that Ritchie should spend to turn on all freckles. The number should come with two decimal places.


Example
-------

### Input

```txt
3
1.0 1.0
2.0 2.0
2.0 4.0
```

### Output

```txt
3.41
```


Credits
-------

This problem is essentially a translation/adaptation of [Problem 10034](http://uva.onlinejudge.org/external/100/10034.html) of UVA Online Judge.
