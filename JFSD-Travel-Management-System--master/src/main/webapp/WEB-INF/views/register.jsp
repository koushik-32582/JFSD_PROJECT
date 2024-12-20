<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="css/styleone.css">
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Spicy+Rice&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Viga&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
    <jsp:include page="navbar.jsp"></jsp:include>
    <div class="clearfix"></div>
    <div class="container mt-5">    
        <h4 class="p-2 text-white">Register Page</h4>
        
        <div class="row">
            <div class="col-sm-5 mx-auto">
                <form method="post" class="card shadow">
                    <div class="card-body">
                        <div class="form-group">
                            <label>Your Name</label>
                            <input type="text" name="uname" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" name="address" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="number" name="phone" class="form-control" minlength="10" maxlength="10" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Gender</label>
                            <select name="gender" class="form-control">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label>User ID</label>
                            <input type="text" name="userid" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="pwd" class="form-control" 
                                   pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}" 
                                   title="Password must contain at least one alphabet, one number, one special character, and be at least 6 characters long" 
                                   required>
                        </div>
                        
                        <button class="btn btn-primary float-right">Register</button>
                    </div>
                </form>
            </div>
        </div>
        
    </div>
</div>
</body>
</html>
