package webprog.eksamensoving.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import webprog.eksamensoving.pojos.Bruker;
import webprog.eksamensoving.pojos.Motorvogn;

import java.util.List;

@Repository
public class MotorvognRepo {
    @Autowired
    private JdbcTemplate db;
    private final Logger logger = LoggerFactory.getLogger(MotorvognRepo.class);

    public boolean lagreMotorvogn(Motorvogn motorvogn){
        String sql = "INSERT INTO Motorvognregister (personnr,navn,adresse,kjennetegn,merke,type) " +
                "VALUES(?,?,?,?,?,?)";
        try{
            db.update(sql,motorvogn.getPersonnr(),motorvogn.getNavn(), motorvogn.getAdresse(),
                    motorvogn.getKjennetegn(),motorvogn.getMerke(),motorvogn.getType());
            return true;
        }
        catch (Exception e){
            logger.error("Feil i lagreMotorvogn: " + e);
            return false;
        }
    }

    public List<Motorvogn> hentAlleMotorvogner (){
        String sql = "SELECT * FROM Motorvognregister";
        try{
            List<Motorvogn> list = db.query(sql,new BeanPropertyRowMapper<>(Motorvogn.class));
            return list;
        }
        catch (Exception e){
            logger.error("Feil i hentAlleMotorvogner " + e);
            return null;
        }
    }

    public boolean slettAlleMotorvogner (){
        String sql = "DELETE FROM Motorvognregister";
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e){
            logger.error("Feil i slettAlleMotorvogner " + e);
            return false;
        }
    }

    public boolean loggInn(String brukernavn, String passord){
        /* Før:
        String sql = "SELECT count(*) FROM Brukerregister WHERE brukernavn = ? AND passord = ?";
        try {
            int funnetEnBruker = db.queryForObject(sql, Integer.class, brukernavn, passord);
            if(funnetEnBruker > 0){
                return true;
            }
            else{
                return false;
            }
         */
        String sql = "SELECT * FROM Brukerregister WHERE brukernavn = ?";
        try{
            List<Bruker> brukere = db.query(sql,new BeanPropertyRowMapper(Bruker.class),brukernavn);
            if (brukere != null) {
                if (sjekkPassord(passord, brukere.get(0).getPassord())) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean krypterAllePassord() {
        String sql = "SELECT * from Brukerregister";
        String kryptertPassord;
        try{
            List<Bruker> alleBrukere = db.query(sql,new BeanPropertyRowMapper(Bruker.class));
            for (Bruker b : alleBrukere){
                kryptertPassord = krypterPassord(b.getPassord());
                sql = "UPDATE Brukerregister SET passord=? WHERE id=?";
                db.update(sql,kryptertPassord,b.getId());
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private String krypterPassord(String passord){
        return BCrypt.hashpw(passord,BCrypt.gensalt(12)); // Saltet lagres i passordhaset.

    }

    private boolean sjekkPassord (String passord,String hashPassord){
        return BCrypt.checkpw(passord,hashPassord);
    }
}
