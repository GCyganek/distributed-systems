function validateForm() {
    let imdbId = document.forms["form"]["imdbId"].value;
    if (imdbId === "") {
        alert("imdbId must be filled out");
        return false;
    }
    if (/^t{2}[0-9]{7,}$/.test(imdbId) === false) {
        alert("imdbId is an identifier starting with tt and then containing at least seven digits. Example: tt1234567");
        return false;
    }
}