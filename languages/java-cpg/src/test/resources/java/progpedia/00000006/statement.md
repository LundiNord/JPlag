Messy Cicadas
=============

Fed up to hear that they began the day singing, some cicadas decided to imitate their rivals. Not realizing the logic of travel in narrow lines, we found them flustered: having left a certain place in Indian line, they began to walk around, systematically passing through places where they previously had been, as if they were lost in a maze. Finally, they found the place where they intended to arrive. Anyway, we would like to know what they could have done if they had had a better sense of guidance and had not ''visited'' the same places repeatedly.


Task
----

Write a program that analyzes the sequence of places that they were ''visiting'', which includes the starting point and, in the end, the arrival point, and find a path that respects this sequence but removes all unnecessary turns. Only the last passage in each of these places can be considered useful. All others constitute deviations. In case of mistake, all the return that the cicadas have given, since they first visited a place until they returned, will be considered unnecessary. The place of arrival was not visited twice, but any of the remaining points, including the origin, may have been.


Input
-----

Only the sequence of places where the cicadas went through, one location for each line of data is given. The last line has the value 0. The number of distinct places does not exceed 30. Each location is identified by a positive integer lower than 10000.


Output
------

The sequence of places they would visit if they were not confused, each place in a seperate line.


Examples
--------

### Input 1

```sh
1600
15
2315
15
1315
0
```

### Output 1

```sh
1600
15
1315
```

### Input 2

```sh
1513
3171
178
1316
1600
1513
1774
178
3171
672
1315
0
```

### Output 2

```sh
1513
1774
178
3171
672
1315
```


Credits
-------

ToPAS'2006 - Ana Paula Tomás
