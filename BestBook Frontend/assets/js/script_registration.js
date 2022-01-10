//Get data
let nameInput = "";
let email ="";
let password ="";
let errorNodes ="";

//document.getElementById("signupButton").addEventListener("click", fuction(){register(nameInput, email, password, errorNodes)});
document.getElementById("signupButton").addEventListener("click", function(){ register(nameInput, email, password, errorNodes); });

function register(nameInput, email, password, errorNodes){
    nameInput = document.getElementById("userName").value;
    email = document.getElementById("userEmail").value;
    password = document.getElementById("userPassword").value;
    errorNodes = document.getElementsByClassName("error");

    let user = {
    "nameInput": nameInput,
    "email": email,
    "password": password,
    };

    const userJson = JSON.stringify(user);

    console.log(userJson);
    validateForm(nameInput, email, password, errorNodes);
    clearMessages(email, password, errorNodes);
    emailIsValid(email);

}

//Validate data
function validateForm(nameInput, email, password, errorNodes){

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
function clearMessages(email, password, errorNodes){
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