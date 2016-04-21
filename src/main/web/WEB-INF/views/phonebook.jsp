
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring phonebook</title>
</head>

<link rel="stylesheet" href="public/bootstrap/css/bootstrap-min.css" media="screen" type="text/css" />

<link rel="stylesheet" href="public/css/login.css" media="screen" type="text/css" />

<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Spring Phonbook</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <div class="row">
    <div class="col-sm-6">
      <div class="dt-buttons btn-group">
        <button id="new-entry" data-toggle="modal" data-target="#new-modal"  aria-controls="example" tabindex="0" class="btn btn-default buttons-create">
          <span class="glyphicon glyphicon-plus">New</span>
        </button>
        <button id="edit-entry" data-toggle="modal" data-target="#edit-modal" aria-controls="example" tabindex="0" class="btn btn-default buttons-selected buttons-edit">
          <span class="glyphicon glyphicon-edit">Edit</span>
        </button>
        <button id="delete-entry" aria-controls="example" tabindex="0" class="btn btn-default buttons-selected buttons-remove">
          <span class="glyphicon glyphicon-remove">Delete</span>
        </button>
      </div>
    </div>
    <div class="col-sm-4">
      <form class="form-inline pull-right">
        <div class="form-group">
          <input id="search-field" class="form-control" type="text" placeholder="Search">
        </div>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-10">
      <table id="phonebook-entry-table" class= "table  table-hover">
        <thead>
        <tr>
          <th>First Name</th>
          <th>Middle Name</th>
          <th>Last Name</th>
          <th>Mobile Phone</th>
          <th>Home Phone</th>
          <th>Address</th>
          <th>e-mail</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td hidden class="id-field">Ivan</td>
          <td class="firstName-field">Ivan</td>
          <td class="middleName-field">Ivanovich</td>
          <td class="lastName-field">Ivanov</td>
          <td class="mobilePhone-field">+38(00)000-00-00</td>
          <td class="homePhone-field">+38(00)000-00-00</td>
          <td class="address-field">Kiev, Some str</td>
          <td class="email-field">email@email.com</td>
        </tr>
        <tr>
          <td hidden class="id-field">Ivan</td>
          <td class="firstName-field">Ivan</td>
          <td class="middleName-field">Ivanovich</td>
          <td class="lastName-field">Ivanov</td>
          <td class="mobilePhone-field">+38(00)000-00-00</td>
          <td class="homePhone-field">+38(00)000-00-00</td>
          <td class="address-field">Kiev, Some str</td>
          <td class="email-field">email@email.com</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div> <!-- /container -->

<!-- New Modal -->
<div class="modal fade" id="new-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="new-modal-label">Add new phonebook entry</h4>
      </div>
      <div class="modal-body">
        <form class="form">
          <div class="form-group">
            <input id="new-entry-id" type="text" hidden class="form-control id-field">
            <label for="new-first-name">First Name</label>
            <input id="new-first-name" type="text" class="form-control firstName-field">
            <label for="new-middle-name">Middle Name</label>
            <input id="new-middle-name" type="text" class="form-control middleName-field">
            <label for="new-last-name">Last Name</label>
            <input id="new-last-name" type="text" class="form-control lastName-field">
            <label for="new-mobile-phone">Mobile Phone</label>
            <input id="new-mobile-phone" type="text" class="form-control mobilePhone-field">
            <label for="new-home-phone">Home Phone</label>
            <input id="new-home-phone" type="text" class="form-control homePhone-field">
            <label for="new-address">Address</label>
            <input id="new-address" type="text" class="form-control address-field">
            <label for="new-email">Email</label>
            <input id="new-email" type="text" class="form-control email-field">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Add</button>
      </div>
    </div>
  </div>
</div><!--New Entry Modal -->

<!-- Edit Modal -->
<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="edit-modal-label">Edit phonebook entry</h4>
      </div>
      <div class="modal-body">
        <form class="form">
          <div class="form-group">
            <input id="edit-entry-id" type="text" hidden class="form-control id-field">
            <label for="edit-first-name">First Name</label>
            <input id="edit-first-name" type="text" class="form-control firstName-field">
            <label for="edit-middle-name">Middle Name</label>
            <input id="edit-middle-name" type="text" class="form-control middleName-field">
            <label for="edit-last-name">Last Name</label>
            <input id="edit-last-name" type="text" class="form-control lastName-field">
            <label for="edit-mobile-phone">Mobile Phone</label>
            <input id="edit-mobile-phone" type="text" class="form-control mobilePhone-field">
            <label for="edit-home-phone">Home Phone</label>
            <input id="edit-home-phone" type="text" class="form-control homePhone-field">
            <label for="edit-address">Address</label>
            <input id="edit-address" type="text" class="form-control address-field">
            <label for="edit-email">Email</label>
            <input id="edit-email" type="text" class="form-control email-field">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Edit</button>
      </div>
    </div>
  </div>
</div><!--Edit Entry Modal -->

<script src="/public/bootstrap/bootstrap.min.js"></script>
<script src="/public/bootstrap/jquery.min.js"></script>
<script src="/public/bootstrap/underscore.min.js"></script>

<script src="/public/bootstrap/main.js"></script>

</body>
</html>
