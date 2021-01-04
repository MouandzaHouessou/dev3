package main.java;
/**
 * Implementation sodoku.
 * @author HOUESSOU
 */
public class GrilleImpl implements Grille {

  /**
   * grille de Sodoku.
   */
  private char[][] grille;

  /**
   * Constructeur public qui prend.
   * @param dimension  de la grille
   */
  public GrilleImpl(final int dimension) {
    setGrille(dimension);
  }

  /**
   * creer une grille avec une dimension.
   * @param dimension  de la grille
   */
  public final void setGrille(final int dimension) {
    this.grille = new char[dimension][dimension];
    for (int i = 0; i < this.grille.length; i++) {
      for (int j = 0; j < this.grille.length; j++) {
        this.grille[i][j] = EMPTY;
      }
    }
  }

  @Override
  public final int getDimension() {
    return grille.length;
  }

  @Override
  public final void setValue(final int x, final int y, final char value)
  throws IllegalArgumentException {
    if (possible(x, y, value)) {
      for (int i = 0;  i < getDimension(); i++) {
        if (this.grille[x][i] == value) {
          throw new IllegalArgumentException("Existe dans la ligne");
        }
      }
      for (int i = 0; i < getDimension(); i++) {
        if (this.grille[i][y] == value) {
          throw new IllegalArgumentException("Existe dans la colonne");
        }
      }
      for (int i = calculRegion(x, y)[0][0];
        i < calculRegion(x, y)[0][1]; i++) {
        for (int j = calculRegion(x, y)[1][0];
          j < calculRegion(x, y)[1][1]; j++) {
          if (this.grille[i][j] == value) {
            throw new IllegalArgumentException("Existe dans la region");
          }
        }
      }
      this.grille[x][y] = value;
    }
  }

  @Override
  public final char getValue(final int x, final int y)
  throws IllegalArgumentException {
    if (x > getDimension() || y > getDimension()) {
      throw new IllegalArgumentException(" Grille est hors bornes");
    } else {
    return this.grille[x][y];
    }
  }

  @Override
  public final boolean complete() {
    for (int i = 0; i < grille.length; i++) {
      for (int j = 0; j < grille.length; j++) {
        if (grille[i][j] == EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public final boolean possible(final int x, final int y, final char value)
  throws IllegalArgumentException {
    boolean autorisee = false;
    if (x > getDimension() || y > getDimension()) {
      throw new IllegalArgumentException(" Grille est hors bornes");
    } else {
      for (int i = 0; i < possible.length; i++) {
        if (possible[i] == value) {
          autorisee = true;
          break;
        }
      }
      if (autorisee) {
        return autorisee;
      } else {
        throw new IllegalArgumentException("Caractère non autorisé");
      }
    }
  }
  /**
   * Methode qui renvoi un interval de coodonnees x et y d'une regions.
   * Dont est contenu le piont(x,y)
   * @param x position dans la grille
   * @param y position dans la grille
   * @return un interval de coodonnees x et y d'une regions.
   */
  public final int[][] calculRegion(final int x, final int y) {
    //base qui represente une region à trois cellule
    final int tailleRegion = 3;
    int regionX = (int) Math.floor(x / tailleRegion) + 1;
    int regionY = ((int) Math.floor(y / tailleRegion)) * tailleRegion;
    //Intervalle des cordonnees de la region
    int[] intervalX = new int[2];
    int[] intervalY = new int[2];
          intervalX[0] =  (regionX - 1) * tailleRegion;
          intervalX[1] =  ((regionX - 1) * tailleRegion) + tailleRegion;
          intervalY[0] =  regionY;
          intervalY[1] =  regionY + tailleRegion;
    int[][] intValGlobal = new int[2][2];
            intValGlobal[0] = intervalX;
            intValGlobal[1] = intervalY;
    return intValGlobal;
  }
}
