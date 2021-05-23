package webprog.eksamensoving.proveEks1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import webprog.eksamensoving.konte2020.Melding;

import java.util.List;

@RestController
public class Prove1Controller {
    @Autowired
    private JdbcTemplate db;

    private final Logger logger = LoggerFactory.getLogger(Prove1Controller.class);

    // Oppg. 3
    @PostMapping("/lagreMelding")
    public void lagreMelding(Melding m){
        if(validerMelding(m, logger)){
            String sql = "INSERT INTO Melding (veistrekning, fraSted, tilSted, fraDatoTid, tilDatoTid, melding)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try{
                db.update(sql,m.getVeistrekning(), m.getFraSted(),m.getTilSted(),
                        m.getFraDatoTid(), m.getTilDatoTid(), m.getMelding());
            }
            catch (Exception e){
                logger.error("Feil i lagreMelding");
            }
        }
    }

    // Oppg. 4
    @GetMapping("/hentAlleMeldinger")
    public List<Melding> hentAlleMeldinger(){
        String sql = "SELECT * FROM Melding";
        return db.query(sql,new BeanPropertyRowMapper(Melding.class));
    }

    // Oppg. 3
    public static boolean validerMelding (Melding m, Logger logger){
        String regexVeistrekning = "[A-Z][0-9]{2}";
        boolean ok = m.getVeistrekning().matches(regexVeistrekning);
        if(ok) return true;
        else {
            logger.error("Feil i validering av veistrekning.");
            return false;
        }
    }
}
