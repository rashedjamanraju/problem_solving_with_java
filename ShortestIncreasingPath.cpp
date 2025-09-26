// This code implements an algorithm to find the shortest increasing path in a 2D grid.
// An increasing path is defined as a sequence of cells where the values are strictly increasing.
// The algorithm utilizes depth-first search (DFS) with memoization to explore all possible paths,
// ensuring that previously computed results are reused to optimize performance.
// 
// The main steps of the algorithm are as follows:
// 1. Initialize a memoization table to store the length of the longest increasing path starting from each cell.
// 2. Iterate through each cell in the grid. For each cell, invoke the DFS function to compute the length
//    of the longest increasing path starting from that cell. If the computed length is greater than the
//    current maximum length, update the maximum length.
// 3. The DFS function explores all four possible directions (up, down, left, right) from the current cell.
//    For each direction, it checks if moving to the next cell is valid (i.e., within bounds and has a
//    greater value). If valid, it recursively calls the DFS function to continue the path.
// 4. If the result for the current cell is already computed (i.e., stored in the memoization table),
//    return the stored value to avoid redundant computations.
// 5. Finally, return the maximum length of the increasing path found across all cells of the grid.

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int dfs(int x, int y, vector<vector<int>>& grid, vector<vector<int>>& memo) {
        // If the result has already been computed, return it
        if (memo[x][y] != -1) return memo[x][y];

        int maxLength = 1; // At least the cell itself
        int directions[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Directions to explore

        for (auto& dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            // Check if we can move to the new cell
            if (newX >= 0 && newX < grid.size() && newY >= 0 && newY < grid[0].size() && 
                grid[newX][newY] > grid[x][y]) {
                maxLength = max(maxLength, 1 + dfs(newX, newY, grid, memo));
            }
        }

        memo[x][y] = maxLength; // Store the computed result
        return maxLength;
    }

    int longestIncreasingPath(vector<vector<int>>& grid) {
        if (grid.empty() || grid[0].empty()) return 0;

        int maxPathLength = 0;
        vector<vector<int>> memo(grid.size(), vector<int>(grid[0].size(), -1)); // Initialize memoization table

        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid[0].size(); y++) {
                maxPathLength = max(maxPathLength, dfs(x, y, grid, memo));
            }
        }

        return maxPathLength; // Return the maximum length found
    }
};