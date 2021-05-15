const validerPersonnr = () => {
    const personnr = $("#innPersonnr").val();
    const regexp = /^[0-9]{11}$/;
    const ok = regexp.test(personnr);
    if (!ok){
        $("#feilPersonnr").html("Personnummeret må bestå av 11 siffer.");
        return false;
    }
    else{
        $("#feilPersonnr").html("");
        return true;
    }
}

const validerNavn = () => {
    const navn = $("#innNavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const ok = regexp.test(navn);
    if (!ok){
        $("#feilNavn").html("Navnet må bestå av mellom 2 og 30 tegn.");
        return false;
    }
    else{
        $("#feilNavn").html("");
        return true;
    }
}

const validerAdresse = () => {
    const adresse = $("#innAdresse").val();
    const regexp = /^[0-9a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const ok = regexp.test(adresse);
    if (!ok){
        $("#feilAdresse").html("Adressen må bestå av mellom 2 og 30 tegn.");
        return false;
    }
    else{
        $("#feilAdresse").html("");
        return true;
    }
}

const validerKjennetegn = () => {
    const kjennetegn = $("#innKjennetegn").val();
    const regexp = /^[A-Z][A-Z][0-9]{5}$/;
    const ok = regexp.test(kjennetegn);
    if (!ok){
        $("#feilKjennetegn").html("Kjennetegnet må bestå av 2 store bokstaver + 5 siffer.");
        return false;
    }
    else{
        $("#feilKjennetegn").html("");
        return true;
    }
}

const validerMerke = () => {
    const merke = $("#valgtMerke").val();
    if (merke ==="Velg merke"){
        $("#feilMerke").html("Velg et merke.")
        return false;
    }
    else{
        $("#feilMerke").html("");
        return true;
    }
}

const ingenValideringsFeil = () => {
    return(validerPersonnr() && validerNavn() && validerAdresse() && validerKjennetegn() && validerMerke());
}

const validerBrukernavn = () => {
    const brukernavn = $("#innBrukernavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,15}$/;
    const ok = regexp.test(brukernavn);
    if(!ok){
        $("#feilBrukernavn").html("Brukernavnet må bestå av 2-15 bokstaver.")
        return false;
    }
    else{
        $("#feilBrukernavn").html("");
        return true;
    }
}

const validerPassord = () => {
    const passord = $("#innPassord").val();
    const regexp = /^(?=.*[0-9])(?=.*[A-Za-zÆØÅæøå])[0-9a-zA-ZæøåÆØÅ. \-]{6,}$/;
    const ok = regexp.test((passord));
    if(!ok){
        $("#feilPassord").html("Passordet må være minst 6 tegn langt og inneholde minst én bokstav og ett tall.")
        return false;
    }
    else{
        $("#feilPassord").html("");
        return true;
    }
}

const brukernavnOgPassordOk = () => {
    return (validerBrukernavn() && validerPassord());
}

export {validerBrukernavn,validerPassord,brukernavnOgPassordOk, ingenValideringsFeil}