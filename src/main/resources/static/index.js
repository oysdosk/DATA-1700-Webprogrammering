import {validerBrukernavn, validerPassord, brukernavnOgPassordOk} from "./validering.js"

$(() => {
    $("#loggInn").click(() => loggInn());
    $("#krypterAllePassord").click(() => krypterAllePassord());
    $("#innBrukernavn").on("change",() => validerBrukernavn());
    $("#innPassord").on("change", () => validerPassord());
});

const loggInn = () => {
    if(brukernavnOgPassordOk){
        const url = "/loggInn?brukernavn="+$("#innBrukernavn").val()+"&passord="+$("#innPassord").val();
        $.get(url, ok => {
            if(ok){
                window.location.href = "liste.html";
            }
            else{
                $("#utlogget").html("Feil brukernavn eller passord.");
            }
        })
            .fail((jqXHR) => {
                const json = $.parseJSON(jqXHR.responseText);
                $("#utlogget").html(json.message);
            });
    }
    else{
        $("#utlogget").html("Feil brukernavn eller passord.");
    }
}

const krypterAllePassord = () => {
    $.get("/krypterAllePassord",ok => {
        if(ok) {
            $("#utlogget").html("Alle passord er kryptert.")
        }
        else{
            $("#utlogget").html("Kryptering feilet.")
        }
    })
        .fail(jqXHR => {
            const json = $.parseJSON(jqXHR.responseText);
            $("#utlogget").html(json.message());
        });
}

