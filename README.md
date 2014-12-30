MDMiniProjet
============

Mini Projet Math Discrete SI3 Molinengo Paris

# Work 1 (23/12/14)
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

# Work 2 (24/12/14)

##GrandEntier

* public void shiftLeft(int n) **OKAY**
* public GrandEntier add(GrandEntier ge) **OKAY MAIS PEUT ETRE UN PEU COMPLIQUE**
* public int length() **OKAY**
* public String toString() **OKAY MAIS A COMPARER AVEC LA VERSION DE BIGINTEGER**
* accesseurs **OKAY**
* TestGrandEntier.java **AMELIORE**

# Work 3 (29/12/14)

##MotBienParenthese

* public static ArrayList<String> MotBienParenthese(int n) **OKAY**

# Work 4 (30/12/14)

##MotBienParenthese **OKAY**

* public static ArrayList<String> MotBienParenthese(int n) **OKAY**
* public static catalan(int n) **OKAY**
* public static catalan2(int n) **OKAY**
* public static TestCatalan2(int n) **OKAY**
* verification mot BP **OKAY**
* methode de paranthese a la ligne **OKAY**
* methode de profondeur d'un mot BP **OKAY**
* TestMotBienParenthese.java **OKAY**
