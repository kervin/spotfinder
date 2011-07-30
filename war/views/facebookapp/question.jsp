<%@ page import="com.kervinramen.spotfinder.base.model.*"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rate Places of Interest in Spotfinder</title>
<link rel="StyleSheet" href="/css/common.css" type="text/css">
<link rel="StyleSheet" href="/css/questions.css" type="text/css">
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu">

<script type="text/javascript" src="/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.custom.min.js"></script>

<!-- Star Rating widget -->
<script type="text/javascript" src="/js/jquery-ui-stars/jquery.ui.stars.js"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-stars/jquery.ui.stars.css" />

<script type="text/javascript">
        $(function(){
            $(".box-rating").children().not("select").hide();
            // Create target element for onHover titles
            $caption = $("<span/>");
    
            $(".box-rating").stars({
                inputType: "select",
                captionEl: $caption, // point to our newly created element
                callback: function(ui, type, value)
                {
                    $.post("/rate/", {rate: value, spotid: ui.$form[0].spotid.value}, function(data)
                    {
                        $("#ajax_response").html(data);
                    });
                }
            });
    
            // Make it available in DOM tree
            $caption.appendTo("#ratings");
        });
    </script>
</head>

<body>
    <h1>Rate your Places of Interests</h1>
    <div class="container">
        <%
            ArrayList<Spot> spots = (ArrayList<Spot>) request.getAttribute("spots");

            for (Spot spot : spots) {
        %>

        <div class="box">
            <div class="inner-box">
                <h3><%=spot.getName()%></h3>
                <img src="/media/<%=spot.getImage()%>" alt="<%=spot.getName()%>" class="box-image" />

                <div class="box-description">
                    <%=spot.getDescription()%>
                </div>
                <form id="ratings" action="/rate/" method="post">

                    <input type="hidden" name="spotid" value="<%=spot.getSpotId()%>" />

                    <div class="box-rating" id="ratings">
                        <select id="rate" name="casela-rate" style="width: 120px">
                            <option value="1">Hate it</option>
                            <option value="2">Not that bad</option>
                            <option value="3">It was okay</option>
                            <option value="4">Had a good time</option>
                            <option value="5">Enjoyed it</option>
                        </select>
                    </div>
                    

                </form>
            </div>
        </div>

        <%
            }
        %>

    </div>

</body>
</html>