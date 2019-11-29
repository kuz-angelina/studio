function populateForm() {
    var form = document.querySelector('.addForm_js');
    var orderDto = document.querySelector('.order-dto_js');


    if (orderDto.getAttribute('data-servicetypeid') !== null) {
        form.elements['serviceType'].selectedIndex = orderDto.getAttribute('data-servicetypeid') - 1;
    }
    if (orderDto.getAttribute('data-repairType') !== null) {
        form.elements['repairType'].selectedIndex = orderDto.getAttribute('data-repairType') - 1;
    }
    if (orderDto.getAttribute('data-clotherType') !== null) {
        form.elements['clotherType'].selectedIndex = orderDto.getAttribute('data-clotherType') - 1;
    }


}

function bindList() {
    var selectService = document.querySelector('.selectService');
    var selectRepairType = document.querySelector('.selectRepairType');

    selectService.addEventListener('change', function () {
        if (selectService.value === '2') {
            selectRepairType.setAttribute("disabled", "");
        }
        else
        {
            selectRepairType.removeAttribute("disabled");
        }
    });
}

bindList();
populateForm();
