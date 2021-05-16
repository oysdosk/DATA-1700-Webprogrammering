/* const valideringOK = () => {
    return (validerSId() && validerFId() && validerKarakter());
}

const validerSId = () => {
    const SId = $("#sId").val();
    const regexp = /^[0-9]{6}$/;
    if(!regexp.test(SId)){
        $("#feilSId").html("Studentnummeret må være seks siffer langt.");
        return false;
    }
    else{
        $("#feilSId").html("");
        return true;
    }
}

const validerFId = () => {
    const FId = $("#fId").val();
    const regexp = /^[A-ZÆØÅ]{2}[0-9]{4}$/;
    if(!regexp.test(FId)){
        $("#feilFId").html("Fag-ID må bestå av to store bokstaver etterfulgt av fire siffer.");
        return false;
    }
    else{
        $("#feilFId").html("");
        return true;
    }
}

const validerKarakter = () => {
    const karakter = $("#karakter").val();
    const regexp = /^[ABCDEF]{1}$/;
    if(!regexp.test(karakter)){
        $("#feilKarakter").html("Karakteren kan bare ha verdi A, B, C, D, E eller F.");
        return false;
    }
    else{
        $("#feilKarakter").html("");
        return true;
    }
}

 */