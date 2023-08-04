<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 7/27/2023
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin management</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <script src="https://kit.fontawesome.com/84744eb47a.js"></script>

    <link rel="stylesheet" href="asset/css/admin.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>

<main id="main-content">
    <ul class="nav">
        <li class="nav-item">
            <i class="fa-solid fa-people-group">&nbsp;&nbsp;Employee</i>
        </li>
        <li class="nav-item" style="display: flex">
            <span style="margin-right: 10px">Welcome ${sessionScope.account.username}</span>
            <div class="nav-icon user-icon" id="logoutBtn" style="cursor: pointer; margin-right: 20px;">
                <i class="fa-solid fa-right-from-bracket"></i>&nbsp;Logout
            </div>
        </li>
    </ul>

    <div class="wrapper">
        <aside>
            <ul>
                <li id="view-contents-btn">
                    <i class="fa-solid fa-gauge"></i>
                    Dashboard
                </li>
                <li id="form-contents-btn">
                    <i class="fa-solid fa-chart-column"></i>
                    <div>Employee manager</div>
                    <i class="fa-solid fa-chevron-down"></i>
                </li>
                <li class="aside-child active" id="view-employeeDTOS-aside-btn">
                    <i class="fa-solid fa-list"></i>
                    Employee List
                </li>
                <li class="aside-child" id="add-employeeDTO-aside-btn">
                    <i class="fa-solid fa-plus"></i>
                    Add Employee
                </li>
            </ul>
        </aside>

        <div class="container-fluid" id="view-contents">
            <h2>Employee List</h2>
            <hr/>

            <form class="main-search form-inline" style="margin-bottom: 10px; margin-left: 456px;">

                <div class="input-group" style="margin-right: 5px;">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa-solid fa-magnifying-glass"
                                                          style="height: 24px;"></i></span>
                    </div>
                    <input type="text" class="searchEmployee" id="searchTxt" style="min-width: 300px;"
                           placeholder="Username"
                           aria-label="Username"
                           aria-describedby="basic-addon1">
                </div>
                <div class="input-group" style="margin-right: 5px;">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i class="fa-solid fa-filter"></i>Filter</span>
                    </div>
                    <select name="search" id="filterBy" class="searchEmployee" style="min-width: 150px;">
                        <option value="name">Name</option>
                        <option value="date_of_birth">Date of birth</option>
                        <option value="address">Address</option>
                        <option value="phone">Phone</option>
                        <option value="department">Department</option>
                    </select>
                </div>

                <button type="button" class="btn btn-info">search</button>

            </form>

            <table class="table table-bordered" id="employeesTable">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Date of birth</th>
                    <th scope="col">Address</th>
                    <th scope="col">Phone number</th>
                    <th scope="col">Department</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

            <nav aria-label="Page navigation example" id="listPaging">
                <ul class="pagination">
                </ul>
            </nav>
        </div>

        <div class="container-fluid" id="add-employeeDTO">
            <h2>Add Employee</h2>
            <hr/>

            <div class="card-body">
                <form action="/add-content" method="post" id="addContentForm">

                    <input type="hidden" id="inputEmployeeId">
                    <input type="hidden" id="inputAccountId">

                    <div class="form-group">
                        <label for="inputFirstName">First name <span>(*)</span></label>
                        <input type="text" class="form-control" id="inputFirstName" placeholder="First name"
                               name="firstName" rules="required">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputLastName">Last name <span>(*)</span></label>
                        <input type="text" class="form-control" id="inputLastName" placeholder="Last name"
                               name="lastName" rules="required">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputPhone">Phone number <span>(*)</span></label>
                        <input type="text" class="form-control" id="inputPhone" placeholder="Phone number"
                               name="phone" rules="required phone">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputDob">Date of birth <span>(*)</span></label>
                        <input type="date" class="form-control" id="inputDob" name="dob"
                               rules="required min-10 max-200">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label>Gender <span>(*)</span></label>
                        <div class="gender-input">
                            <div class="radio-input">
                                <input type="radio" name="gender" value="true" checked>
                                <span>
                                        Male
                                    </span>
                            </div>

                            <div class="radio-input">
                                <input type="radio" name="gender" value="false">
                                <span>
                                        Female
                                    </span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputAccount">Account <span>(*)</span></label>
                        <input type="text" class="form-control" id="inputAccount" name="account"
                               placeholder="Account" rules="required">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputEmail">Email <span>(*)</span></label>
                        <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email"
                               rules="required email">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword">Password <span>(*)</span></label>
                        <input type="password" class="form-control" id="inputPassword" name="password"
                               placeholder="Password" rules="required password">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputAddress">Address</label>
                        <textarea class="form-control" id="inputAddress" name="address"
                                  rules="required" rows="3"></textarea>
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputStatus">Status</label>
                        <div>
                            <input type="checkbox" checked name="status" id="inputStatus" value="true"
                                   style="margin-left: 20px; margin-right: 4px;">Active
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputDepartment">Department <span>(*)</span></label>
                        <select class="form-control" id="inputDepartment" style="cursor: pointer;">
                            <option value="Fsoft Academy">Fsoft Academy</option>
                            <option value="Fsoft Dev">Fsoft Dev</option>
                            <option class="Fsoft Tester">Fsoft Tester</option>
                            <option class="Fsoft Marketing">Fsoft Marketing</option>
                        </select>
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="inputRemark">Remark</label>
                        <textarea class="form-control" id="inputRemark" name="remark" id="inputContent"
                                  rows="3"></textarea>
                        <span class="form-message"></span>
                    </div>

                    <button type="button" class="btn btn-primary" id="turnBackBtn"><i class="fa-solid fa-backward"></i>Back
                    </button>
                    <button type="button" class="btn btn-warning" id="deleteBtn"><i
                            class="fa-solid fa-arrow-rotate-left"></i>Reset
                    </button>
                    <button type="submit" class="btn btn-success" id="mainBtn"><i class="fa-solid fa-plus"></i>Add
                    </button>
                </form>
            </div>

        </div>
    </div>
</main>

<script src="asset/js/Validator.js"></script>

<script>

    let typeOfApi = 'Get';
    let page = {
        pageIndex: 1,
        pageSize: 3
    };

    const fetchApi = (data) => {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: "/Assignment_war_exploded/api/admin",
                type: typeOfApi,
                contentType: 'application/json',
                data: data,
                success: function (response) {
                    resolve(response);
                },
                error: function (response) {
                    reject(response.responseText);
                }
            });
        });
    }

    const renderPaging = () => {
        let html = '<ul class="pagination"' + (page.totalPage == 1 ? 'hidden' : '') + '>';
        html += '<li class="' + (page.pageIndex == 1 ? 'page-item disabled' : 'page-item') + '"><span style="cursor: pointer;" class="page-link previous" onclick="handlePrevious()">Previous</span></li>';

        for (let i = 1; i <= page.totalPage; i++) {
            html += '<li class="' + (page.pageIndex == i ? 'page-item active' : 'page-item') + '"><span style="cursor: pointer;" class="page-link page-link-number" onclick="handlePageNumber(' + i + ')">' + i + '</span></li>';
        }

        html += '<li class="' + (page.pageIndex == page.totalPage ? 'page-item disabled' : 'page-item') + '"><span style="cursor: pointer;" class="page-link next" onclick="handleNext()">Next</span></li>';
        html += '</ul>';

        $('#listPaging').html(html);
    };

    function handlePagingSearch() {
        const filterBy = $('#filterBy').val();
        const searchTxt = $('#searchTxt').val();
        const data = {
            searchTxt,
            filterBy,
            pageIndex: page.pageIndex,
            pageSize: page.pageSize
        }
        console.log(data)
        typeOfApi = 'Get';
        const response = fetchApi(data);
        renderListEmployee(response);
    }

    function handlePrevious() {
        page = {...page, pageIndex: page.pageIndex - 1};
        handlePagingSearch();
    }

    function handlePageNumber(index) {
        page = {...page, pageIndex: index};
        handlePagingSearch();
    }

    function handleNext() {
        page = {...page, pageIndex: page.pageIndex + 1};
        handlePagingSearch();
    }

    const renderListEmployee = (data) => {
        data.then(response => {
            console.log(response)
            if (!response.employees) {
                $('#employeesTable tbody').html('<h2>Can not find any employee</h2>');
                page.totalPage = 1;
                renderPaging();
                return;
            }
            const html = response.employees.reduce((prevData, currData) => {
                const {id, firstName, lastName, dob, address, phone, department} = currData;
                return prevData +
                    '<tr>' +
                    '<th scope="row">' + id + '</th>' +
                    '<td>' + firstName + ' ' + lastName + '</td>' +
                    '<td>' + dob + '</td>' +
                    '<td>' + address + '</td>' +
                    '<td>' + phone + '</td>' +
                    '<td>' + department + '</td>' +
                    '<td class="table-action" onclick="handleViewDetails(' + id + ')" id="' + id + '"><i class="fa-regular fa-eye"></i> View</td>' +
                    '</tr>';
            }, '');

            $('#employeesTable tbody').html(html);

            page = response.page;
            renderPaging();
        }).catch((error) => {
            console.log(error);
        });
    };

    $(document).ready(() => {
        $('#add-employeeDTO').toggle(false);
        const data = {
            pageIndex: 1,
            pageSize: page.pageSize
        }
        const response = fetchApi(data);
        renderListEmployee(response);
    });

    let activeElement = 'view-employeeDTOS-aside-btn';

    const clearFormValue = (formSelector) => {
        let formElement = $(formSelector);
        console.log(formElement)
        let inputElements = formElement.find('input, textarea');

        inputElements.each(function () {
            if (this.type != 'checkbox' && this.type != 'radio') {
                $(this).val('');
            }
            $(this).next('.form-message').html('');
        });
    };

    function handleChangeActive(id) {
        $('#' + activeElement).removeClass('active');
        $('#' + id).addClass('active');
        activeElement = id;
    };

    $('#add-employeeDTO-aside-btn').click(function () {
        const id = $(this).attr('id');
        handleChangeActive(id);
        $('#add-employeeDTO').toggle(true);
        $('#view-contents').toggle(false);
        typeOfApi = 'Post';
        clearFormValue('#addContentForm');

        $('#add-employeeDTO h2').html("Add Employee");
        $('#mainBtn').html('<i class="fa-solid fa-plus"></i>Add');
        $('#deleteBtn').html('<i class="fa-solid fa-arrow-rotate-left"></i>Reset');
    });

    $('#view-employeeDTOS-aside-btn').click(function () {
        const id = $(this).attr('id');
        handleChangeActive(id);
        $('#add-employeeDTO').toggle(false);
        $('#view-contents').toggle(true);
        typeOfApi = 'Get';
        const data = {
            pageIndex: 1,
            pageSize: page.pageSize
        }
        const response = fetchApi(data);
        renderListEmployee(response);

    });

    const handleSearch = (searchTxt, filterBy) => {
        const data = {
            searchTxt,
            filterBy,
            pageIndex: 1,
            pageSize: page.pageSize
        }
        const response = fetchApi(data);
        renderListEmployee(response);
    }

    $('#searchTxt').on('input', function () {
        const filterBy = $('#filterBy').val();
        const searchTxt = $(this).val();
        handleSearch(searchTxt, filterBy);
    });

    $('#filterBy').change(function () {
        const filterBy = $(this).val();
        const searchTxt = $('#searchTxt').val();
        handleSearch(searchTxt, filterBy);
    });

    const fillData = (response) => {
        response.then(data => {
            const employeeDetails = data[0];
            const {employee, account} = employeeDetails;

            $('#inputEmployeeId').val(employee.id);
            $('#inputAccountId').val(account.id)
            $('#inputFirstName').val(employee.firstName);
            $('#inputLastName').val(employee.lastName);
            $('#inputPhone').val(employee.phone);
            $('#inputDob').val(employee.dob);
            $('#inputAccount').val(account.username);
            $('#inputEmail').val(account.email);
            $('#inputPassword').val(account.password);
            $('#inputAddress').val(employee.address);
            $('#inputRemark').val(employee.remark);
            $('#inputDepartment').val(employee.department);
            $("input[name='gender'][value='" + (employee.gender) + "']").prop('checked', true);
            $('#inputStatus').prop('checked', account.status);

            $('#add-employeeDTO').toggle(true);
            $('#view-contents').toggle(false);

            $('#add-employeeDTO h2').html("Employee's Details");
            $('#mainBtn').html('<i class="fa-solid fa-plus"></i>Update');
            $('#deleteBtn').html('<i class="fa-solid fa-arrow-rotate-left"></i>Delete');
        }).catch(error => {
            console.log(error);
        })
    }

    const handleViewDetails = (employeeId) => {
        typeOfApi = 'Get';
        const data = {
            searchTxt: employeeId,
            filterBy: 'id',
            pageIndex: page.pageIndex,
            pageSize: page.pageSize
        }
        const response = fetchApi(data);
        fillData(response);
        typeOfApi = 'Put';
    }

    $('#logoutBtn').click(() => {
        window.location = "logout";
    });

    const getFormData = () => {
        const data = {
            account: {},
            employee: {}
        };

        data.employee['id'] = $('#inputEmployeeId').val() == '' ? null : $('#inputEmployeeId').val();
        data.account['id'] = $('#inputAccountId').val() == '' ? null : $('#inputAccountId').val();
        data.account['employeeId'] = $('#inputEmployeeId').val() == '' ? null : $('#inputEmployeeId').val();
        data.employee['gender'] = $('input[name="gender"]:checked').val();
        data.account['status'] = $('input[name="status"]:checked').val() ? 'true' : 'false';
        data.employee['firstName'] = $('#inputFirstName').val() ? $('#inputFirstName').val() : null;
        data.employee['lastName'] = $('#inputLastName').val() ? $('#inputLastName').val() : null;
        data.employee['phone'] = $('#inputPhone').val() ? $('#inputPhone').val() : null;
        data.employee['dob'] = $('#inputDob').val() ? $('#inputDob').val() : null;
        data.account['username'] = $('#inputAccount').val() ? $('#inputAccount').val() : null;
        data.account['email'] = $('#inputEmail').val() ? $('#inputEmail').val() : null;
        data.account['password'] = $('#inputPassword').val() ? $('#inputPassword').val() : null;
        data.employee['address'] = $('#inputAddress').val() ? $('#inputAddress').val() : null;
        data.employee['remark'] = $('#inputRemark').val() == '' ? null : $('#inputRemark').val();
        data.employee['department'] = $('#inputDepartment').val() ? $('#inputDepartment').val() : null;

        return data;
    };

    const formEmployee = new Validator('#addContentForm');
    formEmployee.callApiFunc = () => {
        if (typeOfApi == 'Delete') {
            typeOfApi = 'Put';
        }
        const data = getFormData();
        console.log(data)
        const response = fetchApi(JSON.stringify(data));
        response.then(data => {
            alert(data.message);
        }).catch(error => {
            console.log(error);
        })
    }

    $('#deleteBtn').click(() => {
        if (typeOfApi == 'Put') {
            const data = {
                employeeId: $('#inputEmployeeId').val()
            }
            typeOfApi = 'Delete';
            const response = fetchApi(JSON.stringify(data));
            response.then((data) => {
                alert(data.message)
                typeOfApi = 'Get';
                const response = fetchApi({pageIndex: 1, pageSize: page.pageSize});
                renderListEmployee(response);
                $('#add-employeeDTO').toggle(false);
                $('#view-contents').toggle(true);
            }).catch(error => {
                console.log(error);
            })
        } else {
            clearFormValue('#addContentForm');
        }
    });

    $('#turnBackBtn').click(() => {
        typeOfApi = 'Get';
        const data = {
            pageIndex: 1,
            pageSize: page.pageSize
        }
        const response = fetchApi(data);
        renderListEmployee(response);
        $('#add-employeeDTO').toggle(false);
        $('#view-contents').toggle(true);
    });


</script>

</body>
</html>
