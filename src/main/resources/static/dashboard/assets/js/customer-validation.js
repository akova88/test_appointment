
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
        fullNameCre: {
            required: true,
            minlength: 5,
            maxlength: 25
        },
        emailCre: {
            required: true,
            regex: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,})+$/,
        },
        genderCre: {
            required: true,
        },
        dobCre: {
            required: true,
            regex: /^(0?[1-9]|[12][0-9]|3[01])\/(0?[1-9]|1[0-2])\/\d{4}$/
        },
        phoneCre: {
            required: true,
            // regex: /^\+\d{1,2} \(\d{3}\) \d{3}-\d{4}$/
        },
        jobCre: {
            required: true
        },
        identityCre: {
            required: true,
            regex: /^\d{9}$/
        },
        addressCre: {
            required: true,
            maxlength: 50
        },
    },
    messages: {
        fullNameCre:{
            required: "Bắt buộc nhập Full Name",
            minlength: 'Full Name tối thiểu ${0} ký tự',
            maxlength: 'Full Name tối đa ${0} ký tự'
        },
        emailCre: {
            required: "Email là bắt buộc",
            regex: 'Email không hợp lệ'
        },

        genderCre: {
            required: 'Vui lòng chọn giới tính',
        },
        dobCre: {
            required: 'Vui lòng chọn ngày',
            regex: 'Ngày không hợp lệ'
        },
        phoneCre: {
            required: 'Điện thoại là bắt buộc',
            // regex: 'Số điện thoại không hợp lệ'
        },
        jobCre: {
            required: 'Vui lòng chọn công việc'
        },
        identityCre: {
            required: 'Số CMND là bắt buộc',
            regex: 'Số CMND không đúng định dạng'
        },
        addressCre: {
            required: 'Địa chỉ là bắt buộc',
            maxlength: 'Địa chỉ tối đa ${0} ký tự'
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

page.dialogs.elements.frmEdit.validate({
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
    errorLabelContainer: "#modalEdit .error-area",
    errorPlacement: function (error) {
        error.appendTo("#modalEdit .error-area");
    },
    showErrors: function(errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaEdit.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaEdit.removeClass("show").addClass("hide").empty();
            $("#frmEdit input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {

        page.dialogs.command.updateCustomer();
    }
})