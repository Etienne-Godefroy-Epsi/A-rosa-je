package com.example.mspr;

import com.example.mspr.Enum.EtatClient;
import com.example.mspr.Enum.EtatContrat;
import com.example.mspr.Repository.ContratRepository;
import com.example.mspr.Repository.PlanteAGarderRepository;
import com.example.mspr.Repository.PlanteRepository;
import com.example.mspr.Repository.UtilisateurRepository;
import com.example.mspr.bo.*;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication()
public class MsprApplication implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private PlanteRepository planteRepository;
    @Autowired
    private PlanteAGarderRepository planteAGarderRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsprApplication.class, args);
    }

    @Override
    public void run(String... args) {

        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        Client client = new Client();
        client.setAdresse("szfdsdf");
        client.setEmail("sdfsdfs1");
        client.setNom("dsfsdfsed");
        client.setPrenom("sdsfsdf");
        client.setMdp(passwordEncryptor.encryptPassword("mdp1"));
        client.setEtat(EtatClient.INDISPONIBLE.getValue());

        Client client2 = new Client();
        client2.setAdresse("szfdsdf");
        client2.setEmail("sdfsdfs2");
        client2.setNom("dsfsdfsed");
        client2.setPrenom("sdsfsdf");
        client2.setMdp(passwordEncryptor.encryptPassword("mdp2"));
        client2.setEtat(EtatClient.INDISPONIBLE.getValue());

        Client gardien = new Client();
        gardien.setAdresse("szfdsdf");
        gardien.setEmail("sdfsdfs3");
        gardien.setNom("dsfsdfsed");
        gardien.setPrenom("sdsfsdf");
        gardien.setMdp(passwordEncryptor.encryptPassword("mdp3"));
        gardien.setEtat(EtatClient.DISPONIBLE.getValue());

        Botaniste botaniste = new Botaniste();
        botaniste.setAdresse("szfdsdf");
        botaniste.setEmail("sdfsdfs4");
        botaniste.setNom("dsfsdfsed");
        botaniste.setPrenom("sdsfsdf");
        botaniste.setMdp(passwordEncryptor.encryptPassword("mdp4"));

        utilisateurRepository.save(client);
        utilisateurRepository.save(client2);
        utilisateurRepository.save(gardien);
        utilisateurRepository.save(botaniste);

        Plante plante1 = new Plante();
        plante1.setPhoto("image1.png");
        plante1.setNom("Aglaonéma");
        plante1.setType("Aglaonema");

        Plante plante2 = new Plante();
        plante2.setPhoto("image2.png");
        plante2.setNom("Aspidistra");
        plante2.setType("Aspidistra elatior");

        Plante plante3 = new Plante();
        plante3.setPhoto("image3.png");
        plante3.setNom("Dieffenbachia");
        plante3.setType("Dieffenbachia");

        planteRepository.save(plante1);
        planteRepository.save(plante2);
        planteRepository.save(plante3);

        Contrat contrat = new Contrat();
        contrat.setClient(client);
        contrat.setGardien(gardien);
        contrat.setBotaniste(botaniste);
        contrat.setDatedebut(LocalDate.of(2023, 1, 1));
        contrat.setDatefin(LocalDate.of(2023, 1, 3));
        contrat.setEtat(EtatContrat.AVECBOTANISTE.getValue());

        Contrat contrat1 = new Contrat();
        contrat1.setClient(gardien);
        contrat1.setGardien(client);
        contrat1.setBotaniste(botaniste);
        contrat1.setDatedebut(LocalDate.of(2023, 1, 1));
        contrat1.setDatefin(LocalDate.of(2023, 1, 3));
        contrat1.setEtat(EtatContrat.AVECBOTANISTE.getValue());

        contratRepository.save(contrat);
        contratRepository.save(contrat1);

        PlanteAGarder planteagarder = new PlanteAGarder();
        planteagarder.setPlante(plante1);
        planteagarder.setContrat(contrat);
        planteagarder.setQuantite(1);

        PlanteAGarder planteagarder1 = new PlanteAGarder();
        planteagarder1.setPlante(plante2);
        planteagarder1.setContrat(contrat);
        planteagarder1.setQuantite(1);

        PlanteAGarder planteagarder2 = new PlanteAGarder();
        planteagarder2.setPlante(plante3);
        planteagarder2.setContrat(contrat1);
        planteagarder2.setQuantite(1);

        PlanteAGarder planteagarder3 = new PlanteAGarder();
        planteagarder3.setPlante(plante1);
        planteagarder3.setContrat(contrat1);
        planteagarder3.setQuantite(1);

        planteAGarderRepository.save(planteagarder);
        planteAGarderRepository.save(planteagarder1);
        planteAGarderRepository.save(planteagarder2);
        planteAGarderRepository.save(planteagarder3);
    }
}
