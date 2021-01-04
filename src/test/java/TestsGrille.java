package test.java;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.java.*;
/**
 * Tests unitaire pour la classe Grille.
 * @author HOUESSOU
 */
public class TestsGrille {
/**
 *declaration de interface grille;
 */
Grille grille = new GrilleImpl(9);

/**
 * Test pour recuperer  la dimension du tableau
 */
  @Test
  public void TestgetDimension() {
    final int dimension = 9;
    assertEquals(dimension, grille.getDimension());
  }
/**
 * Test si x ou y sont hors bornes (0-8)
 */
  @Test
  public void TestSetValue() {
    try {
      final int x = 12;
      final int y = 2;
      final char value = 1;
      grille.setValue(x, y, value);
      fail("L'exception de du debordement aurait du etre levé");
    } catch (IllegalArgumentException e) {
       //Ne rien faire c'est normal
    }
  }

/**
 * Test si la valeur est interdite(existe) dans une meme 
 * colonne
 */
  @Test
  public void TestSetValue2() {
    try {
      int x = 2;
      int y = 2;
      char value = '1';
      grille.setValue(x, y, value);
      final int x2 = 8;
      final int y2 = 2;
      final char value2 = '1';
      grille.setValue(x2, y2, value2);
      fail("L'exception: cet élèement existe deja dans une colonne");
  } catch (IllegalArgumentException e) {
     //Ne rien faire c'est normal
  }
}
/**
 * Test si la valeur est interdite(existe) dans une meme ligne
 */
  @Test
  public void TestSetValue3() {
    try {
      final int x = 3;
      final int y = 3;
      final char value = '1';
      grille.setValue(x, y, value);
      final int x2 = 3;
      final int y2 = 8;
      final char value2 = '1';
      grille.setValue(x2, y2, value2);
      fail("L'exception: cet élèement existe deja dans une ligne");
   } catch (IllegalArgumentException e) {
     //Ne rien faire c'est normal
   }
  }
/**
 * Test si la valeur est interdite(existe) dans une meme region
 */
  @Test
  public void TestSetValue4() {
    try {
      final int x = 1;
      final int y = 1;
      final char value = '1';
      grille.setValue(x, y, value);
      final int x2 = 0;
      final int y2 = 0;
      final char value2 = '1';
      grille.setValue(x2, y2, value2);
      fail("L'exception: cet élèment existe deja dans dans cette region");
   } catch (IllegalArgumentException e) {
     //Ne rien faire c'est normal
   }
  }
/**
 * Test si value n'est pas un caractere autorise
 *        ('1',...,'9')
 */
  @Test
  public void TestSetValue5(){
    try {
      final int x2 = 5;
      final int y2 = 2;
      final char value2 = 'w';
      grille.setValue(x2, y2, value2);
      fail("L'exception: caractère non autorise ");
    } catch (IllegalArgumentException e) {
      //Ne rien faire c'est normal
    }
  }
/**
 * Test si il retourne la valeur dans la case x,y
 */
  @Test
  public void TestGetValue(){
    final int x = 2;
    final int y = 2;
    final char value = '1';
    grille.setValue(x, y, value);
    assertEquals('1', grille.getValue(x, y));
  }
/*
 *Test retourne IllegalArgumentException 
 * si x ou y sont hors bornes (0-8)
 * y > 8
 */
  @Test
  public void TestGetValue2(){
    try {
      final int x = 1;
      final int y = 13;
      grille.getValue(x, y);
      fail("L'exception: caractère non autorise");
    } catch (IllegalArgumentException e) {
       //Ne rien faire c'est normal
    }
    
  }

  /*
 *Test retourne IllegalArgumentException 
 * si x ou y sont hors bornes (0-8)
 * x > 8
 */
  @Test
  public void TestGetValue3(){
    try {
      final int x = 12;
      final int y = 1;
      grille.getValue(x, y);
      fail("L'exception: caractère non autorise");
    } catch (IllegalArgumentException e) {
      //Ne rien faire c'est normal
    }
    
  }

/*
 *Test si la grille est complete
 */
 @Test
 public void TestComplete(){

  char [][] grilleComplete = new char[][] {
    {'9', '1', '3', '6', '2', '7', '5', '8', '4'},
    {'6', '4', '2', '5', '8', '9', '7', '3', '1'},
    {'8', '7', '5', '3', '4', '1', '6', '9', '2'},
    {'5', '8', '9', '1', '7', '4', '3', '2', '6'},
    {'2', '6', '1', '8', '9', '3', '4', '5', '7'},
    {'7', '3', '4', '2', '6', '5', '8', '1', '9'},
    {'1', '2', '8', '7', '3', '6', '9', '4', '5'},
    {'3', '9', '6', '4', '5', '2', '1', '7', '8'},
    {'4', '5', '7', '9', '1', '8', '2', '6', '3'}};

    for (int i = 0; i < grilleComplete.length; i++) {
      for (int j = 0; j < grilleComplete.length; j++) {
        grille.setValue(i, j, grilleComplete[i][j]);
      }
    }
   assertEquals(true, grille.complete());
 }

/*
 *Test si la grille n'est pas complète
 */
 @Test
 public void TestComplete1(){
   assertEquals(false, grille.complete());
 }

/**
 * Test si x ou y sont hors bornes (0-8)
 * cas ou x > 8
 */
 @Test
  public void TestPossible() {
    try {
      final int x = 12;
      final int y = 2;
      final char value = 1;
      grille.possible(x, y, value);
      fail("L'exception de du debordement aurait du etre levé");
  } catch (IllegalArgumentException e) {
      //Ne rien faire c'est normal
  }
 }
 /**
 * Test si x ou y sont hors bornes (0-8)
 * cas ou y > 8
 */
 @Test
 public void TestPossible4() {
   try {
     final int x = 3;
     final int y = 14;
     final char value = 1;
     grille.possible(x, y, value);
     fail("L'exception de du debordement aurait du etre levé");
   } catch (IllegalArgumentException e) {
      //Ne rien faire c'est normal
   }
 }
/**
 * Test si value n'est pas un caractere autorise
 *        ('1',...,'9')
 */
 @Test
 public void TestPossible2(){
  try {
    final int x2 = 2;
    final int y2 = 2;
    final char value2 = 'w';
    grille.possible(x2, y2, value2); 
    fail("L'exception: caractère non autorise ");
  } catch (IllegalArgumentException e) {
    //Ne rien faire c'est normal
  }
 }
  /**
  * Test pour que la methode Possible.
  * retoune true
  */
  @Test
  public void TestPossible3(){
    final int x2 = 2;
    final int y2 = 2;
    final char value2 = '1';
    assertEquals(true, grille.possible(x2, y2, value2));
  }
}
