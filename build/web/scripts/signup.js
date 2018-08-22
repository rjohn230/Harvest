/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function revealForms(show)
{
    document.getElementById("revealButtons").style.display="none";
    
    if(show=="pro")
    {
      document.getElementById("proForm").style.display="block";  
    }
    else
    {
        document.getElementById("ambassForm").style.display="block";
    }
    
    createReturnButton();
}

function reverse()
{
    document.getElementById("ambassForm").style.display="none";
    document.getElementById("proForm").style.display="none"; 
    document.getElementById("revealButtons").style.display="block";
}