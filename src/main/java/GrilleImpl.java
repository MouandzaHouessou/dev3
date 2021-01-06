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
   * Constructeur vide.
   */
  public GrilleImpl() {
    
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
  /**
   * Accesseur permettant de retourner la grille.
   * @return char [][]
   */
  public final char[][] getGrille() {
    return this.grille;
  }

  @Override
  public final int getDimension() {
    return grille.length;
  }

  @Override
  public final void setValue(final int positionX, final int positionY,
  final char value)throws IllegalArgumentException {
    if (possible(positionX, positionY, value)) {
      for (int i = 0;  i < getDimension(); i++) {
        if (this.grille[positionX][i] == value) {
          throw new IllegalArgumentException("Existe dans la ligne");
        }
      }
      for (int i = 0; i < getDimension(); i++) {
        if (this.grille[i][positionY] == value) {
          throw new IllegalArgumentException("Existe dans la colonne");
        }
      }
      for (int i = calculRegion(positionX, positionY,
      (int) Math.sqrt(getDimension()))[0][0]; i < calculRegion(positionX,
      positionY, (int) Math.sqrt(getDimension()))[0][1]; i++) {
        for (int j = calculRegion(positionX, positionY,
        (int) Math.sqrt(getDimension()))[1][0]; j < calculRegion(positionX,
        positionY, (int) Math.sqrt(getDimension()))[1][1]; j++) {
          if (this.grille[i][j] == value) {
            throw new IllegalArgumentException("Existe dans la region");
          }
        }
      }
      this.grille[positionX][positionY] = value;
    }
  }

  @Override
  public final char getValue(final int positionX, final int positionY)
  throws IllegalArgumentException {
    if (positionX > getDimension() || positionY > getDimension()) {
      throw new IllegalArgumentException(" Grille est hors bornes");
    } else {
    return this.grille[positionX][positionY];
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
  public final boolean possible(final int positionX,
  final int positionY, final char value)
  throws IllegalArgumentException {
    boolean autorisee = false;
    if (positionX > getDimension() || positionY > getDimension()) {
      throw new IllegalArgumentException(" Grille est hors bornes");
    } else {
      for (int i = 0; i < POSSIBLE.length; i++) {
        if (POSSIBLE[i] == value) {
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
   * @param positionX position dans la grille
   * @param positionY position dans la grille
   * @param tailleRegion represente la taille d'une region
   * @return un interval de coodonnees
   * positionX et positionY d'une regions.
   */
  public final int[][] calculRegion(final int positionX, final int positionY,
  final int tailleRegion) {
    //base qui represente une region à tailleRegion(un en) cellule
    int regionX = (int) Math.floor(positionX / tailleRegion) + 1;
    int regionY = ((int) Math.floor(positionY / tailleRegion)) * tailleRegion;
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
