package 岛屿的周长463;

public class LandLong {


    public static void main(String[] args) {
        //debug试试
        Solution solution = new Solution();
        int[][] arr = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};

        System.out.println(solution.islandPerimeter(arr));
    }
}

class Solution {
    //自己的思路：遍历二维数组，在1的情况下，如果非边缘的情况下，周围没有1，每个格子先按4算，然后出现一次1则减去一个1
    //所以在考虑要不要再加一圈墙
    public int islandPerimeter(int[][] grid) {

        //还要考虑，只有1的情况
        if (grid.length == 1){
            if (grid[0].length == 1){
                if (grid[0][0] == 1){
                    return 4;
                }
                else {
                    return 0;
                }
            }
        }

        //这个题要分情况讨论，四个角如果为1，则必然首先有两个边，其他边界的话必然有一个边
        //其他情况则根据上下左右的值是否为1来判断是否应该减去1，

        //用来计算岛屿周长
        int sum = 0;

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){

                //这里千万不能直接去操作i，j
                int k = i;
                int l = j;

                int num = 4;
                //如果是0的情况，直接跳过
                if(grid[k][l] == 0){
                    continue;
                }

                //下面是判断值为1的情况
                //首先是四个角

                //左上角
                else if (k == 0 && l ==0){
                    if (grid[k][++l] == 1){
                        num--;

                    }
                    l--;
                    if (grid[++k][l] == 1){
                        num--;

                    }
                    k--;
                    sum += num;
                }

                //右上角
                else if (k == 0 && l == grid[k].length - 1){
                    if (grid[k][--l] == 1){
                        num--;

                    }
                    l++;
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    sum += num;
                }

                //左下角
                else if(k == grid.length -1 && l == 0){
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    if (grid[k][++l] == 1){
                        num--;

                    }
                    l--;
                    sum += num;
                }

                //右下角
                else if (k == grid.length && l == grid[k].length - 1){
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    if (grid[k][--l] == 1){
                        num--;

                    }
                    l++;
                    sum += num;
                }

                //然后是四种边界条件

                //最上边
                else if(k == 0){
                    if (grid[k][--l] == 1){
                        num--;

                    }
                    l++;
                    if (grid[k][++l] == 1){
                        num--;

                    }
                    l--;
                    if (grid[++k][l] == 1){
                        num--;

                    }
                    k--;
                    sum += num;
                }

                //最左边
                else if(l == 0){
                    if (grid[++k][l] == 1){
                        num--;

                    }
                    k--;
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    if (grid[k][++l] == 1){
                        num--;

                    }
                    l--;
                    sum += num;
                }

                //最右边
                else if(l == grid[k].length - 1){
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    if (grid[++k][l] == 1){
                        num--;
                        k--;
                    }
                    if (grid[k][--l] == 1){
                        num--;

                    }
                    l++;
                    sum += num;
                }

                //最下边
                else if(k == grid.length - 1){
                    if (grid[k][--l] == 1){
                        num--;

                    }
                    l++;
                    if (grid[k][++l] == 1){
                        num--;

                    }
                    l--;
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    sum += num;
                }

                else {
                    //最后就是其他内部的情况
                    if (grid[k][--l] == 1){
                        num--;

                    }
                    l++;
                    if (grid[k][++l] == 1){
                        num--;

                    }
                    l--;
                    if (grid[--k][l] == 1){
                        num--;

                    }
                    k++;
                    if (grid[++k][l] == 1){
                        num--;

                    }
                    k--;
                    sum += num;
                }


            }
        }

        return sum;
    }


    //没有考虑到单纯纵向或者横向的问题，看看题解，实在没有更好的解法

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    //第一种是迭代法的思想
    public int islandPerimeter2(int[][] grid){
       // 对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
        // 因此，我们可以遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 1）加入答案 ans 中即可。
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == 1){
                    int cnt = 0;
                    //这一步应该是要进行四边情况的判断
                    for (int k = 0; k < 4 ;k++){

                        //这个方法比较巧妙，没有变动i，j的位置，同时判断四周的情况
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0){
                            cnt += 1;
                        }

                    }
                    ans += cnt;
                }
            }
        }
        return ans;

    }

    //我们也可以将方法一改成深度优先搜索遍历的方式，此时遍历的方式可扩展至统计多个岛屿各自的周长。
    // 需要注意的是为了防止陆地格子在深度优先搜索中被重复遍历导致死循环，我们需要将遍历过的陆地格子标记为已经遍历过，
    // 下面的代码中我们设定值为 2 的格子为已经遍历过的陆地格子。


    //个人感觉第一种方法更实际一些

    //第二种方法是深度优先遍历
    public int islandPerimeter3(int[][] grid){
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                //只有值为1的情况下才是岛屿
                if (grid[i][j] == 1) {
                    ans += dfs(i, j, grid, n, m);
                }
            }
        }
        return ans;


    }


    public int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            //这一步和迭代法的思想是一样的，查看岛屿四周的情况
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }

}
