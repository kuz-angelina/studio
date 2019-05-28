function showErrorMsg() {
    var msg = document.getElementById('var_error_js').getAttribute("data-msg");
    if (msg.length > 2) {
        document.getElementById('error_js').removeAttribute("hidden");
        document.getElementById('b-container_js').removeAttribute("hidden");
    }
}

function hideErrorMsg() {
    document.getElementById('error_js').setAttribute("hidden", "");
    document.getElementById('b-container_js').setAttribute("hidden", "");
}

function sendForm() {
    var form = document.querySelector('.js-form');

    form.addEventListener('submit', function (evt) {
        evt.preventDefault();
        console.log(form.action);
        if (form.elements['login'].value.length !== 0 && form.elements['password'].value.length !== 0) {
            form.submit();
        } else {
            showErrorMsg();
        }
    });
}


showErrorMsg();
sendForm();