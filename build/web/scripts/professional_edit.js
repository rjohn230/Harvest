/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function reveal(todo){
    document.getElementById("revealButtons").style.display="none";
    switch(todo)
    {
        case"Contact":
        {
            document.getElementById("Contact").style.display="block"; 
            break;
        }
        case"About":
        {
            document.getElementById("About").style.display="block"; 
            break;
        }
        case"Picture":
        {
            document.getElementById("Picture").style.display="block"; 
            break;
        }
        case"Subjects":
        {
            document.getElementById("Subjects").style.display="block"; 
            break;
        }
        case"Location":
        {
            document.getElementById("Location").style.display="block"; 
            break;
        }
        case"Password":
        {
            document.getElementById("Password").style.display="block"; 
            break;
        }
    }
}
function reverse()
{
    document.getElementById("revealButtons").style.display="block";
    document.getElementById("Contact").style.display="none";
    document.getElementById("About").style.display="none";
    document.getElementById("Picture").style.display="none";
    document.getElementById("Subjects").style.display="none";
    document.getElementById("Location").style.display="none";
    document.getElementById("Password").style.display="none";
    
}

