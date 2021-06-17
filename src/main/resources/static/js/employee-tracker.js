// Check whether the username is already registered
function isExistingUser() {

    var userName = document.getElementById("username").value;

    $.ajax({
            type: "POST",
            url: 'http://localhost:8080/employee-management/user-exist',
            dataType: 'text',
            contentType: 'text/plain',
            data: userName,
            async: false,
            success: function () {
              alert('done')
            },
            error: function () {
                throw new Error("Could not load script " + script);
            }
        });
}

// Save employee details
function saveEmployee(form) {

    var employeeData = new Object();
    employeeData.userName = document.getElementById("username").value;
    employeeData.email = document.getElementById("email").value;
    employeeData.firstName = document.getElementById("firstname").value;
    employeeData.lastName = document.getElementById("lastname").value;

    var data = JSON.stringify(employeeData);

    $.ajax({
        type: "POST",
        url: 'http://localhost:8080/employee-management/employee',
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        async: false,
        success: function (response) {
          alert('Registration successfully completed!');
        },
        error: function (response) {
          alert('Registration failed!');
        }
    });

}