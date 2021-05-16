package webprog.eksamensoving.konte2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class KonteController2020 {
    // Oppg. 2 b)

    @Autowired
    private JdbcTemplate db;

    private final Logger logger = LoggerFactory.getLogger(KonteController2020.class);

    @PostMapping("/registrer")
    public void registrer (StudentKarakter sk, HttpServletResponse response) throws IOException{
        if (validerStudentKarakter(sk,logger)){     // Gjør inputval. og sjekker om studenten og faget finnes.
            String sql1 = "SELECT COUNT(*) FROM student WHERE SId = ?";
            String sql2 = "SELECT COUNT(*) FROM fag WHERE FId = ?";
            int enStudent = db.queryForObject(sql1,Integer.class,sk.getsId());
            int etFag = db.queryForObject(sql2,Integer.class,sk.getfId());
            if(enStudent > 0 && etFag > 0){
                try{
                    String sql = "INSERT INTO studentfag (SId, FId, Aar, Karakter, Prosent) " +
                            "VALUES (?, ?, ?, ?, ?)";
                    db.update(sql,sk.getsId(),sk.getfId(),sk.getAar(),sk.getKarakter(),sk.getProsent());
                }
                catch (Exception e){
                    logger.error("Feil i registrer " + e);
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Databasefeil - prøv igjen senere");
                }
            }
            else{
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Student eller fag ikke funnet.");
            }
        }
        else{
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Inputvalidering feilet på server.");
        }
    }

    //Oppgave 3 b)

    @GetMapping("/hentStudKarakterer")
    public List<StudentKarakter> hentKarakterer (HttpServletResponse response) throws IOException {
        String sql = "SELECT * FROM studentfag";
        try {
            List<StudentKarakter> alleStudentKarakterer = db.query(sql,new BeanPropertyRowMapper(StudentKarakter.class));
            return alleStudentKarakterer;
        }
        catch (Exception e){
            logger.error("Feil i hentKarakterer " + e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i databasen - prøv igjen senere.");
            return null;
        }
    }

    //Oppgave 2 b) forts.

    public static boolean validerStudentKarakter (StudentKarakter sk, Logger logger){
        String regexSId = "[0-9]{6}";
        String regexFId = "[A-ZÆØÅ]{2}[0-9]{4}";
        String regexKarakter = "[ABCDEF]{1}";

        boolean sIdOK = String.valueOf(sk.getsId()).matches(regexSId);
        boolean fIdOK = sk.getfId().matches(regexFId);
        boolean karakterOK = sk.getKarakter().matches(regexKarakter);

        if(sIdOK && fIdOK && karakterOK){
            return true;
        }
        else{
            logger.error("Valideringsfeil.");
            return false;
        }
    }

}