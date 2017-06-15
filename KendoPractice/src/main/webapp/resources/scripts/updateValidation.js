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
    /*minDate: $('#txtvalidFromDate').val(),*/
   /* onSelect: function(selectedDate) {s
        $("#txtvalidFromDate").datepicker("option", "minDate", selectedDate);
    },*/
   /* mindate:$("#txtvalidFromDate"),*/
   /* onLoad: function (selectedDate) {
        $("#txtvalidFromDate").datepicker("option", "minDate", selectedDate);
    },
*//*onClick: function (selectedDate) {
        $("#txtvalidFromDate").datepicker("option", "minDate", selectedDate);
    },*/
/*    onSelect: function (selectedDate) {
        $("#txtvalidFromDate").datepicker("option", "minDate", selectedDate);
    },*/

    /*onSelect: function(selectedDate) {
        var abc= $('#txtvalidFromDate').val();
        $('#txtvalidToDate').datepicker('minDate', abc);
    },*/
    dateFormat: 'yy-mm-dd',
    autoclose: true,
  /* keypress: $('#txtvalidFromDate').val(),*/
   /* mindate:$("#txtvalidFromDate").val(),*/

});
    /*on('select',function (ev) {
   $('#txtvalidFromDate').val();
});*/
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
        /* if ($(this).attr("value") == "None") {
         $("#divUserid").hide();
         $("#divGroup").hide();
         $("#divRole").show();
         }*/
        /*if ($(this).attr("value") == "") {
         $("#divUserid").hide();
         $("#divGroup").hide();
         $("#divRole").show();
         }*/

    } );
}).change();
$("#txtvalidToDate").on('click', function(){

    if($('#txtvalidFromDate').val()=="") {
        /* alert("Please Select ValidFrom date first");*/
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

$("#txtvalidFromDate").change(function () {

    if($('#txtvalidFromDate').val()!="") {
        /* alert("Please Select ValidFrom date first");*/
        $(this).removeClass("error");
        $(".errorValidTo").text("");
        return true;
    }
});
$("#txtvalidToDate").on('change', function(){

    if($('#txtvalidFromDate').val()!="" && $('#txtvalidToDate').val()< $('#txtvalidFromDate').val()) {
        /* alert("Please Select ValidFrom date first");*/
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
/*$("#txtvalidToDate").on('click', function(){

    if($('#txtvalidFromDate').val()=="" && $('#txtvalidToDate').val()>= $('#txtvalidFromDate').val()) {
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
});*/

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

});


$("#submit").on("click", function() {
    if($("#txtDescription").val()!="") {
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
    }

});
$("#submit").on("click", function() {
    if($("#txtvalidToDate").val()!="") {
        if($('#txtvalidFromDate').val()=="") {
            /* alert("Please Select ValidFrom date first");*/
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
        /* alert("Please Select ValidFrom date first");*/
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
$("#txtName").on("change", function() {

    var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
    var docname = $("#txtName").val();
    if (docname.match(regExAlphabetic)) {
        $(this).removeClass("error");
        $(".errorDocName").text("");
    } else {
        $(this).addClass("error");
        $(".errorDocName").text("Enter Valid DocumentName");
        return false;
    }

});
$("#groupDetails").on("change", function() {
    if ($('#groupDetails').val() != null) {
        // alert("Please Select groupDetails");
        $(this).removeClass("error");
        $(".errorAddressScope").text("");
        return true;
    }
});
$("#roleDetails").on("change", function() {
    if ($('#roleDetails').val() != null) {
        // alert("Please Select groupDetails");
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
            // alert("Please Select groupDetails");
            $(this).removeClass("error");
            $(".errorAddressScope").text("");
            return true;
        }
        else{
            $(this).addClass("error");
            $(".errorAddressScope").text("Select Group Details");
            return false;
        }
    }
    if($("#ddlAddressScope").val()!="None" && $("#ddlAddressScope").val()=="Role" ) {

        if ($('#roleDetails').val() != null) {
            // alert("Please Select groupDetails");
            $(this).removeClass("error");
            $(".errorAddressScope").text("");
            return true;
        }
        else{
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
                    $(this).addClass("error");
                    $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                    // this.$scope.docLink.userId = null;
                    return false;
                }
            }
        }
        else {
            $(this).addClass("error");
            $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
            return false;
            // this.$scope.docLink.userId = null;
        }
    }
});
