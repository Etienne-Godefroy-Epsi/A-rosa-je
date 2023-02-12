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

        Plante plante = new Plante();
        plante.setPhoto("ssqdfsdf");
        plante.setNom("sdsfsdf");
        plante.setType("sdsdf");

        planteRepository.save(plante);

        Contrat contrat = new Contrat();
        contrat.setClient(client);
        contrat.setGardien(gardien);
        contrat.setBotaniste(botaniste);
        contrat.setDatedebut(LocalDate.of(2023, 1, 1));
        contrat.setDatefin(LocalDate.of(2023, 1, 3));
        contrat.setEtat(EtatContrat.AVECBOTANISTE.getValue());

        contratRepository.save(contrat);

        PlanteAGarder planteagarder = new PlanteAGarder();
        planteagarder.setPlante(plante);
        planteagarder.setContrat(contrat);
        planteagarder.setQuantite(1);

        planteAGarderRepository.save(planteagarder);
    }
}
