class character{
    currentHP = 0;
    maxHP = 0;
    constructor(NAME=String,LEVEL=Number,WEAPON=String,ID=Number,attacker=Boolean,CHARAC=String){
        this.NAME = NAME;
        this.LEVEL = LEVEL;
        this.currentHP = LEVEL * 100;
        this.maxHP = LEVEL * 100;
        this.WEAPON = WEAPON;
        this.ID = ID;
        this.attacker = attacker;
        this.CHARAC = CHARAC;
    }
    
    get currentHP(){return this.currentHP;}
    set currentHP(value){return this.currentHP = value;}

    get weapon(){return this.WEAPON;}
    set weapon(value){return this.WEAPON = value;}

    get id(){return this.ID;}
    set id(value){return this.ID;}

    get level(){return this.LEVEL;}
    
    get maxHP(){return this.maxHP;}
    get isAttacker(){return this.attacker;}

    get img(){return document.getElementById(this.CHARAC+"_img")};
    get labelName(){return document.getElementById(this.CHARAC+"_name")};
    get barHP(){return document.getElementById(this.CHARAC+"_HP")};
    get labelATK(){return document.getElementById(this.CHARAC+"_atk")};
    get labelWeapon(){return document.getElementById(this.CHARAC+"_weapon")}
}

let fightingReport;
let indexFight;
let player;
let enemy;
let seconds = 1000;
let sounds =true;
var audio=new Audio();


function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function battle(attacker=character,defender=character,historyAttack){
    changeSpeed(sessionStorage.getItem('speedGame'));
    
    attacker.img.style.filter="saturate(100%) blur(0px) brightness(1) contrast(100%)"
    defender.img.style.filter="saturate(100%) blur(0px) brightness(1) contrast(100%)"

    for(let atk = 0; atk < historyAttack.length;atk++){

        defender.labelATK.innerHTML = ("-"+historyAttack[atk]);
        defender.labelATK.style.scale = 1.1;
        defender.labelATK.style.filter="opacity(1)";
        soundMelee();

        defender.currentHP -= parseInt(historyAttack[atk]);
        if (defender.currentHP < 1){
            defender.img.style.transform="rotate(360deg)";
            defender.img.style.filter= "grayscale(200%)";
            defender.img.style.scale=0.8;
            attacker.img.style.scale=1;

            defender.barHP.innerHTML= "0/"+defender.maxHP+" <strong>HP</strong>";
            alert(attacker.NAME+" is winner");
            break;
        }
        
        defender.img.style.scale=0.8;
        attacker.img.style.transform="rotate(360deg)";
        attacker.img.style.scale=1.2;
        defender.img.style.filter="saturate(300%) blur(2px) brightness(1.25) contrast(60%)"
        
        defender.barHP.innerHTML= defender.currentHP+"/"+defender.maxHP+" <strong>HP</strong>";
        await sleep(seconds);
        defender.labelATK.style.scale = 1;
        defender.labelATK.style.filter="opacity(0)";
        
        attacker.img.style.transform="rotate(0deg)";
        defender.img.style.filter="saturate(100%) blur(0px) brightness(1) contrast(100%)"
        defender.img.style.scale=1;
        
        defender.labelATK.innerHTML = ""
        atk++;

        attacker.labelATK.innerHTML = "-"+ historyAttack[atk];
        attacker.labelATK.style.scale = 1.1;
        attacker.labelATK.style.filter="opacity(1)";
        soundMelee();

        attacker.currentHP -= historyAttack[atk];
        if (attacker.currentHP < 1){
            attacker.img.style.transform="rotate(360deg)";
            attacker.img.style.filter= "grayscale(200%)";
            attacker.img.style.scale=0.8;
            defender.img.style.scale=1;

            attacker.barHP.innerHTML= "0/"+attacker.maxHP+" <strong>HP</strong>";
            alert(defender.NAME+" is winner");
            break;
        }
        attacker.img.style.scale=0.8;
        defender.img.style.scale=1.2;
        defender.img.style.transform="rotate(360deg)";
        attacker.img.style.filter="saturate(300%) blur(2px) brightness(1.25) contrast(60%)"

        attacker.barHP.innerHTML=attacker.currentHP+"/"+attacker.maxHP+" <strong>HP</strong>";

        await sleep(seconds);
        attacker.labelATK.style.scale = 1;
        attacker.labelATK.style.filter="opacity(0)";

        defender.img.style.transform="rotate(0deg)";
        attacker.img.style.filter="saturate(100%) blur(0px) brightness(1) contrast(100%)"
        defender.img.style.scale=1;
        attacker.labelATK.innerHTML = ""
    }
    attacker.labelATK.innerHTML = ""
    defender.labelATK.innerHTML = ""
} 

window.onload = () => {
    const urlParams = new URLSearchParams(window.location.search);
    if (!urlParams.has("IDaccount")) {
    window.location.href = "/index.html";
    }
    if (!urlParams.has("IDcharacter")) {
    window.location.href = "/index.html";
    }

    const IDAccount = urlParams.get("IDaccount");
    const IDCharacter = urlParams.get("IDcharacter");
    let requestURL = ('../API/charac/'+IDCharacter+"/fightingreports");
    
    changeSpeed(sessionStorage.getItem('speedGame'));

    //get data of character's Accounts and return the resume.
    
    $.get(requestURL, (data) => {
        fightingReport = data;
        if (data['fightingReports'].length < 1 || data==null){
            document.getElementById('messageTextSpecial').style.display="block";
            document.getElementById('messageTextSpecial').innerText = ("No fight.");
            document.getElementById('play-fight').style.display="none";
        } else{
            indexFight = data['fightingReports'].length - 1;
            changeFight();
        }

        
    });
    
}

function play() {
    initialBattle();
    let resume = fightingReport['fightingReports'][indexFight]['attacks'];
    document.getElementById('button_play').innerHTML="<h2>Replay</h2>";

    if (player.isAttacker){
        battle(player,enemy,resume)
    } else {
        battle(enemy,player,resume)
    }
    
}

function skip(){
    seconds = 0;
}

function changeSpeed(speed=String){
    document.getElementsByName('speed_battle[]').forEach(button => {
        console.log(button);
        button.style.border=("4px solid #06565D")
    });

    switch (speed){
        case('low'):
            document.getElementsByName('speed_battle[]').forEach(button => {
                if (button.value === 'low'){
                    button.style.border=("4px solid #91f799")
                }
            });
            seconds=1000;
            sessionStorage.removeItem('speedGame');
            sessionStorage.setItem('speedGame',speed);
            break;
        case('normal'):
            document.getElementsByName('speed_battle[]').forEach(button => {
                if (button.value === 'normal'){
                    button.style.border=("4px solid #91f799")
                }
            });
            sessionStorage.removeItem('speedGame');
            seconds=500;
            sessionStorage.setItem('speedGame',speed);
            break;
        case('fast'):
            document.getElementsByName('speed_battle[]').forEach(button => {
                if (button.value === 'fast'){
                    button.style.border=("4px solid #91f799")
                }
            });
            sessionStorage.removeItem('speedGame');
            seconds=250;
            sessionStorage.setItem('speedGame',speed);
            break;
        default:
            document.getElementsByName('speed_battle[]').forEach(button => {
                if (button.value === 'low'){
                    button.style.border=("4px solid #91f799")
                }
            });
            seconds=1000;
            sessionStorage.removeItem('speedGame');
            sessionStorage.setItem('speedGame',speed);
            break;
    }
}

function initialBattle(){
    let resume = fightingReport['fightingReports'][indexFight];
    console.log(resume);

    switch (resume['specialText']){
        case("Alone"):
            document.getElementById('messageTextSpecial').style.display="block";
            document.getElementById('messageTextSpecial').innerText = ("No luck, your character was alone. A consolation weapon were granted to you.");
            document.getElementById('play-fight').style.display="none";
            break;
        case("OverLevel"):
            document.getElementById('messageTextSpecial').style.display="block";
            document.getElementById('messageTextSpecial').innerText = ("Your character outleveled his opponent, he didn't gained a level but got a chance to get an overpowered weapon");
            break;
        default:
            document.getElementById('play-fight').style.display="block";
            document.getElementById('messageTextSpecial').style.display="none";
    };

    if (resume['charac1ID'] == fightingReport['id']){
        player = new character(resume['charac1Name'],resume['charac1Level'],resume['weaponCharac1Name'],resume['charac1ID'],true,"character");
        enemy = new character(resume['charac2Name'],resume['charac2Level'],resume['weaponCharac2Name'],resume['charac2ID'],false,"enemy");
    } else {
        player = new character(resume['charac2Name'],resume['charac2Level'],resume['weaponCharac2Name'],resume['charac2ID'],false,"character");
        enemy = new character(resume['charac1Name'],resume['charac1Level'],resume['weaponCharac1Name'],resume['charac1ID'],true,"enemy");
    }

    player.labelName.innerHTML= (player.NAME + " | <strong> lvl: </strong>" + player.LEVEL);
    enemy.labelName.innerHTML = (enemy.NAME + " | <strong>Lvl: </strong>" + enemy.LEVEL);
    
    player.barHP.innerHTML = (player.maxHP + "/" + player.maxHP + " <strong>HP</strong>");
    enemy.barHP.innerHTML = (enemy.maxHP + "/" + enemy.maxHP + " <strong>HP</strong>");
    
    player.labelWeapon.innerHTML = "<strong>weapon: </strong>" + player.WEAPON;
    enemy.labelWeapon.innerHTML = "<strong>weapon: </strong>" + enemy.WEAPON;
}

function changeFight(value){
    switch(value){
        case('next'):
            indexFight++;
            if (indexFight > fightingReport['fightingReports'].length - 1){
                indexFight--;
                alert("Il n'y a pas d'autres matchs");
                break;
            }
            break;
        case('previous'):
            indexFight--;
            if (indexFight < 0){
                indexFight++;
                alert("Il n'y a pas d'autres matchs");
                break;
            }
            break;
    }
    initialBattle();
    document.getElementById('labelFight').innerText= ((indexFight+1)+"/"+ (fightingReport['fightingReports'].length));
}

function soundMelee(){
    if (sounds){
        let sounds =[];
        sounds.push('assets/sound/animal melee sound.wav');
        sounds.push('assets/sound/melee sound.wav');
        sounds.push('assets/sound/sword sound.wav');
    
        const random = Math.floor(Math.random() * sounds.length);
        console.log(random, sounds[random]);
        audio = new Audio(sounds[random]);
        audio.volume = document.getElementById('volume-control').value/100;
        audio.play();
        console.log(audio.volume)
    }
}

function withSound(){
    if (sounds){
        sounds=false;
        document.getElementById('button_sound').innerHTML="<h2>🔇</h2>";
    } else {
        sounds=true;
        document.getElementById('button_sound').innerHTML="<h2>🔊</h2>";
    }
}

function disconnect(){
    localStorage.removeItem("ID");
  }

function goAccount(){
    if (localStorage.getItem("ID")!= null || localStorage.getItem("ID") != ""){
        window.location.href="/actual-game/manage_account.html?IDaccount="+localStorage.getItem("ID")
        print("/actual-game/manage_account.html?IDaccount="+localStorage.getItem("ID"))
    } else {
        window.location.href="../index.html"
    }
}