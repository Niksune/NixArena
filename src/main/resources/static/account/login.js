document.getElementById('login-login').onkeyup = function(e){
    console.log('login')
    if(e.keyCode == 13){
        console.log('post')
        let values = { name : $("#login-login").val(), password : $("#password-login").val() };
        console.log(values);
        $.ajax({
            type: "POST",
            headers: { 'Content-Type': 'application/json' },
            url: "../API/accounts/connection",
            data: JSON.stringify(values),
            success: function (result) {
            if(result === "00000000-0000-0000-0000-000000000000"){
                alert("Wrong identifiers");
            } else {
                if (localStorage.getItem("ID")==null || localStorage.getItem("ID")==""){
                    localStorage.setItem("ID",result);
                    document.location.href="../actual-game/manage_account.html?IDaccount="+result;
                }
            }},
        });
    }};
  document.getElementById('password-login').onkeyup = function(e){
    console.log('password')
    if(e.keyCode == 13){
        console.log('post')
        let values = { name : $("#login-login").val(), password : $("#password-login").val() };
        console.log(values);
        $.ajax({
            type: "POST",
            headers: { 'Content-Type': 'application/json' },
            url: "../API/accounts/connection",
            data: JSON.stringify(values),
            success: function (result) {
            if(result === "00000000-0000-0000-0000-000000000000"){
                alert("Wrong identifiers");
            } else {
                if (localStorage.getItem("ID")==null || localStorage.getItem("ID")==""){
                    localStorage.setItem("ID",result);
                    document.location.href="../actual-game/manage_account.html?IDaccount="+result;
                }
            }},
        });
    }};


  $("#submit-login").click(function(){
    console.log('post')
    let values = { name : $("#login-login").val(), password : $("#password-login").val() };
    console.log(values);
    $.ajax({
        type: "POST",
        headers: { 'Content-Type': 'application/json' },
        url: "../API/accounts/connection",
        data: JSON.stringify(values),
        success: function (result) {
        if(result === "00000000-0000-0000-0000-000000000000"){
            alert("Wrong identifiers");
        } else {
            if (localStorage.getItem("ID")==null || localStorage.getItem("ID")==""){
                localStorage.setItem("ID",result);
                document.location.href="../actual-game/manage_account.html?IDaccount="+result;
            }
        }},
    });
  });