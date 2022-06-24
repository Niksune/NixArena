let divTuto = document.getElementsByClassName('tutorial[]');
let buttonPlay = document.getElementById('play');
let buttonSkip = document.getElementById('Skip');
let index = 0;

window.onload = ()=>{
    for(let i=0; i < divTuto.length;i++){
        console.log(divTuto[i])
        divTuto[i].style.display="none"
    };

    divTuto[index].style.display="block";
    buttonPlay.style.opacity="0.2";
    
}

function changeTuto(value){
    switch(value){
        case("next"):
            if (index < divTuto.length-1){
                divTuto[index].style.display="none"
                index++;
                divTuto[index].style.display="block";
            }
            if(index == divTuto.length-1){
                buttonPlay.style.opacity="1";
            }
            console.log(index);
            break;
        case("preveous"):
            if (index > 0){
                buttonPlay.style.opacity="0.2";
                divTuto[index].style.display="none"
                index--;
                divTuto[index].style.display="block";
            }
            console.log(index);
            break;
    }
}

function play(value=Boolean){
    if (value && index == (divTuto.length-1)){
        window.location.href="manage_account.html"
    } else if (value && index != (divTuto.length-1)){
        return;
    } else if (!value){
        window.location.href="manage_account.html"
    }
}