package tetris;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class TetrisApplet extends Applet
    implements KeyListener
{

    public String getParameter(String key, String def)
    {
        return isStandalone ? System.getProperty(key, def) : getParameter(key) == null ? def : getParameter(key);
    }

    public TetrisApplet()
    {
        isStandalone = false;
        jPanel1 = new JPanel();
        Start = new JButton();
        borderLayout2 = new BorderLayout();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        xLines = 10;
        yLines = 18;
        mainArea = new JPanel[xLines][yLines];
        gridLayout1 = new GridLayout();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        gridLayout2 = new GridLayout();
        jPanel4 = new JPanel();
        jPanel5 = new JPanel();
        jLabel1 = new JLabel();
        borderLayout1 = new BorderLayout();
        End = new JButton();
        gridLayout3 = new GridLayout();
    }

    public void init()
    {
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void jbInit()
        throws Exception
    {
        border1 = BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140));
        titledBorder1 = new TitledBorder("");
        titledBorder2 = new TitledBorder("");
        titledBorder3 = new TitledBorder("");
        titledBorder4 = new TitledBorder("");
        jPanel2.setBackground(Color.lightGray);
        jPanel2.setBorder(titledBorder2);
        jPanel2.setLayout(gridLayout1);
        jPanel3.setLayout(gridLayout2);
        setLayout(borderLayout2);
        jPanel1.setBackground(Color.lightGray);
        jPanel1.setAlignmentX(0.5F);
        jPanel1.setBorder(border1);
        jPanel1.setDebugGraphicsOptions(0);
        jPanel1.setLayout(gridLayout3);
        Start.setActionCommand("Start");
        Start.setSelected(false);
        Start.setText("New");
        Start.addActionListener(new TetrisApplet_Start_actionAdapter(this));
        timer = new Timer(1000, new TetrisApplet_Timer_actionAdapter(this));
        gridLayout1.setColumns(10);
        gridLayout1.setRows(18);
        jLabel2.setAlignmentX(0.0F);
        jLabel2.setBorder(BorderFactory.createEtchedBorder());
        jLabel2.setDebugGraphicsOptions(0);
        jLabel2.setText("   Score = 0");
        jLabel3.setAlignmentX(0.0F);
        jLabel3.setAlignmentY(0.5F);
        jLabel3.setBorder(BorderFactory.createEtchedBorder());
        jLabel3.setMaximumSize(new Dimension(51, 19));
        jLabel3.setMinimumSize(new Dimension(51, 19));
        jLabel3.setOpaque(false);
        jLabel3.setPreferredSize(new Dimension(51, 19));
        jLabel3.setToolTipText("");
        jLabel3.setHorizontalTextPosition(11);
        jLabel3.setIconTextGap(4);
        jLabel3.setText("   Lines = 0");
        gridLayout2.setRows(2);
        jLabel1.setText("Sir Tet");
        jPanel5.setBackground(Color.lightGray);
        jPanel5.setForeground(Color.black);
        jPanel5.setBorder(BorderFactory.createEtchedBorder());
        jPanel5.setMaximumSize(new Dimension(32767, 32767));
        jPanel4.setBackground(Color.lightGray);
        jPanel4.setBorder(null);
        jPanel4.setLayout(borderLayout1);
        jPanel3.setBorder(null);
        End.setText("End");
        End.addActionListener(new TetrisApplet_End_actionAdapter(this));
        add(jPanel1, "South");
        jPanel1.add(Start, null);
        jPanel1.add(End, null);
        add(jPanel2, "Center");
        add(jPanel3, "North");
        jPanel3.add(jPanel5, null);
        jPanel5.add(jLabel1, null);
        jPanel3.add(jPanel4, null);
        jPanel4.add(jLabel2, "North");
        jPanel4.add(jLabel3, "South");
        core = new Core();
        isPaused = true;
        gameOver = true;
        addKeyListener(this);
        for(int i = 0; i < yLines; i++)
        {
            for(int j = 0; j < xLines; j++)
            {
                JPanel tempPanel = new JPanel();
                jPanel2.add(tempPanel);
                mainArea[j][i] = tempPanel;
                tempPanel.setBackground(Color.WHITE);
            }

        }

        clearToGray();
    }

    public void start()
    {
        isPaused = false;
    }

    public void stop()
    {
        isPaused = true;
    }

    public void destroy()
    {
    }

    public String getAppletInfo()
    {
        return "Applet Information";
    }

    public String[][] getParameterInfo()
    {
        return null;
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if(!gameOver && !isPaused)
        {
            if(e.getKeyCode() == 37)
                core.moveLeft();
            else
            if(e.getKeyCode() == 39)
                core.moveRight();
            else
            if(e.getKeyCode() == 40)
            {
                boolean stuck = core.moveDown();
                if(stuck)
                    next();
                timer.restart();
            } else
            if(e.getKeyCode() == 127)
                core.rotateCCW();
            else
            if(e.getKeyCode() == 38)
                core.rotateCW();
            else
            if(e.getKeyCode() == 32)
            {
                core.drop();
                next();
            }
            updateGrid();
        }
        if(e.getKeyCode() == 19)
            isPaused = !isPaused;
    }

    public static void main(String args[])
    {
        TetrisApplet applet = new TetrisApplet();
        applet.isStandalone = true;
        JFrame frame = new JFrame();
        frame.setTitle("Sir Tet");
        frame.getContentPane().add(applet, "Center");
        applet.init();
        applet.start();
        frame.setSize(130, 360);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }

    void Start_actionPerformed(ActionEvent e)
    {
        core = new Core(xLines, yLines + 4);
        timer.setDelay(1000);
        timer.start();
        isPaused = false;
        gameOver = false;
        numberOfLines = 0;
        score = 0;
        requestFocus();
        updateGrid();
    }

    private void next()
    {
        int numLines = core.nextTurn();
        if(numLines == -1)
        {
            timer.stop();
            isPaused = true;
            gameOver = true;
        } else
        if(numLines > 0)
        {
            numberOfLines += numLines;
            score += numLines * numLines * 100;
            int delay;
            if(numberOfLines > 200)
                delay = 100;
            else
                delay = 1000 - (numberOfLines / 10) * 50;
            timer.setDelay(delay);
        } else
        if(numLines == -2)
            score++;
    }

    void Timer_actionPerformed(ActionEvent e)
    {
        if(!gameOver && !isPaused)
        {
            next();
            core.moveDown();
            updateGrid();
        }
    }

    private void updateGrid()
    {
        requestFocus();
        jLabel2.setText("   Score = ".concat(String.valueOf(String.valueOf(score))));
        jLabel3.setText("   Lines = ".concat(String.valueOf(String.valueOf(numberOfLines))));
        for(int i = 0; i < xLines; i++)
        {
            for(int j = 0; j < yLines; j++)
            {
                if(gameOver)
                    continue;
                if(core.getCoord(i, j + 4))
                    mainArea[i][j].setBackground(Color.BLACK);
                else
                    mainArea[i][j].setBackground(Color.WHITE);
            }

        }

    }

    private void clearToGray()
    {
        for(int i = 0; i < xLines; i++)
        {
            for(int j = 0; j < yLines; j++)
                mainArea[i][j].setBackground(Color.LIGHT_GRAY);

        }

    }

    void End_actionPerformed(ActionEvent e)
    {
        isPaused = true;
        gameOver = true;
        timer.stop();
        clearToGray();
    }

    private boolean isStandalone;
    JPanel jPanel1;
    Border border1;
    JButton Start;
    BorderLayout borderLayout2;
    JPanel jPanel2;
    JPanel jPanel3;
    TitledBorder titledBorder1;
    TitledBorder titledBorder2;
    boolean isPaused;
    boolean gameOver;
    int numberOfLines;
    int score;
    int xLines;
    int yLines;
    Core core;
    Timer timer;
    JPanel mainArea[][];
    GridLayout gridLayout1;
    JLabel jLabel2;
    JLabel jLabel3;
    GridLayout gridLayout2;
    JPanel jPanel4;
    JPanel jPanel5;
    JLabel jLabel1;
    TitledBorder titledBorder3;
    TitledBorder titledBorder4;
    BorderLayout borderLayout1;
    JButton End;
    GridLayout gridLayout3;
}
