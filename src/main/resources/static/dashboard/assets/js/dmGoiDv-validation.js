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
        maGoiCre: {
            required: true,
            minlength: 3,
            maxlength: 25
        },
        tenGoiCre: {
            required: true,
            minlength: 5,
            maxlength: 100
        },
        donGiaGoiCre: {
            required: true,
        },

    },
    messages: {
        maGoiCre:{
            required: "Bắt buộc nhập Mã Gói",
            minlength: 'Mã Gói tối thiểu ${0} ký tự',
            maxlength: 'Mã Gói tối đa ${0} ký tự'
        },
        tenGoiCre: {
            required: "Tên gói là bắt buộc",
            minlength: 'Tên gói tối thiểu ${0} ký tự',
            maxlength: 'Tên gói tối đa ${0} ký tự'
        },

        donGiaGoiCre: {
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