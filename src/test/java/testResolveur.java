package test.java;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.java.*;
import java.io.File;
import java.io.IOException;
/**
 * Tests unitaire pour la classe Grille.
 * @author HOUESSOU
 */
public class testResolveur {
  /**
   * Creation de la grille de sodoku.
   */
  GrilleImpl Sodoku9x9 = new GrilleImpl(9);
  String cheminFichierSodoku9x9 ="sudoku-9x9.txt";
  File fichierSodoku;
  Resolveur solveur = new ResolveurImpl();
  
  @Test
  public void resoudGrille() {
    fichierSodoku = new File(cheminFichierSodoku9x9);
    try {
      GrilleParser.parse(fichierSodoku, Sodoku9x9);
      assertEquals(true, solveur.resoudGrille(Grille.POSSIBLE, Sodoku9x9));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
