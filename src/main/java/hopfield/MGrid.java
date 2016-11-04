package hopfield;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;

public class MGrid extends JPanel implements MouseListener {

  private int[][] cells = new int[10][8];
  private boolean editable = false;
  private int[] defPattern = null;
  private Color dotColor = Color.GRAY;

  public MGrid()
  {
    init();
  }

  public MGrid(int[] pattern)
  {
    init();
    setPattern(pattern);
    defPattern = pattern;
  }

  private void init()
  {
    setBackground(Color.WHITE);
    addMouseListener(this);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        cells[i][j] = -1;
      }
    }
  }

  public void Default()
  {
    if (defPattern != null) {
      this.setPattern(defPattern);
    }
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int w = this.getWidth();
    int h = this.getHeight();

    int rw = w / 8;
    int rh = h / 10;

    g.setColor(Color.DARK_GRAY);

    for (int i = 1; i < 10; i++) {
      g.drawLine(0, rh * i, w - 1, rh * i);
    }

    for (int i = 1; i < 8; i++) {
      g.drawLine(rw * i, 0, rw * i, h - 1);
    }

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        g.setColor((cells[i][j] == 1) ? dotColor : Color.WHITE);
        g.fillRect(j * rw + 1, i * rh + 1, rw - 1, rh - 1);
      }
    }
  }

  public void clearPattern()
  {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        cells[i][j] = -1;
      }
    }

    this.repaint();
  }

  public int[] getPattern()
  {
    int[] pattern = new int[80];

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        pattern[i * 8 + j] = cells[i][j];
      }
    }

    return pattern;
  }

  public void setPattern(int[] pattern)
  {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        cells[i][j] = pattern[i * 8 + j];
      }
    }

    this.repaint();
  }

  public void setEditable(boolean editable)
  {
    this.editable = editable;
  }

  public Color getDotColor()
  {
    return dotColor;
  }

  public void setDotColor(Color dotColor)
  {
    this.dotColor = dotColor;
    this.repaint();
  }

  public void mouseClicked(MouseEvent e)
  {
    if (!editable) {
      return;
    }

    int w = this.getWidth();
    int h = this.getHeight();

    int rw = w / 8;
    int rh = h / 10;

    int cx = e.getX() / rh;
    int cy = e.getY() / rw;

    if (cells[cy][cx] == -1) {
      cells[cy][cx] = 1;
    } else {
      cells[cy][cx] = -1;
    }

    this.repaint();
  }

  public void mousePressed(MouseEvent e)
  {
  }

  public void mouseReleased(MouseEvent e)
  {
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }

}
