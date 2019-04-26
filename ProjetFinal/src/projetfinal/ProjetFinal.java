/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfinal;

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author MK
 */
public class ProjetFinal {

    static String[][] tableauEleve;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Affichage Titre prog au demarrage 
        System.out.println("OMNIPOP");
        System.out.println("=======");
        System.out.println("");

        //Authetification de l'user au programme
        int cpLogin = 0; //On initialise le compteur de login à 0
        System.out.println("Veuillez-vous connecter:");
        String userName = login(cpLogin);
        int lgcompt = loginCp(userName); //Compteur de nombre erreur ds le login
        String leMotDePasse = motPasse(lgcompt);
        System.out.println("");

        //Si l'utilisateur n'atteint pas les 3 essaies de login alors on execute la fonciton
        //Lancement du programme
        if (leMotDePasse.equalsIgnoreCase("1234") && userName.equalsIgnoreCase("mlazar")) {
            int rep;
            System.out.println("Bienvenue " + userName);
            System.out.println("");

            //Si choix
            int choix;

            //Affichage menu
            do {

                System.out.println("Menu principal");
                System.out.println("---------------");
                choix = menus();

                System.out.println("");

                //Affichage Titre des choix
                if (choix != 6) {
                    //Affichage Titre selon le choix
                    titreChoix(choix);
                    System.out.println("");
                } else {
                    System.out.println("Merci d'avoir utilisé OMNIPOP");
                }

            } while (choix != 6);
            System.out.println("");

        } //FinAuthentification
        else {
            System.out.println("Nombre de tentive atteint! Aurevoir");
        }

        //FinMain à ne pas effacer        
    }

    static String[][] createTabEleve(int nbr) {
        
        //Création tableau avec le nombre d'élève entré par l'enseignant
        String tabElev[][] = new String[nbr][6];

        //On defini deja les valeur pour les titre de notre tableau
        tabElev[0][1] = "Prenom";
        tabElev[0][2] = "Nom";
        tabElev[0][3] = "Anne Naissance";
        tabElev[0][4] = "Mail";
        tabElev[0][5] = "Nom d'utilisateur";
        
        //on retourne notre tableau crée
        return tabElev;
    }

    //Ajout d'un nouvel étudiant
    static String[][] saisieEleve(String tab[][], int nbr) {

        Scanner sc = new Scanner(System.in);
        
        // 
        for (int i = 1; i < nbr; i++) {

            for (int j = 0; j < tab[i].length; j++) {

                if (j == 0) { 

                    String id;
                    boolean resId;
                    do {
                        System.out.printf("Veuillez saisir l'id de l'étudiant " + i + ": ");
                        
                        id = sc.next();

                        //Validation ID grâce au expression regulier contenu dans notre foncton
                        resId = validID(id);

                    } while (resId == false);

                    tab[i][0] = id;

                }

                if (j == 1) {

                    String prenUser;
                    boolean resPren;
                    do {
                        System.out.printf("Entre prenom de l'élève " + i + ": ");
                        prenUser = sc.next();

                        //Validation Prénom grâce au expression regulier contenu dans notre foncton
                        resPren = validNomPrenom(prenUser);

                    } while (resPren == false);

                    tab[i][1] = prenUser;
                    //On met la premiere lettre en majuscule et les autres en miniscule
                    tab[i][1] = tab[i][1].substring(0, 1).toUpperCase() + tab[i][1].substring(1).toLowerCase();

                }

                if (j == 2) {

                    String nomUser;
                    boolean resNom;
                    do {
                        System.out.printf("Entrez nom de famille " + i + ": ");
                        nomUser = sc.next();

                        //Validation nom grâce au expression regulier contenu dans notre foncton
                        resNom = validNomPrenom(nomUser);

                    } while (resNom == false);

                    tab[i][2] = nomUser;
                    //On met la premiere lettre en majuscule et les autres en miniscule
                    tab[i][2] = tab[i][2].substring(0, 1).toUpperCase() + tab[i][2].substring(1).toLowerCase();

                }

                if (j == 3) {

                    String anneNaissance;
                    boolean resAnnee;

                    //Saisie Année Naissance
                    do {
                        System.out.print("Année de Naissance " + i + ": ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Saisie invalide!");
                            System.out.print("Entre de nouveau l'année de naissais: ");
                            sc.next();
                            System.out.println("");
                        }

                        anneNaissance = sc.next();
                        
                        //Validation année de Naissanace grâce au expression regulier contenu dans notre foncton   
                        resAnnee = validAnnee(anneNaissance);

                    } while (resAnnee == false);

                    tab[i][3] = anneNaissance;

                }

                if (j == 4) {
                    String mailV;
                    boolean resMail;

                    do {
                        System.out.print("Entrez l'adresse mail " + i + ": ");
                        mailV = sc.next();
                        
                        //Validation mail grâce au expression regulier contenu dans notre foncton
                        resMail = validMail(mailV);

                    } while (resMail == false);

                    tab[i][4] = mailV;

                }

                if (j == 5) {

                    String nomT = tab[i][2];
                    
                    //On prent le 3 lettre nom+la premiere lettre du prenom+l'année de naissance pour créer le username
                    String userName = nomT.substring(0, 3) + tab[i][1].toLowerCase().substring(0, 1) + tab[i][3];
                    //On donne l'username obtenu a la position
                    tab[i][5] = userName;

                }

                System.out.println("");

            }
            System.out.println("");

        }

        return tab;
    }

    //Tab affichage liste Etudiant
    static void listeEleve() {

        //On cree une variable format pour donner espace a notre tableau
        String format = "%-25s";

        //affichage titres tableau
        for (int i = 0; i < 1; i++) {
            System.out.printf(format, "ID");
            for (int j = 1; j < tableauEleve[i].length; j++) {
                System.out.printf(format, tableauEleve[i][j]);
            }
            System.out.println("");

        }
        //Barre en dessous des nos titre
        System.out.printf(format, "--");
        System.out.printf(format, "------");
        System.out.printf(format, "---");
        System.out.printf(format, "--------------");
        System.out.printf(format, "----");
        System.out.printf(format, "----------------");
        System.out.println("");

        //Affichage affichage tableau des élèves
        System.out.println("");
        for (int i = 1; i < tableauEleve.length; i++) {

            for (int j = 0; j < tableauEleve[i].length; j++) {
                System.out.printf(format, tableauEleve[i][j], "position ", j);
            }
            System.out.println("");

        }
        System.out.println("");
    }

    //Listes des cours
    static void listeCours() {
        String format = "%-30s";
        //Affichage TitreSession
        System.out.printf(format, "Automne 2018");
        System.out.printf(format, "Hiver 2019");
        System.out.printf(format, "Été 2019");
        System.out.println("");
        System.out.printf(format, "------------");
        System.out.printf(format, "----------");
        System.out.printf(format, "--------");
        System.out.println("");

        //Affichage CoursSessionAutomne1
        System.out.printf(format, "Base de données");
        //AffichageCoursSessionHiver
        System.out.printf(format, "Orienté Objet");
        //Affichage CoursSessionEte
        System.out.printf(format, "Structure de données");
        System.out.println("");

        //Affichage CoursSessionAutomne2
        System.out.printf(format, "Algorithme");
        //AffichageCoursSessionHiver
        System.out.printf(format, "Base de données");
        //Affichage CoursSessionEte
        System.out.printf(format, "Orienté objet");
        System.out.println("");

        //Affichage CoursSessionAutomne3
        System.out.printf(format, " ");
        //AffichageCoursSessionHiver3
        System.out.printf(format, "Algorithme");
        System.out.println("");
        System.out.println("");

    }

    //Option du choix
    static void titreChoix(int choixUtil) {
        Scanner sc = new Scanner(System.in);
        int nbr = 0;

        switch (choixUtil) {

            case 1:
                System.out.println("Quelle session voulez voir?");
                System.out.println("");
                //On appelle notre methode
                choixCoursSession();

                break;
            case 2:
                System.out.println("Listes des cours");
                System.out.println("");
                //On appelle notre methode
                listeCours();

                break;
            case 3:
                System.out.println("Ajout d'un nouvel étudiant");
                System.out.println("");
                int nbrEleve;
                //On cree une variable format pour donner espace a notre tableau
                String format = "%-25s";

                System.out.print("Combien d'élève voulez-vous ajouter: ");

                //On s'assure que l'user n'entre que des chiffres
                while (!sc.hasNextInt()) {
                    System.out.println("Saisie invalide!");
                    System.out.print("Combien d'élève voulez-vous ajouter: ");
                    sc.next();
                    System.out.println("");
                }
                nbr = sc.nextInt();
                System.out.println("");

                System.out.println("");
                
                nbr += 1;
                
                //Notre tableauEleve aura maintenant comme va dimension ceux du createTabEleve
                tableauEleve = createTabEleve(nbr);
//              On stocke dans notre tableau les données de notre fonction saisieEleve
                tableauEleve = saisieEleve(tableauEleve, nbr);

                break;
            case 4:
//////                System.out.println("Liste des étudiants");
                System.out.println("");
                //On appelle notre fonction qui affiche la liste des eleves
                listeEleve();
                break;
            case 5:
                System.out.println("Recherche d'étudiant");
                System.out.println("");
                
                //On appelle la methode 
                rechercheEtudiant();
                break;

            default:
                System.out.println("Choix invalidel, veuillez réessayer!");
                break;

        }

    }

    static void rechercheEtudiant() {
        Scanner sc = new Scanner(System.in);
        String recP, recN, format = "%-25s";
        int result = 0, pos;
        boolean resP, resN;
        
        
        do {  //Tant que la l'expression n'est pas respecté alors on reprend
            System.out.print("Preom: ");
            recP = sc.next();
            System.out.println("");

            //Validation nom et Prénom
            resP = validNomPrenom(recP);

        } while (resP == false);

        do { //Tant que la l'expression n'est pas respecté alors on reprend

            System.out.print("Nom: ");
            recN = sc.next();
            System.out.println("");

            //Validation nom et Prénom
            resN = validNomPrenom(recN);

        } while (resN == false);

        sortie:
        for (int i = 0; i < tableauEleve.length; i++) {
            for (int j = 0; j < tableauEleve[i].length; j++) {

                if (tableauEleve[i][1].equalsIgnoreCase(recP) && tableauEleve[i][2].equalsIgnoreCase(recN)) {
                    
                    //on sauvegarde la position de i dès que l'on trouve le prenom et nom
                    result = i;
                    
                    //on sort de notre bouche dès que la conditon est respecté
                    break sortie;

                } 
                else {
                    result = -1;
                }

            }

        }
        pos = result;

        //Affichage du resultat
        if (result > -1) {

            //affichage titres tableau
            for (int i = 0; i < 1; i++) {
                System.out.printf(format, "ID");
                for (int j = 1; j < tableauEleve[i].length; j++) {
                    System.out.printf(format, tableauEleve[i][j]);
                }
                System.out.println("");

            }

            System.out.printf(format, "--");
            System.out.printf(format, "------");
            System.out.printf(format, "---");
            System.out.printf(format, "--------------");
            System.out.printf(format, "----");
            System.out.printf(format, "----------------");
            System.out.println("");
            
            
            
            //Affichage eleve trouvé en reprenant la position trouvé
            for (int i = 0; i < 1; i++) {

                for (int j = 0; j < tableauEleve[i].length; j++) {

                    System.out.printf(format, tableauEleve[pos][j]);

                }
                System.out.println("");

            }

        }
        
        if (result == -1) {
            
            System.out.println("Aucun élève trouvé!");
            
        }
    }

    //Liste chois cours par session
    static void choixCoursSession() {
        Scanner sc = new Scanner(System.in);
        
        //On appelle notre methode
        menuCoursSession();

        System.out.println("");
        System.out.print("Votre choix: ");
        System.out.println("");

        //On s'assure que l'user n'entre que des chiffre
        while (!sc.hasNextInt()) {

            System.out.println("Chiffre seulement");
            menuCoursSession();
            System.out.println("");
            System.out.print("Votre choix: ");
            sc.next();

        }

        int choix = sc.nextInt();
        
        //Selon le choix faire
        switch (choix) {
            case 1:
                System.out.println("\t Automne 2018");
                System.out.println("\t ------------");
                System.out.println("\t Base de données");
                System.out.println("\t Algorithme");
                break;
            case 2:
                System.out.println("\t Hiver 2019");
                System.out.println("\t ----------");
                System.out.println("\t Orienté objet");
                System.out.println("\t Base de données");
                System.out.println("\t Algorithme");
                break;
            case 3:
                System.out.println("\t Été 2019");
                System.out.println("\t ------");
                System.out.println("\t Structure de données");
                System.out.println("\t Orienté objet");
                break;

            default:
                System.out.println("Choix invalide");

        }

    }

    static void menuCoursSession() {

        System.out.println("1. Automne 2018 ");
        System.out.println("2. Hiver 2019");
        System.out.println("3. Été 2019");

    }

    //Menu
    static int menus() {
        Scanner sc = new Scanner(System.in);

        //On appelle la fonction ListeMenu qui affiche la liste
        listeMenu();
        System.out.println("");
        System.out.print("Votre choix: ");
        System.out.println("");

        while (!sc.hasNextInt()) {

            //On appelle la fonction ListeMenu qui affiche la liste
            listeMenu();
            System.out.println("");
            System.out.print("Votre choix: ");
            sc.next();

        }

        int choix = sc.nextInt();

        return choix;

    }

    //Listes choix
    static void listeMenu() {
        System.out.println("1. Liste des cours par session");
        System.out.println("2. Afficher tous les cours");
        System.out.println("3. Entrer un nouvel étudiant");
        System.out.println("4. Liste de tous les étudiants");
        System.out.println("5. Rechercher un étudiant");
        System.out.println("6. Quitter le programme");
    }

    //Mot de pass
    static String motPasse(int cpLogin) {
        Scanner sc = new Scanner(System.in);
        int cpPass = 0; //
        String motDePasse = new String(); //On cree un objet de type String
        motDePasse = "";

        if (cpLogin < 3) {
            do {

                System.out.print("Mot de passe: ");
                motDePasse = sc.next();

                cpPass++;

                //Cas d'exception pour afficher le message seulement en cas d'erreur
                if (motDePasse.equalsIgnoreCase("1234") == false) {
                    System.out.println("Mot de passe incorrect! Réessayez, il vous reste " + (3 - cpPass) + " tentive");
                }

            } while (motDePasse.equalsIgnoreCase("1234") == false && cpPass < 3);

        }
        return motDePasse;
    }

    static String login(int cpLogin) {
        Scanner sc = new Scanner(System.in);

        String nomUtilisateur = new String(); //On cree un objet de type String        
        nomUtilisateur = "";

        do {

            System.out.print("Login: ");
            nomUtilisateur = sc.next();

            cpLogin++;

            //Cas d'exception pour afficher le message seulement en cas d'erreur
            if (nomUtilisateur.equalsIgnoreCase("mlazar") == false && nomUtilisateur.equalsIgnoreCase("mk") == false) {
                System.out.println("Utilisateur non trouvé! Réessayez, il vous reste " + (3 - cpLogin) + " tentive");
            }

        } while (nomUtilisateur.equalsIgnoreCase("mlazar") == false && nomUtilisateur.equalsIgnoreCase("mk") == false && cpLogin < 3);

        return nomUtilisateur;
    }

    //Compteur nbre fois erreur/login
    static int loginCp(String nomUtilisateur) {

        int cpLogin = 0;

        do {

            cpLogin++;

        } while (nomUtilisateur.equalsIgnoreCase("mlazar") == false && cpLogin < 3);

        return cpLogin;
    }

    static boolean validNomPrenom(String name) {
        boolean result = false;
        String exp = "^[A-Za-z-]+$";
        boolean resultN = name.matches(exp);

        if (resultN == false) {
            System.out.println("Lettre A-Z seulement");
            result = false;
        }
        if (resultN == true) {
            result = true;

        }
        return result;
    }

    static boolean validID(String identifiant) {
        boolean result = false;
        String exp = "^[0-9]+$";
        boolean resultId = identifiant.matches(exp);

        if (resultId == false) {
            System.out.println("chiffre seulement");
            result = false;
        }
        if (resultId == true) {
            result = true;

        }
        return result;
    }

    static boolean validAnnee(String annee) {
        //On cree un objet de type Calendrier
        Calendar calend = Calendar.getInstance();
        //On recupere l'année courrant et stocke dans une variable
        int annCourant = calend.get(Calendar.YEAR);
        //On convertit en integer l'année saisi par l'utilisateur
        int anneN = Integer.parseInt(annee);
        //On stocke l'age dans une variable
        int age = annCourant - anneN;

        boolean result = false;
        //On stocke notre expression dans un variable de type string
        String exp = "^[0-9]{4}+$";
        //On verifie si l'année respecte l'expression et on stocke le resultat
        boolean resultA = annee.matches(exp);

        //Si resutl est faux alors c'est faut
        if (resultA == false) {
            System.out.println("L'année doit avoir 4 chiffre seulement");
            result = false;
        }

        //Si mineur alors c'est faux
        if (resultA == true) {
            if (age < 18 || age > 70) {
                System.out.println("L'étudiant a " + age + " il ne doit pas avoir moins de 18 ans ou plus 70 ans");
                result = false;
            } else {
                result = true;
            }

        }
        return result;
    }

    static boolean validMail(String mail) {
        boolean result = false;
        String exp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
        boolean resultN = mail.matches(exp);

        if (resultN == false) {
            System.out.println("adresse mail non valide");
            result = false;
        }
        if (resultN == true) {
            result = true;

        }
        return result;
    }
}
