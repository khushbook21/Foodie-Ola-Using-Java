var JsonFromServlet;
var lat;
var long;
var final_position;
var address;
var toBeHidden;
  
var ration=0;
var veg=0;
var any=0;

var JsonForAccept={};

var boundingBox={
    "longitudeUp":null,
    "longitudeDown":null,
    "latitudeUp":null,
    "latitudeDown": null
}


sessionCheck();
setTimeout(loadUserName,600);

locationdata();
setTimeout(needAroundYou,1000);



function locationdata(){
    getlocation();
    // setTimeout(getaddress,50);
 }



  // function getaddress(){
  //     const KEY = "AIzaSyCmw-kOQSoWLjvSNgZUzlnuc4Znr969Mx8";
  //     const LAT = 19.19069;
  //     const LNG = 73.01234;
  //     let url = `https://maps.googleapis.com/maps/api/geocode/json?latlng=${LAT},${LNG}&key=${KEY}`;
  //     var xhttp = new XMLHttpRequest();
  //     xhttp.onreadystatechange = function() {
  //       if (this.readyState == 4 && this.status == 200) {
  //         address = this.responseText;
  //         console.log(this.responseText)
  //       }
  //     };
  //     xhttp.open("GET", url, false);
  //     xhttp.send();
  //     console.log(address)

  //  }     

    function getlocation(){
  
        if (navigator.geolocation) { //check if geolocation is available
          navigator.geolocation.getCurrentPosition(function(position){
              console.log(position);
            final_position=position;
            lat=position.coords.latitude;
            long=position.coords.longitude;
          })} 
      }
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


function needAroundYou(){

    boundingBox.latitudeUp=(lat+0.5).toString();
    boundingBox.latitudeDown=(lat-0.5).toString();
    boundingBox.longitudeUp=(long+0.5).toString();
    boundingBox.longitudeDown=(long-0.5).toString();
    jsonString=JSON.stringify(boundingBox);
    console.log(jsonString);
    
    webData(jsonString);

}

      function webData(jsonString) {
        $.ajax({
            method: 'POST'
            ,data:"needAroundYou="+jsonString
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
        displayResult()     
      }
      var HTMLdata="";
  
      function displayResult(){
        for(a in JsonFromServlet)
        {
          // getaddress(parseFloat(JsonFromServlet[a].latitude),parseFloat(JsonFromServlet[a].longitude));
          HTMLdata+='<li class="item"><div class="product-info">'
          HTMLdata+='<a href="javascript:void(0)" id="mod'+JsonFromServlet[a].id+'" class="product-title" data-toggle="modal" data-target="#modal-default" onclick="showDetail(this.id)">'+JsonFromServlet[a].username+' | '+JsonFromServlet[a].noOfPlate+' | '+JsonFromServlet[a].foodType
          HTMLdata+='<span class="badge badge-warning float-right">'+JsonFromServlet[a].cretedAt+'</span></a><span class="product-description addaddress">'
          HTMLdata+=JsonFromServlet[a].pickingType+', Reti bandar,Mumbra, Thane </span></div>  </li>'
            if(JsonFromServlet[a].foodType=="Any"){ 
            any+=1;
            }
            else if(JsonFromServlet[a].foodType=="Ration"){
            ration+=1;
            }
            else if(JsonFromServlet[a].foodType=="Veg"){
            veg+=1;
            }
            
            }
            console.log(HTMLdata);
           $("#displayData").html(HTMLdata);
           
           $("#any").html(any);
           $("#ration").html(ration);
           $("#veg").html(veg);
           
           
      }
 
  function showDetail(postIdOnClick){
        toBeHidden=postIdOnClick;
  
  var postIdCleaned = postIdOnClick.replace('mod','');
  for(a in JsonFromServlet)
    {
    if(JsonFromServlet[a].id==postIdCleaned){
      console.log("in matched "+postIdCleaned+" "+JsonFromServlet[a].id);
      document.getElementById("postInfo").innerHTML='Number of people to eat: '+JsonFromServlet[a].noOfPlate+'<br> Food type: '+JsonFromServlet[a].foodType+'<br> Mode:'+ JsonFromServlet[a].pickingType;
      document.getElementById("locationData").innerHTML="Reti Bandar, Mumbra, Thane";
      document.getElementById("phone").innerHTML=JsonFromServlet[a].contact;
      idForAcceptButton="but"+postIdCleaned;
      document.getElementById("username").innerHTML=JsonFromServlet[a].username;
      $(".acceptButton").attr("id",idForAcceptButton);
    }
    
  }
}   

function acceptRequest(postIdOnClickAccept){
  var postIdCleanedAccept = postIdOnClickAccept.replace('but','');
  JsonForAccept.postIdCleanedAccept=parseInt(postIdCleanedAccept);
  jsonString=JSON.stringify(JsonForAccept);
    console.log(jsonString);
    ajaxForAccepting(jsonString);
}

function ajaxForAccepting(jsonString){
  $.ajax({
    method: 'POST'
    ,data:"acceptThePost="+jsonString
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
    classToHide="hideTheElement";
    
    if(data)
    {
    ToBeHidden.parentElement.parentElement.classList.add(classToHide);  
    alert('Request Is pending to Accept');  
    }
    else{
    
    alert('You cannot accept this request');
    }
       
}

function logOut() {
     localStorage.removeItem("username");
     window.location.href="index.html";
	 return false;

}

function loadUserName(){
document.querySelector("body > div > aside > div > div.os-padding > div > div > div > div > a").innerText=localStorage.getItem("username");
}