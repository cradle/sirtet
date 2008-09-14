package tetris;

class Shape
{

    public Shape()
    {
        grid = randomShape();
        x = grid.length;
        y = grid[0].length;
    }

    public Shape(boolean newGrid[][])
    {
        grid = newGrid;
        x = grid.length;
        y = grid[0].length;
    }

    public Shape rotateCW()
    {
        boolean newGrid[][] = new boolean[y][x];
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
                newGrid[y - 1 - j][i] = grid[i][j];

        }

        return new Shape(newGrid);
    }

    public Shape rotateCCW()
    {
        boolean newGrid[][] = new boolean[y][x];
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
                newGrid[j][x - 1 - i] = grid[i][j];

        }

        return new Shape(newGrid);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean getCoord(int xCoord, int yCoord)
    {
        return grid[xCoord][yCoord];
    }

    private boolean[][] randomShape()
    {
        boolean tempGrid[][] = new boolean[0][0];
        switch((int)(Math.random() * (double)7))
        {
        case 0: // '\0'
            tempGrid = new boolean[2][3];
            tempGrid[0][0] = true;
            tempGrid[1][0] = false;
            tempGrid[0][1] = true;
            tempGrid[1][1] = true;
            tempGrid[0][2] = false;
            tempGrid[1][2] = true;
            break;

        case 1: // '\001'
            tempGrid = new boolean[2][2];
            tempGrid[0][0] = true;
            tempGrid[1][0] = true;
            tempGrid[0][1] = true;
            tempGrid[1][1] = true;
            break;

        case 2: // '\002'
            tempGrid = new boolean[2][3];
            tempGrid[0][0] = false;
            tempGrid[1][0] = true;
            tempGrid[0][1] = true;
            tempGrid[1][1] = true;
            tempGrid[0][2] = true;
            tempGrid[1][2] = false;
            break;

        case 3: // '\003'
            tempGrid = new boolean[2][3];
            tempGrid[0][0] = true;
            tempGrid[1][0] = true;
            tempGrid[0][1] = true;
            tempGrid[1][1] = false;
            tempGrid[0][2] = true;
            tempGrid[1][2] = false;
            break;

        case 4: // '\004'
            tempGrid = new boolean[2][3];
            tempGrid[0][0] = true;
            tempGrid[1][0] = true;
            tempGrid[0][1] = false;
            tempGrid[1][1] = true;
            tempGrid[0][2] = false;
            tempGrid[1][2] = true;
            break;

        case 5: // '\005'
            tempGrid = new boolean[2][3];
            tempGrid[0][0] = true;
            tempGrid[1][0] = false;
            tempGrid[0][1] = true;
            tempGrid[1][1] = true;
            tempGrid[0][2] = true;
            tempGrid[1][2] = false;
            break;

        case 6: // '\006'
            tempGrid = new boolean[1][4];
            tempGrid[0][0] = true;
            tempGrid[0][1] = true;
            tempGrid[0][2] = true;
            tempGrid[0][3] = true;
            break;
        }
        return tempGrid;
    }

    private int x;
    private int y;
    private boolean grid[][];
}
