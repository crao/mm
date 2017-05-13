$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});



function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);
    
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',        
        url: "/photo/upload",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
    
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            console.log("ERROR : ", data);
            $("#btnSubmit").prop("disabled", false);

        }
    });
}
    
 /*   $("slist").click(function() {
    	alert("In jquery 2");
	})
    
  //  $('a[title=sl]').click(function (event){ 
$('a[title=sl]').click(function (event){
    	debugger;
     alert("In jquery");
       event.preventDefault(); 
       $.ajax({
           url: $(this).attr('href')
          ,success: function(response) {
        	   $("#result1").text(response);
               console.log("SUCCESS : ", response);
               
           },
           error: function (e) {

               $("#result1").text(e.responseText);
               console.log("ERROR : ", e);
               console.log("ERROR : ", data);
               $("#btnSl").prop("disabled", false);

           }
        })
        return false; //for good measure
   });
    
    
    $(document).ready(function () {

        $("#btnSl").click(function (event) {

            //stop submit the form, we will post it manually.
            event.preventDefault();

            fire_sl_submit();

        });

    });
    
    function fire_sl_submit() {
    	debugger;
        // Get form
        var form = $('#sl')[0];

        var data = new FormData(form);
        var uid = $("#uid");
        var sid = $("#sid");
      //  data.append("CustomField", "This is some extra data, testing");

        $("#btnSl").prop("disabled", true);

        $.ajax({
            type: "GET",       
            url: "/shortList?"+"userId="+uid+"&slId="+sid,
            data: data,
            //http://api.jquery.com/jQuery.ajax/
            //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
           processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            timeout: 600000,
        
            success: function (data) {

                $("#result1").text(data);
                console.log("SUCCESS : ", data);
                $("#btnSl").prop("disabled", false);

            },
            error: function (e) {

                $("#result1").text(e.responseText);
                console.log("ERROR : ", e);
                console.log("ERROR : ", data);
                $("#btnSl").prop("disabled", false);

            }
        });
    }*/

