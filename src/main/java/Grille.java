package main.java;
/**
 * Interface d'une grille de sudoku.
 */
public interface Grille {
/**
 * Caractere de case vide.
 */
  char EMPTY = '@';
/**
 * @return largeur/hauteur de la grille.
 */
  int getDimension();

/**
 * Affecte une valeur dans la grille.
 *
 * @param positionX  position x dans la grille.
 * @param positionY  position y dans la grille
 * @param value   valeur a mettre dans la case
 * @throws IllegalArgumentException si x ou y sont hors bornes (0-8)
 * @throws IllegalArgumentException si la valeur est interdite aux vues des
 *        autres valeurs de la grille
 * @throws IllegalArgumentException si value n'est pas un caractere autorise
 *        ('1',...,'9')
 */
  void setValue(int positionX, int positionY, char value)
   throws IllegalArgumentException;

/**
 * Recupere une valeur de la grille.
 *
 * @param positionX position x dans la grille
 * @param positionY  position y dans la grille
 * @return valeur dans la case x,y
 * @throws IllegalArgumentException si x ou y sont hors bornes (0-8)
 */
  char getValue(int positionX, int positionY)
   throws IllegalArgumentException;

/**
 * Test si une grille est terminee.
 *
 * @return true si la grille est complete
 */
  boolean complete();

/**
 * Test si une valeur est possible dans la grille par rapport a ce qu'elle.
  contient deja
 *
 * @param positionX position x dans la grille
 * @param positionY position y dans la grille
 * @param value valeur a mettre dans la case
 * @throws IllegalArgumentException si x ou y sont hors bornes (0-8)
 * @throws IllegalArgumentException si value n'est pas un caractere autorise
 *        ('1',...,'9',..)
 * @return true si c'est possible
 */
 boolean possible(int positionX, int positionY, char value)
  throws IllegalArgumentException;
}
