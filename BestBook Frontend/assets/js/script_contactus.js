
//Get data
const nameInput1 = document.getElementById("name");
const email = document.getElementById("email");
const subject = document.getElementById("subject");
const message = document.getElementById("message");
const nameInput = document.getElementById("name");
const success = document.getElementById("success");
const errorNodes = document.getElementsByClassName("error");

//Validate data
function validateForm(){

    clearMessages();
    let errorFlag = false;

    if(nameInput1.value.length < 1){
        errorNodes[0].innerText = "Name cannot be blank";
        nameInput1.classList.add("error-border");
        errorFlag = true;
    }

    if(subject.value.length < 1){
        errorNodes[1].innerText = "Please enter a subject of your concer";
        subject.classList.add("error-border");
        errorFlag = true;
    }

    if(!emailIsValid(email.value)){
        errorNodes[2].innerText = "Invalid email address";
        email.classList.add("error-border");
        errorFlag = true;
    }

    if(message.value.length < 1){
        errorNodes[3].innerText = "Please enter message";
        email.classList.add("error-border");
        errorFlag = true;
    }

    if(!errorFlag){
        success.innerText = "Your message has been sent!";
    }
}

// Clear error / success messages
function clearMessages(){
    for(let i = 0; i < errorNodes.length; i++){
        errorNodes[i].innerText = "";
    }
    success.innerText = "";
    nameInput1.classList.remove("error-border");
    email.classList.remove("error-border");
    message.classList.remove("error-border");
}

//check if email is valid
function emailIsValid(email){
    let pattern = /\S+@\S+\.\S+/;
    return pattern.test(email);
}