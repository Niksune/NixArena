document.getElementById('name-register').onkeyup = function(e){
    if(e.keyCode == 13){
      if($("#name-register").val()==null || $("#mail-register").val()==null || $("#password-register").val()==null){
        alert("complete the form");
      } else{let values = { name : $("#name-register").val(), mail : $("#mail-register").val(), password : $("#password-register").val() };
      console.log(values);
      $.ajax({
        type: "POST",
        headers: { 'Content-Type': 'application/json' },
        url: "../API/accounts",
        data: JSON.stringify(values),
        success: function (result) {
          if(result === "AccountNameAlreadyExist")
            alert("Account already exists");
          else{
            //alert("Account "+$("#name").val()+" Created !");
            localStorage.setItem("ID",result);
            document.location.href="../actual-game/tutorial.html?IDaccount="+result;
          }
        },
      }); }
        
    }}
document.getElementById('mail-register').onkeyup = function(e){
    if(e.keyCode == 13){
      if($("#name-register").val()==null || $("#mail-register").val()==null || $("#password-register").val()==null){
        alert("complete the form");
      } else{let values = { name : $("#name-register").val(), mail : $("#mail-register").val(), password : $("#password-register").val() };
      console.log(values);
      $.ajax({
        type: "POST",
        headers: { 'Content-Type': 'application/json' },
        url: "../API/accounts",
        data: JSON.stringify(values),
        success: function (result) {
          if(result === "AccountNameAlreadyExist")
            alert("Account already exists");
          else{
            //alert("Account "+$("#name").val()+" Created !");
            localStorage.setItem("ID",result);
            document.location.href="../actual-game/tutorial.html?IDaccount="+result;
          }
        },
      }); }
        
    }}
document.getElementById('password-register').onkeyup = function(e){
    if(e.keyCode == 13){
      if($("#name-register").val()==null || $("#mail-register").val()==null || $("#password-register").val()==null){
        alert("complete the form");
      } else{let values = { name : $("#name-register").val(), mail : $("#mail-register").val(), password : $("#password-register").val() };
      console.log(values);
      $.ajax({
        type: "POST",
        headers: { 'Content-Type': 'application/json' },
        url: "../API/accounts",
        data: JSON.stringify(values),
        success: function (result) {
          if(result === "AccountNameAlreadyExist")
            alert("Account already exists");
          else{
            //alert("Account "+$("#name").val()+" Created !");
            localStorage.setItem("ID",result);
            document.location.href="../actual-game/tutorial.html?IDaccount="+result;
          }
        },
      }); }
        
    }}

  $("#submit-register").click(function (){
    if($("#name-register").val()==null || $("#mail-register").val()==null || $("#password-register").val()==null){
      alert("complete the form");
    } else{let values = { name : $("#name-register").val(), mail : $("#mail-register").val(), password : $("#password-register").val() };
    console.log(values);
    $.ajax({
      type: "POST",
      headers: { 'Content-Type': 'application/json' },
      url: "../API/accounts",
      data: JSON.stringify(values),
      success: function (result) {
        if(result === "AccountNameAlreadyExist")
          alert("Account already exists");
        else{
          //alert("Account "+$("#name").val()+" Created !");
          localStorage.setItem("ID",result);
          document.location.href="../actual-game/tutorial.html?IDaccount="+result;
        }
      },
    }); }
      
  });