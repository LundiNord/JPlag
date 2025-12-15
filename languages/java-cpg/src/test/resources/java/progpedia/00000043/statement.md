The Shoemaker
=============

A shoemaker has **N** orders for pairs of shoes that he needs to manufacture. For a certain day, a shoemaker can only work in a single pair of shoes and after starting can only move to another pair after finishing the pair he had started to do.

For each pair of shoes, the shoemaker knows exactly the time in days that takes to do that work. He also knows which fine he has to pay for each day of delay before the beginning of each of the peers.

For example, imagine that the shoemaker has two pairs of shoes ordered: (1) takes 10 days and has a fine of 10 euros; (2) takes 14 days and also has a fine of 10 euros. If you choose to start with pair (1), you can start (2) after 10 days and soon pay a fine of 100 euros (10 days \* 10 euros). If you choose first to start with pair (2), then you can only start (1) after 14 days and soon pay a fine of 140 euros (10 days).

Can you help the shoemaker to decide in which order should he manufacture the shoes to minimize the total fine to pay?


Task
----

Given a set of **N** shoes' orders, each with their duration and fine per day, your task is to determine the order in which the shoemaker must deal with orders to pay a fine as small as possible.


Input
-----

The first line of the input contains the number **N** (1 ≤ N ≤ 1.000) indicating the number of orders to process.

Following **N** lines indicate the _i_\-th order. Each of these lines has two integers: **Di** and **Mi**, indicating respectively the duration and fine per day of the _i_ \-th pair of shoes. Note that orders are "numbered" from 1 to **N**: the first is 1, the second is 2, etc. **Di** and **Mi** are positive lower or equal to 1.000.


Output
------

The output must consist of the sequence of orders to be processed in order to minimize the fine to pay. An order is represented by its number in the input, so the output is a permutation of numbers from 1 to **N**. The sequence numbers should come separated by a space.

If there are multiple solutions with equal fine, print the solution that is lower lexicographically, that is, that starts with the lowest number, in case of a tie for the 2nd lower number and so on.


Example
-------

### Input

```txt
4
3 4
1 1000
2 2
5 5
```

### Output

```txt
2 1 3 4
```


Credits
--------

This problem is essentially a translation/adaptation of [Problem 10026](http://uva.onlinejudge.org/external/100/10026.html) of UVA Online Judge.
