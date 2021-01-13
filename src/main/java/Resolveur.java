package main.java;
/**
 * Interface d'un solveur de sudoku.
 */
public interface Resolveur {

/**
 * Une methode qui resout une grille de sodoku.
 *
 * @param possible l'ensemble de caractère possible.
 * @param grille la grille a solver
 * @return true si la grille est complète
 */
  boolean resoudGrille(final char[] possible, final GrilleImpl grille);

/**
 * affiche la grille une fois resolu.
 *
 * @param grille a affiche
 */
  void afficheGrille(final GrilleImpl grille);

}
