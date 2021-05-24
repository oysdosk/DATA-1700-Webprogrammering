package webprog.eksamensoving.eksamen2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class EksamensController2020 {

    @Autowired
    private JdbcTemplate db;

    private final Logger logger = LoggerFactory.getLogger(EksamensController2020.class);

    // Oppg. 1 b)
    @PostMapping("/lagreUtover")
    public void lagreUtover (Utover u, HttpServletResponse response) throws IOException {
        String sql = "INSERT INTO utover (fornavn, etternavn, klubb, epost, passord) " +
                "VALUES (?,?,?,?,?)";
        String hashPassord = krypterPassord(u.getPassord());
        try{
            db.update(sql,u.getFornavn(),u.getEtternavn(),u.getKlubb(),u.getEpost(),hashPassord);
        }
        catch (Exception e){
            logger.error("Feil i lagreUtover. " + e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i databasen - prøv igjen senere. ");
        }
    }

    private String krypterPassord(String passord){
        return BCrypt.hashpw(passord,BCrypt.gensalt(12));
    }

    // Oppg. 2 b)
    @Autowired
    private HttpSession session;

    @GetMapping("/loggInn2020")
    public boolean loggInn2020 (Person p, HttpServletResponse response) throws IOException {
        String sql = "SELECT * FROM utover WHERE epost = ?";
        try {
            Utover u = db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Utover.class),
                    new Object[]{p.getEpost()});

            if (sjekkPassord(p.getPassord(), u.getPassord())) {
                session.setAttribute("Innlogget", true);
                return true;
            }
            else return false;
        }

        catch (Exception e){
             logger.error("Feil i loggInn2020." + e);
             response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Brukernavnet finnes ikke eller feil i db.");
            return false;
        }
    }

    private boolean sjekkPassord (String passord, String hashpassord){
        return BCrypt.checkpw(passord,hashpassord);
    }
}
