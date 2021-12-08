var JsonFromServlet={};
  
  var JsonForAccept={};
   var JsonForOtp={};
    var toBeHidden;
    
    
  sessionCheck()
  myGiving();
  setTimeout(loadUserName,600);
  
  
  
  var sessionUser=localStorage.getItem("username");;     

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
  
  function myGiving(){  
    webData();
    setTimeout(displayResult,1000);
  
  }
  
        function webData() {
          $.ajax({
              method: 'POST'
              ,data:"myGiving"
              , url: 'SessionServlet'
              , dataType: 'json'
          }).done(output)
          .fail(function () {
              console.log('Error!!');

          })
        }
      
        function output(data) {       
          console.log(data); 
          JsonFromServlet=data;      
        }
        var HTMLdata="";
        var HTMLdata2="";
 
        function displayResult(){
          for(a in JsonFromServlet)
          {
              if(JsonFromServlet[a].taker_accept==0){
            HTMLdata+='<li class="item"><div class="product-info">'
            HTMLdata+='<a href="javascript:void(0)" id="mod'+JsonFromServlet[a].id+'" class="product-title" data-toggle="modal" data-target="#modal-default" onclick="showDetail(this.id)">'+JsonFromServlet[a].taker+' | '+JsonFromServlet[a].plate+' | '+JsonFromServlet[a].foodType
            HTMLdata+='<span class="badge badge-warning float-right">'+JsonFromServlet[a].created_at+'</span></a><span class="product-description addaddress">'
            HTMLdata+=JsonFromServlet[a].pick+', Reti bandar,Mumbra, Thane </span></div>  </li>'
        }
        else{
            HTMLdata2+='<li class="item"><div class="product-info">'
            HTMLdata2+='<a href="javascript:void(0)" id="mod'+JsonFromServlet[a].id+'" class="product-title" data-toggle="modal" data-target="#modal-default2" onclick="showDetail2(this.id)">'+JsonFromServlet[a].taker+' | '+JsonFromServlet[a].plate+' | '+JsonFromServlet[a].foodType
            HTMLdata2+='<span class="badge badge-warning float-right">'+JsonFromServlet[a].created_at+'</span></a><span class="product-description addaddress">'
            HTMLdata2+=JsonFromServlet[a].pickingType+', Reti bandar,Mumbra, Thane </span></div>  </li>'
        }
        
        }
              console.log(HTMLdata);
             $("#displayData").html(HTMLdata);
             $("#displayData2").html(HTMLdata2);
        }
   
    function showDetail(postIdOnClick){
          toBeHidden=postIdOnClick;
    
    var postIdCleaned = postIdOnClick.replace('mod','');
    for(a in JsonFromServlet)
      {
      if(JsonFromServlet[a].id==postIdCleaned){
        console.log("in matched "+postIdCleaned+" "+JsonFromServlet[a].id);
        document.getElementById("postInfo").innerHTML='Number of people to eat: '+JsonFromServlet[a].plate+'<br> Food type: '+JsonFromServlet[a].foodType+'<br> Mode:'+ JsonFromServlet[a].pickingType;
        document.getElementById("locationData").innerHTML="Reti Bandar, Mumbra, Thane";
        document.getElementById("phone").innerHTML=JsonFromServlet[a].contact;
        idForAcceptButton="but"+postIdCleaned;
        document.getElementById("username").innerHTML=JsonFromServlet[a].taker;
        console.log("idForAcceptButton"+idForAcceptButton)
        $(".declineButton").attr("id",idForAcceptButton);
      }
      
    }
  }   
  
  function declineRequest(postIdOnClickAccept){
    var postIdCleanedAccept = postIdOnClickAccept.replace('but','');
    JsonForAccept.postIdCleanedAccept=parseInt(postIdCleanedAccept);
    jsonString=JSON.stringify(JsonForAccept);
      console.log(jsonString);
      ajaxForAccepting(jsonString);
  }
  
  function ajaxForAccepting(jsonString){
    $.ajax({
      method: 'POST'
      ,data:"deleteRelation="+jsonString
      , url: 'SessionServlet'
      , dataType: 'json'
  }).done(acknowledge)
  .fail(function () {
      console.log('Error!!');
  })
  }
  function acknowledge(data) {       
    console.log(data);
     ToBeHidden=document.getElementById(toBeHidden);
    classToHide="hideTheElement"    
    ToBeHidden.parentElement.parentElement.classList.add(classToHide);  
    alert('Request Is Deleted');     
  }
  
  
  function showDetail2(postIdOnClick){
        toBeHidden=postIdOnClick;
    console.log("In show detail2")
  var postIdCleaned = postIdOnClick.replace('mod','');
  for(a in JsonFromServlet)
    {
    if(JsonFromServlet[a].id==postIdCleaned){
      console.log("in matched "+postIdCleaned+" "+JsonFromServlet[a].id);
      idForAcceptButton="but"+postIdCleaned;
      console.log("idForAcceptButton"+idForAcceptButton)
      $(".otpButton").attr("id",idForAcceptButton);
    }
    
  }
}   
  
  
  function otpReqest(postIdOnClickAccept){
    var postIdCleanedAccept = postIdOnClickAccept.replace('but','');

    var otpEntered=$("#otpField").val();
    if(otpEntered!=0){
      JsonForOtp.postIdCleanedDecline=parseInt(postIdCleanedAccept);
      JsonForOtp.otpEntered=parseInt(otpEntered);
    jsonString=JSON.stringify(JsonForOtp);
      console.log(jsonString);
      ajaxForOtp(jsonString);
    }
    else{
      alert("Enter the OTP");
    }
    
  }
  function ajaxForOtp(jsonString){
    $.ajax({
      method: 'POST'
      ,data:"OtpThPost="+jsonString
      , url: 'SessionServlet'
      , dataType: 'json'
  }).done(acknowledge2)
  .fail(function () {
      console.log('Error!!');
  })
  }
  function acknowledge2(data) {       
    console.log(data); 
     ToBeHidden=document.getElementById(toBeHidden);
    classToHide="hideTheElement"    
    
    
    if(data){
    alert('Work Completed'); 
    ToBeHidden.parentElement.parentElement.classList.add(classToHide);     
    }
    else{
    alert("Enter correct OTP");
    }
    
  }
  
  function logOut(){
     localStorage.removeItem("username");
     window.location.href="index.html";
	 return false;

}

function loadUserName(){
document.querySelector("body > div > aside > div > div.os-padding > div > div > div > div > a").innerText=localStorage.getItem("username");
}