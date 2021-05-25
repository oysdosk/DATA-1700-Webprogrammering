const registrerPakke = () => {
    const pakke = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        adresse : $("#adresse").val(),
        postnr : $("#postnr").val(),
        telefonnr : $("#telefonnr").val(),
        epost : $("#epost").val(),
        volum : $("#volum").val(),
        vekt : $("#vekt").val()
    }
    if(valideringOK()){
        if(sjekkPostnr(pakke.postnr)){
            $.post("/lagrePakke",pakke,() => {
            });
        }
    }
}

const sjekkPostnr = postnr => {
        $.get("/sjekkPostnr",postnr, ok => {
            if (!ok){
                $("#feilPostnr").html("Postnummeret ble ikke funnet i databasen.");
                return false;
            }
            else{
                $("#feilPostnr").html("");
                return true;
            }
        });
}

const valideringOK = () => {
    return(validerFornavn() && validerEtternavn() && validerPostnr())
}

const validerFornavn = () =>{
    const fornavn = $("#fornavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,50}$/;
    const ok = regexp.test(fornavn);
    if(!ok){
        $("#feilFornavn").html("Feil i input for fornavn!")
        return false;
    }
    else{
        $("#feilFornavn").html("")
        return true;
    }
}

const validerEtternavn = () =>{
    const etternavn = $("#etternavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,50}$/;
    const ok = regexp.test(etternavn);
    if(!ok){
        $("#feilEtternavn").html("Feil i input for etternavn!")
        return false;
    }
    else{
        $("#feilEtternavn").html("")
        return true;
    }
}

const validerPostnr = () =>{
    const postnr = $("#postnr").val();
    const regexp = /^[0-9]{4}$/;
    const ok = regexp.test(postnr);
    if(!ok){
        $("#feilPostnr").html("Postnummeret må bestå av fire tall!")
        return false;
    }
    else{
        $("#feilPostnr").html("")
        return true;
    }
}