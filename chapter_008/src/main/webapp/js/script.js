/**
 * checks whether the current field is set
 * @param id of the current field
 * @returns {boolean} true if the current field isn't empty,
 * otherwise - false
 */
function isEmpty(id) {
    var value = $(id); //reference to the field with curent id
    var result = true;
    if (!value.val()) {
        result = false;
        alert(value.attr('name').concat(' is empty'));
    }
    return result;
}

/**
 * checks whether the input fields are valid
 * @returns {boolean} true if all the input fields aren't empty,
 * otherwise - false
 */
function checkField() {
    var result = false;
    if (isEmpty('#description')) {
        result = true;
    }
    return result;
}

/**
 * Creates a new item
 * @returns {boolean} it always return false,
 * because a query is being done by an ajax query
 */
function createItem() {
    if (checkField()) {
        createAjax();
        clearTextArea('#description');
    }
    return false;
}

/**
 * cleans a textarea
 */
function clearTextArea(id) {
    $(id).val('');
}

/**
 * A method determines what kind of items will
 * fill the table(done item or item in work)
 */
function selectDoneOrAllItem() {
    if ($("#select").is(':checked')) {
        fillAjax('all')
    } else {
        fillAjax('notDone');
    }
}

/**
 * Fills the table of items from the db via ajax query
 * @param param a parameter which allow to know what kind
 * of items will fill the table: which were done or weren't done
 */
function fillAjax(param) {
    $.ajax({
        type: 'GET',
        url: '/json',
        data: {
            cond: param
        },
        complete: function (response) {
            var result = "";
            var items = JSON.parse(response.responseText);
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var id = item.id;
                result +=
                    "<tr>\n" +
                        "<td>" + id + "</td>\n" +
                        "<td>" + item.desc + "</td>\n" +
                        "<td>" + new Date(item.created) + "</td>\n" +
                        "<td><input type='checkbox' id='"+ id + "' onchange='changeAjax(id)'" + (item.done ? 'checked' : '') + "></td>\n" +
                    "</tr>";
            }
            var table = document.getElementById("body-tasks");
            table.innerHTML = result;
        }
    });
}

/**
 * Creates a new item ang does an ajax query
 * to the db for creating a new note
 */
function createAjax() {
    $.ajax({
        type: 'POST',
        url: '/json',
        async: false,
        data: {
            desc: $('#description').val()
        }
    }).then(selectDoneOrAllItem());
}

/**
 * Changes a state of a current task and does
 * an ajax query to the db for updating the state
 * @param id an id of current task
 */
function changeAjax(id) {
    $.ajax({
        type: 'POST',
        url: '/json',
        async: false,
        data: {
            id: id,
            done: document.getElementById(id).checked
        }
    }).then(selectDoneOrAllItem());
}

/**
 * fills the table after loading a current page
 */
$(window).load(selectDoneOrAllItem());