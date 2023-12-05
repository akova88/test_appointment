page.dialogs.elements.formCreate.validate({
    rules: {
        speciality: {
            required: true,
            minlength: 5,
            maxlength: 25
        },
        name: {
            required: true,
            minlength: 5,
            maxlength: 25
        }
    },
    messages: {
        speciality: {
            required: 'Speciality là bắt buộc',
            minlength: 'Speciality tối thiểu là ${0} ký tự',
            maxlength: 'Speciality tối đa là ${0} ký tự'
        },
        name: {
            required: 'Name là bắt buộc',
            minlength: 'Name tối thiểu là ${0} ký tự',
            maxlength: 'Name tối đa là ${0} ký tự'
        }
    },
    errorLabelContainer: "#modalCreate .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalCreate .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaCreate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaCreate.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaCreate.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.create();
    }
});
