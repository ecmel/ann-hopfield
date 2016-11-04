package hopfield;

/**
 * <p>
 * Title: </p>
 *
 * <p>
 * Description: </p>
 *
 * <p>
 * Copyright: Copyright (c) 2005</p>
 *
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class UniqueRandom {

  public UniqueRandom()
  {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  java.util.Random rand = new java.util.Random();

  public int[] getNumbers(int count, int max)
  {

    int[] all = new int[max];
    for (int i = 0; i < max; i++) {
      all[i] = i;
    }

    for (int i = 0; i < max; i++) {
      int ra = i + rand.nextInt(max - i);
      int swap = all[ra];
      all[ra] = all[i];
      all[i] = swap;
    }

    int start = rand.nextInt(max - count + 1);
    int[] r = new int[count];

    for (int i = 0; i < count; i++) {
      r[i] = all[start + i];
    }

    return r;
  }

  private void jbInit() throws Exception
  {
  }

}
