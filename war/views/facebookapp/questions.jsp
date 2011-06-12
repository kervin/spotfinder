<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Rate Places of Interest in Spotfinder</title>
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
                    $.post("demo2.php", {rate: value}, function(data)
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
        <div class="box">
            <div class="inner-box">
                <h3>Casela</h3>
                <img src="/media/casela.jpg" alt="casela" class="box-image" /> 
                
                <div class="box-description">
                    blab blab blab blab blab blab blab blab blab blab blab blab 
                </div>
                
                <div class="box-rating" id="ratings">
                    <select id="casela-rate" name="casela-rate" style="width: 120px">
                        <option value="1">Hate it</option>
                        <option value="2">Not that bad</option>
                        <option value="3">It was okay</option>
                        <option value="4">Had a good time</option>
                        <option value="5">Enjoyed it</option>
                    </select>
                </div>
            </div>
        </div>
        
        <div class="box"></div>
        
        <div class="box"></div>
        
        <div class="box"></div>
    </div>
    
</body>
</html>