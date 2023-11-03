import java.util.*;

public class Agence {
    private String nom;
    private ListVoitures parking;
    private Map<Client, ListVoitures> clientVoitureLoue;

    public Agence(String nom) {
        this.nom = nom;
        this.parking = new ListVoitures();
        this.clientVoitureLoue = new HashMap<>();
    }

    public void ajoutVoiture(Voiture v) throws VoitureException {
        parking.ajoutVoiture(v);
    }

    public void suppVoiture(Voiture v) throws VoitureException {
        parking.supprimeVoiture(v);
    }

    public void loueClientVoiture(Client client, Voiture voiture) throws VoitureException {
        if (!clientVoitureLoue.containsKey(client)) {
            clientVoitureLoue.put(client, new ListVoitures());
        }
        ListVoitures voituresLouees = clientVoitureLoue.get(client);
        voituresLouees.ajoutVoiture(voiture);
    }

    public void retourClientVoiture(Client client, Voiture voiture) throws VoitureException {
        if (clientVoitureLoue.containsKey(client)) {
            ListVoitures voituresLouees = clientVoitureLoue.get(client);
            voituresLouees.supprimeVoiture(voiture);
        }
    }

    public List<Voiture> selectVoitureSelonCritere(Critere critere) {
        List<Voiture> voituresSelectionnees = new ArrayList<>();
        for (Voiture voiture : parking.getVoitures()) {
            if (critere.estSatisfaitPar(voiture)) {
                voituresSelectionnees.add(voiture);
            }
        }
        return voituresSelectionnees;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return clientVoitureLoue.keySet();
    }

    public List<Voiture> listeVoituresEnLocation() {
        List<Voiture> voituresEnLocation = new ArrayList<>();
        for (ListVoitures voituresLouees : clientVoitureLoue.values()) {
            voituresEnLocation.addAll(voituresLouees.getVoitures());
        }
        return voituresEnLocation;
    }

    public void afficheLesClientsEtLeursListesVoitures() {
        for (Map.Entry<Client, ListVoitures> entry : clientVoitureLoue.entrySet()) {
            Client client = entry.getKey();
            ListVoitures voituresLouees = entry.getValue();

            System.out.println("Client: " + client.toString());
            System.out.println("Voitures louées:");
            voituresLouees.affiche();
            System.out.println();
        }
    }

    public Map<Client, ListVoitures> triCodeCroissant() {
        Map<Client, ListVoitures> trieeParCode = new TreeMap<>(new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return Integer.compare(c1.getCode(), c2.getCode());
            }
        });

        trieeParCode.putAll(clientVoitureLoue);
        return trieeParCode;
    }



    public Map<Client, ListVoitures> triNomCroissant() {
        Map<Client, ListVoitures> trieeParNom = new TreeMap<>(new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getNom().compareTo(c2.getNom());
            }
        });

        trieeParNom.putAll(clientVoitureLoue);
        return trieeParNom;
    }

}
