Minimum Coverage
================


Task
----

Given a set of **N** line segments with coordinates **\[Li, Ri\]** and a number **M**, your task is to find out which is the lowest possible amount of segments covering the segment \[0,**M**\].


Input
-----

The first line of the input consists of a number **M** (1 ≤ M ≤ 1,000,000) indicating the size of the segment we want to cover, as explained above.

The second line comes a number **N**, indicating the amount of segments to be considered (1 ≤ N ≤ 1.000). Following are **N** lines, each with 2 integers indicating the initial and final points of each of the segments, i.e., **Li** and **Ri**. These points are not negative and lower or equal to 1,000,000.


Output
------

The output must consist of a line containing a single number: the minimum amount of segments required to cover the segment \[0, **M**\]. For the given test cases, it is guaranteed that there is always a way to cover the segment \[0, **M**\].


Example
-------

### Input

```txt
6
5
0 2
3 4
4 6
3 8
2 4
```

### Output

```txt
3
```

The solution is 3 because we could use the segments \[0.2\], \[2.4\] and \[3.8\], covering the entire zone \[0.6\], as requested.


Credits
-------

This problem is essentially a translation/adaptation of [Problem 10020](http://uva.onlinejudge.org/external/100/10020.html) of UVA Online Judge.
