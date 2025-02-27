# Supernova

An explosion has occurred at some integer coordinate $(x,y)$ where $0 ≤ x,y ≤ 256$.

A series of dust particles (single points) were ejected from the source of the explosion at varying speeds and straight trajectories.

All you have is two photos; both capture all of the dust particles, and the second photo was taken after the first one. **Every dust particle has a different speed, and therefore the time between the photos is irrelevant.**

The catch is that each of the two photos are provided in the form of an **unordered set**. That is, $p_1[i]$ is not necessarily the same particle as $p_2[i]$.

Find the origin of the supernova.

## Input

Two lines.

1. An unordered set of coordinates in the form $x,y$ (no parentheses) separated by spaces.
2. An unordered set of coordinates representing the same dust particles, an arbitrary amount of time after the first was taken.

The second series of coordinates will be shuffled, and **the order of dust particles will not be the same**.

## Output

A single coordinate; space separated $x$ and $y$ of the explosion origin.

## Example

### Input

```aiignore
20,31 19,30 21,30 20,29
20,32 18,30 22,30 20,28
```

### Output

```aiignore
20 30
```