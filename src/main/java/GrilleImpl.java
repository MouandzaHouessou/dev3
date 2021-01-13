package main.java;
/**
 * Implementation de la grille de sodoku.
 * @author HOUESSOU
 */
public class GrilleImpl implements Grille {

  /**
   * grille de Sodoku.
   */
  private char[][] grille;
  /**
 * Caractere possible a mettre dans la grille.
 * pour une grille 9x9 : 1..9
 * pour une grille 16x16: 0..9-a..f
 */
  private char[] valeurPossible = new char[]{'1', '2', '3', '4', '5',
  '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f'};

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
  /**
   * Accesseur permettant de retourner la grille.
   * @return char [][]
   */
  public final char[][] getGrille() {
    char[][] grilleCp = new char[getDimension()][getDimension()];
    System.arraycopy(this.grille, 0, grilleCp, 0, getDimension());
    return grilleCp;
  }
  /**
   * Accesseur permettant de retourner les valeurs possibles.
   * @return char[] possible
   */
  public final char[] getValeurPossible() {
    char[] valeurPossibleCp = new char[getDimension()];
    System.arraycopy(this.valeurPossible, 0, valeurPossibleCp,
    0, getDimension());
    return valeurPossibleCp;
  }

  @Override
  public final int getDimension() {
    return grille.length;
  }

  @Override
  public final void setValue(final int positionX, final int positionY,
  final char value)throws IllegalArgumentException {
    final int region = (int) Math.sqrt(this.getDimension());
    final int nbrDeCondition = 3;
    if (possible(positionX, positionY, value)) {
      if (value == EMPTY) {
        this.grille[positionX][positionY] = value;
      } else if (verification(positionX, positionY,
      value, region) == nbrDeCondition) {
        this.grille[positionX][positionY] = value;
      } else {
        throw new IllegalArgumentException("valeur interdite");
      }
    }
  }

  @Override
  public final char getValue(final int positionX, final int positionY)
  throws IllegalArgumentException {
    if (positionX > getDimension() || positionY > getDimension()) {
      throw new IllegalArgumentException(" Grille est hors bornes");
    } else {
    return this.getGrille()[positionX][positionY];
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
      for (int i = 0; i < getValeurPossible().length; i++) {
        if (getValeurPossible()[i] == value || EMPTY == value) {
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
   * La Méthode teste si une valeur existe dans une colonne.
   * @param positionX numero de la colonne
   * @param value valeur a jouter dans la grille
   * @return true si aucune valeur identique existe
   */
  public final boolean verifiColonne(final int positionX, final char value) {
    for (int i = 0;  i < getDimension(); i++) {
      if (this.grille[positionX][i] == value) {
        return false;
      }
    }
    return true;
  }
  /**
   * La Méthode teste si une valeur existe dans une ligne.
   * @param positionY numero de la ligne
   * @param value valeur a jouter dans la grille
   * @return true si aucune valeur identique existe
   */
  public final boolean verifiLigne(final int positionY, final char value) {
    for (int i = 0; i < getDimension(); i++) {
      if (this.getGrille()[i][positionY] == value) {
        return false;
      }
    }
    return true;
  }

  /**
   *  La Méthode teste si une valeur existe dans une region.
   * @param positionX entier position de la valeur
   * @param positionY entier position de la valeur
   * @param value valeur ajouter dans la grille
   * @param tailleRegion taille d'une region
   * @return true si aucune valeur identique existe
   */
  private boolean verifiRegion(final int positionX, final int positionY,
  final char value, final int tailleRegion) {
    int posX = positionX - (positionX % tailleRegion);
    int posY = positionY - (positionY % tailleRegion);

    for (int s = posX; s < posX + tailleRegion; s++) {
      for (int f = posY; f < posY + tailleRegion; f++) {
        if (this.getGrille()[s][f] == value) {
          return false;
        }
      }
    }
    return true;
  }
  /**
   *  La Méthode teste si une valeur existe dans une region.
   * @param positionX entier position de la valeur
   * @param positionY entier position de la valeur
   * @param value valeur ajouter dans la grille
   * @param tailleRegion taille d'une region
   * @return condition entier qui represente la somme des condition
   */
  private int verification(final int positionX, final int positionY,
  final char value, final int tailleRegion) {
    int condition = 0;
    if (verifiColonne(positionX, value)) {
      condition = condition + 1;
    }
    if (verifiLigne(positionY, value)) {
      condition = condition + 1;
    }
    if (verifiRegion(positionX, positionY, value, tailleRegion)) {
      condition = condition + 1;
    }
    return condition;
  }
}
