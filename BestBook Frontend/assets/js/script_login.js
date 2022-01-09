//Get data from server


//Get data
const email = document.getElementById("userEmail");
const password = document.getElementById("userPassword");
const errorNodes = document.getElementsByClassName("error");

//Validate data
function validateForm(){

    clearMessages();
    let errorFlag = false;

    if(!emailIsValid(email.value)){
        errorNodes[0].innerText = "Invalid email address";
        email.classList.add("error-border");
        errorFlag = true;
    }

    if(password.value.length < 1){
        errorNodes[1].innerText = "Please enter password";
        email.classList.add("error-border");
        errorFlag = true;
    }

    if(!errorFlag){
        success.innerText = "You're successfully logged in!";
    }
}

// Clear error / success messages
function clearMessages(){
    for(let i = 0; i < errorNodes.length; i++){
        errorNodes[i].innerText = "";
    }
    success.innerText = "";
    email.classList.remove("error-border");
    password.classList.remove("error-border");
}

//check if email is valid
function emailIsValid(email){
    let pattern = /\S+@\S+\.\S+/;
    return pattern.test(email);
}