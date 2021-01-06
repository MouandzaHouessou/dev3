package main.java;

public class ResolveurImpl implements Resolveur {

  @Override
  public boolean resoudGrille(char[] possible, GrilleImpl grille) {
    char[] valeurPossibles = new char[grille.getDimension()];
    System.arraycopy(possible, 0, valeurPossibles, 0, grille.getDimension());
    for (int ligne = 0; ligne < grille.getDimension(); ligne++) {
      for (int colonne = 0; colonne < grille.getDimension(); colonne++) {
        if (grille.getGrille()[ligne][colonne] == Grille.EMPTY) {
          for (char carac : valeurPossibles) {
            try {
                grille.setValue(ligne, colonne, carac);
              if (this.resoudGrille(possible,grille)) {
                return true;
              } else {
                grille.getGrille()[ligne][colonne] = Grille.EMPTY;
              }
            } catch (Exception ex) {
              //throw new IllegalArgumentException(ex.toString());
              continue;
            }
          }
          
          return false;
        }
      }
    }
    return grille.complete();
  }

  @Override
  public void afficheGrille(GrilleImpl grille) {
    // TODO Auto-generated method stub
  }  
}
