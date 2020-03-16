let regexps = {
    name: /^[A-Za-zА-Яа-яё]+$/,
    email: /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/,
    password: /^[a-zA-Z0-9]{6,}$/,
}
let right = 0;

$('input[data-rule]').each(function() {
    $(this).on('keyup', function() {
        let field_name = $(this).attr('data-rule');
        let value = $(this).val();
        let check;
        var alertBox = '#' + $(this).attr('id') + 'AlertBox';

        check = regexps[field_name].test(value);

        try {
            if (check) {
                if (!$(this).hasClass("border-success")) {
                    right++;
                }
                $(this).removeClass("border");
                $(this).removeClass("border-danger");
                $(this).addClass("border");
                $(this).addClass("border-success");
                $(alertBox).hide();
            } else {
                if ($(this).hasClass("border-success")) {
                    right--;
                }
                $(this).removeClass("border");
                $(this).removeClass("border-success");
                $(this).addClass("border");
                $(this).addClass("border-danger");
                $(alertBox).show();
            }
            comparePasswords();
        } catch (DOMException) {}
    });
});

function checkFullness() {
    $('input[data-rule]').each(function() {
        if (this.value == "") {
            $(this).addClass("border");
            $(this).addClass("border-danger");
        }
    });
}

function validForm() {
    $('form').submit(function(event) {
        checkFullness();
        event.preventDefault();
        if (right == 5 && comparePasswords()) {
            $('form').unbind('submit').submit();
        }
    });
}

function comparePasswords() {
    if ($('#password').val() === $('#cpassword').val()) {
        $('#cpassword').removeClass("border");
        $('#cpassword').removeClass("border-danger");
        $('#differentPasswords').hide();
        return true;
    } else {
        $('#differentPasswords').show();
        $('#cpassword').addClass("border");
        $('#cpassword').addClass("border-danger");
        return false;
    }
};