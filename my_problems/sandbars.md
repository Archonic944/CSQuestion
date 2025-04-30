# Sand Bars

You are given a two-dimensional grid representing an area of ocean. Some cells contain sand bars (represented by `1`) while others are just water (represented by `0`).

An "island" is formed by connecting adjacent sand bars horizontally or vertically (not diagonally). You need to count the total number of islands in the grid.

## Input

The first line contains two integers `n` and `m` representing the dimensions of the grid (rows and columns).
The next `n` lines contain `m` binary digits each (0 or 1), representing the grid of sand bars and water.

## Output

An integer representing the number of distinct islands in the grid.

## Example 1

### Input
```aiignore
4 5
11000
11000
00100
00011
```

### Output
```aiignore
3
```

### Explanation
The grid contains three islands:
1. The island in the top-left corner (2×2)
2. The single sand bar in the middle
3. The island in the bottom-right corner (2×1)

## Example 2

### Input
```aiignore
3 3
111
010
111
```

### Output
```aiignore
1
```

### Explanation
The grid contains only one island. Although it appears disconnected, all sand bars are part of the same island when considering horizontal and vertical connections.