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
    
}

function changeTuto(value){
    switch(value){
        case("next"):
            if (index < divTuto.length-1){
                divTuto[index].style.display="none"
                index++;
                divTuto[index].style.display="block";
            }
            console.log(index);
            break;
        case("preveous"):
            if (index > 0){
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
        window.location.href="choose_your_account.html"
    } else if (value && index != (divTuto.length-1)){
        return;
    } else if (!value){
        window.location.href="choose_your_account.html"
    }
}