<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Hotels Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styleone.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</head>
<body>
<div class="main" style="background-attachment:fixed;">
    <jsp:include page="navbar.jsp"></jsp:include>
    <div class="clearfix"></div>

    <!-- Filter Form -->
    <div class="container mt-5">
        <form method="get" action="filterHotels">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="city">City</label>
                    <input type="text" class="form-control" name="city" placeholder="Enter City">
                </div>
                <div class="form-group col-md-4">
                    <label for="state">State</label>
                    <input type="text" class="form-control" name="state" placeholder="Enter State">
                </div>
                <div class="form-group col-md-4">
                    <label for="country">Country</label>
                    <input type="text" class="form-control" name="country" placeholder="Enter Country">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>
    </div>

    <!-- Hotels Listing -->
    <div class="container mt-5">
        <c:if test="${sessionScope.role eq 'Admin'}">
            <button type="button" class="btn btn-success btn-sm float-right" data-toggle="modal" data-target="#addHotelModal">
                Add New
            </button>
        </c:if>

        <h4 class="p-2 text-white">Hotels Page</h4>

        <c:forEach items="${list}" var="hotel">
            <div class="hotel">
                <div class="container">
                    <div class="row shadow-lg p-3 mt-5 mb-5 rounded">
                        <div class="col-md-4">
                            <img class="h-img shadow-lg" src="img/hotel1.png" alt="image">
                        </div>
                        <div class="col-md-8">
                            <p class="h-name">${hotel.hname}</p>
                            <p class="h-add">${hotel.city}, ${hotel.state}, ${hotel.country}<br>1KM from nearest airport.</p>
                            <p class="h-price">&#8377;${hotel.fare}*</p>
                            <p class="h-pricex">/night</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty list}">
            <div class="alert alert-warning mt-5" role="alert">
                No hotels found matching your criteria.
            </div>
        </c:if>
    </div>
</div>

<!-- Add New Hotel Modal -->
<div class="modal fade" id="addHotelModal" tabindex="-1" aria-labelledby="addHotelLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="addHotel">
                <div class="modal-header">
                    <h5 class="modal-title" id="addHotelLabel">Add New Hotel</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="hname">Hotel Name</label>
                        <input type="text" class="form-control" name="hname" required>
                    </div>
                    <div class="form-group">
                        <label for="country">Country</label>
                        <input type="text" class="form-control" name="country" required>
                    </div>
                    <div class="form-group">
                        <label for="state">State</label>
                        <input type="text" class="form-control" name="state" required>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" class="form-control" name="city" required>
                    </div>
                    <div class="form-group">
                        <label for="fare">Fare per night</label>
                        <input type="number" class="form-control" name="fare" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
