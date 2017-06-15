/**
 * Created by pca48 on 6/7/2017.
 */


/*
            $("#submit").on("click", function() {
                if($('#txtvalidFromDate').val()=="") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).addClass("error");
                    $(".errorValidTo").text("Enter ValidFromDate First");
                    $('#txtvalidFromDate').focus();
                    return false;
                }
                else {
                    $(this).removeClass("error");
                    $(".errorValidTo").text("");
                    return true;
                }
            });
            $("#submit").on("click", function() {

                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+1; //January is 0!

                var yyyy = today.getFullYear();
                if(dd<10){
                    dd='0'+dd;
                }
                if(mm<10){
                    mm='0'+mm;
                }
                var today = yyyy+'/'+mm+'/'+dd;
                var createdate=$('#txtcreationDate').val();

                var currentDate=Date.parse(today);
                var creattionDate=Date.parse(createdate);

                if(createdate!="" && creattionDate <=currentDate) {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorCreationDate").text("");
                    return true;
                }
                else {
                    $(this).addClass("error");
                    $(".errorCreationDate").text("CreationDate  should be <=today's date");
                    $('#txtcreationDate').focus();
                    return false;
                }

            });
            $("#submit").on("click", function() {

                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+1; //January is 0!

                var yyyy = today.getFullYear();
                if(dd<10){
                    dd='0'+dd;
                }
                if(mm<10){
                    mm='0'+mm;
                }
                var today = yyyy+'/'+mm+'/'+dd;
                var validfrom=$('#txtvalidFromDate').val();
                var validfrm=Date.parse(validfrom);
                var currentDate=Date.parse(today);

                if(validfrom!="" && validfrm >=currentDate) {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorValidFrom").text("");
                    return true;
                }
                else {
                    $(this).addClass("error");
                    $(".errorValidFrom").text("ValidFrom Date should be >=today's date");
                    $('#txtvalidFromDate').focus();
                    return false;
                }

            });
                   $("#ddlAddressScope").on('change', function(){

                     if($('#ddlAddressScope').val()!="None") {
                         /!* alert("Please Select ValidFrom date first");*!/
                         $(this).removeClass("error");
                         $(".errorGroup").text("");
                         $(".errorRole").text("");
                         $(".errorUserId").text("");
                         return true;
                     }
                 });

            ($("#groupDetails").kendoMultiSelect).on('click', function(){

                if($('#groupDetails').val()!="") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
            });
                function  change() {
                    $("#groupDetails").kendoMultiSelect({
                        change: function(e) {
                            $(this).removeClass("error");
                            $(".errorAddressScope").text("");

                        }
                    });

                }
       $("#groupDetails").on('click', function(){

                if($('#groupDetails').val()!="") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
            });
            $("#roleDetails").on('click', function(){

                if($('#roleDetails').val()!="") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
            });
            $("#txtUserId").on('change', function(){

                if($('#txtUserId').val()!="") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
            });

           $("#ddlAddressScope").on('click', function(){

                if($("#ddlAddressScope").val()=="Group") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).addClass("error");
                    $(".errorGroup").text("Select Gruop Details");
                    return false;
                }
                else if($("#ddlAddressScope").val()=="Role") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).addClass("error");
                    $(".errorRole").text("Select Role Details");
                    return false;
                }
                else if($("#ddlAddressScope").val()=="UserId") {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).addClass("error");
                    $(".errorUserId").text("Enter Proper UserId Details");
                    return false;
                }

            });
              $("#file").on("click", function() {
                var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;

                var docname = $("#file").val();

                if (docname.match(regExAlphabetic) && angular.lowercase(docname) === 'pdf' && files.size<5242880 ) {
                    $(this).removeClass("error");
                    $(".errorFile").text("");
                    return true;
                }
                else {
                   // alert("Only pdf allow with max 5 MB");
                    $(this).addClass("error");
                    $(".errorFile").text("Enter Valid DocumentDescription");
                    return false;
                }

            });
            $("#txtDescription").on("change", function() {

                var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
                var docDesc = $("#txtDescription").val();
                if (docDesc.match(regExAlphabetic)) {
                    $(this).removeClass("error");
                    $(".errorDocDescription").text("");
                } else {
                    $(this).addClass("error");
                    $(".errorDocDescription").text("Enter Valid DocumentDescription");
                    return false;
                }

            });*/

