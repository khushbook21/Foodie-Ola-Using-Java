var JsonFromServlet;
var postId;
var JsonforApprove={} 
  var toBeHidden;

function url() {
    var url = document.location.href,
        params = url.split('?')[1].split('&'),
        data = {}, tmp;
    for (var i = 0, l = params.length; i < l; i++) {
         tmp = params[i].split('=');
         data[tmp[0]] = tmp[1];
    }
    postId = data.name;
    console.log(postId);
    
}
sessionCheck();
  url();
  
 setTimeout( myPostInfo, 50);
 setTimeout(loadUserName,600);
 
  
  function myPostInfo(){  
    webData();
      console.log(postId);
  
  }

        function webData() {
          $.ajax({
              method: 'POST'
              ,data:"myPostInfo="+postId
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
          displayPostInfo();
          displayAcceptorInfo();     
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
        var HTMLdata="";
        var a;
          function displayAcceptorInfo(){
            for(a in JsonFromServlet)
            {
               
                HTMLdata+='<li class="item"><div class="product-info">';
                HTMLdata+='<a href="javascript:void(0)" id="'+JsonFromServlet[a].g_name+'12" class="product-title" data-toggle="modal" data-target="#modal-default" onclick="showDetail(this.id)">'+JsonFromServlet[a].g_name
                HTMLdata+='<span class="badge badge-warning float-right">'+JsonFromServlet[a].created_At+'</span></a><span class="product-description addaddress">'
                HTMLdata+=JsonFromServlet[a].pickingType+', Reti bandar,Mumbra, Thane </span></div> </li>'      
            
          }
                console.log(HTMLdata);
               $("#displayData").html(HTMLdata);
          }
  
         function displayPostInfo(){
            document.getElementById("username").innerHTML=JsonFromServlet[0].t_username;
            document.getElementById("plate").innerHTML=JsonFromServlet[0].plate;
            document.getElementById("foodType").innerHTML=JsonFromServlet[0].foodType;
            document.getElementById("mode").innerHTML=JsonFromServlet[0].mode;
        

          }

          function showDetail(postIdOnClick){
                toBeHidden=postIdOnClick;
          
          var postIdCleaned = postIdOnClick.replace('12','');
            for(a in JsonFromServlet)
              {
              if(JsonFromServlet[a].g_name==postIdCleaned){
                console.log("in matched "+postIdCleaned+" "+JsonFromServlet[a].g_name);
                document.getElementById("phone").innerHTML=JsonFromServlet[a].g_phone;
                document.getElementById("giverNanme").innerHTML=JsonFromServlet[a].g_name;
                console.log(postIdCleaned);
                JsonforApprove['giverName']=postIdCleaned;
                 JsonforApprove['postId']=parseInt(postId);
              }
              
            }
          }   

          function approveRequest(){          
            jsonString=JSON.stringify(JsonforApprove);
              console.log(jsonString);
              ajaxForApproving(jsonString);
          }
          
          function ajaxForApproving(jsonString){
            $.ajax({
              method: 'POST'
              ,data:"approveThePost="+jsonString
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
            alert('Request Is Accepted');     
          }
          
  function logOut(){
     localStorage.removeItem("username");
     window.location.href="index.html";
	 return false;

}

function loadUserName(){
document.querySelector("body > div > aside > div > div.os-padding > div > div > div > div > a").innerHTML="samina"
}