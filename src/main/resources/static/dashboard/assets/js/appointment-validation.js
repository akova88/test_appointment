$.validator.addMethod("dateCus", function (date, element) {
    return this.optional(element) || date.match(/^(0?[1-9]|[12][0-9]|3[01])\/(0?[1-9]|1[0-2])\/\d{4}$/);
}, "Ngày phải đúng định dạng dd/MM/yyyy");

page.dialogs.elements.frmCreate.validate({
    rules:{
        'dayCre': {
            required: true,
            dateCus : true,
        }
    },
    messages:{
        'dayCre': {
            required: "Ngày là bắt buộc",
        }
    },
    errorLabelContainer: "#frmCreate .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#frmCreate .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaCreate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaCreate.removeClass("show").addClass("hide").empty();
            $("#frmCreate input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.commands.createAppointment();
    }
})

page.dialogs.elements.frmUpdate.validate({
    rules:{
        'dayUp': {
            required: true,
            dateCus : true,
        }
    },
    messages:{
        'dayUp': {
            required: "Ngày là bắt buộc",
        }
    },
    errorLabelContainer: "#frmUpdate .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#frmUpdate .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaUpdate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaUpdate.removeClass("show").addClass("hide").empty();
            $("#frmUpdate input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.commands.editAppointment();
    }
})