/**
 * Created by pca48 on 6/13/2017.
 */
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
    $('#txtvalidToDate').datepicker ({
    dateFormat: 'yy-mm-dd',
    autoclose: true,
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
        if ($(this).attr("value") == "None") {
            $("#divUserid").hide();
            $("#divGroup").hide();
            $("#divRole").hide();
        }
    } );
}).change();
    $("#txtvalidToDate").on('click', function(){

    if($('#txtvalidFromDate').val()=="") {
       /* alert("Enter ValidFromDate First");*/
        $(this).addClass("error");
        $(".errorValidTo").text("Enter Valid From Date First");
        $('#txtvalidFromDate').focus();
        return false;
    }
    else {
        $(this).removeClass("error");
        $(".errorValidTo").text("");
        return true;
    }
});

    $("#txtvalidFromDate").change(function () {

    if($('#txtvalidFromDate').val()!="") {
        $(this).removeClass("error");
        $(".errorValidTo").text("");
        return true;
    }
});
    $("#txtvalidToDate").on('change', function(){

    if($('#txtvalidFromDate').val()!="" && $('#txtvalidToDate').val()< $('#txtvalidFromDate').val()) {
       // alert("ValidToDate sholud be >=ValidFromDate");
        $(this).addClass("error");
        $(".errorValidTo").text("Valid To Date sholud be >=Valid From Date");
        $('#txtvalidFromDate').focus();
        return false;

    }
    else {
        $(this).removeClass("error");
        $(".errorValidTo").text("");
        return true;
    }
});
    $("#txtDescription").on("change", function() {

        var regExAlphabetic = /^[a-zA-Z0-9]+(([.-][a-zA-Z0-9])?[a-zA-Z0-9\s]*)*$/;
    var docDesc = $("#txtDescription").val();
        if (docDesc.match(regExAlphabetic) || docDesc=="") {
        $(this).removeClass("error");
        $(".errorDocDescription").text("");
    } else {
       /* alert("Enter Valid DocumentDescription");*/
        $(this).addClass("error");
        $(".errorDocDescription").text("Enter Valid Document Description using A-Z,a-z,0-9,.,- characters only");
        return false;
    }

});
    $("#submit").on("click", function() {
    if($("#txtDescription").val()!="") {
        var regExAlphabetic = /^[a-zA-Z0-9]+(([.-][a-zA-Z0-9])?[a-zA-Z0-9\s]*)*$/;
        var docDesc = $("#txtDescription").val();
        if (docDesc.match(regExAlphabetic) || docDesc=="") {
            $(this).removeClass("error");
            $(".errorDocDescription").text("");
        } else {
           /* alert("Enter Valid DocumentDescription");*/
            $(this).addClass("error");
            $(".errorDocDescription").text("Enter Valid Document Description using A-Z,a-z,0-9,.,- characters only");
            return false;
        }
    }
});
    $("#submit").on("click", function() {
    if($("#txtvalidToDate").val()!="") {
        if($('#txtvalidFromDate').val()=="") {
           /* alert("Enter ValidFromDate First");*/
            $(this).addClass("error");
            $(".errorValidTo").text("Enter Valid From Date First");
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
    $("#documentType").on("change", function() {

    if($('#documentType').val()=="") {
        $(this).addClass("error");
        $(".errorDocType").text("DocumentType is required");
        return false;
    }
    else {
        $(this).removeClass("error");
        $(".errorDocType").text("");
        return true;
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
        /*alert("ValidFrom Date should be >=today's date");*/
        $(this).addClass("error");
        $(".errorValidFrom").text("Valid From Date should be >=today's date");
        $('#txtvalidFromDate').focus();
        return false;
    }
});
    $("#txtName").on("change", function() {
    var regExAlphabetic = /^[a-zA-Z0-9]+(([.-][a-zA-Z0-9])?[a-zA-Z0-9\s]*)*$/;
    var docname = $("#txtName").val();
    if (docname.match(regExAlphabetic)) {
        $(this).removeClass("error");
        $(".errorDocName").text("");
    } else {
       /* alert("Enter Valid DocumentName");*/
        $(this).addClass("error");
        $(".errorDocName").text("Enter Valid Document Name using A-Z,a-z,0-9,.,- characters only");
        return false;
    }
});


    $("#groupDetails").on("change", function() {
    if ($('#groupDetails').val() != null) {
        $(this).removeClass("error");
        $(".errorAddressScope").text("");
        return true;
    }
});
    $("#roleDetails").on("change", function() {
    if ($('#roleDetails').val() != null) {
        $(this).removeClass("error");
        $(".errorAddressScope").text("");
        return true;
    }
});
    $("#txtUserId").on("change", function() {
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
             /*   alert("Select any User from Bhavin,Harshal,Vimal,Savan only");*/
                $(this).addClass("error");
                $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                // this.$scope.docLink.userId = null;
                return false;
            }
        }
    }
});
    $("#ddlAddressScope").on("click", function() {
    if($("#ddlAddressScope").val()!="None" && $("#ddlAddressScope").val()=="Group" ) {

        if ($('#groupDetails').val() != null) {
            $(this).removeClass("error");
            $(".errorAddressScope").text("");
            return true;
        }
        else{
           /* alert("Select Group Details");*/
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
           /* alert("Select Role Details");*/
            $(this).addClass("error");
            $(".errorAddressScope").text("Select Role Details");
            return false;
        }
    }
    if($("#ddlAddressScope").val()=="None") {
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
                   /* alert("Select any User from Bhavin,Harshal,Vimal,Savan only");*/
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
