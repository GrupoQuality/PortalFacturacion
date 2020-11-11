//consts principal form
const btnPrincipalButton = document.getElementById('principal-button');
const loginForm = document.getElementById('login-form');
const inputUser = document.getElementById('input-user')
const inputPass = document.getElementById('input-pass')

//events
loginForm.addEventListener('submit', btnPrincipalButtonPushed);


//functions
function btnPrincipalButtonPushed(event) {
    let user = inputUser.value
    let pass = inputPass.value
    event.preventDefault();

    if (user === "admin" && pass === "admin") {
        this.submit();
    } else if (user == null || pass == null) {
        alert("Ingrese usuario y contraseña")
        p.preventDefault();
        return;
    } else {
        alert("Usuario o contraseña incorrecto")
        evt.preventDefault();
        return;
    }
}