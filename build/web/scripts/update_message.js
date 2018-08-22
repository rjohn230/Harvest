/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

setInterval(update_message,1000);

function update_message(){
    document.getElementById("update").click();
}

 $(document).ready(function(){
        // Set trigger and container variables
        var trigger = $('#update'),
            container = $('#directed_message');
        
        // Fire on click
        trigger.on('click', function(){
          // Set $this for re-use. Set target from data attribute
                 who= document.getElementById("about").value;
          
          // Load target page into container
          container.load('directed_message.jsp?');
          
          
          // Stop normal link behavior
          return false;
        });
      });



              