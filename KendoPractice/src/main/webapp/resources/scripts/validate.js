$('#txtcreationDate').datepicker({
    dateFormat: 'yy-mm-dd',
    autoclose: true,
    maxDate: 0,
});
$('#txtvalidFromDate').datepicker({
    dateFormat: 'yy-mm-dd',
    autoclose: true,
    minDate: 0,
    onClose: function (selectedDate) {
        $("#txtvalidToDate").datepicker("option", "minDate", selectedDate);
    },
});
$('#txtvalidToDate').datepicker({
    dateFormat: 'yy-mm-dd',
    autoclose: true
});
$("#ddlAddressScope").change(function () {
    $("select option:selected").each(function () {
        if ($(this).attr("value") == "UserId") {
            $("#divUserid").show();
            $("#divGroup").hide();
            $("#divRole").hide();
        }
        if ($(this).attr("value") == "Group") {
            $("#divUserid").hide();
            $("#divGroup").show();
            $("#divRole").hide();
        }
        if ($(this).attr("value") == "Role") {
            $("#divUserid").hide();
            $("#divGroup").hide();
            $("#divRole").show();
        }
        if ($("#ddlAddressScope").val()=="") {
            $("#divUserid").hide();
            $("#divGroup").hide();
            $("#divRole").hide();

        }
    } );
}).change();
$("#txtvalidFromDate").change(function () {

    if($('#txtvalidFromDate').val()!="") {
        $(this).removeClass("error");
        $(".errorValidTo").text("");
        return true;
    }
});



$("#txtvalidToDate").on('click', function(){

    if($('#txtvalidFromDate').val()=="") {
     /*   alert("Please Select ValidFrom date first");*/
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
$("#txtcreationDate").on('change', function(){
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
    var today = yyyy+'-'+mm+'-'+dd;
    var createdate=$('#txtcreationDate').val();

    if(createdate!="" && createdate <=today) {
        $(this).removeClass("error");
        $(".errorCreationDate").text("");
        return true;
    }
    else {
       /* alert("CreationDate  should be <=today's date");*/
        $(this).addClass("error");
        $(".errorCreationDate").text("CreationDate  should be <=today's date");
        $('#txtcreationDate').focus();
        return false;
    }
});
$("#txtvalidFromDate").on('change', function(){
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
    var today = yyyy+'-'+mm+'-'+dd;
    var validfrom=$('#txtvalidFromDate').val();

    if(validfrom!="" && validfrom >=today) {
        $(this).removeClass("error");
        $(".errorValidFrom").text("");
        return true;
    }
    else {
       /* alert("ValidFrom Date should be >=today's date");*/
        $(this).addClass("error");
        $(".errorValidFrom").text("ValidFrom Date should be >=today's date");
        $('#txtvalidFromDate').focus();
        return false;
    }
});
$("#txtName").on("change", function() {
    var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
    var docname = $("#txtName").val();
    if (docname.match(regExAlphabetic)) {
        $(this).removeClass("error");
        $(".errorDocName").text("");
    } else {
        /*alert("Enter Valid DocumentName");*/
        $(this).addClass("error");
        $(".errorDocName").text("Enter Valid DocumentName");
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
      /*  alert("Enter Valid DocumentDescription");*/
        $(this).addClass("error");
        $(".errorDocDescription").text("Enter Valid DocumentDescription");
        return false;
    }

});
$("#submit").on("click", function() {
    if($("#txtName").val()!="") {
        var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
        var docname = $("#txtName").val();
        if (docname.match(regExAlphabetic)) {
            $(this).removeClass("error");
            $(".errorDocName").text("");
        } else {
           /* alert("Enter Valid DocumentName");*/
            $(this).addClass("error");
            $(".errorDocName").text("Enter Valid DocumentName");
            return false;
        }
    }
});
$("#submit").on("click", function() {
    if($("#txtDescription").val()!="") {
        var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
        var docDesc = $("#txtDescription").val();
        if (docDesc.match(regExAlphabetic)) {
            $(this).removeClass("error");
            $(".errorDocDescription").text("");
        } else {
           /* alert("Enter Valid DocumentDescription");*/
            $(this).addClass("error");
            $(".errorDocDescription").text("Enter Valid DocumentDescription");
            return false;
        }
    }

});
$("#submit").on("click", function() {
    if($("#txtvalidToDate").val()!="") {
        if($('#txtvalidFromDate').val()=="") {
            /*alert("Enter ValidFromDate First");*/
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
    }
});
$("#submit").on("click", function() {
    if($("#ddlAddressScope").val()!="None" && $("#ddlAddressScope").val()=="Group" ) {

        if ($('#groupDetails').val() != null) {
            $(this).removeClass("error");
            $(".errorAddressScope").text("");
            return true;
        }
        else{
            /*alert("Select Group Details");*/
            $(this).addClass("error");
            $(".errorAddressScope").text("Select Group Details");
            return false;
        }
    }
    if($("#ddlAddressScope").val()!="None" && $("#ddlAddressScope").val()=="Role" ) {

        if ($('#roleDetails').val() != null) {
            $(this).removeClass("error");
            $(".errorAddressScope").text("");
            return true;
        }
        else{
            /*alert("Select Role Details");*/
            $(this).addClass("error");
            $(".errorAddressScope").text("Select Role Details");
            return false;
        }
    }
    if($("#ddlAddressScope").val()=="") {
            $(this).removeClass("error");
            $(".errorAddressScope").text("");
            return true;
    }

    if($("#ddlAddressScope").val()!="None" && $("#ddlAddressScope").val()=="UserId" ) {


        if ($('#txtUserId').val() != "") {
            var userId = ['Bhavin', 'Harshal', 'Savan', 'Vimal'];
            var check = $('#txtUserId').val();
            for (var i = 0; i < userId.length; i++) {
                if (userId.indexOf(check) > -1) {
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
                else {
                /*    alert("Select any User from Bhavin,Harshal,Vimal,Savan only");*/
                    $(this).addClass("error");
                    $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                    return false;
                }
            }
        }
        else {
           /* alert("Select any User from Bhavin,Harshal,Vimal,Savan only");*/
            $(this).addClass("error");
            $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
            return false;
        }
    }
});

