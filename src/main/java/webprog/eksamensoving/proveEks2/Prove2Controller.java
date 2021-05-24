package webprog.eksamensoving.proveEks2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class Prove2Controller {

    @Autowired
    private JdbcTemplate db;

    private final Logger logger = LoggerFactory.getLogger(Prove2Controller.class);

    // Oppg. 3 a)
    @Autowired
    private HttpSession session;

    // Oppg. 1 b)
    @PostMapping("/lagreBruker")
    public void lagreBruker(Bruker b){
        String sql = "INSERT INTO Kunde (brukernavn, passord) VALUES (?,?)";
        try{
            db.update(sql,b.getBrukernavn(),b.getPassord());
        }
        catch (Exception e){
            logger.error("Feil i lagreBruker " + e);
        }
    }

    // Oppg. 1 c)
    @GetMapping("/hentBruker")
    public Bruker hentBruker () {
        String sql = "SELECT * FROM Kunde ORDER BY id DESC";
        try{
            List<Bruker> brukere = db.query(sql,new BeanPropertyRowMapper<>(Bruker.class));
            return brukere.get(0);
        }
        catch (Exception e){
            logger.error("Feil i hentAlleBrukere " + e);
            return null;
        }
    }

    // Oppg. 2
    @GetMapping("/loggInn2")
    public boolean loggInn (Bruker b){
        String sql = "SELECT count(*) from Kunde where brukernavn = ? AND passord = ?";
        try{
            int brukerFinnes = db.queryForObject(sql,Integer.class,b.getBrukernavn(),b.getPassord());
            if(brukerFinnes > 0){
                session.setAttribute("Innlogget",true);     // Oppg. 3 a)
                return true;
            }
        }
        catch (Exception e){
            logger.error("Feil i loggInn2 " + e);
            return false;
        }
        return false;
    }
}

/*  Oppg. 3 b)

if((boolean)session.getAttribute("Innlogget")){

        }
 */
