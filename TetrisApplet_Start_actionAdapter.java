package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TetrisApplet_Start_actionAdapter
    implements ActionListener
{

    TetrisApplet_Start_actionAdapter(TetrisApplet adaptee)
    {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e)
    {
        adaptee.Start_actionPerformed(e);
    }

    TetrisApplet adaptee;
}
