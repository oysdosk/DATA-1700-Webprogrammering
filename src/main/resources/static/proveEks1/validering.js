// Oppg. 1
const validerVeistrekning = () => {
    const veiStrekning = $("#veistrekning").val();
    const regex = /^[A-Z][0-9]{2}$/;
    const ok = regex.test(veiStrekning);
    if(!ok){
        $("#feilVeistrekning").html("Skriv inn en gyldig veistrekning (Stor bokstav + to siffer.");
        return false;
    }
    else{
        $("#feilVeistrekning").html("");
        return true;
    }
}