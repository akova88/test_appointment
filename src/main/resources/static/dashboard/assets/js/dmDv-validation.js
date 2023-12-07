$.validator.addMethod(
    "regex",
    function(value, element, regexp) {
        return this.optional(element) || regexp.test(value);
    },
    "Giá trị không hợp lệ"
);
page.dialogs.elements.frmCreate.validate({
    onfocusout: function (element) {
        page.dialogs.elements.frmCreate.valid();
    },
    rules: {
        maDvCre: {
            required: true,
            minlength: 3,
            maxlength: 25
        },
        tenDvCre: {
            required: true,
            minlength: 5,
            maxlength: 100
        },
        donGiaCre: {
            required: true,
        },

    },
    messages: {
        maDvCre:{
            required: "Bắt buộc nhập Mã Dịch vụ",
            minlength: 'Mã Dịch vụ tối thiểu ${0} ký tự',
            maxlength: 'Mã Dịch vụ tối đa ${0} ký tự'
        },
        tenDvCre: {
            required: "Tên dịch vụ là bắt buộc",
            minlength: 'Mã Dịch vụ tối thiểu ${0} ký tự',
            maxlength: 'Mã Dịch vụ tối đa ${0} ký tự'
        },

        donGiaCre: {
            required: 'Đơn giá là bắt buộc',
        },
    },
    errorLabelContainer: "#modalCreate .error-area",
    errorPlacement: function (error) {
        error.appendTo("#modalCreate .error-area");
    },
    showErrors: function(errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaCreate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaCreate.removeClass("show").addClass("hide").empty();
            $("#frmCreate input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.command.create();
    }
})