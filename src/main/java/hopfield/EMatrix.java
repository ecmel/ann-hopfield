package hopfield;

public class EMatrix {

  private final double[][] matrix;
  private final int rows;
  private final int cols;

  public EMatrix(int r, int c)
  {
    matrix = new double[r][c];
    rows = r;
    cols = c;
  }

  public EMatrix(double[][] m)
  {
    matrix = m;
    rows = m.length;
    cols = m[0].length;
  }

  public double[][] get()
  {
    return matrix;
  }

  public double[] getRow(int row)
  {
    double[] ret = new double[cols];

    for (int i = 0; i < cols; i++) {
      ret[i] = matrix[row][i];
    }

    return ret;
  }

  public void setRowInt(int n, int[] m)
  {
    for (int i = 0; i < cols; i++) {
      matrix[n][i] = m[i];
    }
  }

  public double[] getCol(int col)
  {
    double[] ret = new double[rows];

    for (int i = 0; i < rows; i++) {
      ret[i] = matrix[i][col];
    }

    return ret;
  }

  public int[] getColInt(int col)
  {
    int[] ret = new int[rows];

    for (int i = 0; i < rows; i++) {
      ret[i] = (int) matrix[i][col];
    }

    return ret;
  }

  public void setCol(int n, double[] m)
  {
    for (int i = 0; i < rows; i++) {
      matrix[i][n] = m[i];
    }
  }

  public void setColInt(int n, int[] m)
  {
    for (int i = 0; i < rows; i++) {
      matrix[i][n] = m[i];
    }
  }

  public EMatrix plus(EMatrix m)
  {
    double[][] g = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        g[i][j] = matrix[i][j] + m.matrix[i][j];
      }
    }

    return new EMatrix(g);
  }

  public EMatrix minus(EMatrix m)
  {
    double[][] g = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        g[i][j] = matrix[i][j] - m.matrix[i][j];
      }
    }

    return new EMatrix(g);
  }

  public EMatrix multiply(EMatrix m)
  {
    int r = m.matrix.length;
    int c = m.matrix[0].length;

    double[][] g = new double[rows][c];

    for (int i = 0; i < rows; i++) {
      for (int k = 0; k < c; k++) {
        double t = 0;
        for (int j = 0; j < cols; j++) {
          t += matrix[i][j] * m.matrix[j][k];
        }
        g[i][k] = t;
      }
    }
    return new EMatrix(g);
  }

  public EMatrix multiplySimple(EMatrix z)
  {
    double e[][] = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        e[i][j] = matrix[i][j] * z.matrix[i][j];
      }
    }

    return new EMatrix(e);
  }

  public EMatrix transpose()
  {
    EMatrix m = new EMatrix(cols, rows);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        m.matrix[j][i] = matrix[i][j];
      }
    }

    return m;
  }

  public EMatrix hyperTan()
  {
    EMatrix m = new EMatrix(rows, cols);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        m.matrix[i][j] = 1D / (1D + Math.exp(-1D * matrix[i][j]));
      }
    }

    return m;
  }

  public EMatrix hyperTanDerivative()
  {
    EMatrix m = new EMatrix(rows, cols);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        double y = 1D / (1D + Math.exp(-1D * matrix[i][j]));
        m.matrix[i][j] = y - Math.pow(y, 2);
      }
    }
    return m;
  }

  public void diagonals(int s)
  {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (i == s) {
          matrix[s][j] = 1.0D;
        } else {
          matrix[i][j] = 0.0D;
        }
      }
    }
  }

  public void randomize()
  {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = 0.3D * Math.random();
      }
    }
  }

}

/*
 // Tanh squashing function
  // Better transition for between -1 and 1
 
  float tanh (float x) {
 
    double xd = (double) x;
    double a = 1.716;          // (suggested constants from Haykin, p. 160)
    double b = .6666666;
 
    x = (float) ( (2 * a) / (1 + Math.exp(-b * xd)) - a );
    return x;
 
  }
 
 
  // Derivative of Tanh function
 
  float derivTanh (float x) {
 
    x = 1 - (tanh(x) * tanh(x));
    return x;
 
  }
 
 
  // Sigmoid squashing function
  // for a smooth transition between 0 and 1
 
  float sigmoid (float x) {
 
    if (!boolean_func)
      x = (float) (1 / (1 + Math.exp ((double) (-x)) ));
    else
      x = x - 1;   // is this correct?
    return x;
  }
 
 
  // Derivative of the sigmoid function
 
  float derivSigmoid (float x) {
    double xd = (double) x;
 
    x = (float) ( sigmoid(x) * (1 - sigmoid(x)) );   // (Haykin p. 149)
    //    x = (float) ((1 + 2 * Math.exp(-xd)) / (1 + 2 * Math.exp(-xd) + Math.exp(-2 * xd)));
    return x;
  }
 
 
    public EMatrix hyperTan() {
        EMatrix m = new EMatrix(rows,cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                m.matrix[i][j] = 1D / (1D + Math.exp(-1D * matrix[i][j]));
 
        return m;
    }
 
 
   public EMatrix hyperTanDerivative() {
        EMatrix m = new EMatrix(rows,cols);
 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double y = 1D / (1D + Math.exp(-1D * matrix[i][j]));
                m.matrix[i][j] = y - Math.pow(y, 2);
            }
        }
        return m;
    }
 
 
     public EMatrix multiplyConst(double c) {
        double g[][] = new double[rows][cols];
 
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                g[i][j] = c * matrix[i][j];
 
        return new EMatrix(g);
    }
 
     public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
 
 
    public void printRC(String s) {
        int r = matrix.length;
        int c = matrix[0].length;
        System.out.println(s + ":" + r + "," + c);
    }
 
    public void Add() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = matrix[i][j] + 1.0D;
    }
 
 
    public void Diagonal() {
        for (int i = 0; i < rows; i++)
            matrix[i][i] = 0;
    }
 
    public EMatrix Hypert() {
 
        EMatrix m = new EMatrix(rows,cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                m.matrix[i][j] = 2 / (1 + Math.exp( -2 * matrix[i][j])) - 1;
 
        return m;
 
    }
 
    public EMatrix Hypertd() {
        EMatrix m = new EMatrix(rows,cols);
        double y = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
            y = 2 / (1 + Math.exp( -2 * matrix[i][j])) - 1;
            m.matrix[i][j] = 1-y*y;
            }
        return m;
    }
 
    public void HPatternize() {
        for (int i=0; i < rows; i++)
            for (int j=0; j < cols; j++)
                if (Hypert(matrix [i][j]) > 0)
                    matrix [i][j] = 1;
                else if (Hypert(matrix [i][j]) < 0)
                    matrix [i][j] = -1;
    }
 
    public EMatrix Zeynep(EMatrix z) {
        double e [][] = new double[rows][cols];
 
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                e[i][j] = matrix[i][j] * z.matrix[i][j];
 
        return new EMatrix(e);
    }
 
    public EMatrix Ecmel(EMatrix z) {
        double e [][] = new double[rows][cols];
 
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                e[i][j] = matrix[i][j] + z.matrix[i][0];
 
        return new EMatrix(e);
    }
 
     public boolean isEqual(EMatrix m) {
        boolean eq = true;
 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (m.matrix[i][j] != matrix[i][j]) {
                    eq = false;
                    break;
                }
            }
        }
        return eq;
    }
 
 */
