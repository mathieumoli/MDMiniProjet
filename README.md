MDMiniProjet
============

Mini Projet Math Discrete SI3 Molinengo Paris

# Work 1 (23/12/14) Mathieu Molinengo
Etude du projet 

## Mots Bien Parenthésés

* Les mots bien parenthésés sont compliqués au niveau du return ... est-ce qu'il faut retourner un void, un String... a voir
* Comment les récupérer tous pour ensuite les afficher ?
* Faut il que le schéma soit libre ?

## GrandEntier

* Constructeur    public GrandEntier(ArrayList<Integer> ge) **OKAY**
* Constructeur    public GrandEntier(int nombreDeBits, Random rnd) **PAS OKAY**
   Je crée des octets aléatoirement grace au rnd et en fonction du nombreDeBits en parametre.
   * Des octets sont négatifs ...
   * Comment convertir dans la Base que nous avons en parametre de classe...

# Work 2 (24/12/14) Mathieu Molinengo

##GrandEntier

* public void shiftLeft(int n) **OKAY**
* public GrandEntier add(GrandEntier ge) **OKAY MAIS PEUT ETRE UN PEU COMPLIQUE**
* public int length() **OKAY**
* public String toString() **OKAY MAIS A COMPARER AVEC LA VERSION DE BIGINTEGER**
* accesseurs **OKAY**
* TestGrandEntier.java **AMELIORE**

# Work 3 (29/12/14) Simon Paris

##MotBienParenthese

* public static ArrayList<String> MotBienParenthese(int n) **OKAY**

# Work 4 (30/12/14) Mathieu Molinengo

##GrandEntier

* Constructeur public GrandEntier(ArrayList<Integer> ge) **AMELIORE**
* Constructeur public GrandEntier(int nombreDeBits, Random rnd) **OKAY**
* TestGrandEntier.java **AMELIORE**

# Work 5 (30/12/14) Simon Paris

##MotBienParenthese **OKAY**

* javadoc **COMMING SOON**

* public static ArrayList<String> MotBienParenthese(int n) **OKAY**
* public static catalan(int n) **OKAY**
* public static catalan2(int n) **OKAY**
* public static TestCatalan2(int n) **OKAY**
* verification mot BP **OKAY**
* methode de paranthese a la ligne **OKAY**
* methode de profondeur d'un mot BP **OKAY**
* TestMotBienParenthese.java **OKAY**


# Work 6 (30/12/14) Mathieu Molinengo

##GrandEntier

* public String toString() **AMELIORE**
* public GrandEntier multiply(GrandEntier m) **OKAY**
* public GrandEntier sub(GrandEntier m) **OKAY**
* public GrandEntier addGrandEntier m) **AMELIORE**
* TestGrandEntier.java **AMELIORE**

# Work 7 (31/12/14) Mathieu Molinengo

##GrandEntier

* ajout de variable de classe zero et un pour optimiser le code
* public GrandEntier shiftRight(int n) **OKAY**
* public GrandEntier multiplyFast(GrandEntier ge) **OKAY**
* private static boolean clearZero(ArrayList<Integer> theArray) **OKAY**
* public GrandEntier sub(GrandEntier m) **AMELIORE**
* public static void compareSimpleWithFast(String[] args) throws Exception **STACKOVERFLOW**
    *trop de création d'objet... je ne sais pas comment le faire...
 
# Work 8 (1/1/15) Mathieu Molinengo

##GrandEntier

* public GrandEntier multiplyFast(GrandEntier ge) **OKAY**
* public static void compareSimpleWithFast(String[] args) throws Exception **STACKOVERFLOW**
    *mais on constate qu'a partir de 8 bits notre multiplyFast est plus performant que le multiply recursif (Question 7)
    *comment virer le stackoverflow...
 

# Work 9 (5/1/15) Mathieu Molinengo

##GrandEntier

* Everything **OKAY**

# Work 10 (5/1/15) Mathieu Molinengo

##GrandEntier

* Complexité **OKAY**
 
