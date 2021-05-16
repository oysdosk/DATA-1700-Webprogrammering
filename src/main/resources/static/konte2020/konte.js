// Oppg. 2 a)

$(() => {
    $("#Registrer").click(() => registrerStudKar());
});

const registrerStudKar = () => {
    const studentKarakter = {
        sId : $("#sId").val(),
        fId : $("#fId").val(),
        karakter : $("#karakter").val(),
        prosent : $("#prosent").val(),
        aar : $("#aar").val()
    };

    if(valideringOK()){
        $.post("/registrer",studentKarakter, () => {
            $("#status").html("Registrering vellykket!")
            hentStudentKarakterer();    // Oppg. 3
        })
            .fail(jqXHR => {
                const json = $.parseJSON(jqXHR.responseText);
                $("#status").html(json.message);
            });
    }
}

// Oppg. 3 a)

const hentStudentKarakterer = () => {
    $.get("/hentStudKarakterer", liste => formaterUtskrift(liste))
        .fail(jqXHR => {
        const json = $.parseJSON(jqXHR.responseText);
        $("#status").html(json.message);
    });
}

const formaterUtskrift = (liste) => {
    let sum = 0, antall = 0;
    let ut = "<table><tr>" +
        "<th>Student</th><th>Fag</th><th>Karakter</th><th>Prosent</th><th>År</th></tr>";
    for (const s of liste){
        ut += "<tr><td>" +s.sId + "</td><td>" + s.fId + "</td><td>" + s.karakter + "</td>" +
            "<td>" + s.prosent + "</td><td>" + s.aar + "</td></tr>";
        sum += s.prosent;
        antall ++;
    }
    const snitt =  Math.round(sum / antall).toFixed(2);
    ut += "</table>" +
    "<br> Gjennomsnittscoren ble: " + snitt + " %.";
    $("#utskrift").html(ut);
}


// Inputvalidering

const valideringOK = () => {
    return (validerSId() && validerFId() && validerKarakter() && validerProsent() && validerAar());
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

const validerProsent = () => {
    const prosent = $("#prosent").val();
    const regexp = /^[1-9][1-9]?$|^100$/;
    if(!regexp.test(prosent)){
        $("#feilProsent").html("Tallet må være mellom 0 og 100.");
        return false;
    }
    else{
        $("#feilProsent").html("");
        return true;
    }
}

const validerAar = () => {
    const aar = $("#aar").val();
    const regexp = /[1-2][0-9]{3}$/;    // Må rettes
    if(!regexp.test(aar)){
        $("#feilAar").html("Skriv inn et gyldig årstall");
        return false;
    }
    else{
        $("#feilAar").html("");
        return true;
    }
}