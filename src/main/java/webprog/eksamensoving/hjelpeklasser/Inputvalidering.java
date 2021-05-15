package webprog.eksamensoving.hjelpeklasser;

import org.slf4j.Logger;
import webprog.eksamensoving.pojos.Motorvogn;

public class Inputvalidering {
    public static boolean validerMotorvogn(Motorvogn motorvogn, Logger logger){
        String regexPersonnr = "[0-9]{11}";
        String regexNavn = "[a-zA-ZæøåÆØÅ. \\-]{2,30}";
        String regexAdresse = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,30}";
        String regexKjennetegn = "[A-Z][A-Z][0-9]{5}";
        String regexMerke = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,15}";

        boolean personnrOK = motorvogn.getPersonnr().matches(regexPersonnr);
        boolean navnOK = motorvogn.getNavn().matches(regexNavn);
        boolean adresseOK = motorvogn.getAdresse().matches(regexAdresse);
        boolean kjennetegnOK = motorvogn.getKjennetegn().matches(regexKjennetegn);
        boolean merkeOK = motorvogn.getMerke().matches(regexMerke);

        if(personnrOK && navnOK && adresseOK && kjennetegnOK && merkeOK){
            return true;
        }
        logger.error("Valideringsfeil");
        return false;
    }
}
