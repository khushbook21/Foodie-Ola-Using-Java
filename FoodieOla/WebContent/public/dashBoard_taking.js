var JsonFromServlet;
var JsonFromServlet2;
    var toBeHidden;
  
  var JsonForAccept={};
  
  sessionCheck()
  myTaking();;
 setTimeout(otp,500);
 setTimeout(loadUserName,600);
 



 var sessionUser=null;     

function sessionCheck(){
 sessionUser=localStorage.getItem("username");
if(sessionUser==null)
{
    console.log(sessionUser);
    window.location.href="index.html";
    return false;
}
}

  function otp(){
      webData2();
      setTimeout(displayResult2,3000);
  }
  
  function myTaking(){  
    webData();
    setTimeout(displayResult,3000);
  
  }

  function webData2() {
    $.ajax({
        method: 'POST'
        ,data:"myOTP"
        , url: 'SessionServlet'
        , dataType: 'json'
    }).done(output2)
    .fail(function () {
        console.log('Error!!');

    })
  }
  
        function webData() {
          $.ajax({
              method: 'POST'
              ,data:"myTaking"
              , url: 'SessionServlet'
              , dataType: 'json'
          }).done(output)
          .fail(function () {
              console.log('Error!!');

          })
        }

        function output2(data) {       
            console.log(data); 
            JsonFromServlet2=data;      
          }
        function output(data) {       
          console.log(data); 
          JsonFromServlet=data;      
        }
        var HTMLdata="";
        var HTMLdata2="";
 
       function displayResult2(){
            for(a in JsonFromServlet2)
            {
              HTMLdata2+='<li class="item"><div class="product-info"><a href="javascript:void(0)" class="product-title"  onclick="showDetail()">'
              HTMLdata2+=JsonFromServlet2[a].OTP+'<span class="badge badge-warning float-right">'+JsonFromServlet2[a].created_At+'</span></a><span class="product-description">'
              HTMLdata2+=JsonFromServlet2[a].giver+' thane</span></div></li>'
       
          }
                console.log(HTMLdata2);
               $("#displayData2").html(HTMLdata2);
          }
          
        function displayResult(){
          for(a in JsonFromServlet)
          {
             
            HTMLdata+='<li class="item"><div class="product-info">';
              HTMLdata+='<a href="javascript:void(0)" id="mod'+JsonFromServlet[a].id+'" class="product-title" data-toggle="modal" data-target="#modal-default" onclick="showDetail(this.id)">'+JsonFromServlet[a].username+' | '+JsonFromServlet[a].noOfPlate+' | '+JsonFromServlet[a].foodType;
              HTMLdata+='<span class="badge badge-warning float-right">'+JsonFromServlet[a].cretedAt+'</span></a><span class="product-description addaddress">'
              HTMLdata+=JsonFromServlet[a].pickingType+', Reti bandar,Mumbra, Thane </span></div>  </li>'      
          
        }
              console.log(HTMLdata);
             $("#displayData").html(HTMLdata);
        }
  
      
  
  function showDetail(transferId){
    var postIdCleaned = transferId.replace('mod','');
    url = 'http://localhost:8080/FoodieOla/dashBoard_taking_client.html?name=' + encodeURIComponent(postIdCleaned);
    document.location.href = url
  }
  
  
  function logOut(){
     localStorage.removeItem("username");
     window.location.href="index.html";
	 return false;

}

function loadUserName(){
document.querySelector("body > div > aside > div > div.os-padding > div > div > div > div > a").innerText=localStorage.getItem("username");
}