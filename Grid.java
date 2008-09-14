package tetris;

class Grid
{

    public Grid(int xSize, int ySize)
    {
        x = xSize;
        y = ySize;
        grid = new boolean[xSize][ySize];
        for(int i = 0; i < xSize; i++)
        {
            for(int j = 0; j < ySize; j++)
                grid[i][j] = false;

        }

    }

    public boolean overlaps(Shape shape, int xCoord, int yCoord)
    {
        boolean stuck = false;
        for(int i = xCoord; i < xCoord + shape.getX(); i++)
        {
            for(int j = yCoord; j < yCoord + shape.getY(); j++)
                stuck = stuck || shape.getCoord(i - xCoord, j - yCoord) && grid[i][j];

        }

        return stuck;
    }

    public boolean getCoord(int xCoord, int yCoord)
    {
        return grid[xCoord][yCoord];
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean cement(Shape shape, int xCoord, int yCoord)
    {
        boolean gameOver = false;
        if(yCoord < 4)
            gameOver = true;
        for(int i = 0; i < shape.getX(); i++)
        {
            for(int j = 0; j < shape.getY(); j++)
                grid[xCoord + i][yCoord + j] = grid[xCoord + i][yCoord + j] || shape.getCoord(i, j);

        }

        return gameOver;
    }

    public int check()
    {
        int numLines = 0;
        for(int i = y - 1; i >= 0; i--)
        {
            boolean fullLine = true;
            for(int j = 0; j < x; j++)
                fullLine = fullLine && grid[j][i];

            if(!fullLine)
                continue;
            numLines++;
            for(int k = i; k > 0; k--)
            {
                for(int l = 0; l < x; l++)
                    grid[l][k] = grid[l][k - 1];

            }

            for(int k = 0; k < x; k++)
                grid[k][0] = false;

            i++;
        }

        if(numLines == 0)
            numLines = -2;
        return numLines;
    }

    private int x;
    private int y;
    private boolean grid[][];
}
