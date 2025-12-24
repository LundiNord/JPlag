Inversions
==========

The **number of inversions** of an array indicates how far (or close) it is to be ordered. A completely ordered array (ascending) has a number of inversions equal to zero. If the array is ordered but in reverse (in a decreasing way) then the number of inversions is maximum.

In a more formal way, two elements v[i] and v[j] are reversed v[i]<>v[j] and i<j. For instance, the sequence (4,3,1,2) has 5 inversions: (4,3), (4,1), (4,2), (3.1), and (3.2).


Task
----

Given a sequence of numbers, calculate the number of inversions needed to sort them out.


Input
-----

The first line of the input contains the number **N** (1 ≤ N ≤ 100000) that corresponds to the amount of numbers to consider.

Following is a line containing the numbers to consider, separated by a single space. It is guaranteed that all numbers are positive integer lower than one million.


Output
------

The output must consist of a single line containing a number: the amount of inversions.


Example
-------

### Input

```txt
4
4 3 1 2
```

### Output

```txt
5
```
