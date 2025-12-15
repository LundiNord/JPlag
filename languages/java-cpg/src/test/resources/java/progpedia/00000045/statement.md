Pyramids
========

Egypt's pyramids are tombs. The pharaohs who sent them to build and the architects who drew them, have not prepared them so that the 21st century tourists had fun climbing them. However, there are always some bolder visitors that surpass safety barriers and venture to climb the pyramids, to get to the top and stupidly wave the flag of their country.

Climbing the pyramids is difficult and dangerous. Each pyramid is formed by layers of stones. Let's admit that the lower layer has, on each face, N stones. The second layer, on top of this, will have N-1 stones, each resting on two lower stones. Thus, there will be N layers. To climb the pyramid, each of these "climbers", begins by climbing one of the base stones; then moves from one of the two stones of the second layer that rests on the stone he initially climbed; and so on, until it reaches the top. If the pyramid was in good condition, with all the stones in place, there would be 2N-1 different "ways" of climbing to the top, each way corresponding to a route up the pyramid. However, the pyramids are quite degraded and some stones are missing or so deteriorated that it is impossible to climb them to continue the climb. The question is: under these conditions, how many different ways are there to climb the pyramid to the top?

Task
----

Write a program that gives a pyramid with N layers, and a description of the missing or very deteriorated stones on one of the pyramid faces, calculate the number in different ways to climb the pyramid to the top, starting with any of the stones of the first layer, avoiding the stones that are missing or very deteriorated.


Input
-----

The first line has the number **N** that represents the number of stones in the first layer and also the number of levels of the pyramid (_1 ≤ N ≤ 1000_). The second line has the number **D** of missing or very deteriorated stones ( _0 ≤ D ≤ N \* (N + 1) / 2_). The **D** following lines contain two numbers, **C** and **P**, which describe each of these missing or very deteriorated stones: **C** represents the layer (_1 ≤ C ≤ N_), and **P** represents the position of the stone in this layer (_1 <= P <= N - (C - 1)_).


Output
------

A single line, in which there is a single number: the number of different ways to climb the pyramid. It is guaranteed that in test cases this number will be lower than 263.


Examples
--------

### Input 1

```txt
4
2
2 1
3 2
```

### Output 1

```txt
2
```

### Input 2

```txt
5
3
3 2
2 3
1 4
```

### Output 2

```txt
5
```


Credits
-------

Selection of the Portuguese Participants for the 2008 International Olympics of Informatics _(August 1, 2008)_
