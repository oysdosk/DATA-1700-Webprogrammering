// Oppg. 1 a)

const registrerUtover = () => {
    const utover = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        klubb: $("#klubb").val(),
        epost: $("#e-post").val(),
        passord: $("#passord").val()
    };
    $.post("/utover", () => {})
        .fail((jqXHR) => {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message());
        });
}

// Oppg. 2 a)

const loggInn = () => {
    if(valideringOK()){     // Oppg. 3 sjekker om input er godkjent før kall til server.
        const url = "/loggInn2020?epost="+$("#e-post2").val()+"&passord="+$("#passord2").val();
        $.get(url,ok => {
            if(ok) {
                $("#loggetInn").html("Innlogging vellykket!");
            }
            else {
                $("#loggetInn").html("Innlogging feilet.");
            }
        });
    }
}

// Oppg. 3

const valideringOK = () => {
    return(validerEpost() && validerPassord());
}

const validerEpost = () => {
    const epost = $("#e-post2").val();
    const regexp = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if(!regexp.test(epost)){
        $("#loggetInn").html("Ugyldig e-post.")
        return false;
    }
    else{
        return true;
    }

}
const validerPassord = () => {
    const passord = $("#passord2").val();
    const regexp = /(?=.*[A-ZÆØÅa-zæøå])(?=.*\d)[A-ZÆØÅa-zæøå\d]{6,}$/;
    if(!regexp.test(passord)){
        $("#loggetInn").html("Passordet må bestå av minst seks tegn og inneholde minst én bokstav og ett tall.")
        return false;
    }
    else{
        return true;
    }
}