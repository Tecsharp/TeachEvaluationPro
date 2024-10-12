document.getElementById("wordFormIncorrect").addEventListener("submit", function (event) {
  event.preventDefault();
});


//DECLARE HTML CLASS, ID, TAG VARIABLES
const destinationContainer = document.getElementById("destination__container");
const originContainer = document.getElementById("origin__container");
const originalText = document.getElementById("original__text");
const words = document.getElementsByClassName("word");

let destinationPosDefault = destinationContainer.getBoundingClientRect();

//DECLARE CODE VARIABLES
//store coordinates of the words by order of their placement.
let destinationArray = [];

//store coordinates of the words in the origin array
const originArray = [];

//Pick a random exercise from the list
let exercise = exercises[Math.floor(Math.random() * exercises.length)];
let englishSentence = exercise.english.split(" ");
let listOfWords = exercise.list;

//Print the sentence in the speech bubble
for (let i = 0; i < englishSentence.length; i++) {
  const spanNode = document.createElement("span");
  spanNode.textContent = englishSentence[i];
  originalText.appendChild(spanNode);
}



function shuffleArray(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
}

shuffleArray(listOfWords);

//Print the list of words
for (let i = 0; i < listOfWords.length; i++) {
  const wordNode = document.createElement("div");
  wordNode.textContent = listOfWords[i];
  wordNode.classList.add("word");
  originContainer.appendChild(wordNode);
}

function calibrateDestinationCursorPos(destinationArray) {
  //When no word is clicked on
  if (destinationArray.length === 0) {
    return destinationPosDefault.x;
  } else {
    //if word is placed in the destination, fetch coordinates of last word
    //as starting point for the next word.
    let sum = destinationPosDefault.x;
    destinationArray.forEach((element) => {
      sum += element.width + 20; //add 20px for the spacing
    });
    return sum;
  }
}

function createOriginArray(word) {
  let wordPosition = word.getBoundingClientRect();
  let newWordObject = Object.assign(wordPosition);
  newWordObject.word = word.textContent;
  newWordObject.location = "origin";
  originArray.push(newWordObject);
}

// Agrega un evento clic al botón
document.getElementById("submitButtonIncorrect").addEventListener("click", function () {
  // Crea un array para almacenar las palabras seleccionadas
  const selectedWords = [];

  // Itera sobre las palabras en destinationArray y agrega cada palabra al array
  destinationArray.forEach((wordObject) => {
    selectedWords.push(wordObject.word);
  });

  // Actualiza el valor del campo oculto con las palabras seleccionadas
  document.getElementById("selectedWord").value = selectedWords.join(", ");

  // Envía el formulario al servidor
  document.getElementById("wordFormIncorrect").submit();
});

for (let i = 0; i < words.length; i++) {
  createOriginArray(words[i]);

  words[i].addEventListener("click", () => {
    console.log("Clic en la palabra:", originArray[i].word);
    //Check de destinationStartPos
    destinationStartPos = calibrateDestinationCursorPos(destinationArray);

    //Calculate X and Y distance between destination and the word
    let yTravel =
      originArray[i].y -
      (destinationPosDefault.y +
        (destinationPosDefault.height - originArray[i].height) / 2);

    let xTravel = 0;
    if (originArray[i].x > destinationStartPos) {
      xTravel = -(originArray[i].x - destinationStartPos);
    } else {
      xTravel = destinationStartPos - originArray[i].x;
    }

    if (originArray[i].location === "origin") {
      originArray[i].location = "destination";
      //Put the word object in the destination array
      destinationArray.push(originArray[i]);
    } else if (originArray[i].location === "destination") {
      yTravel = 0;
      xTravel = 0;
      originArray[i].location = "origin";
      //delete the word from the destination Array
      let test = destinationArray.filter(
        (wordObject) => wordObject.word !== originArray[i].word
      );
      destinationArray = test;
    }

    //Apply translate
    words[i].style.transform = `translate(${xTravel}px,-${yTravel}px)`;
  });
}
