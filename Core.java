package tetris;

import java.io.PrintStream;

class Core
{

    public Core(int tempX, int tempY)
    {
        xSize = tempX;
        ySize = tempY;
        grid = new Grid(xSize, ySize);
        shape = new Shape();
        x = startX();
        y = startY();
    }

    public Core()
    {
        this(10, 22);
    }

    public void moveLeft()
    {
        if(x > 0 && !grid.overlaps(shape, x - 1, y))
            x--;
    }

    public void moveRight()
    {
        if(x + shape.getX() < grid.getX() && !grid.overlaps(shape, x + 1, y))
            x++;
    }

    public boolean moveDown()
    {
        boolean stuck = false;
        if(y + shape.getY() < grid.getY() && !grid.overlaps(shape, x, y + 1))
            y++;
        else
            stuck = true;
        return stuck;
    }

    public void rotateCW()
    {
        Shape temp = shape.rotateCW();
        if(temp.getX() + x <= grid.getX() && temp.getY() + y <= grid.getY() && !grid.overlaps(temp, x, y))
            shape = temp;
    }

    public void rotateCCW()
    {
        Shape temp = shape.rotateCCW();
        if(temp.getX() + x <= grid.getX() && temp.getY() + y <= grid.getY() && !grid.overlaps(temp, x, y))
            shape = temp;
    }

    public void drop()
    {
        for(; y + shape.getY() < grid.getY() && !grid.overlaps(shape, x, y + 1); y++);
    }

    public void display()
    {
        String display = "";
        for(int i = 0; i < grid.getY(); i++)
        {
            display = String.valueOf(String.valueOf(display)).concat("*\r\n*");
            for(int j = 0; j < grid.getX(); j++)
                if(getCoord(j, i))
                    display = String.valueOf(String.valueOf(display)).concat("0");
                else
                    display = String.valueOf(String.valueOf(display)).concat(" ");

        }

        System.out.println(String.valueOf(String.valueOf(display)).concat("*\r\n*****************"));
    }

    public int nextTurn()
    {
        int numLines = 0;
        if(y + shape.getY() >= grid.getY() || grid.overlaps(shape, x, y + 1))
        {
            boolean gameOver = grid.cement(shape, x, y);
            if(!gameOver)
            {
                numLines = grid.check();
                shape = new Shape();
                x = startX();
                y = startY();
            } else
            {
                numLines = -1;
            }
        }
        return numLines;
    }

    public boolean getCoord(int xCoord, int yCoord)
    {
        boolean status = grid.getCoord(xCoord, yCoord);
        if(xCoord >= x && xCoord < x + shape.getX() && yCoord >= y && yCoord < y + shape.getY())
            status = status || shape.getCoord(xCoord - x, yCoord - y);
        return status;
    }

    private int startX()
    {
        return xSize / 2;
    }

    private int startY()
    {
        return 5 - shape.getY();
    }

    private int x;
    private int y;
    private Grid grid;
    private Shape shape;
    private int xSize;
    private int ySize;
}
