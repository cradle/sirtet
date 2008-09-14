package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TetrisApplet_End_actionAdapter
    implements ActionListener
{

    TetrisApplet_End_actionAdapter(TetrisApplet adaptee)
    {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e)
    {
        adaptee.End_actionPerformed(e);
    }

    TetrisApplet adaptee;
}
