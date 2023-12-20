document.getElementById("registrationForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita il comportamento predefinito del form (invio della richiesta)

    // Recupera i dati dal modulo
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    // Esegui qui la logica di registrazione (puoi inviare i dati a un server backend)

    // Mostra un messaggio di alert (da sostituire con la logica di gestione reale)
    alert("Registrazione completata:\nNome: " + name + "\nEmail: " + email);
});
