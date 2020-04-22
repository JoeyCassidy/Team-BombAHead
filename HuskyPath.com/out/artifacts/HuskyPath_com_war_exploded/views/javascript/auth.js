//necessary configuration details ***DONT CHANGE***
var firebaseConfig = {
    apiKey: "AIzaSyDB5SGi03D_BSH5d9gHG7cWr2GQ8VakDRg",
    authDomain: "huskypath.firebaseapp.com",
    databaseURL: "https://huskypath.firebaseio.com",
    projectId: "huskypath",
    storageBucket: "huskypath.appspot.com",
    messagingSenderId: "16768770059",
    appId: "1:16768770059:web:4c333d399489f7c7e6e70e"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const auth = firebase.auth();

//sign up
function signUp(){
    const tempEmail = document.getElementById("emailInput-signUp");
    const tempPassword = document.getElementById("passwordInput-signUp");
    const email = tempEmail.value;
    const password = tempPassword.value;
    console.log(email, password);

    //sign up the user
    auth.createUserWithEmailAndPassword(email, password).then(cred => {
        console.log(cred.user);
        //form reset
        clearForm("signUpForm");
        location.href='/profile';
    })

}
//sign in
function signIn(){
    const tempEmail = document.getElementById("emailInput-signIn");
    const tempPassword = document.getElementById("passwordInput-signIn");
    const email = tempEmail.value;
    const password = tempPassword.value;

    //sign in the user
    auth.signInWithEmailAndPassword(email, password).then(cred => {
        console.log(cred.user);
        //form reset
        clearForm("signInForm");
        Location.href='/profile';
    })
}
//sign out
function signOut(){
    //sign out the user
    auth.signOut().then(() => {
        console.log('signed out');
    });
}
//clear form with id tag 'id'
function clearForm(id){
    document.getElementById(id).reset();
}