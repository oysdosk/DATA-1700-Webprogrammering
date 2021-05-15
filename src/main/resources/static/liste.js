import {ingenValideringsFeil, validerPassord} from "./validering.js";

$(() => {
    hentAlleMotorvogner();
    hentAlleBiler();

    $("#registrerMotorvogn").click(() => registrerMotorvogn());

    $("#slettAlleMotorvogner").click(() => {
        $.ajax("/motorvogn", {
            type: 'DELETE',
            success: () => hentAlleMotorvogner()
        })
    });

    $("#loggUt").click(() => loggUt());
});

const registrerMotorvogn = () => {
    const motorvogn = {
        personnr: $("#innPersonnr").val(),
        navn: $("#innNavn").val(),
        adresse: $("#innAdresse").val(),
        kjennetegn: $("#innKjennetegn").val(),
        merke: $("#valgtMerke").val(),
        type: $("#valgtType").val()
    };
    if(ingenValideringsFeil()){
        $.post("/motorvogn", motorvogn, () => {
            hentAlleMotorvogner();
            resetInputfelt();
        });
    }
}

const hentAlleMotorvogner = () => $.get("/motorvogn", data => formaterUtskrift(data))
    .fail(jqXHR => {
        const json = $.parseJSON(jqXHR.responseText);
        $("#utlogget").html(json.message);
    });

const hentAlleBiler = () => $.get("/bil", data => fyllInnBiler(data));

const fyllInnBiler = bilListe => {
    let ut = '<select id="valgtMerke">';
    let forrigeMerke = "";
    ut += '<option>Velg merke</option>';
    for (const bil of bilListe){
        if(bil.merke !== forrigeMerke){
            ut += '<option>' + bil.merke + '</option>';
        }
        forrigeMerke = bil.merke;
    }
    ut += '</select>';
    $("#innMerke").html(ut);
    $("#valgtMerke").on("change", () => finnTyper());
}

const finnTyper = () => {
    const valgtMerke = $("#valgtMerke").val();
    $("#feilMerke").html("");
    $.get("/bil", data => {
        formaterTyper(data,valgtMerke)
    })
        .fail(jqXHR => {
            const json = jqXHR.responseText;
            $("#feilType").html(json.message);
        });
}

const formaterTyper = (biler,valgtMerke) => {
    let ut = '<select id="valgtType">';
    for (const bil of biler){
        if(bil.merke === valgtMerke){
            ut += '<option>' + bil.type + '</option>';
        }
    }
    ut += '</select>';
    $("#innType").html(ut);
}

const formaterUtskrift = motorvognListe => {
    let utskrift =
        '<table><tr>' +
        '<th>Personnr</th>' +
        '<th>Navn</th>' +
        '<th>Adresse</th>' +
        '<th>Kjennetegn</th>' +
        '<th>Merke</th>' +
        '<th>Type</th>' +
        '</tr>';

    for (let motorvogn of motorvognListe) {
        utskrift +=
            '<tr>' +
            '<td>' + motorvogn.personnr + '</td>' +
            '<td>' + motorvogn.navn + '</td>' +
            '<td>' + motorvogn.adresse + '</td>' +
            '<td>' + motorvogn.kjennetegn + '</td>' +
            '<td>' + motorvogn.merke + '</td>' +
            '<td>' + motorvogn.type + '</td>' +
            '</tr>';
    }
    utskrift += '</table>';

    $("#motorvognUtskrift").html(utskrift);
}

const resetInputfelt = () => {
    $("#innPersonnr").val("");
    $("#innNavn").val("");
    $("#innAdresse").val("");
    $("#innKjennetegn").val("");
    $("#valgtMerke").val("");
    $("#valgtType").val("");
}

const loggUt = () => {
    $.get("/loggUt", () => {
        window.location.href = "/";
    });
}