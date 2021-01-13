package main.java;
/**
 *  Implementation de la classe "GrilleParser"
 *  necessaire a la au chargement des grilles
 *  d'evaluation.
 */

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 *  Classe permettant initialiser une grille a partir.
 *  Des donnees contenues dans un fichier
 */
public abstract class AbstractGrilleParser {
  /**
   *  Constructeur par defaut de la classe.
   */
  private AbstractGrilleParser() {
  }

  /**
   *  Methode permettant de charger une grille a l'aide
   *  des donnees contenues dans un flux.
   *  @param input le le flux de donnees a exploiter.
   *  @param grille la grille sur laquelle effectuer l'opreration
   *  @throws EOFException si la donnee lus n'est pas correcte
   *  @throws IOException si la donnee lus ne sont pas correctes
   */
  public static void parse(final InputStream input, final GrilleImpl grille)
      throws IOException {
    Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
    int dimension = grille.getDimension();
    char[] buffer = new char[dimension];
    for (int line = 0; line < dimension; line++) {
      int lus = reader.read(buffer);
      if (lus != dimension) {
        throw new EOFException("format incorrect");
      }

      for (int i = 0; i < dimension; i++) {
        grille.setValue(line, i, buffer[i]);
      }

      lus = reader.read(new char[1]);
      if (lus != 1) {
        throw new EOFException("pas de fin de ligne ? ligne=" + line);
      }
    }

    reader.close();

  }

  /**
   *  Methode permettant de charger une grille a l'aide
   *  des donnees contenues dans un fichier.
   *  @param file le fichier a lire.
   *  @param grille la grille sur laquelle effectuer l'opreration
   *  @throws IOException si la donnee lus n'est pas correcte
   */
  public static void parse(final File file, final GrilleImpl grille)
      throws IOException {
    parse(new FileInputStream(file), grille);
  }

}
