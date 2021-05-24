// Oppg. 2

$(() => {
    hentAlleMeldinger();
    $("#registrerMelding").click(() => registrerMelding());
});

const registrerMelding = () => {
    const trafikkMelding = {
        veistrekning : $("#veistrekning").val(),
        fraSted : $("#fraSted").val(),
        tilSted : $("#tilSted").val(),
        fraDatoTid : $("#fraDatoTid").val(),
        tilDatoTid : $("#tilDatoTid").val(),
        melding : $("#melding").val()
    };

    // Oppg. 4
    if(validerVeistrekning()){
        $.post("/lagreMelding", trafikkMelding, () => hentAlleMeldinger());
    }
}

const hentAlleMeldinger = () => {
    $.get("/hentAlleMeldinger", meldinger => formaterUt(meldinger));
}

const formaterUt = (meldinger) => {
    let ut = '<table class="table-striped table-bordered" width="100%"><thead><tr>' +
        "<th>Veistrekning</th><th>Fra sted</th><th>Til Sted</th>" +
        "<th>Fra dato/tid</th><th>Til dato/tid</th><th>Melding</th>" +
        "</tr></thead><tbody>";
    for(const m of meldinger){
        ut += "<tr>" +
            "<td>"+m.veistrekning+"</td>" +
            "<td>"+m.fraSted+"</td>" +
            "<td>"+m.tilSted+"</td>" +
            "<td>"+m.fraDatoTid+"</td>" +
            "<td>"+m.tilDatoTid+"</td>" +
            "<td>"+m.melding+"</td>" +
            "</tr>";
    }
    ut += "</tbody></table>";

    $("#meldingsUtskrift").html(ut);
}

