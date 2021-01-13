package main.java;
/**
 * Implementation du solveur de sodoku.
 * @author HOUESSOU
 */
public class ResolveurImpl implements Resolveur {

  @Override
  public final boolean resoudGrille(final char[] possible,
  final GrilleImpl grille) {
    for (int ligne = 0; ligne < grille.getDimension(); ligne++) {
      for (int colonne = 0; colonne < grille.getDimension(); colonne++) {
        if (grille.getGrille()[ligne][colonne] == Grille.EMPTY) {
          for (char value : possible) {
            try {
                grille.setValue(ligne, colonne, value);
              if (this.resoudGrille(possible, grille)) {
                return true;
              } else {
                grille.getGrille()[ligne][colonne] = Grille.EMPTY;
              }
            } catch (Exception ex) {
              continue;
            }
          }
          return false;
        }
      }
    }
    afficheGrille(grille);
    return grille.complete();
  }

  @Override
  public final void afficheGrille(final GrilleImpl grille) {
    int racine = (int) Math.sqrt(grille.getDimension());
    String trait = "------------------------------------";
    System.out.println(trait);
    for (int i = 0; i < grille.getDimension(); i++) {
      if (i % racine == 0 && i != 0) {
        System.out.println(trait);
      }
      for (int j = 0; j < grille.getDimension(); j++) {
        if (j % racine == 0 && j != 0) {
          System.out.print(" | ");
        }
        if (j == 0) {
          System.out.print("| " + grille.getGrille()[i][j] + " ");
        } else {
          System.out.print(" " + grille.getGrille()[i][j] + " ");
        }
        if (j == grille.getDimension() - 1) {
          System.out.print(" |");
        }
      }
      System.out.println();
    }
    System.out.println(trait + "\n\n");
  }
}
