
page.dialogs.elements.frmUpdate.validate({
    rules: {
        fullNameUp: {
            required: true,
            minlength: 5,
            maxlength: 25
        },
        emailUp: {
            required: true,
        }
    },
    messages: {
        fullNameUp:{
            required: "Bắt buộc nhập Full Name",
            minlength: 'Full Name tối thiểu ${0} ký tự',
            maxlength: 'Full Name tối đa ${0} ký tự'
        },
        emailUp: {
            required: "Email là bắt buộc"
        }
    },
    errorLabelContainer: "#modalUpdate .error-area",
    errorPlacement: function (error) {
        error.appendTo("#modalUpdate .error-area");
    },
    showErrors: function(errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaUpdate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaUpdate.removeClass("show").addClass("hide").empty();
            $("#frmUpdate input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.update();
    }
})

