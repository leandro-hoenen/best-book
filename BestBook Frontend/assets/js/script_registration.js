debugger;
//Get data
const nameInput = document.getElementById("userName").value;
const email = document.getElementById("userEmail").value;
const password = document.getElementById("userPassword").value;
const errorNodes = document.getElementsByClassName("error");

//Data for server
const user = {
    "nameInput": nameInput,
    "email": email,
    "password": password
};

const userJson = JSON.stringify(user);

console.log(userJson);

function register(){
    nameInput = document.getElementById("userName").value;
    email = document.getElementById("userEmail").value;
    password = document.getElementById("userPassword").value;
    errorNodes = document.getElementsByClassName("error");

    const user = {
        "nameInput": nameInput,
        "email": email,
        "password": password
    };

    const userJson = JSON.stringify(user);

    console.log(userJson);
}

//Validate data
function validateForm(){

    clearMessages();
    let errorFlag = false;

    if(nameInput.value.length < 1){
        errorNodes[0].innerText = "Name cannot be blank";
        nameInput.classList.add("error-border");
        errorFlag = true;
    }

    if(!emailIsValid(email.value)){
        errorNodes[1].innerText = "Invalid email address";
        email.classList.add("error-border");
        errorFlag = true;
    }

    if(password.value.length < 1){
        errorNodes[2].innerText = "Please enter password";
        email.classList.add("error-border");
        errorFlag = true;
    }

    if(!errorFlag){
        success.innerText = "You have successfully registered!";
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