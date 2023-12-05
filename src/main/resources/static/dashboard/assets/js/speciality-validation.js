page.dialogs.elements.formCreate.validate({
    rules: {
        codeNameCre: {
            required: true,
            minlength: 5,
            maxlength: 25
        },
        nameCre: {
            required: true,
            minlength: 5,
            maxlength: 25
        }
    },
    messages: {
        codeNameCre: {
            required: 'Code name là bắt buộc',
            minlength: 'Code name tối thiểu là ${0} ký tự',
            maxlength: 'Code name tối đa là ${0} ký tự'
        },
        nameCre: {
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
