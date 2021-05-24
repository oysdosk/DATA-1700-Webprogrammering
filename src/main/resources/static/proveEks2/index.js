// Oppg. 1 a)

const registrerBruker = () => {
    const bruker = {
        brukernavn : $("#brukernavn").val(),
        passord : $("#passord").val()
    }

    $.post("/lagreBruker", bruker, () => hentBruker());
}

// Oppg. 1 d)
const hentBruker = () => {
    $.get("/hentBruker", bruker => formaterBruker(bruker));
}

const formaterBruker = bruker => {
    let ut = '<table class="table-striped table-bordered" width="80%"><thead><tr>' +
        '<th>Brukernavn</th>' +
        '<th>Passord</th>' +
        '</tr></thead><tbody><tr>' +
        '<td>' + bruker.brukernavn + '</td>' +
        '<td>' + bruker.passord + '</td>' +
        '</tr></tbody></table>';

    $("#brukerUtskrift").html(ut);
}

const loggInn2 = () => {
    const bruker = {
        brukernavn : $("#brukernavn").val(),
        passord : $("#passord").val()
    }
    $.get("/loggInn2", bruker, ok => {
        if(ok){
            $("#loggInnStatus").html("Innlogging vellykket!");
        }
        else{
            $("#loggInnStatus").html('<span style="color:red">Innlogging mislyktes!</span>')
        }
    });

}

