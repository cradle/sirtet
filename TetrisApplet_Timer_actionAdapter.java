package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TetrisApplet_Timer_actionAdapter
    implements ActionListener
{

    TetrisApplet_Timer_actionAdapter(TetrisApplet adaptee)
    {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e)
    {
        adaptee.Timer_actionPerformed(e);
    }

    TetrisApplet adaptee;
}
