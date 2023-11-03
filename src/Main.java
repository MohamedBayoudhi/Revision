import java.util.List;
import java.util.Map;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Création de l'agence
        Agence agence = new Agence("A9wa Agence");

        // Création de quelques voitures
        Voiture voiture1 = new Voiture(1, "class G", 50.0f);
        Voiture voiture2 = new Voiture(2, "X6", 60.0f);
        Voiture voiture3 = new Voiture(3, "206", 70.0f);

        try {
            // Ajout de voitures à l'agence
            agence.ajoutVoiture(voiture1);
            agence.ajoutVoiture(voiture2);
            agence.ajoutVoiture(voiture3);
        } catch (VoitureException e) {
            System.out.println("Erreur lors de l'ajout de voiture : " + e.getMessage());
        }

        // Création de quelques clients
        Client client1 = new Client(101, "Jude", "Bellingham");
        Client client2 = new Client(102, "Kylian", "Mbappe");

        try {
            // Location de voitures par les clients
            agence.loueClientVoiture(client1, voiture1);
            agence.loueClientVoiture(client1, voiture2);
            agence.loueClientVoiture(client2, voiture3);
        } catch (VoitureException e) {
            System.out.println("Erreur lors de la location : " + e.getMessage());
        }

        // Affichage des clients et de leurs voitures louées
        System.out.println("Liste des clients et de leurs voitures louées :");
        agence.afficheLesClientsEtLeursListesVoitures();

        // Tri des clients par code
        System.out.println("Clients triés par code :");
        Map<Client, ListVoitures> clientsTriesParCode = agence.triCodeCroissant();
        for (Map.Entry<Client, ListVoitures> entry : clientsTriesParCode.entrySet()) {
            System.out.println(entry.getKey() + " a loué :");
            entry.getValue().affiche();
        }

        // Tri des clients par nom
        System.out.println("Clients triés par nom :");
        Map<Client, ListVoitures> clientsTriesParNom = agence.triNomCroissant();
        for (Map.Entry<Client, ListVoitures> entry : clientsTriesParNom.entrySet()) {
            System.out.println(entry.getKey() + " a loué :");
            entry.getValue().affiche();
        }

        // Sélection de voitures selon un critère de prix
        System.out.println("Voitures avec un prix inférieur à 60.0 :");
        CriterePrix criterePrix = new CriterePrix(60.0f);
        List<Voiture> voituresSelectionnees = agence.selectVoitureSelonCritere(criterePrix);
        for (Voiture voiture : voituresSelectionnees) {
            System.out.println(voiture);
        }

        // Liste des clients qui ont loué au moins une voiture
        System.out.println("Clients ayant loué au moins une voiture :");
        Set<Client> clientsLoueurs = agence.ensembleClientsLoueurs();
        for (Client client : clientsLoueurs) {
            System.out.println(client);
        }

        // Liste des voitures actuellement en location
        System.out.println("Voitures actuellement en location :");
        List<Voiture> voituresEnLocation = agence.listeVoituresEnLocation();
        for (Voiture voiture : voituresEnLocation) {
            System.out.println(voiture);
        }
    }
}
