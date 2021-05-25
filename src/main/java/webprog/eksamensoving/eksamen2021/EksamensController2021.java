package webprog.eksamensoving.eksamen2021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;

@RestController
public class EksamensController2021 {
    @Autowired
    private JdbcTemplate db;

    private final Logger logger = LoggerFactory.getLogger(EksamensController2021.class);

    @PostMapping("/lagrePakke")
    @Transactional
    public void lagre (Pakke p){
        if(validerInput(p,logger)){
            String sql1 = "INSERT INTO Kunde (fornavn, etternavn, adresse, postnr, telefonnr, epost) VALUES (?,?,?,?,?,?)";
            String sql2 = "INSERT INTO Pakke (KId,volum, vekt) VALUES (?,?,?)";
            KeyHolder id = new GeneratedKeyHolder();
            try{
                db.update(con -> {
                    PreparedStatement par = con.prepareStatement(sql1,new String[]{"KId"});
                    par.setString(1,p.getFornavn());
                    par.setString(2,p.getEtternavn());
                    par.setString(3,p.getAdresse());
                    par.setString(4,p.getPostnr());
                    par.setString(5,p.getTelefonnr());
                    par.setString(6,p.getEpost());
                    return par;
                },id);
                int kid = id.getKey().intValue();
                db.update(sql2,kid,p.getVolum(),p.getVekt());
            }
            catch (Exception e){
                logger.error("Feil i lagrePakke " + e);
            }
        }
    }

    @GetMapping("/sjekkPostnr")
    public boolean sjekkPostnr(String postnr){
        String sql = "SELECT COUNT (*) FROM Poststed where Postnr = ?";
        try{
            int etPostnr = db.queryForObject(sql,Integer.class,postnr);
            if(etPostnr > 0){
                return true;
            }
        }
        catch (Exception e){
            logger.error("Feil i sjekkPostnr " + e);
        }
        return false;
    }

    public static boolean validerInput (Pakke p, Logger logger){
        String regexFornavn = "[a-zA-ZæøåÆØÅ. \\-]{2,50}";
        String regexEtternavn = "[a-zA-ZæøåÆØÅ. \\-]{2,50}";
        String regexPostnr = "[0-9]{4}";

        boolean fornavnOK = p.getFornavn().matches(regexFornavn);
        boolean etternavnOK = p.getEtternavn().matches(regexEtternavn);
        boolean postnrOK = p.getPostnr().matches(regexPostnr);

        if(fornavnOK && etternavnOK && postnrOK){
            return true;
        }
        else{
            logger.error("Valideringsfeil.");
            return false;
        }
    }
}
