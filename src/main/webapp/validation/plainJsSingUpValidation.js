let regexps = new Map();

regexps.set("name", /^[A-Za-zА-Яа-яё]+$/);
regexps.set("email", /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/);
regexps.set("password", /^[a-zA-Z0-9]{6,}$/);

let inputs = document.querySelectorAll('input[data-rule]');
let right = 0;

for (let input of inputs) {
    input.addEventListener('keyup', function() {
        let field_name = this.dataset.rule;
        let value = this.value;
        var alertBox = this.id + "AlertBox";

        let check;

        check = value.match(regexps.get(field_name));

        try {
            if (check) {
                if (!this.classList.contains("border-success")) {
                    right++;
                }
                this.classList.remove("border");
                this.classList.remove("border-danger");
                this.classList.add("border");
                this.classList.add("border-success");
                document.getElementById(alertBox).style.display = "none";
            } else {
                if (this.classList.contains("border-success")) {
                    right--;
                }
                this.classList.remove("border");
                this.classList.remove("border-success");
                this.classList.add("border");
                this.classList.add("border-danger");
                document.getElementById(alertBox).style.display = "block";
            }
            comparePasswords();
        } catch (DOMException) {}
    });
}

function checkFullness() {
    for (let input of inputs) {
        if (input.value == "") {
            input.classList.add("border");
            input.classList.add("border-danger");
        }
    }
}

function validForm() {
    checkFullness();
    if (right == 5 && comparePasswords()) {
        return true;
    }
    event.preventDefault();
    return false;
}

function comparePasswords() {
    if (document.getElementById('password').value === document.getElementById('cpassword').value) {
        document.getElementById("differentPasswords").style.display = "none";
        document.getElementById('cpassword').classList.remove("border");
        document.getElementById('cpassword').classList.remove("border-danger");
        return true;
    } else {
        document.getElementById("differentPasswords").style.display = "block";
        document.getElementById('cpassword').classList.add("border");
        document.getElementById('cpassword').classList.add("border-danger");
        event.preventDefault();
        return false;
    }
}