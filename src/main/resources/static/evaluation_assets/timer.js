document.addEventListener("DOMContentLoaded", function () {
    var timerElement = document.getElementById('timer');
    var timerContainer = document.getElementById('timer-container');
    var totalTimeInSeconds = 300; // Ajusta el tiempo total en segundos
    var currentExerciseForm = document.getElementById('wordForm'); // Ajusta el ID del formulario actual

    function updateTimerDisplay(remainingTime) {
        var minutes = Math.floor(remainingTime / 60);
        var seconds = remainingTime % 60;
        timerElement.innerText = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }

    function startTimer() {
        var remainingTime = totalTimeInSeconds;

        function update() {
            updateTimerDisplay(remainingTime);

            if (remainingTime === 0) {
                // Lógica cuando el tiempo se agota
                // Mostrar ventana emergente
                alert('¡Tiempo agotado! Al dar "Aceptar" se enviará a la siguiente pregunta.');

                // Enviar formulario automáticamente
                currentExerciseForm.submit();

                // Puedes agregar más lógica aquí si es necesario antes de pasar al siguiente ejercicio
            } else {
                remainingTime--;
                setTimeout(update, 1000);
            }
        }

        update();
    }

    // Iniciar el temporizador cuando se carga la página
    startTimer();
});
